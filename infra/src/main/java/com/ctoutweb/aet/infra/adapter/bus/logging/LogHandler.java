package com.ctoutweb.aet.infra.adapter.bus.logging;

import com.ctoutweb.aet.domain.event.logger.LogLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LogHandler {
    private static final Logger LOGGER = LogManager.getLogger();


    public void log(LogLevel logLevel, String message) {
        switch (logLevel) {
            case INFO -> LOGGER.info(message);
            case DEBUG -> LOGGER.debug(message);
            case ERROR -> LOGGER.error(message);
        }
    }
}
