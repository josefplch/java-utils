package com.github.josefplch.utils.data.math;

import com.github.josefplch.utils.data.math.fraction.IntegerFraction;

/**
 * @author  Josef Plch
 * @since   2021-01-11
 * @version 2021-01-11
 */
public class FractionTest {
    public static void main (String [] args) {
        IntegerFraction f12 = IntegerFraction.of (1, 2);
        IntegerFraction f23 = IntegerFraction.of (2, 3);
        IntegerFraction f48 = IntegerFraction.of (4, 8);
        
        System.out.println (f48 + " = " + f48.cancel ());
        
        System.out.println (f12 + " + " + f12 + " = " + (f12.sum (f12)));
        
        System.out.println (f12 + " + " + f12 + " = " + (f12.sum (f12)));
        System.out.println (f12 + " + " + f23 + " = " + (f12.sum (f23)));
        System.out.println (f23 + " + " + f23 + " = " + (f23.sum (f23)));
    }
}
