package com.github.josefplch.utils.data.tuple;

import java.util.Comparator;

/**
 * An ordered pair of numeric bounds. The first element is the lower bound, the
 * second element is the upper bound.
 * 
 * @param <N> Type of range bounds: it must be a comparable number type.
 * 
 * @author  Josef Plch
 * @since   2015-06-16
 * @version 2018-05-17
 */
public class Range <N extends Number & Comparable <N>> extends UniformPair <N> implements Comparable <Range <N>> {
    private Comparator <Pair <N, N>> comparator;
    
    /**
     * Create a new range. The lower bound must be lower than the upper bound.
     * 
     * @param lowerBound Lower bound.
     * @param upperBound Upper bound.
     * @throws IllegalArgumentException If lowerBound is greater than upperBound.
     */
    public Range (N lowerBound, N upperBound) throws IllegalArgumentException {
        super (lowerBound, upperBound);
        // In Java, it is not possible to use "lowerBound > upperBound" with
        // generic numbers.
        if (lowerBound.compareTo (upperBound) > 0) {
            throw new IllegalArgumentException (
                Range.class.getSimpleName ()
                + ": lower bound is greater than upper bound"
            );
        }
        this.comparator = Pair.LexicographicalComparator.natural ();
    }
    
    @Override
    public int compareTo (Range <N> other) {
        return comparator.compare (this, other);
    }
    
    /**
     * Get the lower bound.
     * 
     * @deprecated Use {@link #getLowerBound()} instead.
     * 
     * @return The lower bound.
     */
    @Deprecated
    @Override
    public N get1 () {
        return super.get1 ();
    }
    
    /**
     * Get the upper bound.
     * 
     * @deprecated Use {@link #getUpperBound()} instead.
     * 
     * @return The upper bound.
     */
    @Deprecated
    @Override
    public N get2 () {
        return super.get2 ();
    }
    
    public N getLowerBound () {
        return super.get1 ();
    }
    
    public N getUpperBound () {
        return super.get2 ();
    }
    
    /**
     * Set the lower bound.
     * 
     * @deprecated Use {@link #setLowerBound(lowerBound)} instead.
     * 
     * @param lowerBound The new value of the lower bound.
     */
    @Deprecated
    @Override
    public void set1 (N lowerBound) {
        super.set1 (lowerBound);
    }
    
    /**
     * Set the upper bound.
     * 
     * @deprecated Use {@link #setUpperBound(upperBound)} instead.
     * 
     * @param upperBound The new value of the upper bound.
     */
    @Deprecated
    @Override
    public void set2 (N upperBound) {
        super.set1 (upperBound);
    }
    
    public void setLowerBound (N lowerBound) {
        super.set1 (lowerBound);
    }
    
    public void setUpperBound (N upperBound) {
        super.set2 (upperBound);
    }
    
    /**
     * This method is not supported because the bounds must be ordered.
     * 
     * @return Nothing.
     * @throws UnsupportedOperationException Always.
     */
    @Deprecated
    @Override
    public Range <N> swap () throws UnsupportedOperationException {
        throw new UnsupportedOperationException (
            "This method is not supported because the bounds must be ordered."
        );
    }
}
