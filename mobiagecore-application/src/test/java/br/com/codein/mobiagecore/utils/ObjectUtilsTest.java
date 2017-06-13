package br.com.codein.mobiagecore.utils;

import br.com.codein.mobiagecore.AbstractTest;
import br.com.codein.mobiagecore.domain.utils.ObjectUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ObjectUtilsTest  extends AbstractTest {


    @Test
    public void testCoalesce() {
        Object obj1 = null;
        Object obj2 = new Object();
        Object objTest = ObjectUtils.coalesce(obj1, obj2);
        assertEquals(obj2, objTest);
    }
    @Test
    public void testCoalesceArray() {
        List<String> obj1 = null;
        List<String> obj2 = new ArrayList<>();
        Object objTest = ObjectUtils.coalesce(obj1, obj2);
        assertEquals(obj2, objTest);
    }
    @Test
    public void testCoalesceNull() {
        Object obj1 = null;
        Object obj2 = null;
        Object objTest = ObjectUtils.coalesce(obj1, obj2);
        assertEquals(null, objTest);
    }
    @Test
    public void testCoalesceNullInsideArray() {
        List<String> strings = new ArrayList<>();
        strings.add(null);
        strings.add("teste");
        String objTest = ObjectUtils.coalesceArray(strings);
        assertEquals(strings.get(1), objTest);
    }
}
