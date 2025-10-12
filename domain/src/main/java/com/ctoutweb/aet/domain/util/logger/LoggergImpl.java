package com.ctoutweb.aet.domain.util.logger;

public class LoggergImpl implements ILogger {
    private LogLevel logLevel;
    private String message;

    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ILogger log(LogLevel logLevel, String message) {
        ILogger logger = new LoggergImpl();
        logger.setLogLevel(logLevel);
        logger.setMessage(message);

        return logger;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
