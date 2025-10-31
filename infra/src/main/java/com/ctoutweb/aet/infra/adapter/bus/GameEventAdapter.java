package com.ctoutweb.aet.infra.adapter.bus;

import com.ctoutweb.aet.domain.event.IEventBus;
import com.ctoutweb.aet.domain.event.logger.LogEvent;
import com.ctoutweb.aet.infra.adapter.bus.logging.LogHandler;
import org.springframework.stereotype.Component;

@Component
public class GameEventAdapter {

    private final IEventBus bus;
    private final LogHandler logHandler;

    public GameEventAdapter(IEventBus bus, LogHandler logHandler) {
        this.bus = bus;
        this.logHandler = logHandler;
        this.suscribe();
    }

    private void suscribe() {
        bus.subscribe(LogEvent.class, logEvent -> logHandler.log(logEvent.logLevel(), logEvent.message()));
    }
}
