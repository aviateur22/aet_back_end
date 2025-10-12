package com.ctoutweb.aet.domain.util.logger;

public interface ILogger {
   LogLevel getLogLevel();
   String getMessage();
   void setLogLevel(LogLevel logLevel);
   void setMessage(String message);

   /**
    * Transmission niveau de log et du message
    *
    * @param logLevel Le niveau de log
    * @param message Le message afficher
    * @return ILogger
    */
   ILogger log(LogLevel logLevel, String message);


}
