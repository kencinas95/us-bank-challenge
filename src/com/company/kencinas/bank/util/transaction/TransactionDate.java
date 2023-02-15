package com.company.kencinas.bank.util.transaction;

import java.util.Calendar;

public class TransactionDate {
    private Integer year;
    private Integer month;
    private Integer day;

    public TransactionDate() {
    }

    public TransactionDate(final Integer year, final Integer month, final Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(final Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(final Integer day) {
        this.day = day;
    }

    public static TransactionDate today() {
        Calendar cal = Calendar.getInstance();
        return new TransactionDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    }
}
