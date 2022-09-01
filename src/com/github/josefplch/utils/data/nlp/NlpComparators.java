package com.github.josefplch.utils.data.nlp;

import com.github.josefplch.utils.data.string.StringUtils;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * @author  Josef Plch
 * @since   2021-01-14
 * @version 2021-01-14
 */
public abstract class NlpComparators {
    public static Comparator <? super String> natural (String isoLanguageCode) {
        return Collator.getInstance (new Locale (isoLanguageCode));
    }
    
    public static Comparator <? super String> retrograde (String isoLanguageCode) {
        return Comparator.comparing (StringUtils :: reverse, NlpComparators.natural (isoLanguageCode));
    }
}
