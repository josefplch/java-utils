package com.github.josefplch.utils.data.set.number;

import com.github.josefplch.utils.data.foldable.number.IntegerFoldable;
import com.github.josefplch.utils.data.list.number.IntegerList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author  Josef Plch
 * @since   2022-01-19
 * @version 2022-01-19
 */
public class IntegerSet extends NumberSet <Integer, Float> implements IntegerFoldable {
    public IntegerSet () {
    }
    
    public IntegerSet (Collection <Integer> collection) {
        super (collection);
    }
    
    public static IntegerSet interval (int from, int to) {
        return interval (from, to, 1);
    }
    
    public static IntegerSet interval (int from, int to, int difference) {
        IntegerSet result = new IntegerSet ();
        for (int n = from; n <= to; n += difference) {
            result.add (n);
        }
        return result;
    }
    
    public static IntegerSet ofNumbers (Integer ... numbers) {
        return new IntegerSet (Arrays.asList (numbers));
    }
    
    @Override
    public IntegerSet filter (Predicate <Integer> predicate) {
        return super.filter (predicate, IntegerSet :: new);
    }
    
    @Override
    public IntegerList toList () {
        return new IntegerList (delegate);
    }
}
