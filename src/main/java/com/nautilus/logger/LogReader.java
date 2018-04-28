package com.nautilus.logger;

/**
 * Created by michaeldunnegan on 4/28/18.
 * A priority based log reader. Delegates all work to LogMessageStore
 */
public class LogReader {

    public LogReader() {
    }

    public String get() {
        return LogMessageStore.get();
    }

}
