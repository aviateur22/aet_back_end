package com.ctoutweb.aet.infra.bus;

import com.ctoutweb.aet.domain.util.IEventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class SimpleEventBus implements IEventBus {
    Map<Class<?>, List<Consumer<?>>> suscribers = new ConcurrentHashMap<>();

    @Override
    public <T> void publish(T event) {
        Class<?> eventType = event.getClass();
        if(suscribers.containsKey(eventType)) {
            for(Consumer<?> c : suscribers.get(eventType)) {
                @SuppressWarnings("unchecked")
                Consumer<T> typedConsumer = (Consumer<T>) c;
                typedConsumer.accept(event);
            }
        }
    }

    @Override
    public <T> void suscribe(Class<T> messageType, Consumer<T> consumer) {
        this.suscribers.computeIfAbsent(messageType, k -> new ArrayList<>()).add(consumer);
    }
}
