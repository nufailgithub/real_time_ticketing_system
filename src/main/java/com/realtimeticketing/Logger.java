package com.realtimeticketing;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Logger using Singleton pattern
class Logger {
    private static Logger instance;
    private final String logFile = "ticket_system.log";

    private Logger() {
        // Initialize log file
        try {
            new FileWriter(logFile, true).close();
        } catch (IOException e) {
            System.err.println("Error initializing log file: " + e.getMessage());
        }
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public synchronized void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String logMessage = String.format("[%s] %s%n", timestamp, message);

        // Write to console
        System.out.print(logMessage);

        // Write to file
        try (FileWriter fileWriter = new FileWriter(logFile, true)) {
            fileWriter.write(logMessage);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}