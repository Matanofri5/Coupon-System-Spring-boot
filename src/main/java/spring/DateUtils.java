package spring;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

	private static LocalDate lDate;

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * This method return current date .
	 * 
	 * @return date
	 */
	public static Date getCurrentDate() {
		LocalDate localDate = LocalDate.now();
		Date date = java.sql.Date.valueOf(localDate);
		return date;
	}

	/**
	 * This method get the expiration date and return the end date .
	 * 
	 * @return date
	 */
	public static Date getByMounth() {

		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusMonths(1);
		Date date = java.sql.Date.valueOf(localDate);

		return date;
	}

	/**
	 * This method get the Local Date and return date before 2 months.
	 * 
	 * @return date
	 */
	public static Date getByTwoMountsAgo() {
		LocalDate localDate = LocalDate.now();
		localDate = localDate.minusMonths(2);
		Date date = java.sql.Date.valueOf(localDate);

		return date;
	}

	/**
	 * This method get the Local Date and return date plus 2 weeks from today.
	 * 
	 * @return date
	 */
	public static Date getByTwoWeeks() {
		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusWeeks(2);
		Date date = java.sql.Date.valueOf(localDate);

		return date;
	}

	/**
	 * This method get the Local Date and return date plus one week from today.
	 * 
	 * @return date
	 */
	public static Date getByWeek() {
		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusWeeks(1);
		Date date = java.sql.Date.valueOf(localDate);

		return date;
	}

	public static LocalDate convertDateToString(String endDate) {
		lDate = LocalDate.parse(endDate, formatter);
		return lDate;
	}
}
