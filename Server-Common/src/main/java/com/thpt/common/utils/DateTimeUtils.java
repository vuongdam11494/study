package com.thpt.common.utils;

public class DateTimeUtils {
	
	public static final long SENCOND = 1000;
	public static final long MINUTES = SENCOND * 60;
	public static final long HOUR = MINUTES * 60;
	public static final long DAY = HOUR * 24;
	public static final long MONTH = DAY * 30;

    public static long currentDateTime() {
        return System.currentTimeMillis();
    }
}
