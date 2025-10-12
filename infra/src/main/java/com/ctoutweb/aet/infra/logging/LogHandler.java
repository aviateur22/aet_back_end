package com.ctoutweb.aet.infra.logging;

import com.ctoutweb.aet.domain.util.logger.ILogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public void handle(ILogger logger) {
        switch (logger.getLogLevel()) {
            case INFO -> {
                LOGGER.info(logger::getMessage);
                break;
            }
            case DEBUG -> {
                LOGGER.debug(logger::getMessage);
                break;
            }
            case ERROR -> {
                LOGGER.error(logger::getMessage);
                break;
            }

        }
    }
}
