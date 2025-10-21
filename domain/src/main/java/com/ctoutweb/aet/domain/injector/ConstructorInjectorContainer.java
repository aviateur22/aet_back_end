package com.ctoutweb.aet.domain.injector;

import com.ctoutweb.aet.domain.annotation.InjectConstructorParam;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class ConstructorInjectorContainer {
    private static final ConstructorInjectorContainer INSTANCE = new ConstructorInjectorContainer();

    private final Map<Class<?>, Object> registry = new HashMap<>();

    private ConstructorInjectorContainer() {
        throw new IllegalStateException("");
    }

    public static ConstructorInjectorContainer getInstance() {
        return INSTANCE;
    }

    public <T> void register(Class<T> type, T instance) {
        this.registry.put(type, instance);
    }

    public <T> T instanciate(Class<T> type) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?>[] constructors = type.getConstructors();

        if(constructors.length != 1)
            throw new IllegalArgumentException("Erreur chargemenbt contructeur, la class doit avoir un seul constructeur public");

        Constructor<?> constructor = constructors[0];
        Parameter[] parameters = constructor.getParameters();
        Object[] args = new Object[parameters.length];

        for( int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if(parameter.isAnnotationPresent(InjectConstructorParam.class)){
                Class<?> paramType = parameter.getType();
                if(!registry.containsKey(paramType))
                    throw new IllegalArgumentException("Pas de dépendance enregistré pour {}" + parameter.getType());
                args[i] = registry.get(paramType);
            } else {
                throw new IllegalArgumentException("Le paramètre doit être annoté avec @InjectConstructorParam : " + parameter.getName());
            }
        }

        return type.cast(constructor.newInstance(args));
    }

}
