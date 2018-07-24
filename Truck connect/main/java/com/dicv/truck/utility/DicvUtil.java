package com.dicv.truck.utility;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.data.domain.PageRequest;

import com.dicv.truck.exception.InvalidValueException;

/**
 * This class is the helper class.
 * 
 * @author SEG3KOR
 * 
 */
public final class DicvUtil {

	public static Integer ifReadOnlyUserChangeToRootAdmin(Integer userId) {
		return (userId != null && userId.intValue() == 3) ? 2 : userId;
	}

	public static Integer getRootAdmin() {
		return 2;
	}

	/**
	 * Holds instance of Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(DicvUtil.class);

	/**
	 * Holds instance of long.
	 */
	private static final long MILLIS_PER_DAY = (long) 24 * 60 * 60 * 1000;

	/**
	 * Empty constructor. To prevent construction.
	 */
	private DicvUtil() {

	}

	/**
	 * Validates the attribute. For null and empty strings.
	 * 
	 * @param attribute
	 *            - Attribute to be validated.
	 * @return Status.
	 */
	public static boolean isValidAttribute(String attribute) {
		// return attribute == null ? false : attribute.trim().length() > 0;
		return attribute == null ? false : true;
	}

	public static String convertFromMillisToHoursMinsSecs(long millis) {

		// LOGGER.log(Level.TRACE, millis);

		return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));

	}

	/**
	 * Convert the base 64 image string to byte array.
	 * 
	 * @param imageString
	 *            - image string.
	 * @return - byte array.
	 * @throws IOException
	 *             - on handling images.
	 */
	public static byte[] Base64ToBytes(String imageString) throws IOException {
		byte[] decodedBytes = Base64.decodeBase64(imageString);
		return decodedBytes;
	}

	public static long getDifferenceDays(Date fromDate, Date toDate) {
		long diff = toDate.getTime() - fromDate.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public static String dateToString(Date date) {
		DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
		return (dateFormate.format(date));
	}

	public static Date stringToDate(String date) {
		DateFormat dateFormate = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			return (dateFormate.parse(date));
		} catch (ParseException e) {
			LOGGER.log(Level.ERROR, "There was an exception in the server during stringToDate." + e.getMessage());
			throw new InvalidValueException("Could not parse date to the given format!");
		}
	}

	public static Date addDays(Date currentDate, int noOfDays) {
		return new Date(currentDate.getTime() + noOfDays * MILLIS_PER_DAY);
	}

	public static String dateToStringFormat(Date date) {
		DateFormat dateFormate = new SimpleDateFormat("dd MMM yyyy");
		return (dateFormate.format(date));
	}

	public static String dateToStrings(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		return (dateFormat.format(date));
	}

	/**
	 * Validate email with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean isValidEmail(String email) {
		Pattern pattern;
		Matcher matcher;
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Generate 8 character length password.
	 * 
	 * @return password.
	 */
	public static String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	public static boolean isValidPassword(String password) {
		Pattern pattern = Pattern.compile(DicvConstants.ACCESS_KEY_PATTERN);
		Matcher matcher = pattern.matcher(password.trim());
		return matcher.matches();
	}

	public static boolean isValidVehicleRegistration(String registrationNumber) {
		Pattern pattern = Pattern.compile(DicvConstants.VEHICLE_REGISTRATION_PATTERN);
		return pattern.matcher(registrationNumber.toUpperCase()).find();
	}

	public static boolean isAlphaNumeric(String value) {
		Pattern pattern = Pattern.compile("^[0-9a-zA-Z]+$");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();

	}

	public static Timestamp getTimestamp() {
		java.util.Date date = new java.util.Date();
		return new java.sql.Timestamp(date.getTime());
	}

	public static Timestamp getStringToDate(String purchaseDate) {
		if (purchaseDate == null)
			return null;
		return new Timestamp(DicvUtil.stringToDate(purchaseDate).getTime());
	}

	public static String getTimeFromSeconds(int seconds) {
		int hr = seconds / 3600;
		int rem = seconds % 3600;
		int mn = rem / 60;
		int sec = rem % 60;
		String hrStr = (hr < 10 ? "0" : "") + hr;
		String mnStr = (mn < 10 ? "0" : "") + mn;
		String secStr = (sec < 10 ? "0" : "") + sec;
		return hrStr + ":" + mnStr + ":" + secStr;
	}

	public static String getPreviousDateEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		return sdf1.format(calendar.getTime());
	}

	public static String getPreviuosDateStartTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		return sdf1.format(calendar.getTime());
	}

	public static Date getEndTimeOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date getStartTimeOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Calendar getDateForStartTime(String dateInString) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
			Date date = formatter.parse(dateInString);
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			LOGGER.info("Starting Time :: " + calendar.getTime());
			return calendar;
		} catch (ParseException e) {
			LOGGER.error("Parse Exception ", e);
		}
		return null;
	}

	public static Calendar getDateForToTime(String dateInString) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
			Date date = formatter.parse(dateInString);
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			LOGGER.info("Stop Time :: " + calendar.getTime());
			return calendar;
		} catch (ParseException e) {
			LOGGER.error("Parse Exception ", e);
		}
		return null;
	}

	public static String getStringForCalendar(Calendar calendar) {
		if (calendar == null) {
			return null;
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf1.format(calendar.getTime());
	}

	public static String getStringForTimestamp(Timestamp calendar) {
		if (calendar == null)
			return null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf1.format(calendar.getTime());
	}

	public static String getStringForTimestamp(Date calendar) {
		if (calendar == null)
			return null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf1.format(calendar.getTime());
	}

	public static PageRequest getPageable(Integer pageNo, Integer size) {
		if (pageNo == null || size == null) {
			return new PageRequest(0, 10);
		}
		return new PageRequest(pageNo - 1, size);
	}

	public static Date getPreviousDayTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

}
