package com.github.josefplch.utils.data.function;

/**
 * A consumer able to throw an exception.
 * 
 * See:
 * https://stackoverflow.com/questions/18198176/java-8-lambda-function-that-throws-exception
 * http://www.baeldung.com/java-lambda-exceptions
 * 
 * @param <T>
 * @param <E> Type of thrown exception.
 * 
 * @author  Josef Plch
 * @since   2018-05-21
 * @version 2018-05-21
 */
@FunctionalInterface
public interface CheckedConsumer <T, E extends Throwable> {
    void accept (T t) throws E;
}
