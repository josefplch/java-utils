package com.github.josefplch.utils.data.set.number;

import com.github.josefplch.utils.data.foldable.number.FloatFoldable;
import com.github.josefplch.utils.data.list.number.FloatList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author  Josef Plch
 * @since   2022-01-19
 * @version 2022-01-19
 */
public class FloatSet extends NumberSet <Float, Float> implements FloatFoldable {
    public FloatSet () {
    }
    
    public FloatSet (Collection <Float> collection) {
        super (collection);
    }
    
    public static FloatSet interval (int from, int to, int difference) {
        FloatSet result = new FloatSet ();
        for (float x = from; x <= to; x += difference) {
            result.add (x);
        }
        return result;
    }
    
    public static FloatSet ofNumbers (Float ... numbers) {
        return new FloatSet (Arrays.asList (numbers));
    }
    
    @Override
    public FloatSet filter (Predicate <Float> predicate) {
        return super.filter (predicate, FloatSet :: new);
    }
    
    @Override
    public FloatList toList () {
        return new FloatList (delegate);
    }
}
