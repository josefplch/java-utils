package com.github.josefplch.utils.data;

import com.github.josefplch.utils.data.ComparableUtils;
import com.github.josefplch.utils.data.string.StringUtils;
import com.github.josefplch.utils.data.tuple.Pair;
import java.util.Comparator;

/**
 * Different tests.
 * 
 * @author  Josef Plch
 * @since   2020-11-12
 * @version 2020-11-12
 */
public class Tests {
    public static void main (String [] args) {
        if (true) {
            System.out.println (StringUtils.removeDiacritics ("Příliš žluťoučký kůň (ß)"));
        }
        
        if (true) {
            Integer keyX = 0;
            Integer keyY = 1;
            Pair <Integer, String> x = Pair.of (keyX, "a");
            Pair <Integer, String> y = Pair.of (keyY, "b");
            System.out.println (ComparableUtils.max (keyX, keyY));
            System.out.println (ComparableUtils.maxBy (x, y, Comparator.comparing (Pair :: get1)));
            System.out.println (ComparableUtils.maxComparing (x, y, Pair :: get1));
            System.out.println (ComparableUtils.maxComparing (x, y, Pair :: get1, Integer :: compareTo));
            System.out.println (ComparableUtils.min (keyX, keyY));
            System.out.println (ComparableUtils.minBy (x, y, Comparator.comparing (Pair :: get1)));
            System.out.println (ComparableUtils.minComparing (x, y, Pair :: get1));
            System.out.println (ComparableUtils.minComparing (x, y, Pair :: get1, Integer :: compareTo));
        }
    }
}
