package br.com.codein.mobiagecore.utils;

import br.com.codein.mobiagecore.AbstractTest;
import br.com.codein.mobiagecore.domain.utils.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by gelatti on 26/05/17.
 */
public class StringUtilsTest extends AbstractTest {

    @Test
    public void testSingleQuotedStr() {
        String test = StringUtils.singleQuotedStr("test");
        assertEquals("'test'", test);
    }

    @Test
    public void testSingleQuotedStrEmpty() {
        String test = StringUtils.singleQuotedStr("");
        assertEquals("", test);
    }

    @Test
    public void testSingleQuotedStrNull() {
        String test = StringUtils.singleQuotedStr(null);
        assertEquals(null, test);
    }

    @Test
    public void testDoubleQuotedStr() {
        String test = StringUtils.doubleQuotedStr("test");
        assertEquals("\"test\"", test);
    }

    @Test
    public void testDoubleQuotedStrEmpty() {
        String test = StringUtils.doubleQuotedStr("");
        assertEquals("", test);
    }

    @Test
    public void testDoubleQuotedStrNull() {
        String test = StringUtils.doubleQuotedStr(null);
        assertEquals(null, test);
    }

    @Test
    public void testCoalesceNotBlankString() {
        String test = StringUtils.coalesceNotBlankString("", null, "test");
        assertEquals("test", test);
    }

    @Test
    public void testCoalesce() {
        Object obj1 = null;
        Object obj2 = new Object();
        Object objTest = StringUtils.coalesce(obj1, obj2);
        assertEquals(obj2, objTest);
    }
}
