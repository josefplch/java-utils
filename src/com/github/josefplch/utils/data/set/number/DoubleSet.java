package com.github.josefplch.utils.data.set.number;

import com.github.josefplch.utils.data.foldable.number.DoubleFoldable;
import com.github.josefplch.utils.data.list.number.DoubleList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author  Josef Plch
 * @since   2022-01-19
 * @version 2022-01-19
 */
public class DoubleSet extends NumberSet <Double, Double> implements DoubleFoldable {
    public DoubleSet () {
    }
    
    public DoubleSet (Collection <Double> collection) {
        super (collection);
    }
    
    public static DoubleSet interval (double from, double to, double difference) {
        DoubleSet result = new DoubleSet ();
        for (double x = from; x <= to; x += difference) {
            result.add (x);
        }
        return result;
    }
    
    public static DoubleSet ofNumbers (Double ... numbers) {
        return new DoubleSet (Arrays.asList (numbers));
    }
    
    @Override
    public DoubleSet filter (Predicate <Double> predicate) {
        return super.filter (predicate, DoubleSet :: new);
    }
    
    @Override
    public DoubleList toList () {
        return new DoubleList (delegate);
    }
}
