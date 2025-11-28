package com.lab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class SwedishSocialSecurityNumberTest {
    
    private SSNHelper helper;
    //private BuggySSNHelperAllowDayUpTo30 helper;
    //private BuggySSNHelperAllowMonth0 helper;
    //private BuggySSNHelperIncorrectFormat helper;
    //private BuggySSNHelperIncorrectFormatFalse helper;
    //private BuggySSNHelperMessyLuhn helper;
    
    private SSNHelper mockHelper;
    
    
    @BeforeEach
    public void setUp() {
        helper = new SSNHelper();
        //helper = new BuggySSNHelperAllowDayUpTo30();
        //helper = new BuggySSNHelperAllowMonth0();
        //helper = new BuggySSNHelperIncorrectFormat();
        //helper = new BuggySSNHelperIncorrectFormatFalse();
        //helper = new BuggySSNHelperMessyLuhn();

        mockHelper = mock(SSNHelper.class);
    }
    
    @Test
    public void shouldAcceptValidSSN() throws Exception {
        SwedishSocialSecurityNumber ssn = new SwedishSocialSecurityNumber("900101-0017", helper);
        
        assertEquals("90", ssn.getYear());
        assertEquals("01", ssn.getMonth());
        assertEquals("01", ssn.getDay());
        assertEquals("0017", ssn.getSerialNumber());

        assertEquals("900101-0017", ssn.getSSN());
    }

    @Test
    public void shouldBeCorrectLength() {
        String ssn = "900101-0017"; 
        assertTrue(helper.isCorrectLength(ssn));
    }

    @Test
    public void shouldBeWrongLength() {
        String ssn = "900101-00171";
        assertFalse(helper.isCorrectLength(ssn));
    }

    @Test
    public void shouldBeCorrectFormat() {
        String ssn = "900101-0017";
        assertTrue(helper.isCorrectFormat(ssn));
    }

    @Test
    public void shouldBeWrongFormat() {
        String ssn = "9001010-0017";
        assertFalse(helper.isCorrectFormat(ssn));
    }


    @Test
    public void shouldBeValidMonth() {
        assertTrue(helper.isValidMonth("01"));
        assertTrue(helper.isValidMonth("06"));
        assertTrue(helper.isValidMonth("12"));
    }

    @Test
    public void shouldBeInvalidMonth() {
        assertFalse(helper.isValidMonth("-1"));
        assertFalse(helper.isValidMonth("13"));
        assertFalse(helper.isValidMonth("0"));

    }

    @Test
    public void shouldBeValidDay() {
        assertTrue(helper.isValidDay("01"));
        assertTrue(helper.isValidDay("15"));
        assertTrue(helper.isValidDay("31"));
    }

    @Test
    public void shouldBeInvalidDay() {
        assertFalse(helper.isValidDay("-1"));
        assertFalse(helper.isValidDay("0"));
        assertFalse(helper.isValidDay("32"));
    }

    @Test
    public void shouldBeCorrectLuhn() {
        String ssn = "900101-0017";
        assertTrue(helper.luhnIsCorrect(ssn));
    }

    @Test
    public void shouldBeWrongLuhn() {
        String ssn = "900101 0017";
        assertFalse(helper.luhnIsCorrect(ssn));
    }

    @Test
    public void shouldTrimPassword() {
        String ssn = "900101-0017  ";
        when(mockHelper.isCorrectLength("900101-0017")).thenReturn(true);
        when(mockHelper.isCorrectFormat("900101-0017")).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect("900101-0017")).thenReturn(true);

        assertDoesNotThrow(() -> new SwedishSocialSecurityNumber(ssn, mockHelper));
    }

    @Test
    public void shouldRejectWhenLengthIsIncorrect() {
        String ssn = "9001010-0017";
        when(mockHelper.isCorrectLength(ssn)).thenReturn(false);

        Exception ex = assertThrows(Exception.class, () -> new SwedishSocialSecurityNumber(ssn, mockHelper));
        assertEquals("To short, must be 11 characters", ex.getMessage());

        verify(mockHelper).isCorrectLength(ssn);
    }

    @Test
    public void shouldRejectWhenFormatIsIncorrect() {
        String ssn = "900101 0017";
        when(mockHelper.isCorrectLength(ssn)).thenReturn(true);
        when(mockHelper.isCorrectFormat(ssn)).thenReturn(false);

        Exception ex = assertThrows(Exception.class, () -> new SwedishSocialSecurityNumber(ssn, mockHelper));
        assertEquals("Incorrect format, must be: YYMMDD-XXXX", ex.getMessage());

        verify(mockHelper).isCorrectFormat(ssn);
    }

    @Test
    public void shouldRejectWhenMonthIsInvalid() {
        String ssn = "900101-0017";
        when(mockHelper.isCorrectLength(ssn)).thenReturn(true);
        when(mockHelper.isCorrectFormat(ssn)).thenReturn(true);
        when(mockHelper.isValidMonth("13")).thenReturn(false);


        Exception ex = assertThrows(Exception.class, () -> new SwedishSocialSecurityNumber(ssn, mockHelper));
        assertEquals("Invalid month in SSN", ex.getMessage());

        verify(mockHelper).isValidMonth("01");
    }

    @Test
    public void shouldRejectWhenDayIsInvalid() {
        String ssn = "900101-0017";
        when(mockHelper.isCorrectLength(ssn)).thenReturn(true);
        when(mockHelper.isCorrectFormat(ssn)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(false);


        Exception ex = assertThrows(Exception.class, () -> new SwedishSocialSecurityNumber(ssn, mockHelper));
        assertEquals("Invalid day in SSN", ex.getMessage());

        verify(mockHelper).isValidDay("01");
    }

    @Test
    public void shouldRejectWhenLuhnIsInvalid() {
        String ssn = "900101-0017";
        when(mockHelper.isCorrectLength(ssn)).thenReturn(true);
        when(mockHelper.isCorrectFormat(ssn)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect(ssn)).thenReturn(false);


        Exception ex = assertThrows(Exception.class, () -> new SwedishSocialSecurityNumber(ssn, mockHelper));
        assertEquals("Invalid SSN according to Luhn's algorithm", ex.getMessage());

        verify(mockHelper).luhnIsCorrect(ssn);
    }

    @Test
    public void shouldBeCorrectYear() throws Exception {
        String ssn = "900101-0017";
        when(mockHelper.isCorrectLength(ssn)).thenReturn(true);
        when(mockHelper.isCorrectFormat(ssn)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect(ssn)).thenReturn(true);

        SwedishSocialSecurityNumber ssnObject = new SwedishSocialSecurityNumber("900101-0017", mockHelper);
        assertEquals("90", ssnObject.getYear());

        // Verify that the mock methods were called
        verify(mockHelper).isCorrectLength("900101-0017");
        verify(mockHelper).isCorrectFormat("900101-0017");
        verify(mockHelper).isValidMonth("01");
        verify(mockHelper).isValidDay("01");
        verify(mockHelper).luhnIsCorrect("900101-0017");
    }
}