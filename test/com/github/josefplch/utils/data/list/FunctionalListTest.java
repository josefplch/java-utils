package com.github.josefplch.utils.data.list;

import com.github.josefplch.utils.data.list.ListUtils;
import com.github.josefplch.utils.data.list.StringList;
import com.github.josefplch.utils.data.list.CharList;
import com.github.josefplch.utils.data.list.FunctionalList;
import com.github.josefplch.utils.data.list.number.IntegerList;
import com.github.josefplch.utils.data.nlp.NlpComparators;
import com.github.josefplch.utils.data.string.AlignmentUtils;
import java.util.Optional;

/**
 * @author  Josef Plch
 * @since   2019-04-24
 * @version 2022-01-20
 */
public class FunctionalListTest {
    private static void testCartesianProduct () {
        System.out.println (
            ListUtils.cartesianProduct (
                FunctionalList.of (
                    CharList.fromString ("abc"),
                    CharList.fromString ("123"),
                    CharList.fromString ("xy")
                )
            )
        );
    }
    
    private static void testMap () {
        StringList list = StringList.words ("a bb ccc");
        System.out.println (list.mapToString ((i, s) -> "#" + i + ": " + s).join (", "));
        System.out.println (list.map (String :: length));
        System.out.println (list.mapOptional (s -> s.length () == 1 ? Optional.empty () : Optional.of (s + s)));
        System.out.println (list.flatMap (CharList :: fromString));
    }
    
    private static void testNumeric () {
        System.out.println (IntegerList.ofNumbers (1, 2, 3).foldl ((m, n) -> m + n, 0));
        System.out.println (IntegerList.ofNumbers (1, 2, 3).foldr ((m, n) -> m + n, 0));
        
        IntegerList intList = IntegerList.interval (1, 5);
        System.out.println (intList.sum ());
        System.out.println (intList.product ());
        System.out.println (intList.arithmeticMean ());
    }
    
    private static void testMisc () {
        CharList empty  = CharList.ofCharacters ();
        CharList aa     = CharList.fromString ("aa");
        CharList aaa    = CharList.fromString ("aaa");
        CharList abcab  = CharList.fromString ("abcab");
        CharList abcab3 = CharList.fromString ("abca abca abca xy");
        CharList bc     = CharList.fromString ("bc");
        CharList xy     = CharList.fromString ("xy");
        CharList y      = CharList.fromString ("y");
        CharList space  = CharList.fromString (" \n \t text \n \t ");
        
        System.out.println ("CountAll: " + abcab.countAll ());
        System.out.println ("Set 1: " + abcab.toSet ());
        System.out.println ("Set 2: " + StringList.ofStrings ("a", "a", "b").toSet ());
        System.out.println ("Trim: " + space.trim ());
        System.out.println ("Partition: " + abcab.partition (c -> c == 'a').map (CharList :: sortAsc));
        System.out.println (abcab);
        System.out.println ("Sublists:");
        System.out.println (abcab.sublists ());
        System.out.println (abcab.sublists (1, 2));
        System.out.println (abcab.sublists (2, 2));
        System.out.println (abcab.sublists (3, 5));
        System.out.println (abcab.sublists ().toSet ().toList ().mapToString ().sortAsc ());
        System.out.println ("----------");
        System.out.println ("Subindex:");
        System.out.println (abcab.subIndex (empty));
        System.out.println (abcab.subIndex (aa));
        System.out.println (abcab.subIndex (bc));
        System.out.println (abcab.subIndex (abcab));
        System.out.println (abcab3.subIndex (xy));
        System.out.println (abcab3.subIndex (y));
        System.out.println ("----------");
        System.out.println ("SubIndices:");
        System.out.println (abcab3.subIndices (empty));
        System.out.println (abcab3.subIndices (aa));
        System.out.println (abcab3.subIndices (bc));
        System.out.println (abcab3.subIndices (xy));
        System.out.println (abcab3.subIndices (y));
        System.out.println ("----------");
        System.out.println (aaa.subIndices (empty));
        System.out.println (aaa.subIndices (aa));
    }
    
    private static void testSort () {
        StringList stringList = StringList.words ("příliš žluťoučký kůň úpěl ďábelské ódy");
        System.out.println (
                ListUtils.zipWith4 ((a, b, c, d) ->
                    AlignmentUtils.toLeft (a, 10)
                    + AlignmentUtils.toLeft (b, 10)
                    + AlignmentUtils.toLeft (c, 10)
                    + AlignmentUtils.toLeft (d, 10),
                stringList,
                stringList.sortAsc (),
                stringList.sortBy (NlpComparators.natural ("cs")),
                stringList.sortBy (NlpComparators.retrograde ("cs")),
                StringList :: new
            )
            .unlines ()
        );
    }
    
    public static void main (String [] args) {
        testCartesianProduct ();
        // testMap ();
        // testMisc ();
        // testNumeric ();
        // testSort ();
    }
}
