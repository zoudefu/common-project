
package com.common.project.util;

import com.eversec.pms.pms.entity.PmsContractMilestone;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * 日期时间工具类
 * 类名称：DateTimeUtil
 * 类描述：
 * 修改人：
 * 修改时间：2017年11月17日 上午11:47:46
 * 修改备注：
 *
 * @version 1.0.0
 */
public class DateUtil {
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyyMMdd";
	public static final String DATE_FORMAT_LINE = "yyyy-MM-dd";
	public static final String DATE_TIME_FORMAT_HM = "HH:mm";

	public static String getYear() {
		Date date = new Date();
		return foramtTime(date, "yyyy");
	}

	public static Integer getMonth() {
		Date date = new Date();
		return Integer.parseInt(foramtTime(date, "MM"));
	}

	/**
	 * 获得当前Date 对象表示的毫秒数
	 *
	 * @return
	 */
	public static long getTime() {
		Date date = new Date();
		return date.getTime();
	}

	/**
	 * 获得凌晨时间
	 *
	 * @return
	 */
	public static Date getBerforeDraw() {
		Date zero = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		zero = calendar.getTime();
		return zero;
	}

	/**
	 * getCurrDateTime
	 * 描述: 获得系统当前时间(格式：yyyy-MM-dd HH:mm:ss)
	 **/
	public static String getCurrDateTimeStr() {
		return foramtTime(new Date(), null);
	}

	/**
	 * getCurrDate
	 * 描述: 获得系统当前日期(格式：yyyyMMdd)
	 *
	 * @return String
	 */
	public static String getCurrDateStr() {
		return foramtTime(new Date(), DATE_FORMAT);
	}

	/**
	 * getCurrDateLineStr
	 * 描述: 获得系统当前日期(格式：yyyy-MM-dd)
	 *
	 * @return String
	 */
	public static String getCurrDateLineStr() {
		return foramtTime(new Date(), DATE_FORMAT_LINE);
	}

	/**
	 * 格式化时间
	 * foramtTime
	 * 描述:
	 *
	 * @param format
	 * 默认格式：yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String foramtTime(Date date, String format) {
		if (StringUtils.isBlank(format)) {
			format = DATE_TIME_FORMAT;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String formatTime(LocalDateTime dateTime) {
		if (dateTime == null) {
			return null;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
		return dtf.format(dateTime);
	}

	/**
	 * 返回"yyyy-MM-dd"
	 *
	 * @param dateTime
	 * @return
	 */
	public static String formatDate(LocalDateTime dateTime) {
		if (dateTime == null) {
			return null;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT_LINE);
		return dtf.format(dateTime);
	}

	public static String formatHhMmDate(LocalDateTime dateTime) {
		if (dateTime == null) {
			return null;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_HM);
		return dtf.format(dateTime);
	}

	/**
	 * 转换为时间
	 *
	 * @return
	 */
	public static Date parseTime(String str, String format) {
		if (StringUtils.isBlank(format)) {
			format = DATE_TIME_FORMAT;
		}

		if (StringUtils.isBlank(str)) {
			return null;
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(str.replace("T", " "));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date(date.getTime());
	}

	/**
	 * 转换为时间
	 * 2021-03-25T05:33:00.000+0000 转 LocalDateTime
	 *
	 * @return
	 */
	public static LocalDateTime parseDate(String str, String format) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localTime = LocalDateTime.parse(str.replace("T", " ").substring(0, 19), df);
		return localTime;
	}

