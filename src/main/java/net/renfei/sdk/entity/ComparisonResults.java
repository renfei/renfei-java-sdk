package net.renfei.sdk.entity;

import java.util.Hashtable;

/**
 * <p>Title: ComparisonResults</p>
 * <p>Description: 比对结果</p>
 *
 * @author RenFei(i @ renfei.net)
 */
public class ComparisonResults<K,V> {
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
