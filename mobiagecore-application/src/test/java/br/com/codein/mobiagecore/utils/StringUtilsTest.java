package br.com.codein.mobiagecore.utils;

import br.com.codein.mobiagecore.AbstractTest;
import br.com.codein.mobiagecore.domain.utils.StringUtils;
import org.junit.Test;

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
    public void testCoalesceNotBlankStringWithAllBlank() {
        String test = StringUtils.coalesceNotBlankString("", "", "");
        assertEquals(null, test);
    }

    @Test
    public void testIsBlankBlank() {
        Boolean test = StringUtils.isBlank("");
        assertEquals(true, test);
    }

    @Test
    public void testIsBlankNotBlank() {
        Boolean test = StringUtils.isBlank("test");
        assertEquals(false, test);
    }


}
