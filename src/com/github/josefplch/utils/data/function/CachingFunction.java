package com.github.josefplch.utils.data.function;

import com.github.josefplch.utils.data.map.LimitedSizeMap;
import java.util.Objects;
import java.util.function.Function;

/**
 * @param <A> Type of the argument.
 * @param <R> Type of the result.
 * 
 * @author  Josef Plch
 * @since   2018-05-03
 * @version 2018-10-22
 */
public class CachingFunction <A, R> implements Function <A, R> {
    private final LimitedSizeMap <A, R> cache;
    private final Function <A, R> delegate;
    
    public CachingFunction (Function <A, R> f, int cacheSize) {
        this.cache = new LimitedSizeMap <> (cacheSize);
        this.delegate = f;
    }
    
    @Override
    public R apply (A x) {
        R y;
        if (cache.containsKey (x)) {
            y = cache.get (x);
        }
        else {
            y = delegate.apply (x);
            if (cache.maxSize () > 0) {
                cache.put (x, y);
            }
        }
        return y;
    }
    
    public int cacheSize () {
        return cache.maxSize ();
    }
    
    @Override
    public boolean equals (Object object) {
        boolean result;
        if (object == null || ! (object instanceof CachingFunction)) {
            result = false;
        }
        else {
            final CachingFunction <?, ?> other = (CachingFunction <?, ?>) object;
            result =
                Objects.equals    (this.cache,    other.cache)
                && Objects.equals (this.delegate, other.delegate);
        }
        return result;
    }
    
    @Override
    public int hashCode () {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode (this.cache);
        hash = 89 * hash + Objects.hashCode (this.delegate);
        return hash;
    }
}
