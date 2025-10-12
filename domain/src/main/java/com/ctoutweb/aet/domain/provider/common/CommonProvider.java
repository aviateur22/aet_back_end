package com.ctoutweb.aet.domain.provider.common;

import com.ctoutweb.aet.domain.util.logger.ILogger;
import com.ctoutweb.aet.domain.util.logger.LoggergImpl;

public class CommonProvider implements ICommonInstanceProvider {

    @Override
    public ILogger provideLoggerInstance() {
      return new LoggergImpl();
  }
}
