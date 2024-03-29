/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.sdk.test.utils;

import net.renfei.sdk.entity.ComparisonResults;
import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;
import java.util.UUID;

/**
 * @author renfei
 */
public class CollectionUtilsTest extends Tests {
    @Test
    public void comparisonTest() {
        CollectionUtils collectionUtils = new CollectionUtils();
        Hashtable<String, String> hashtableA = new Hashtable<>(10),
                hashtableB = new Hashtable<>(10);
        hashtableA.put("zhangsan", "123");
        hashtableA.put("lisi", "234");
        hashtableA.put("wangwu", "dddd");
        hashtableA.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        hashtableA.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        //////////////////////////////////
        hashtableB.put("zhangsan", "123");
        hashtableB.put("lisi", "234");
        hashtableB.put("wangwu555", "dddd");
        for (int i = 0; i < 5; i++) {
            hashtableA.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            hashtableB.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        ComparisonResults<String, String> comparisonResults = collectionUtils.comparison(hashtableA, hashtableB);
        for (String key : comparisonResults.getIntersection().keySet()
        ) {
            System.out.println(Thread.currentThread().getName() + "：AB集合交集:" + key + ":" + comparisonResults.getIntersection().get(key));
        }
        for (String key : comparisonResults.getComplementA().keySet()
        ) {
            System.out.println(Thread.currentThread().getName() + "：A集合的补集CuA:" + key + ":" + comparisonResults.getComplementA().get(key));
        }
        for (String key : comparisonResults.getComplementB().keySet()
        ) {
            System.out.println(Thread.currentThread().getName() + "：B集合的补集CuB:" + key + ":" + comparisonResults.getComplementB().get(key));
        }
        collectionUtils = new CollectionUtils();
        hashtableA = new Hashtable<>(10);
        hashtableA.put("zhangsan", "123");
        hashtableA.put("lisi", "234");
        hashtableA.put("wangwu", "dddd");
        hashtableA.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        hashtableA.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        collectionUtils.comparison(hashtableA, null);
        collectionUtils.comparison(null, hashtableA);
        collectionUtils.comparison(null, null);
    }
}
