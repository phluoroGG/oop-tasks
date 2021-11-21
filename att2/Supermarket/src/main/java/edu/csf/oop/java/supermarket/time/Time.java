package edu.csf.oop.java.supermarket.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Time {

    private static final Logger logger = LoggerFactory.getLogger(Time.class);

    private int days;
    private int hours;
    private int minutes;

    public Time() {
        days = 1;
        hours = 8;
        minutes = 0;
    }

    public Time(int days, int hours, int minutes) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
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

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void nextDay() {
        days++;
        hours = 8;
        minutes = 0;
        logger.info("The next day");
    }

    public boolean isEndOfDay() {
        return hours == 23 && minutes == 0;
    }
}
