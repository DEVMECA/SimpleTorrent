package com.devmeca.simpletorrent.common;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DateUtil {
	
	// yyyymmddhh24miss
	public static String getYYYYMMDD() {
		Calendar calendar = Calendar.getInstance();
	    long longValue = calendar.getTimeInMillis();   
	    LocalDateTime date =LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
	    String yyyymmdd = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		return yyyymmdd; 
	}

	// yyyymmddhh24miss
	public static String getYYYYMMDDHH24MISS() {
		Calendar calendar = Calendar.getInstance();
	    long longValue = calendar.getTimeInMillis();   
	    LocalDateTime date =LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
	    String yyyymmddhh24miss = date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		return yyyymmddhh24miss; 
	}

	// yyyymmddhh24miss
	public static String getHypenYYYYMMDDHH24MISS() {
		Calendar calendar = Calendar.getInstance();
		long longValue = calendar.getTimeInMillis();
		LocalDateTime date =LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
		String yyyymmddhh24miss = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return yyyymmddhh24miss;
	}
	
	//yyyymmddhh24missSSS
	public static String getYYYYMMDDHH24MISSSSS() {
		Calendar calendar = Calendar.getInstance();
	    long longValue = calendar.getTimeInMillis();   
	    LocalDateTime date =LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
	    String yyyymmddhh24missSSS = date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
		return yyyymmddhh24missSSS; 
	}
	
	
	// yyyymmddhh24miss
	public static String getAdjustYYYYMMDD(int year, int month, int day, int hour, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DATE, day);
		calendar.add(Calendar.HOUR, hour);
		calendar.add(Calendar.MINUTE, min);

	    long longValue = calendar.getTimeInMillis();   
	    LocalDateTime date =LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
	    String yyyymmdd = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		return yyyymmdd; 
	}

	// yyyymmddhh24miss
	public static String getAdjustYYYYMMDDHH24MISS(int year, int month, int day, int hour, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DATE, day);
		calendar.add(Calendar.HOUR, hour);
		calendar.add(Calendar.MINUTE, min);
		
	    long longValue = calendar.getTimeInMillis();   
	    LocalDateTime date =LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
	    String yyyymmddhh24miss = date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		return yyyymmddhh24miss; 
	}
	
	//yyyymmddhh24missSSS
	public static String getAdjustYYYYMMDDHH24MISSSSS(int year, int month, int day, int hour, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DATE, day);
		calendar.add(Calendar.HOUR, hour);
		calendar.add(Calendar.MINUTE, min);
		
	    long longValue = calendar.getTimeInMillis();   
	    LocalDateTime date =LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
	    String yyyymmddhh24missSSS = date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
		return yyyymmddhh24missSSS; 
	}
	
}
