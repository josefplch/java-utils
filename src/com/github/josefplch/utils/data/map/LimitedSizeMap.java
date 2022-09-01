package com.github.josefplch.utils.data.map;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Map with limited number of elements. The oldest element is discarded when the
 * map is full and a new element is inserted.
 * 
 * This type of map can be used as a cache memory.
 * 
 * @param <K> Type of keys.
 * @param <V> Type of values.
 * 
 * @author  Josef Plch
 * @since   2018-03-15
 * @version 2018-04-25
 */
public class LimitedSizeMap <K, V> extends LinkedHashMap <K, V> {
    private final int maxSize;
    
    /**
     * Map constructor. The maximum size may be zero, in which case no elements
     * can be added and the map will always be empty.
     * 
     * @param maxSize Maximum allowed size of the map.
     */
    public LimitedSizeMap (int maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException (
                "Maximum map size cannot be negative."
            );
        }
        else {
            this.maxSize = maxSize;
        }
    }
    
    @Override
    public boolean equals (Object object) {
        Boolean result;
        if (object == null || ! (object instanceof LimitedSizeMap)) {
            result = false;
        }
        else {
            final LimitedSizeMap <?, ?> other = (LimitedSizeMap <?, ?>) object;
            result =
                super.equals (other)
                && Objects.equals (this.maxSize, other.maxSize);
        }
        return result;
    }
    
    /**
     * Get the first key (according to map's iterator).
     * 
     * Note: The order is guaranteed, even through keys are returned in set.
     * 
     * @return The first key of the map.
     * @throws NoSuchElementException If the map is empty.
     */
    private K firstKey () throws NoSuchElementException {
        return this.keySet ().iterator ().next ();
    }
    
    @Override
    public int hashCode () {
        int hash = 7;
        hash = 59 * hash + super.hashCode ();
        hash = 59 * hash + this.maxSize;
        return hash;
    }
    
    /**
     * Returns maximum allowed size of the map.
     * 
     * @return Maximum allowed size of the map.
     */
    public int maxSize () {
        return maxSize;
    }
    
    /**
     * Associates the specified value with the specified key in this map. If the
     * map previously contained a mapping for the key, the old value is replaced
     * by the specified value. (A map m is said to contain a mapping for a key k
     * if and only if m.containsKey(k) would return true.)
     * 
     * @param key   Key with which the specified value is to be associated.
     * @param value Value to be associated with the specified key.
     * @return      The previous value associated with key, or null if there was
     *              no mapping for key.
     * @throws UnsupportedOperationException If the maximum map size is zero.
     */
    @Override
    public V put (K key, V value) throws UnsupportedOperationException {
        if (maxSize == 0) {
            throw new UnsupportedOperationException (
                "The map has zero size, no elements can be added."
            );
        }
        else {
            if (this.size () >= maxSize) {
                removeFirst ();
            }
            return super.put (key, value);
        }
    }
    
    /**
     * Remove the first key of the map.
     * 
     * @throws NoSuchElementException If the map is empty.
     */
    private void removeFirst () throws NoSuchElementException {
        remove (firstKey ());
    }

    @Override
    public String toString () {
        return ("LimitedSizeMap {maxSize = " + maxSize + "}");
    }
}
