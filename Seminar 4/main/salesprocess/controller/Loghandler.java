package se.kth.iv1350.salesprocess.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Logs all exceptions that can occur.
 * 
 */
public class Loghandler {
        StringBuilder logg = new StringBuilder();
    
    public void logException(Exception exception){
        logg.append("Exception Log\n");
        logg.append(createTime());
        logg.append("\n");
        logg.append("The following exceptions has been thrown: ");
        logg.append(exception.getMessage());   
        logg.append("\n");
    }
    
    /**
     *  Method that creates a string for the current time.
     * @return time as the format we want
     */
    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
    /**
     * Prints the log.
     */
    public void printLog(){
        System.out.println(logg);
    }
    
}
