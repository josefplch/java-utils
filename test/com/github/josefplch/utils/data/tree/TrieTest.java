package com.github.josefplch.utils.data.tree;

import com.github.josefplch.utils.data.tree.StringFrequencyTrie;
import com.github.josefplch.utils.data.list.StringList;
import com.github.josefplch.utils.data.string.RandomString;

/**
 * @author  Josef Plch
 * @since   2018-05-12
 * @version 2021-01-12
 */
public class TrieTest {
    public static void main (String [] args) {
        test1 ();
        testTrieSize ();
    }
    
    private static void test1 () {
        StringFrequencyTrie trie = new StringFrequencyTrie ();
        System.out.println ("trie #1 = " + trie);
        
        String molKey = "mol";
        String moliKey = "moli";
        String moloKey = "molo";
        String molitanKey = "molitan";
        Long molValue = 10L;
        Long moloValue = 100L;
        Long molitanValue = 1000L;
        
        // Order matters.
        trie.modifyFrequency (molitanKey, molitanValue);
        trie.modifyFrequency (molitanKey, molitanValue);
        trie.modifyFrequency (moloKey, moloValue);
        trie.modifyFrequency (moloKey, moloValue);
        trie.modifyFrequency (moloKey, moloValue);
        trie.modifyFrequency (molKey, molValue);
        
        System.out.println ();
        System.out.println ("trie #2 = " + trie);
        System.out.println ("trie as list = " + trie.toDescList ());
        
        System.out.println ();
        System.out.println ("[] = " + trie.get (""));
        System.out.println ("mol = " + trie.get (molKey));
        System.out.println ("moli = " + trie.get (moliKey));
        System.out.println ("molo = " + trie.get (moloKey));
        System.out.println ("molitan = " + trie.get (molitanKey));
        
        System.out.println ();
        System.out.println ("Bimap: " + trie.bimap (Object :: toString, Object :: toString));
    }
    
    private static void testTrieSize () {
        StringFrequencyTrie trie = new StringFrequencyTrie ();
        
        String russianLowerB = "в";
        String russianUpperB = "В";
        
        StringList list = StringList.ofStrings ("sem", "s", "se", "semestr");
        System.out.println ("List size: " + list.size ());
        list.forEach (trie :: incrementFrequency);
        
        RandomString random = new RandomString (10);
        for (int i = 0; i < 0; i++) {
            String string = random.nextString ();
            trie.incrementFrequency (string);
            System.out.println (
                "#" + (i + 1) + ":"
                + " + " + string + " => size = "
                + trie.size ()
            );
        }
        
        System.out.println ("Trie size: " + trie.size ());
        System.out.println ("Trie:");
        System.out.println (trie.toString (true));
        System.out.println ("Trie as list: " + trie.toDescList ());
    }
}
