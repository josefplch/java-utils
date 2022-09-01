package com.github.josefplch.utils.data.list;

import com.github.josefplch.utils.data.list.GluedList;

/**
 * @author  Josef Plch
 * @since   2020-12-08
 * @version 2020-12-08
 */
public class GluedListTest {
    public static void main (String [] args) {
        GluedList <Character, Integer> list = new GluedList <> ('#');
        System.out.println (list);
        list.addLast (1, 'a');
        list.addLast (2, 'b');
        System.out.println (list);
        list.addFirst ('Z', -1);
        list.addFirst ('Y', -2);
        System.out.println (list);
    }
}
