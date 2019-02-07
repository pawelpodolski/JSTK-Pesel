package com.capgemini.starterkit.pesel;

import java.time.LocalDate;

public interface PeselValidator {

	boolean isValid(String pesel, Sex sex, LocalDate dateOfBirth);
	
	boolean isSexValid(String pesel, Sex sex);
	
	boolean isDateOfBirthValid(String pesel, LocalDate dateOfBirth);
	
	boolean isLengthValid(String pesel);
	
	boolean containsOnlyNumbers(String pesel);
	
	boolean isChecksumValid(String pesel);
}
