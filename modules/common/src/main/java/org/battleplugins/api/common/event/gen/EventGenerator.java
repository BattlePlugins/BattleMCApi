package org.battleplugins.api.common.event.gen;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ForwardingMap;
import com.google.common.primitives.Primitives;

import org.battleplugins.api.common.util.MethodHandleUtil;
import org.battleplugins.api.event.Event;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents a class capable of generating the
 * event "classes" from interfaces.
 */
public class EventGenerator {

    private static final Map<Class<? extends Event>, EventGenerator> EVENT_CACHE = new ForwardingEventMap<>(new ConcurrentHashMap<>(), EventGenerator::new);

    private Class<? extends Event> eventClass;
    private List<Method> methods;
    private Map<String, Class<?>> returnTypes;

    private EventGenerator(Class<? extends Event> eventClass) {
        this.eventClass = eventClass;

        this.methods = Collections.unmodifiableList(Arrays.stream(
                eventClass.getMethods())
                .filter(method -> !method.isDefault())
                .collect(Collectors.toList())
        );
        this.returnTypes = Collections.unmodifiableMap(this.methods.stream()
                .collect(Collectors.toMap(Method::getName, Method::getReturnType)));
    }

    /**
     * Generates a new {@link Event} instance with the
     * given parameters
     *
     * @param parameters the parameters
     * @return a new event with the given parameters
     */
    public Event newInstance(Map<String, Object> parameters) {
        for (Map.Entry<String, Class<?>> returnTypeEntry : returnTypes.entrySet()) {
            String variable = returnTypeEntry.getKey();
            if (returnTypeEntry.getKey().startsWith("is")) {
                variable = returnTypeEntry.getKey().substring("is".length());
            }

            if (returnTypeEntry.getKey().startsWith("get")) {
                variable = returnTypeEntry.getKey().substring("get".length());
            }

            if (returnTypeEntry.getKey().startsWith("set")) {
                variable = returnTypeEntry.getKey().substring("set".length());
            }
            Object parameter = parameters.get(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, variable));
            if (parameter == null) {
                throw new IllegalArgumentException("Could not find parameter with method " + returnTypeEntry.getKey() + " and variable name " + variable + "!");
            }

            // We need to wrap here since maps auto box
            if (!Primitives.wrap(returnTypeEntry.getValue()).isInstance(parameter) && !returnTypeEntry.getValue().equals(Void.TYPE)) {
                throw new IllegalArgumentException("Parameter " + variable + " (" + parameter.getClass() + ") cannot be assigned to " + returnTypeEntry.getValue());
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

            if (method.isDefault()) {
                try {
                    return MethodHandleUtil.getMethodHandle(method)
                            .bindTo(obj)
                            .invokeWithArguments(args);
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
                return null;
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