	public static LocalDate dateToLocalDate(Date date) {
		if (date == null) {
			return null;
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * 获取几天前的日期
	 *
	 * @return
	 */
	public static Date getDaysAgo(int days) {
		GregorianCalendar d = new GregorianCalendar();
		d.add(Calendar.DATE, -days);
		d.set(Calendar.HOUR_OF_DAY, 0);
		d.set(Calendar.MINUTE, 0);
		d.set(Calendar.SECOND, 0);
		d.set(Calendar.MILLISECOND, 0);
		return d.getTime();
	}

	/**
	 * 加小时
	 * addHour
	 * 描述:
	 */
	public static Date addHour(Date date, int hour) {
		Calendar cal = Calendar.getInstance();
		if (null != date) {
			cal.setTime(date);
		}
		cal.add(Calendar.HOUR, hour);
		return cal.getTime();
	}

	/**
	 * 加日期
	 * addDay
	 * 描述:
	 *
	 * @param day
	 * ?注释
	 * @return String
	 */
	public static String addDay(int day, String... format) {
		Calendar cal = new GregorianCalendar(1900, 0, -1);
		cal.add(Calendar.DATE, day);

		if (format == null || format.length == 0) {
			return foramtTime(cal.getTime(), "yyyy-MM-dd");
		} else {
			return foramtTime(cal.getTime(), format[0]);
		}

	}

	/**
	 * 将Date转换为yyyyMMddHHmmss的格式
	 *
	 * @param date
	 * @return
	 */
	public static String getDateYYYYMMDDHHMMSS(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}

	/**
	 * 将Date转换为yyyyMMddHHmmss的格式
	 *
	 * @param date
	 * @return
	 */
	public static String getDateYYYYMMDD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String getDateYYYYMM(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}

	public static String getDateYYYYMMDDHHMMSSSSS(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(date);
	}

	/**
	 * 根据传入的时间查询 本月的第一天
	 *
	 * @return
	 */
	public static LocalDate getStartDayOfMonth(LocalDate day) {
		return LocalDate.of(day.getYear(), day.getMonth(), 1);
	}

	/**
	 * 本月的第一天
	 *
	 * @return
	 */
	public static LocalDate getStartDayOfMonth() {
		LocalDate today = LocalDate.now();
		return LocalDate.of(today.getYear(), today.getMonth(), 1);
	}

	/**
	 * 根据传入的时间查询 本月的最后一天
	 *
	 * @return
	 */
	public static LocalDate getLastDayOfMonth(LocalDate day) {
		return day.with(TemporalAdjusters.lastDayOfMonth());
	}

	/**
	 * 本月的最后一天
	 *
	 * @return
	 */
	public static LocalDate getLastDayOfMonth() {
		return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
	}

	/**
	 * 本周第一天
	 *
	 * @return
	 */
	public static LocalDate getStartDayOfWeek() {
		TemporalField fieldIso = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek();
		// 用于设置给定字段的新值
		return LocalDate.now().with(fieldIso, 1);
	}

	/**
	 * 本周最后一天
	 *
	 * @return 本周最后一天
	 */
	public static LocalDate getEndDayOfWeek() {
		TemporalField fieldIso = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek();
		return LocalDate.now().with(fieldIso, 7);
	}

	/**
	 * 加上天
	 *
	 * @param days
	 * @param date
	 * @return
	 */
	public static Date plusDay(int days, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE, days);
		return calendar.getTime();
	}

	public static LocalDateTime plusDay(int days, LocalDateTime dateLocal) {
		if (dateLocal == null) {
			return null;
		}

		return dateLocal.plusDays(days);
	}

	public static LocalDate plusDayToLocalDate(int days, LocalDate dateLocal) {
		if (dateLocal == null) {
			return null;
		}
		return dateLocal.plusDays(days);
	}

	public static String plusDayToString(int days, String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String localTime = df.format(strToLocalDate(date).plusDays(days));
		return localTime;
	}

	/**
	 * 指定日期加上月数后的日期
	 */
	public static LocalDateTime plusMonth(int month, LocalDateTime dateLocal) {
		return dateLocal.plusMonths(month);
	}

	/**
	 * String转LocalDate 年月日
	 */
	public static LocalDateTime stringToLocalDate(String str) {
		LocalDateTime localDateTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return localDateTime;
	}

	public static LocalDate strToLocalDate(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		LocalDate localDate = LocalDate.parse(str.substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return localDate;
	}

	public static String localDateToStr(LocalDate param) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateStr = param.format(df);
		return dateStr;
	}

	public static String localDateToStrYyyyMm(LocalDateTime time) {
		if (time == null) {
			return "";
		}
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
		String dateStr = time.format(df);
		return dateStr;
	}

	public static String localDateToStrYyyyMm(LocalDate time) {
		if (time == null) {
			return "";
		}
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
		String dateStr = time.format(df);
		return dateStr;
	}

	public static String localDateToYyyyMm(LocalDate time) {
		if (time == null) {
			return "";
		}
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy年MM月");
		String dateStr = time.format(df);
		return dateStr;
	}

	public static String localDateToYyyyMmDd(LocalDate time) {
		if (time == null) {
			return "";
		}
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		String dateStr = time.format(df);
		return dateStr;
	}

	public static String localDateTostring(LocalDateTime time) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String localTime = df.format(time);
		return localTime;
	}

