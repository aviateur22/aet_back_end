package com.ctoutweb.aet.domain.injector;

import java.util.HashMap;
import java.util.Map;

public class MethodInjectorContainer {
    private static final MethodInjectorContainer INSTANCE = new MethodInjectorContainer();

    private final Map<Class<?>, Object> registry = new HashMap<>();

    private MethodInjectorContainer() {

    }

    public static MethodInjectorContainer getInstance() {
        return INSTANCE;
    }

    public <T> void register(Class<T> type, T instance) {
        this.registry.put(type, instance);
    }

    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> type) {
        if(!contains(type))
            throw new IllegalArgumentException("Pas de dépendance enregistré pour le type: " + type);

        return (T) registry.get(type);
    }

    boolean contains(Class<?> type) {
        return registry.containsKey(type);
    }


}
