package net.renfei.sdk.test.utils;

import net.renfei.sdk.utils.ListUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListUtilsTest {
    @Test
    public void testListGetOne(){
        List<String> stringList=new ArrayList<String>();
        Assert.assertNull(ListUtils.getOne(stringList));
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");
        Assert.assertEquals(ListUtils.getOne(stringList),"test1");
    }
}
