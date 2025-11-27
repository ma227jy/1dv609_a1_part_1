package com.lab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class SwedishSocialSecurityNumberTest {
    
    private SSNHelper helper;
    
    @BeforeEach
    public void setUp() {
        helper = new SSNHelper();
    }
    
    @Test
    public void shouldAcceptValidSSN() throws Exception {
        SwedishSocialSecurityNumber ssn = new SwedishSocialSecurityNumber("900101-0017", helper);
        
        assertEquals("90", ssn.getYear());
        assertEquals("01", ssn.getMonth());
        assertEquals("01", ssn.getDay());
        assertEquals("0017", ssn.getSerialNumber());
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


}