package com.github.josefplch.utils.data.tuple;

/**
 * Unit, a tuple of 0 objects.
 * 
 * Also known as: 0-tuple, empty tuple, unit, void.
 * 
 * @author  Josef Plch
 * @since   2022-01-10
 * @version 2022-01-10
 */
public class Tuple0 {
    protected Tuple0 () {
    }
    
    public static Tuple0 of () {
        // TODO: Consider using a single static instance.
        return new Tuple0 ();
    }
    
    @Override
    public boolean equals (Object object) {
        return (object != null && object instanceof Tuple0);
    }
    
    @Override
    public int hashCode () {
        return 17;
    }
    
    @Override
    public String toString () {
        return ("()");
    }
}
