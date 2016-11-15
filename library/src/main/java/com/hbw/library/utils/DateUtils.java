package com.hbw.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mwy on 2016/6/30.
 * 时间工具
 */
public class DateUtils {
	static final String formatPattern = "yyyy-MM-dd";

	static final String formatPattern_Short = "yyyyMMdd";


	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getCurrentDate(){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(new Date());
	}

	/**
	 * 获取制定毫秒数之前的日期
	 * @param timeDiff
	 * @return
	 */
	public static String getDesignatedDate(long timeDiff){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		long nowTime = System.currentTimeMillis();
		long designTime = nowTime - timeDiff;
		return format.format(designTime);
	}

	/**
	 *
	 * 获取前几天的日期
	 */
	public static String getPrefixDate(String count){
		Calendar cal = Calendar.getInstance();
		int day = 0-Integer.parseInt(count);
		cal.add(Calendar.DATE,day);   // int amount   代表天数
		Date datNew = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(datNew);
	}
	/**
	 * 日期转换成字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(date);
	}
	/**
	 * 字符串转换日期
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str){
		//str =  " 2008-07-10 19:20:00 " 格式
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		if(!str.equals("")&&str!=null){
			try {
				return format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	//java中怎样计算两个时间如：“21:57”和“08:20”相差的分钟数、小时数 java计算两个时间差小时 分钟 秒 .
	public void timeSubtract(){
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date begin = null;
		Date end = null;
		try {
			begin = dfs.parse("2004-01-02 11:30:24");
			end = dfs.parse("2004-03-26 13:31:40");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

		long day1 = between / (24 * 3600);
		long hour1 = between % (24 * 3600) / 3600;
		long minute1 = between % 3600 / 60;
		long second1 = between % 60;
		System.out.println("" + day1 + "天" + hour1 + "小时" + minute1 + "分"
				+ second1 + "秒");
	}

	/**
	 * @param nowFormatPattern 现在的时间格式   比如 yyyy-MM-dd
	 * @param toFormatPattern  要转换的时间格式 比如 MM月dd日
	 * @param strDdate 要转换的字符
	 * @return 返回已经转换的字符
	 */
	public static  String simpleFormat(String nowFormatPattern,String toFormatPattern,String strDdate){
		SimpleDateFormat sdf1 = new SimpleDateFormat(nowFormatPattern);
		SimpleDateFormat sdf2 = new SimpleDateFormat(toFormatPattern);
		Date date = null;
		try {
			date = sdf1.parse(strDdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date == null) return null;
		return sdf2.format(date);
	}

	/**
	 * @param formatPattern 时间格式
	 * @param strData 要转换的字符
	 * @return 返回星期
	 */
	public static String getWeek(String formatPattern,String strData){
		SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
		Date date = null;
		try {
			date = sdf.parse(strData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date == null) return null;
		return sdf2.format(date);
	}

}
