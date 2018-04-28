package com.nautilus.logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michaeldunnegan on 4/28/18.
 */
public class LogMessageStore {

    private static List<LinkedList<String>> messages;
    private static int numLogLevels = 3;
    private static int startingLogLevel = 1;
    private static boolean appendMode = false;

    /*
        Statically initialize our log storage data structure
     */
    static {
        messages = new ArrayList<>();
        for (int i = 0; i < numLogLevels; i++) {
            messages.add(new LinkedList<>());
        }
    }

    /*
        A method that adds to the log storage object. It optionally supports multi-line logs,
        message formatting, and capacity checking
    */
    public static synchronized void log(int logLevel, String message, LoggerConfig loggerConfig) {
        if (logLevel > numLogLevels || logLevel < startingLogLevel) {
            throw new RuntimeException(String.format(
                    "Log level must be between %s and %s", startingLogLevel, numLogLevels));
        }

        if (appendMode) {
            String previous = messages.get(logLevel - 1).pollFirst();
            if (previous == null) {
                previous = "";
            }
            message = previous + message;
        }

        if (loggerConfig != null) {
            message = loggerConfig.formatMessage(logLevel, message);
        }

        messages.get(logLevel-1).offerFirst(message);

        if (loggerConfig != null && getCurrentLogSize() > loggerConfig.getCapacity()) {
            System.out.println("Log capacity has been met. Consider running the LogReader to clear messages");
        }
    }

    /*
        Retrieves the highest priority message that has been created most recently
     */
    public static synchronized String get() {
        int current = numLogLevels-1;
        int end = startingLogLevel-1;

        while (current > end && messages.get(current).isEmpty()) {
            current--;
        }
        return messages.get(current).pollFirst();
    }

    /*
        Method for beginning append mode
     */
    public static void beginAppend() {
        if (appendMode) {
            throw new RuntimeException("Append mode is already on");
        }
        appendMode = true;
    }

    /*
        Method for stopping append mode
     */
    public static void endAppend() {
        if (!appendMode) {
            throw new RuntimeException("Append mode is already off");
        }
        appendMode = false;
    }

    /*
        helper method for checking the size of our log object
     */
    private static Long getCurrentLogSize() {
        int start = startingLogLevel-1;
        int end = numLogLevels;
        Long sum = 0L;
        for (int i = start; i < end; i++) {
            sum += messages.get(i).size();
        }
        return sum;
    }

}
