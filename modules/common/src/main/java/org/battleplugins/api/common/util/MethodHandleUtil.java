package org.battleplugins.api.common.util;

import com.google.common.collect.MapMaker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.function.Supplier;

public class MethodHandleUtil {

    private static final Map<Method, MethodHandle> METHOD_HANDLE_CACHE = new MapMaker().weakKeys().weakValues().makeMap();
    private static final Lookup METHOD_HANDLE_LOOKUP = Lookup.getLookup();

    public static MethodHandle getMethodHandle(Method method) throws Exception {
        MethodHandle handle = METHOD_HANDLE_CACHE.get(method);
        if (handle == null) {
            handle = METHOD_HANDLE_LOOKUP.lookup(method);
            METHOD_HANDLE_CACHE.put(method, handle);
        }
        return handle;
    }

    enum Lookup {
        ENCAPSULATED {
            private final Method privateLookupIn = findMethod(Class.class, MethodHandles.Lookup.class);

            @Override
            MethodHandle lookup(Method method) throws ReflectiveOperationException {
                if (privateLookupIn == null) {
                    throw new IllegalStateException("Could not obtain MethodHandles.privateLookupIn!");
                }

                return doLookup(method, getLookup(method.getDeclaringClass(), privateLookupIn));
            }

            @Override
            boolean isAvailable() {
                return privateLookupIn != null;
            }

            private MethodHandles.Lookup getLookup(Class<?> declaringClass, Method privateLookupIn) {
                MethodHandles.Lookup lookup = MethodHandles.lookup();

                try {
                    return (MethodHandles.Lookup) privateLookupIn.invoke(MethodHandles.class, declaringClass, lookup);
                } catch (ReflectiveOperationException ex) {
                    return lookup;
                }
            }

            private Method findMethod(Class<?>... paramTypes) {
                try {
                    return MethodHandles.class.getDeclaredMethod("privateLookupIn", paramTypes);
                } catch (NoSuchMethodException ex) {
                    return null;
                }
            }
        },

        /**
         * Open (via reflection construction of {@link MethodHandles.Lookup}) method handle lookup.
         * Works with Java 8 and with Java 9 permitting illegal access.
         */
        OPEN {
            private final Supplier<Constructor<MethodHandles.Lookup>> constructor = Lookup::getConstructor;

            @Override
            MethodHandle lookup(Method method) throws ReflectiveOperationException {
                if (!isAvailable()) {
                    throw new IllegalStateException("Could not obtain MethodHandles.lookup constructor!");
                }

                Constructor<MethodHandles.Lookup> constructor = this.constructor.get();
                return constructor.newInstance(method.getDeclaringClass()).unreflectSpecial(method, method.getDeclaringClass());
            }

            @Override
            boolean isAvailable() {
                return constructor.get() != null;
            }
        },

        /**
         * Fallback {@link MethodHandle} lookup using {@link MethodHandles#lookup() public lookup}.
         */
        FALLBACK {
            @Override
            MethodHandle lookup(Method method) throws ReflectiveOperationException {
                return doLookup(method, MethodHandles.lookup());
            }

            @Override
            boolean isAvailable() {
                return true;
            }
        };

        private static MethodHandle doLookup(Method method, MethodHandles.Lookup lookup)
                throws NoSuchMethodException, IllegalAccessException {
            MethodType methodType = MethodType.methodType(method.getReturnType(), method.getParameterTypes());

            if (Modifier.isStatic(method.getModifiers())) {
                return lookup.findStatic(method.getDeclaringClass(), method.getName(), methodType);
            }

            return lookup.findSpecial(method.getDeclaringClass(), method.getName(), methodType, method.getDeclaringClass());
        }

        /**
         * Lookup a {@link MethodHandle} given {@link Method} to look up.
         *
         * @param method must not be {@literal null}.
         * @return the method handle.
         * @throws ReflectiveOperationException the operation exception
         */
        abstract MethodHandle lookup(Method method) throws ReflectiveOperationException;

        /**
         * @return {@literal true} if the lookup is available.
         */
        abstract boolean isAvailable();

        /**
         * Obtain the first available {@link Lookup}.
         *
         * @return the {@link Lookup}
         * @throws IllegalStateException if no {@link Lookup} is available.
         */
        public static Lookup getLookup() {
            for (Lookup it : Lookup.values()) {
                if (it.isAvailable()) {
                    return it;
                }
            }

            throw new IllegalStateException("No MethodHandleLookup available!");
        }

        private static Constructor<MethodHandles.Lookup> getConstructor() {
            try {
                Constructor<MethodHandles.Lookup> constructor =
                        MethodHandles.Lookup.class.getDeclaredConstructor(Class.class);
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }

                return constructor;
            } catch (Exception ex) {

                // this is the signal that we are on Java 9 (encapsulated) and can't use the
                // accessible constructor approach.
                if (ex.getClass().getName().equals("java.lang.reflect.InaccessibleObjectException")) {
                    return null;
                }

                throw new IllegalStateException(ex);
            }
        }
    }
}
