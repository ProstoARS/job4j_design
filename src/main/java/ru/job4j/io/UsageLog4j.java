package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Arseny Sudakov";
        byte number = 77;
        int age = 33;
        char league = 'f';
        float averagePoints = 3.2f;
        double efficiency = 34.8;
        boolean sex = true;
        long time = 2345678;
        LOG.debug("User info name : {}, age : {}, number : {}, league : {}, averagePoints : {},"
                + " efficiency : {}, sex : {}, time : {}.", name, age, number, league,
                averagePoints, efficiency, sex, time);
    }
}