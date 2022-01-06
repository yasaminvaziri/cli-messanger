package com.company;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        logger.info("program started");
        Load.loadUser();
        Start start = new Start();
        Start.start();
    }
}
