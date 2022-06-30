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
package net.renfei.sdk.entity;

import java.util.Hashtable;

/**
 * 比对结果
 *
 * @author renfei
 */
public class ComparisonResults<K, V> {
    /**
     * 交集
     */
    private Hashtable<K, V> intersection;
    /**
     * A的补集CuA
     */
    private Hashtable<K, V> complementA;
    /**
     * B的补集CuB
     */
    private Hashtable<K, V> complementB;

    public Hashtable<K, V> getIntersection() {
        return intersection;
    }

    public void setIntersection(Hashtable<K, V> intersection) {
        this.intersection = intersection;
    }

    public Hashtable<K, V> getComplementA() {
        return complementA;
    }

    public void setComplementA(Hashtable<K, V> complementA) {
        this.complementA = complementA;
    }

    public Hashtable<K, V> getComplementB() {
        return complementB;
    }

    public void setComplementB(Hashtable<K, V> complementB) {
        this.complementB = complementB;
    }
}
