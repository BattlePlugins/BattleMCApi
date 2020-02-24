package org.battleplugins.api.common.event.gen;

import com.google.common.collect.ForwardingMap;

import org.battleplugins.api.event.Event;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents a class capable of generating the
 * event "classes" from interfaces.
 */
public class EventGenerator {

    private static final Constructor<MethodHandles.Lookup> LOOKUP_CONSTRUCTOR;
    private static final Map<Class<? extends Event>, EventGenerator> EVENT_CACHE = new ForwardingEventMap<>(new ConcurrentHashMap<>(), EventGenerator::new);

    static {
        try {
            LOOKUP_CONSTRUCTOR = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
            LOOKUP_CONSTRUCTOR.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private Class<? extends Event> eventClass;
    private List<Method> methods;
    private List<Class<?>> returnTypes;

    private EventGenerator(Class<? extends Event> eventClass) {
        this.eventClass = eventClass;
        this.methods = Collections.unmodifiableList(Arrays.stream(
                eventClass.getMethods())
                .filter(method -> !method.isDefault())
                .collect(Collectors.toList())
        );
        this.returnTypes = Collections.unmodifiableList(this.methods.stream()
                .map(Method::getReturnType)
                .collect(Collectors.toList())
        );
    }

    /**
     * Generates a new {@link Event} instance with the
     * given parameters
     *
     * @param parameters the parameters
     * @return a new event with the given parameters
     */
    public Event newInstance(LinkedHashMap<String, Object> parameters) {
        for (int i = 0; i < parameters.size(); i++) {
            Object param = new ArrayList<>(parameters.values()).get(i);
            Class<?> expectedType = this.returnTypes.get(i);
            if (!expectedType.isInstance(param) && !(expectedType.equals(Void.TYPE))) {
                throw new IllegalArgumentException("Parameter at index " + i + " (" + param.getClass() + ") cannot be assigned to " + expectedType);
            }
        }

        EventInvocationHandler eventInvocationHandler = new EventInvocationHandler(parameters);
        return (Event) Proxy.newProxyInstance(EventGenerator.class.getClassLoader(), new Class[]{this.eventClass}, eventInvocationHandler);
    }

    public static EventGenerator generate(Class<? extends Event> event) {
        return EVENT_CACHE.get(event);
    }

    private class EventInvocationHandler implements InvocationHandler {

        private Map<String, Object> parameters = new HashMap<>();

        public EventInvocationHandler(Map<String, Object> parameters) {
            parameters.forEach((str, obj) -> this.parameters.put(str.toLowerCase(), obj));
        }

        @Override
        public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == Object.class || method.isDefault()) {
                return LOOKUP_CONSTRUCTOR.newInstance(method.getDeclaringClass(), -1)
                        .unreflectSpecial(method, method.getDeclaringClass())
                        .bindTo(obj)
                        .invokeWithArguments(args);
            }

            if (method.getName().equals("toString")) {
                return "Event(" +
                        "proxy=" + obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode()) + ", " +
                        "class=" + EventGenerator.this.eventClass.getName() + ", " +
                        "fields=" + this.parameters.values() + ")";
            }

            if (method.getName().equals("equals")) {
                return obj == args[0];
            }

            if (method.getName().equals("hashCode")) {
                return System.identityHashCode(obj);
            }

            if (method.getName().startsWith("is")) {
                String variable = method.getName().substring("is".length());
                return this.parameters.get(variable.toLowerCase());
            }

            if (method.getName().startsWith("get")) {
                String variable = method.getName().substring("get".length());
                return this.parameters.get(variable.toLowerCase());
            }

            if (method.getName().startsWith("set")) {
                String variable = method.getName().substring("set".length());
                return this.parameters.put(variable.toLowerCase(), args[0]);
            }

            int methodIndex = EventGenerator.this.methods.indexOf(method);
            if (methodIndex == -1) {
                throw new UnsupportedOperationException("Method " + method + " is not supported!");
            }

            return new ArrayList<>(parameters.values()).get(methodIndex);
        }
    }

    private static class ForwardingEventMap<K, V> extends ForwardingMap<K, V> implements Map<K, V> {

        private Map<K, V> backingMap;
        private Function<K, V> function;

        private ForwardingEventMap(Map<K, V> backingMap, Function<K, V> function) {
            this.backingMap = backingMap;
            this.function = function;
        }

        @Override
        protected Map<K, V> delegate() {
            return this.backingMap;
        }

        @Override
        public V get(Object key) {
            V value = this.backingMap.get(key);
            if (value != null) {
                return value;
            }
            return this.backingMap.computeIfAbsent((K) key, this.function);
        }
    }
}
