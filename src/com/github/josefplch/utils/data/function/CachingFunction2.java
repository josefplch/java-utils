package com.github.josefplch.utils.data.function;

import com.github.josefplch.utils.data.map.LimitedSizeMap;
import com.github.josefplch.utils.data.tuple.Pair;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * @param <A1> Type of the 1st argument of the function.
 * @param <A2> Type of the 2nd argument of the function.
 * @param <R> Type of value (result of the function).
 * 
 * @author  Josef Plch
 * @since   2018-05-03
 * @version 2019-05-13
 */
public class CachingFunction2 <A1, A2, R> implements Function2 <A1, A2, R> {
    private final LimitedSizeMap <Pair <A1, A2>, R> cache;
    private final BiFunction <A1, A2, R> delegate;
    
    public CachingFunction2 (BiFunction <A1, A2, R> f, int cacheSize) {
        this.cache = new LimitedSizeMap <> (cacheSize);
        this.delegate = f;
    }
    
    public R apply (Pair <A1, A2> args) {
        R y;
        if (cache.containsKey (args)) {
            y = cache.get (args);
        }
        else {
            y = delegate.apply (args.get1 (), args.get2 ());
            if (cache.maxSize () > 0) {
                cache.put (args, y);
            }
        }
        return y;
    }
    
    @Override
    public R apply (A1 x1, A2 x2) {
        return this.apply (Pair.of (x1, x2));
    }
    
    public int cacheSize () {
        return cache.maxSize ();
    }
    
    @Override
    public boolean equals (Object object) {
        boolean result;
        if (object == null || ! (object instanceof CachingFunction2)) {
            result = false;
        }
        else {
            final CachingFunction2 <?, ?, ?> other = (CachingFunction2 <?, ?, ?>) object;
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