	public static String localDateTimeTostring(LocalDateTime time) {
		if (time == null) {
			return null;
		}
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String localTime = df.format(time);
		return localTime;
	}

	public static LocalDateTime getLocalDateTime(PmsContractMilestone lastMiles) {
		if (lastMiles.getBaseLineRealTime() != null) {
			return lastMiles.getBaseLineRealTime();
		} else if (lastMiles.getBaseLinePredictTime() != null) {
			return lastMiles.getBaseLinePredictTime();
		} else if (lastMiles.getCustomerConjectureTime() != null) {
			return lastMiles.getCustomerConjectureTime();
		} else if (lastMiles.getCustomerBaseTime() != null) {
			return lastMiles.getCustomerBaseTime();
		}
		return null;
	}

	public static Map<String, Date> getWeekDate() {
		Map<String, Date> map = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayWeek == 1) {
			dayWeek = 8;
		}

		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		Date mondayDate = cal.getTime();
		String weekBegin = sdf.format(mondayDate) + " 00:00:00";

		cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
		Date sundayDate = cal.getTime();
		String weekEnd = sdf.format(sundayDate) + " 23:59:59";

		map.put("mondayDate", parseTime(weekBegin, null));
		map.put("sundayDate", parseTime(weekEnd, null));
		return map;
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate
	 * 较小的时间
	 * @param bdate
	 * 较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(betweenDays));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(betweenDays));
	}

	/**
	 * LocalDateTime 转Date
	 *
	 * @param localDateTime
	 * @return
	 */
	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * LocalDate 转Date
	 *
	 * @param localDate
	 * @return
	 */
	public static Date localDateToDate(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		Date date = Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
		return date;
	}

	/**
	 * 输入 ： 2020-02-12
	 *
	 * @return
	 */
	public static LocalDateTime parse(String str, String format) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(str, df);
	}

	/**
	 * 取当前时间12个月内的年月
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<String> getLast12Month(Integer year, Integer month) {
		List<String> list = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);// 年份
		c.set(Calendar.MONTH, month - 1);// 月份
		// 取当前时间12个月内的年月
		for (int i = 0; i < 12; i++) {
			int k = c.get(Calendar.YEAR);
			int j = c.get(Calendar.MONTH) + 1 - i;
			String date = "";
			if (j >= 1) {
				date = k + (j >= 10 ? "" : "0") + j;
			} else {
				int p = 11 - i;
				int m = c.get(Calendar.YEAR) - 1;
				int n = c.get(Calendar.MONTH) + 2 + p;
				date = m + (n >= 10 ? "" : "0") + n;
			}
			list.add(date);
			System.out.println(date);
		}
		return list;
	}

	/**
	 * 取当前时间12个月内的年月
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<String> getNext12Month(Integer year, Integer month) {
		List<String> list = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);// 年份
		c.set(Calendar.MONTH, month);// 月份
		// 取当前时间12个月内的年月
		for (int i = 0; i < 12; i++) {
			int k = c.get(Calendar.YEAR);
			int j = c.get(Calendar.MONTH) + 1 + i;
			String date = "";
			if (j >= 1) {
				date = k + (j >= 10 ? "" : "0") + j;
			} else {
				int p = 11 - i;
				int m = c.get(Calendar.YEAR) + 1;
				int n = c.get(Calendar.MONTH) + 2 + p;
				date = m + (n >= 10 ? "" : "0") + n;
			}
			list.add(date);
			System.out.println(date);
		}
		return list;
	}

	/**
	 * @param parm
	 * ：多少个月（前后）
	 * @param lb
	 * ： 1：之后月份，-1：之前月份
	 * @return
	 */
	public static List<String> getCurrentYearMonthLC(int parm, int lb, Integer year, Integer month) {
		List<String> indexList = new ArrayList<String>();
		String dateString;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);// 年份
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);// 日，必须加，否则31日的时候，取月份的会有bug
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		for (int i = 0; i < parm; i++) {
			dateString = sdf.format(cal.getTime());
			indexList.add(dateString.substring(0, 7));
			cal.add(Calendar.MONTH, lb);
		}
		return indexList;
	}

	public static List<String> getBetweenDates(String start, String end) {
		List<String> result = new ArrayList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = sdf.parse(start);
			Date endDate = sdf.parse(end);
			Calendar tempStart = Calendar.getInstance();
			tempStart.setTime(startDate);
			Calendar tempEnd = Calendar.getInstance();
			tempEnd.setTime(endDate);
			while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
				result.add(sdf.format(tempStart.getTime()));
				tempStart.add(Calendar.DAY_OF_YEAR, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Collections.reverse(result);
		return result;

	}

	/**
	 * @param start
	 * 2021-01
	 * @param end
	 * 2023-03
	 * @return 中间的年月集合
	 */
	public static List<String> getBetweenMonth(String start, String end) {
		List<String> result = new ArrayList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date startDate = sdf.parse(start);
			Date endDate = sdf.parse(end);
			Calendar tempStart = Calendar.getInstance();
			tempStart.setTime(startDate);
			Calendar tempEnd = Calendar.getInstance();
			tempEnd.setTime(endDate);
			while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
				result.add(sdf.format(tempStart.getTime()));
				tempStart.add(Calendar.MONTH, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Collections.reverse(result);
		return result;

	}

	/**
	 * 获取指定月的天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<String> getDayListOfMonth(String year, String month) {

		List<String> list = new ArrayList<String>();
		String ystr = year;
		String mstr = month;

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, Integer.parseInt(ystr));// 年份
		a.set(Calendar.MONTH, Integer.parseInt(mstr) - 1);// 月份
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);

		int maxDate = a.get(Calendar.DATE);

		if (Integer.parseInt(mstr) < 10) {
			mstr = "0" + String.valueOf(Integer.parseInt(mstr));
		} else {
			mstr = String.valueOf(mstr);
		}
		for (int i = 0; i < maxDate; i++) {
			int d = i + 1;
			String dstr = "";
			if (d < 10) {
				dstr = "0" + String.valueOf(d);
			} else {
				dstr = String.valueOf(d);
			}

			String day = ystr + "-" + mstr + "-" + dstr;
			list.add(day);
		}

		return list;
	}

	/**
	 * 根据开始和结束的年份，获取中间的年份
	 * 
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	public static List<String> getAllYears(int startYear, int endYear) {
		List<String> years = new ArrayList<>();
		for (int year = startYear; year <= endYear; year++) {
			years.add(String.valueOf(year));
		}
		return years;
	}

	public static String getMonthString(String month) {
		Integer mon = Integer.parseInt(month);
		String mm = null;
		if (mon > 10) {
			mm = month;
		} else {
			mm = "0" + mon;
		}
		return mm;
	}

	public static String getPreMonthString(String month) {
		Integer mon = Integer.parseInt(month) - 1;
		String mm = null;
		if (mon > 10) {
			mm = month;
		} else {
			mm = "0" + mon;
		}
		return mm;
	}

	public static String yesterdayDate() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();// 获取当前时间
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);// 设置时间为前一天
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// 格式化前一天的当前时
		String yesterday = df.format(cal.getTime());
		return yesterday;
	}

	/**
	 * 获取某月的最后一天 如201712结果为2017-12-30
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastday(String year, String month) {
		LocalDate firstDayOfCurrentDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
		LocalDate lastDayOfCurrentDate = firstDayOfCurrentDate.with(TemporalAdjusters.lastDayOfMonth());
		return lastDayOfCurrentDate.toString();
	}

	/**
	 * 获取指定年月的最后一天
	 * 
	 * @param yearMonth
	 * @return
	 */
	public static String getLastday(String yearMonth) {
		String year = yearMonth.substring(0, 4);
		String month = yearMonth.substring(5, 7);
		LocalDate firstDayOfCurrentDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
		LocalDate lastDayOfCurrentDate = firstDayOfCurrentDate.with(TemporalAdjusters.lastDayOfMonth());
		return lastDayOfCurrentDate.toString();
	}

	public static long getDurationDay(LocalDateTime begin, LocalDateTime end) {
		if (end == null) {
			end = LocalDateTime.now();
		}
		Duration duration = Duration.between(begin, end);
		return duration.toDays();
	}

	public static LocalDateTime getBeginTimeOfMount(String yyyyMm) {

		Integer year = Integer.parseInt(yyyyMm.substring(0, 4));
		Integer mount = Integer.parseInt(yyyyMm.substring(5, 7));
		String date = getFirstDateForYearAndMonth(year, mount);
		LocalDateTime localDateTime = strToLocalDate(date).atTime(0, 0, 0);
		return localDateTime;
	}

	public static LocalDateTime getEndTimeOfMount(String yyyyMm) {

		Integer year = Integer.parseInt(yyyyMm.substring(0, 4));
		Integer mount = Integer.parseInt(yyyyMm.substring(5, 7));
		String date = getLastDateForYearAndMonth(year, mount);
		LocalDateTime localDateTime = strToLocalDate(date).atTime(23, 59, 59);
		return localDateTime;
	}

	/**
	 * 打印该月份的第一天
	 *
	 * @param year
	 * 年份
	 * @param month
	 * 月份
	 */
	public static String getFirstDateForYearAndMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	/**
	 * 指定月份的第一天
	 *
	 * @param year
	 * 年份
	 * @param month
	 * 月份
	 */
	public static String getLastDateForYearAndMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	/**
	 * 获取指定年月的最后一天
	 * 
	 * @return
	 */
	public static LocalDateTime getLastDateOfMonth(Integer year, Integer month) {
		LocalDate yearMonth = LocalDate.of(year, month, 1);
		LocalDateTime date = LocalDateTime.of(year, month, 1, 23, 59, 59).with(TemporalAdjusters.lastDayOfMonth());
		return date;
	}

	public static LocalDateTime getBeginDateOfMonth(Integer year, Integer month) {
		LocalDateTime date = LocalDateTime.of(year, month, 1, 0, 0, 1);
		return date;
	}

	// 时间戳转本地时间
	public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
	}

	// 本地时间转时间戳
	public static Long localDateTimeToTimestamp(LocalDateTime localDateTime) {
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
	}

	// public static void main(String[] args) {
	// LocalDate startDate = LocalDate.of(2023, 1, 1);
	// LocalDate endDate = LocalDate.of(2023, 9, 30);
	//
	// identifyQuartersInRange(startDate, endDate);
	// }

	public static Set<String> getMonthOneYear(int startMonth, int endMonth) {
		Set<String> month = new HashSet<>();
		if (endMonth >= startMonth) {
			while (endMonth >= startMonth) {
				month.add(String.valueOf(startMonth));
				startMonth++;
			}
		}
		return month;
	}

	public static Set<String> getQuarterOneYear(int startMonth, int endMonth) {
		Set<String> quarter = new HashSet<>();
		if (endMonth >= startMonth) {
			while (endMonth >= startMonth) {
				quarter.add(String.valueOf(getQuarter(startMonth)));
				startMonth++;
			}
		}
		return quarter;
	}

	public static boolean isLastDayOfDecember(LocalDate date) {
		// 检查日期是否是12月31日
		return date.getMonth().getValue() == 12 && date.getDayOfMonth() == 31;
	}

	public static boolean isFirstDayOfDecember(LocalDate date) {
		// 检查日期是否是12月31日
		return date.getMonth().getValue() == 1 && date.getDayOfMonth() == 1;
	}

	public static void main(String[] args) {
		List<String> aa =getCurrentYearMonthLC(12, -1, 2024, 6);
		System.out.println(aa);
		Set<String> quarter = getMonthOneYear(1, 9);
		System.out.println(quarter);

		// String dateRange = "20230101-20230930";
		// String[] dates = dateRange.split("-");
		// String startDate = dates[0];
		// String endDate = dates[1];
		// int startYear = Integer.parseInt(startDate.substring(0, 4));
		// int endYear = Integer.parseInt(endDate.substring(0, 4));
		// if (startYear == endYear) {
		// System.out.println("Same year: " + startYear);
		// int startMonth = Integer.parseInt(startDate.substring(4, 6));
		// int endMonth = Integer.parseInt(endDate.substring(4, 6));
		//
		// } else {
		// System.out.println("Different years: start year - " + startYear + ", end year - " +
		// endYear);
		// }
	}

	// public static void main(String[] args) throws ParseException {
	// LocalDate startDate = LocalDate.of(2023, 1, 1);
	// LocalDate endDate = LocalDate.of(2023, 9, 30);
	//
	// identifyQuartersInRange(startDate, endDate);

	// int startYear = 2010;
	// int endYear = 2020;
	// List<String> allYears = getAllYears(startYear, endYear);
	// for (String year : allYears) {
	// System.out.println(year);
	// }

	// LocalDateTime aa= getLastDateOfMonth(2020,4);
	// LocalDateTime bb= getBeginDateOfMonth(2020,4);
	// System.out.println(aa);
	// System.out.println(bb);

	// System.out.println(localDateToYyyyMm(LocalDate.now()));

	// System.out.println(strToLocalDate("2024-05-16 00:00:00"));
	// List<String> list =
	// DateUtil.getBetweenMonth(DateUtil.localDateToStrYyyyMm(LocalDate.now().plusDays(-365)),
	// DateUtil.localDateToStrYyyyMm(LocalDate.now().plusMonths(-1)));
	// Collections.reverse(list);
	// System.out.println(list);
	// System.out.println(getDurationDay(LocalDateTime.of(2022, 11, 20, 10, 30, 10), null));
	// String year = "2021";
	// for (int i = 1; i <= 12; i++) {
	// String lastday = getLastday(year, String.valueOf(i));
	// System.out.println("last day:" + lastday + "--> month: " + String.valueOf(i));
	// }
	// String date = "2022-06";
	// LocalDate date1 = DateUtil.strToLocalDate(
	// DateUtil.getLastday(date.substring(0, 4), date.substring(5, 7)));
	// System.out.println(date1);
	// System.out.println(yesterdayDate());
	// getLast12Month(2021, 7);
	// getNext12Month(2021, 7);
	// List<String> list = new ArrayList<String>();
	// List last12Month = getCurrentYearMonthLC(12,-1,2022, 8-1);
	// Collections.reverse(last12Month);
	// list.addAll(last12Month);
	// list.addAll(getCurrentYearMonthLC(12,1,2022, 8));
	// list.forEach(System.out::println);
	// LocalDateTime aa = plusDay(-1, LocalDateTime.now());
	//
	// // TODO Auto-generated method stub
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date d1 = sdf.parse("2012-09-08 10:10:10");
	// Date d2 = sdf.parse("2012-09-15 00:00:00");
	// System.out.println(daysBetween(d2, d1));
	// DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	// LocalDateTime time = LocalDateTime.now();
	// String localTime = df.format(time);
	// LocalDateTime ldt = LocalDateTime.parse("2017-09-28 00:00:01",df);
	// System.out.println(localDateToStrYyyyMm(LocalDateTime.now()));
	// System.out.println("String类型的时间转成LocalDateTime："+ldt);
	// List<LocalDateTime> list = getDayListOfMonth("2022","2");

	// }

	// 获取中文 星期几
	public static final String getWeekStr(LocalDate date) {
		if (date == null) {
			return null;
		}
		return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINESE);
	}

	/**
	 * 比较两个yyyy-mm的大小，返回 1 为第一个日期大，返回0为相等，返回-1 为后一个后
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareYyyyMm(String date1, String date2) {
		YearMonth yearMonth1 = YearMonth.of(Integer.parseInt(date1.substring(0, 4)), Integer.parseInt(date1.substring(5, 7)));
		YearMonth yearMonth2 = YearMonth.of(Integer.parseInt(date2.substring(0, 4)), Integer.parseInt(date2.substring(5, 7)));

		if (yearMonth1.isBefore(yearMonth2)) {
			return -1;
		} else if (yearMonth1.isAfter(yearMonth2)) {
			return 1;
		} else {
			return 0;
		}
	}

	public static int getQuarter(int month) {
		if (month >= 1 && month <= 3) {
			return 1;
		} else if (month >= 4 && month <= 6) {
			return 2;
		} else if (month >= 7 && month <= 9) {
			return 3;
		} else if (month >= 10 && month <= 12) {
			return 4;
		} else {
			throw new IllegalArgumentException("Invalid month: " + month);
		}
	}

}