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
package net.renfei.sdk.utils;

import net.renfei.sdk.entity.ComparisonResults;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * 集合工具
 *
 * @author renfei
 */
public class CollectionUtils {
    /**
     * 集合比对工具（求两个集合的交集、补集）
     * 先对比Value，如果Value一致对比Key
     *
     * @param hashtableA 集合A
     * @param hashtableB 集合B
     * @param <K>        Key
     * @param <V>        Value
     * @return 两个集合的交集、补集（CuA、CuB）
     */
    public <K, V> ComparisonResults<K, V> comparison(Hashtable<K, V> hashtableA, Hashtable<K, V> hashtableB) {
        ComparisonResults<K, V> comparisonResults = new ComparisonResults<>();
        if (hashtableA == null || hashtableA.size() == 0) {
            comparisonResults.setIntersection(new Hashtable<>(0));
            comparisonResults.setComplementA(hashtableB == null ? new Hashtable<>(0) : hashtableB);
            comparisonResults.setComplementB(new Hashtable<>(0));
            return comparisonResults;
        }
        if (hashtableB == null || hashtableB.size() == 0) {
            comparisonResults.setIntersection(new Hashtable<>(0));
            comparisonResults.setComplementA(new Hashtable<>(0));
            comparisonResults.setComplementB(hashtableA);
            return comparisonResults;
        }
        Hashtable<K, V> intersection = new Hashtable<>();
        if (hashtableA.size() < hashtableB.size()) {
            comparisonTraversal(hashtableA, hashtableB, intersection);
        } else {
            comparisonTraversal(hashtableB, hashtableA, intersection);
        }
        comparisonResults.setIntersection(intersection);
        comparisonResults.setComplementA(hashtableB);
        comparisonResults.setComplementB(hashtableA);
        return comparisonResults;
    }

    private <K, V> void comparisonTraversal(Hashtable<K, V> left, Hashtable<K, V> right, Hashtable<K, V> intersection) {
        Iterator<Map.Entry<K, V>> iteratorA = left.entrySet().iterator();
        while (iteratorA.hasNext()) {
            Map.Entry<K, V> entryA = iteratorA.next();
            Iterator<Map.Entry<K, V>> iteratorB = right.entrySet().iterator();
            while (iteratorB.hasNext()) {
                Map.Entry<K, V> entryB = iteratorB.next();
                if (entryA.getValue().equals(entryB.getValue())) {
                    if (entryA.getKey().equals(entryB.getKey())) {
                        intersection.put(entryA.getKey(), entryA.getValue());
                        iteratorA.remove();
                        iteratorB.remove();
                    }
                }
            }
        }
    }
}
