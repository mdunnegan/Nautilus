package com.nautilus.logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by michaeldunnegan on 4/28/18.
 * The configuration object that is optionally passed to the Logger constructor
 */
public class LoggerConfig {

    private Class userClass;
    private String messageFormat;
    private Long capacity;

    public LoggerConfig(Class userClass, String messageFormat) {
        this.userClass = userClass;
        this.messageFormat = messageFormat;
        this.capacity = Long.MAX_VALUE;
    }

    public LoggerConfig(Class userClass, String messageFormat, Long capacity) {
        this.userClass = userClass;
        this.messageFormat = messageFormat;
        this.capacity = capacity;
    }

    /*
        Formats a given message. messageFormat is specified previously by the user, this does the replacements
     */
    public String formatMessage(int logLevel, String message) {
        return messageFormat
            .replaceAll("\\{priority\\}", Integer.toString(logLevel))
            .replaceAll("\\{classname\\}", userClass.getName())
            .replaceAll("\\{thread\\}", Thread.currentThread().getName())
            .replaceAll("\\{datetime\\}", LocalDateTime.now().toString())
            .replaceAll("\\{message\\}", message);
    }

    public Long getCapacity() {
        return capacity;
    }

}
