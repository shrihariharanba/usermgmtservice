package org.usermgmt.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityUtilTest {

    @Autowired
    private SecurityUtil util;

    @Test
    public void encodeTest() {
        System.out.println(util.encode("test123"));
        assertNotNull(util.encode("test123"));
    }

    @Test
    public void checkPositiveCaseTest() {
        String password = "test123";
        String hash = "$2a$10$4mZWTwIMgN1htNucod2eie9.LscQlKSh2tTq1lo43OCwgG/j2HRQG";
        assertTrue(util.check(password, hash));
    }

    @Test
    public void checkNegativePasswordCaseTest() {
        String password = "test1234";
        String hash = "$2a$10$4mZWTwIMgN1htNucod2eie9.LscQlKSh2tTq1lo43OCwgG/j2HRQG";
        assertFalse(util.check(password, hash));
    }

    @Test
    public void checkNegativeHashCaseTest() {
        String password = "test123";
        String hash = "$2a$10$4mZWTwIMgN1htNucod2eie9sdfsdf.LscQlKSh2tTq1lo43OCwgG/j2HRQG";
        assertFalse(util.check(password, hash));
    }

}
