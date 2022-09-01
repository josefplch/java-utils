package com.github.josefplch.utils.data.math.fraction;

import java.math.BigInteger;

/**
 * @author  Josef Plch
 * @since   2019-05-10
 * @version 2021-01-11
 */
public class IntegerFraction extends AbstractFraction <Integer> {
    protected IntegerFraction (int numerator, int denominator) {
        super (numerator, denominator);
    }
    
    public static IntegerFraction of (int numerator, int denominator) {
        return new IntegerFraction (numerator, denominator);
    }
    
    public static IntegerFraction valueOf (String string) throws NumberFormatException {
        String [] parts = string.split ("/");
        if (parts.length != 2) {
            throw new NumberFormatException ("Illegal fraction: " + string);
        }
        else {
            return new IntegerFraction (
                Integer.valueOf (parts [0]),
                Integer.valueOf (parts [1])
            );
        }
    }
    
    @Override
    public IntegerFraction cancel () {
        int gcd =
            BigInteger.valueOf (numerator)
            .gcd (BigInteger.valueOf (denominator))
            .intValue ();
        return new IntegerFraction (numerator / gcd, denominator / gcd);
    }
    
    @Override
    public IntegerFraction sum (AbstractFraction <Integer> other) {
        return (
            new IntegerFraction (
                this.numerator * other.denominator + other.numerator * this.denominator,
                this.denominator * other.denominator
            )
            .cancel ()
        );
    }
    
    @Override
    public IntegerFraction sumElementWise (AbstractFraction <Integer> other) {
        return new IntegerFraction (this.numerator + other.numerator, this.denominator + other.denominator);
    }
    
    @Override
    public IntegerFraction swap () {
        return new IntegerFraction (denominator, numerator);
    }
}
