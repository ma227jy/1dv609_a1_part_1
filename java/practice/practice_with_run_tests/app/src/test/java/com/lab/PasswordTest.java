package com.lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Password implementations.
 * 
 * To test different buggy versions, simply uncomment the corresponding
 * getPassword() method and comment out the others.
 * 
 * Available implementations:
 * - Password: Correct implementation
 * - BugDoesNotTrim: Does not trim whitespace
 * - BugToShortPassword: Allows passwords shorter than 12 characters
 * - BugVeryShort: Allows way to short passwords
 * - BugWrongExceptionMessage: Wrong exception message for short passwords
 * - BugMissingPasswordLengthCheck: Does not throw exception for short passwords
 * - BugMissingNumberCheck: Does not throw exception if password lacks a number
 * - BugIsPasswordSameAlwaysTrue: isPasswordSame always returns true
 * - BugWrongHashingAlgorithm: Wrong hashing algorithm
 */

public class PasswordTest {
    private IPassword getPassword(String s) throws Exception {
        return (IPassword) new Password(s);
        //return (IPassword) new BugDoesNotTrim(s);
        //return (IPassword) new BugToShortPassword(s);
        // return (IPassword) new BugToShortPassword(s);
        //return (IPassword) new BugVeryShort(s);
        //return (IPassword) new BugWrongExceptionMessage(s);
        //return (IPassword) new BugMissingPasswordLengthCheck(s);
        //return (IPassword) new BugMissingNumberCheck(s);
        //return (IPassword) new BugIsPasswordSameAlwaysTrue(s);
        // return (IPassword) new BugWrongHashingAlgorithm(s);
    }

    @Test
    public void shouldAlwaysPass() throws Exception {
        assertTrue(true);
    }

    @Test
    public void shouldTrimPassword() {
        String pw = "     Test123    ";
        assertThrows(Exception.class,()-> getPassword(pw));
    }

    @Test
    public void shouldRejectPasswordThatIsToShort() {
        String pw = "11charlongP"; // 11 char long password
        assertThrows(Exception.class,() -> getPassword(pw));
    }

    @Test
    public void shouldRejectVeryShortPasswword() {
        String pw = "6chars"; // 6 char long password
        assertThrows(Exception.class, () -> getPassword(pw));
    }

    @Test
    public void shouldThrowToShortMessage() {
        String pw = "short";
        Exception ex = assertThrows(Exception.class, () -> getPassword(pw));
        assertEquals("To short password", ex.getMessage());
    }

    @Test
    public void shouldRejectPasswordWithoutNumber() {
        String pw = "PwWithoutNum";
        assertThrows(Exception.class, () -> getPassword(pw));
    }


    @Test
    public void shouldRejectNotSamePasswords() throws Exception {
        IPassword pw1 = getPassword("TestPassword1");
        IPassword pw2 = getPassword("TestPassword2");

        assertFalse(pw1.isPasswordSame(pw2));
    }

    @Test
    public void shouldAcceptSamePasswords() throws Exception {
        IPassword pw1 = getPassword("TestPassword1");
        IPassword pw2 = getPassword("TestPassword1");

        assertTrue(pw1.isPasswordSame(pw2));
    }
}
