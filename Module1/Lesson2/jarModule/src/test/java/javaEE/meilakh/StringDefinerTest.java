package javaEE.meilakh;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringDefinerTest {

    @Test
    public void checkNULL() {
        assertEquals("Wrong query", StringDefiner.defineContent(null));
    }

    @Test
    public void checkPhoneNumber() {
        assertEquals("Phone Number", StringDefiner.defineContent("3257851"));
    }

    @Test
    public void checkEmail() {
        assertEquals("Email", StringDefiner.defineContent("asd@asd"));
    }

    @Test
    public void checkFamilyName() {
        assertEquals("Family Name", StringDefiner.defineContent("asdasd"));
    }

    @Test
    public void checIDinsurance() {
        assertEquals("ID insurance", StringDefiner.defineContent("asdas123"));
    }
}
