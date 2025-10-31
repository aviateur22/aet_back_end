package com.ctoutweb.aet.domain.event.logger;

/**
 * Model pour Affichage des messages
 *
 * @param logLevel Niveau de importance du message
 * @param message Le message
 */
public record LogEvent(LogLevel logLevel, String message) {
}
