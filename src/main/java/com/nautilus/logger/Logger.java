package com.nautilus.logger;

/**
 * Created by michaeldunnegan on 4/28/18.
 *
 * The interface for our logging package. Effectively a facade for LogMessageStore
 */
public class Logger {

    private int logLevel;
    private LoggerConfig loggerConfig;

    public Logger() {
    }

    public Logger(int logLevel) {
        this.logLevel = logLevel;
    }

    public Logger(LoggerConfig loggerConfig) {
        this.loggerConfig = loggerConfig;
    }

    public Logger(int logLevel, LoggerConfig loggerConfig) {
        this.logLevel = logLevel;
        this.loggerConfig = loggerConfig;
    }

    public void setConfig(LoggerConfig loggerConfig) {
        this.loggerConfig = loggerConfig;
    }

    public void log(int logLevel, String message) {
        log(logLevel, message, null);
    }

    public void log(int logLevel, String message, LoggerConfig loggerConfig) {
        LogMessageStore.log(logLevel, message, loggerConfig);
    }

    public void log(String message) {
        LogMessageStore.log(logLevel, message, null);
    }

    public void log(String message, LoggerConfig loggerConfig) {
        LogMessageStore.log(logLevel, message, loggerConfig);
    }

    public void beginAppend() {
        LogMessageStore.beginAppend();
    }

    public void endAppend() {
        LogMessageStore.endAppend();
    }
}
