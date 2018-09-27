package org.ojbc.mondrian.rest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "time_by_day")
@Entity
public class TimeByDay {

    @Id
    Integer time_id;
    Timestamp the_date;
    String the_day;
    String the_month;
    Integer the_year;
    Integer day_of_month;
    Integer week_of_year;
    Integer month_of_year;
    String quarter;

    public Timestamp getThe_date() {
        return the_date;
    }

    public void setThe_date(Timestamp the_date) {
        this.the_date = the_date;
    }

    public String getThe_day() {
        return the_day;
    }

    public void setThe_day(String the_day) {
        this.the_day = the_day;
    }

    public String getThe_month() {
        return the_month;
    }

    public void setThe_month(String the_month) {
        this.the_month = the_month;
    }

    public Integer getThe_year() {
        return the_year;
    }

    public void setThe_year(Integer the_year) {
        this.the_year = the_year;
    }

    public Integer getDay_of_month() {
        return day_of_month;
    }

    public void setDay_of_month(Integer day_of_month) {
        this.day_of_month = day_of_month;
    }

    public Integer getWeek_of_year() {
        return week_of_year;
    }

    public void setWeek_of_year(Integer week_of_year) {
        this.week_of_year = week_of_year;
    }

    public Integer getMonth_of_year() {
        return month_of_year;
    }

    public void setMonth_of_year(Integer month_of_year) {
        this.month_of_year = month_of_year;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
}