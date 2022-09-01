package com.github.josefplch.utils.data.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * Constant function is a function which always returns the same value.
 * 
 * @param <T> Type of the argument.
 * @param <R> Type of the result.
 * 
 * @author  Josef Plch
 * @since   2018-05-15
 * @version 2019-03-26
 */
public final class ConstantFunction <T, R> implements Function <T, R> {
    private final R value;
    
    private ConstantFunction (R value) {
        this.value = value;
    }
    
    public static <T, R> ConstantFunction <T, R> forValue (R value) {
        return new ConstantFunction <> (value);
    }
    
    @Override
    public R apply (T parameter) {
        return value;
    }
    
    @Override
    public boolean equals (Object object) {
        boolean result;
        if (object == null || ! (object instanceof ConstantFunction)) {
            result = false;
        }
        else {
            final ConstantFunction <?, ?> other = (ConstantFunction <?, ?>) object;
            result = Objects.equals (this.value, other.value);
        }
        return result;
    }
    
    @Override
    public int hashCode () {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode (this.value);
        return hash;
    }
}
