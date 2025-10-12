package com.ctoutweb.aet.domain.provider.common;

import com.ctoutweb.aet.domain.util.logger.ILogger;
import com.ctoutweb.aet.domain.util.logger.LogLevel;

public interface ICommonInstanceProvider {
    public ILogger provideLoggerInstance();
}
