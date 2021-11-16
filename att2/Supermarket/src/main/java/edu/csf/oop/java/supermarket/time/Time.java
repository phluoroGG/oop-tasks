package edu.csf.oop.java.supermarket.time;

import edu.csf.oop.java.supermarket.Main;
import edu.csf.oop.java.supermarket.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Time {

    private static final Logger logger
            = LoggerFactory.getLogger(Main.class);

    private int days;
    private int hours;
    private int minutes;

    private final int[] startOfDay = {8, 0};
    private final int[] endOfDay = {23, 0};

    public Time() {
        this.days = 1;
        this.hours = startOfDay[0];
        this.minutes = startOfDay[1];
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void waitTime(int number) {
        int[] oldTime = new int[]{hours, minutes};
        int cycles;
        if (endOfDay[1] == 0) {
            cycles = ((endOfDay[0] - 1 - hours) * 60 + (60 - minutes)) / 10;
        } else {
            cycles = ((endOfDay[0] - hours) * 60 + (endOfDay[1] - minutes)) / 10;
        }
        if (number == 0 || number > cycles) {
            number = cycles;
        }
        for (int i = 0; i < number; i++) {
            Utils.sell();
            minutes += 10;
            if (minutes == 60) {
                hours++;
                minutes = 0;
            }
        }
        logger.info("Waited from {} hours {} minutes to {} hours {} minutes", oldTime[0], oldTime[1], hours, minutes);
    }

    public void nextDay() {
        days++;
        hours = startOfDay[0];
        minutes = startOfDay[1];
        logger.info("The next day");
    }

    public boolean isEndOfDay() {
        return hours == endOfDay[0] && minutes == endOfDay[1];
    }
}
