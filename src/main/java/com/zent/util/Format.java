package com.zent.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Format {
	public static String formatDate(Date curDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(curDate);
        return strDate;
	}
	
	public static String formatDateDatabase(Date curDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(curDate);
        return strDate;

	}
	
	public static boolean checkFormatEmail(String email) {
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		return pattern.matcher(email).matches();
	}
	
	public static boolean checkFormatPhone(String phone) {
		Pattern patternPhone = Pattern.compile("^[0-9]*$");
		return patternPhone.matcher(phone).matches();
	}
}
