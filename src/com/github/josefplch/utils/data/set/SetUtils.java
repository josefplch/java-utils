package com.github.josefplch.utils.data.set;

import com.github.josefplch.utils.data.list.FunctionalList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Some Haskell-like functional operations for sets.
 * 
 * @author  Josef Plch
 * @since   2015-05-06
 * @version 2022-02-21
 */
public abstract class SetUtils {
    public static <A> FunctionalSet <A> intersection (Set <A> setA, Set <A> setB) {
        Set <A> largerSet;
        Set <A> smallerSet;
        
        if (setA.size () > setB.size ()) {
            largerSet = setA;
            smallerSet = setB;
        }
        else {
            largerSet = setB;
            smallerSet = setA;
        }
        
        FunctionalSet <A> intersection = new FunctionalSet <> ();
        for (A element : smallerSet) {
            if (largerSet.contains (element)) {
                intersection.add (element);
            }
        }
        
        return intersection;
    }
}
