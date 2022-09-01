package com.github.josefplch.utils.data.string;

import com.github.josefplch.utils.data.string.StringUtils;
import com.github.josefplch.utils.data.nlp.Tokenizer;
import com.github.josefplch.utils.data.list.StringList;

/**
 * @author  Josef Plch
 * @since   2020-12-23
 * @version 2020-12-23
 */
public abstract class TokenizerTest {
    public static void main (String [] args) {
        String text =
            "„Pejsku náš, co děláš, žes tak-vesel stále?“"
            + " - „Řek’ bych Vám, nevím sám.“"
            + " Hop! a skákal dále."
            + " https://www.pisnicky.cz/skakal-pes"
            + " jan@novak.cz"
            + " <a href=\"#\">odkaz</a>"
            + " 100cm3 0.5cm3"
            + " jimmy’s"
            + " ✓ ✓yes"
            + " ελληνική" + " 日本語" + " हिन्दी"
            + " Příliš žluťoučký 'kůň' úpěl (ďábelské!) ódy?!";
        
        System.out.println (text);
        System.out.println (Tokenizer.stripHtmlTags (text));
        System.out.println (Tokenizer.findTokens (Tokenizer.stripHtmlTags (text)).unwords ());
        System.out.println (Tokenizer.findWords (Tokenizer.stripHtmlTags (text), true, true, true).unwords ());
        System.out.println (Tokenizer.findWords (Tokenizer.stripHtmlTags (text), false, false, false).unwords ());
        
        System.out.println (StringList.split (' ', "žár šíp čáp řád ďas ťap").map (StringUtils :: ucfirst));
    }
}
