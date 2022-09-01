package com.github.josefplch.utils.data.function;

import com.github.josefplch.utils.data.tuple.Tuple4;
import java.util.function.Consumer;

/**
 * @param <A1> The type of the 1st argument to the consumer.
 * @param <A2> The type of the 2nd argument to the consumer.
 * @param <A3> The type of the 3rd argument to the consumer.
 * @param <A4> The type of the 4th argument to the consumer.
 * 
 * @author  Josef Plch
 * @since   2022-01-14
 * @version 2022-01-14
 */
@FunctionalInterface
public interface Consumer4 <A1, A2, A3, A4> {
    public void accept (A1 a1, A2 a2, A3 a3, A4 a4);
    
    public default Consumer3 <A2, A3, A4> accept1 (A1 a1) {
        return ((a2, a3, a4) -> this.accept (a1, a2, a3, a4));
    }
    
    public default Consumer3 <A1, A3, A4> accept2 (A2 a2) {
        return ((a1, a3, a4) -> this.accept (a1, a2, a3, a4));
    }
    
    public default Consumer3 <A1, A2, A4> accept3 (A3 a3) {
        return ((a1, a2, a4) -> this.accept (a1, a2, a3, a4));
    }
    
    public default Consumer3 <A1, A2, A3> accept4 (A4 a4) {
        return ((a1, a2, a3) -> this.accept (a1, a2, a3, a4));
    }
    
    public default Consumer <Tuple4 <A1, A2, A3, A4>> curry () {
        return (tuple ->
            this.accept (
                tuple.get1 (),
                tuple.get2 (),
                tuple.get3 (),
                tuple.get4 ()
            )
        );
    }
}
