package com.capgemini.starterkit.pesel;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class StarterkitPeselValidator implements PeselValidator {

	private static final Pattern ONLY_NUMBERS_PATTERN = Pattern.compile("[0-9]+");
	private static final int PESEL_LENGTH = 11;
	
	@Override
	public boolean isValid(String pesel, Sex sex, LocalDate dateOfBirth) {
		return isLengthValid(pesel)
			&& isSexValid(pesel, sex)
			&& isDateOfBirthValid(pesel, dateOfBirth)
			&& isChecksumValid(pesel)
			&& containsOnlyNumbers(pesel)
			&& isChecksumValid(pesel);
	}

	@Override
	public boolean isSexValid(String pesel, Sex sex) {
		switch (sex) {
			case MALE:
				return pesel.charAt(pesel.length() - 1) % 2 != 0;
			case FEMALE:
				return pesel.charAt(pesel.length() - 1) % 2 == 0;
			default:
				throw new IllegalArgumentException(String.format("Error occured for the given parameter: %s", sex));
		}
	}

	@Override
	public boolean isDateOfBirthValid(String pesel, LocalDate dateOfBirth) {
		return isDayOfBirthValid(pesel, dateOfBirth)
			&& isMonthOfBirthValid(pesel, dateOfBirth)
			&& isYearOfBirthValid(pesel, dateOfBirth);
	}

	@Override 
	public boolean isChecksumValid(String pesel) {
		return calculateChecksum(pesel) % 10 == 0;
	}

	@Override
	public boolean isLengthValid(String pesel) {
		return pesel.length() == PESEL_LENGTH;
	}

	@Override
	public boolean containsOnlyNumbers(String pesel) {
		pesel.matches("[]");
		return ONLY_NUMBERS_PATTERN.matcher(pesel).matches();
	}
	
	private boolean isYearOfBirthValid(String pesel, LocalDate dateOfBirth) {
		return Integer.valueOf(pesel.substring(0, 1)).intValue() + 1900 == dateOfBirth.getYear() ;
	}

	private boolean isMonthOfBirthValid(String pesel, LocalDate dateOfBirth) {
		return Integer.valueOf(pesel.substring(2, 3)).intValue() == dateOfBirth.getMonthValue();
	}

	private boolean isDayOfBirthValid(String pesel, LocalDate dateOfBirth) {
		return Integer.valueOf(pesel.substring(4, 5)).intValue() == dateOfBirth.getDayOfMonth();
	}
	
	private int calculateChecksum(String pesel) {
		return pesel.charAt(0)
			+ 3 * pesel.charAt(1)
			+ 7 * pesel.charAt(2)
			+ 9 * pesel.charAt(3)
			+ 1 * pesel.charAt(4)
			+ 3 * pesel.charAt(5)
			+ 7 * pesel.charAt(6)
			+ 9 * pesel.charAt(7)
			+ 1 * pesel.charAt(8)
			+ 3 * pesel.charAt(9)
			+ 1 * pesel.charAt(10);
			
	}

}
