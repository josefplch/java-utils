package com.github.josefplch.utils.data.string;

import com.github.josefplch.utils.data.list.StringList;
import com.github.josefplch.utils.data.set.StringSet;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

/**
 * Source: https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
 * 
 * @author  Josef Plch
 * @since   2018-12-21
 * @version 2022-01-14
 */
public class RandomString {
    public static final String DIGITS = "0123456789";
    public static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA = LOWER + UPPER;
    public static final String ALPHANUM = ALPHA + DIGITS;
    private final Random random;
    private final char [] buffer;
    private final char [] characters;
    
    /**
     * @param random     Pseudorandom number generator.
     * @param characters List of characters to use in the string.
     * @param length     Length of the generated strings.
     */
    public RandomString (Random random, String characters, int length) {
        Objects.requireNonNull (random);
        if (Objects.isNull (characters) || characters.isEmpty ()) {
            throw new IllegalArgumentException ("There must be at least one character.");
        }
        this.buffer = new char [length];
        this.characters = characters.toCharArray ();
        this.random = Objects.requireNonNull (random);
    }
    
    /**
     * Create strings from a secure generator.
     * 
     * @param characters List of characters to use in the string.
     * @param length     Length of the generated strings.
     */
    public RandomString (String characters, int length) {
        this (new SecureRandom (), characters, length);
    }
    
    /**
     * Create an alphanumeric strings from a secure generator.
     * 
     * @param length Length of the generated strings.
     */
    public RandomString (int length) {
        this (new SecureRandom (), ALPHANUM, length);
    }
    
    /**
     * Generate a random string.
     * 
     * @return Pseudorandom string.
     */
    public String nextString () {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = characters [random.nextInt (characters.length)];
        }
        return new String (buffer);
    }
    
    /**
     * Generate n random strings.
     * 
     * @param n Number of generated strings.
     * @return  Pseudorandom strings.
     */
    public StringList nextStrings (int n) {
        StringList result = new StringList ();
        for (int i = 0; i < n; i++) {
            result.add (this.nextString ());
        }
        return result;
    }
    
    /**
     * Generate n unique random strings.
     * 
     * @param n Number of generated strings.
     * @param maxFailedAttempts How many unsuccessful attempts are tolerated?
     * @return  Pseudorandom strings.
     */
    public StringList nextUniqueStrings (int n, int maxFailedAttempts) {
        StringSet result = new StringSet ();
        for (int i = 0; result.size () < n && i < n + maxFailedAttempts; i++) {
            result.add (this.nextString ());
        }
        if (result.size () < n) {
            throw new RuntimeException ("Too many collisions (" + (maxFailedAttempts + 1) + ")");
        }
        else {
            return result.toList ().shuffle ();
        }
    }
}
