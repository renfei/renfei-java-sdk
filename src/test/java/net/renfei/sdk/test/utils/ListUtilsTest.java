package net.renfei.sdk.test.utils;

import lombok.SneakyThrows;
import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.ListUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListUtilsTest extends Tests {
    @Test
    public void testListGetOne() throws InterruptedException {
        Runnable taskTemp = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("==== " + this.getClass().getName() + " ====");
                List<String> stringList = new ArrayList<String>();
                Assertions.assertNull(ListUtils.getOne(stringList));
                stringList.add("test1");
                stringList.add("test2");
                stringList.add("test3");
                Assertions.assertEquals(ListUtils.getOne(stringList), "test1");
            }
        };
        startTaskAllInOnce(100, taskTemp);
    }
}
