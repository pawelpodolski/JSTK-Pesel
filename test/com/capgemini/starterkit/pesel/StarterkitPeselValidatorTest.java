package com.capgemini.starterkit.pesel;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StarterkitPeselValidatorTest {
	private PeselValidator peselValidator;
	private Sex testMale = Sex.MALE;
	private Sex testFemale = Sex.FEMALE;

	@Before
	public void setUp() {
		// given
		peselValidator = new StarterkitPeselValidator();
	}

	@Test
	public void shouldCheckLengthOfPeselIfPeselIsTooLong() {
		// given
		String pesel = "88122712474124";
		// when
		boolean result = peselValidator.isLengthValid(pesel);
		// then
		Assert.assertFalse(result);
	}

	@Test
	public void isSexValidOrInvalidTest() {
		// given
		String peselMale = "831248539121";
		String peselFemale = "87942173832";
		// when
		boolean resultMSucc = peselValidator.isSexValid(peselMale, testMale);
		boolean resultFSucc = peselValidator.isSexValid(peselFemale, testFemale);
		boolean resultMFail = peselValidator.isSexValid(peselMale, testFemale);
		boolean resultFFail = peselValidator.isSexValid(peselFemale, testMale);
		// then
		Assert.assertTrue(resultMSucc);
		Assert.assertTrue(resultFSucc);
		Assert.assertFalse(resultMFail);
		Assert.assertFalse(resultFFail);
	}
	
	@Test
	public void isDateOfBirthValidIfYearIsNotValid() {
		//given
		String pesel = "93111105353";
		LocalDate dateOfBirth = LocalDate.of(1994, 11, 11);
		// when
		boolean resultBirthCheck = peselValidator.isDateOfBirthValid(pesel, dateOfBirth);
		// then 
		Assert.assertFalse(resultBirthCheck);		
	}
	
	@Test
	public void shouldCheckChecksumIfChecksumIsCorrect() {
		// given
		String pesel = "65080113345";
		
		//when
		boolean result = peselValidator.isChecksumValid(pesel);
		//then
		Assert.assertTrue(result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionWhenSexIsHelikopter() {
		// given
		Sex sex= Sex.HELIKOPTER;
		String pesel = "65080113345";
		//when
		peselValidator.isSexValid(pesel, sex);
	}
	
	@Test
	public void shouldCheckifPeselContainsNotOnlynumbers() {
		//given
		String pesel = "65080113ac5";
		//when
		boolean result = peselValidator.containsOnlyNumbers(pesel);
		//then
		Assert.assertFalse(result);
	}
	
	
	

}
