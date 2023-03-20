package com.example.nbu_rusl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Result {
    private int dt;
    private float value;
    private float difference;

    public Result(int dt, float value) {
        this.dt = dt;
        this.value = value;
        this.localDate = LocalDate.parse(String.valueOf(dt), DateTimeFormatter.BASIC_ISO_DATE);
    }

    public void setDifference(float difference) {
        this.difference = difference;
    }

    public float getValue() {
        return value;
    }

    LocalDate localDate;

    public String[] toArray() {
        return new String[]{String.valueOf(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))), String.valueOf(value), String.valueOf(difference)};
    }

    @Override
    public String toString() {
        return "Date=" + localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                ", value=" + value +
                ", різниця складає= " + difference;
    }
}
