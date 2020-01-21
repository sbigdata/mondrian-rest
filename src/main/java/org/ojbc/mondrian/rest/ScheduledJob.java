package org.ojbc.mondrian.rest;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
/**
 * Running Time Check Scheduled
 */
class ScheduledJob extends TimerTask {

    public void run() {
        System.out.println(new Date());
    }
}
