package com.github.josefplch.utils.data.tuple;

import com.github.josefplch.utils.data.tuple.Pair;
import com.github.josefplch.utils.data.list.PairList;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * @author  Josef Plch
 * @since   2020-11-11
 * @version 2020-11-11
 */
public class TupleTest {
    public static void main (String [] args) {
        Collator collator = Collator.getInstance (Locale.forLanguageTag ("cs"));
        System.out.println (
            PairList.ofPairs (
                Pair.of (0, "babička"),
                Pair.of (0, "čas"),
                Pair.of (0, "auto"),
                Pair.of (0, "dům"),
                Pair.of (0, "candát")
            )
            .sortBy (Pair.LexicographicalComparator.basedOn (Comparator.naturalOrder (), collator))
            .mapToString ()
            .unlines ()
        );
    }
}
