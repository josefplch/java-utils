package com.github.josefplch.utils.data.tuple;

import com.github.josefplch.utils.data.Functor;
import com.github.josefplch.utils.data.function.Function2;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Singleton, a tuple of one object.
 * 
 * Also known as: 1-tuple, monotuple.
 * 
 * @param <T> Type of the element.
 * 
 * @author  Josef Plch
 * @since   2020-12-23
 * @version 2022-01-10
 */
public class Tuple1 <T> implements Functor <T>, Serializable {
    protected T e;
    
    protected Tuple1 (T e) {
        this.e = e;
    }
    
    public static <T> Tuple1 <T> of (T e) {
        return new Tuple1 <> (e);
    }
    
    @Override
    public boolean equals (Object object) {
        boolean result;
        if (object == null || ! (object instanceof Tuple1)) {
            result = false;
        }
        else {
            final Tuple1 <?> other = (Tuple1 <?>) object;
            result = Objects.equals (this.e, other.e);
        }
        return result;
    }
    
    /**
     * @return The element.
     */
    public T get () {
        return e;
    }
    
    @Override
    public int hashCode () {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode (this.e);
        return hash;
    }
    
    // 2 = number of arguments.
    public static <A, B, R> Function2 <? super Tuple1 <A>, ? super Tuple1 <B>, Tuple1 <R>> lift2 (
        BiFunction <A, B, R> f
    ) {
        return ((a, b) -> Tuple1.of (f.apply (a.e, b.e)));
    }
    
    @Override
    public <U> Tuple1 <U> map (Function <T, U> f) {
        return Tuple1.of (f.apply (e));
    }
    
    /**
     * Set new value of the element.
     * 
     * @param element New element value.
     */
    public void set (T element) {
        this.e = element;
    }

    @Override
    public String toString () {
        return ("(" + e + ")");
    }
}
