package com.github.josefplch.utils.system;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Objects;

/**
 * Fork the output to multiple writers.
 * 
 * @author  Josef Plch
 * @since   2019-03-30
 * @version 2021-01-14
 */
public class ForkWriter extends Writer {
    private final Iterable <Writer> writers;

    public ForkWriter (Iterable <Writer> writers) {
        this.writers = writers;
    }
    
    public ForkWriter (Writer ... writers) {
        this (Arrays.asList (writers));
    }
    
    @Override
    public void close () throws IOException {
        for (Writer writer : writers) {
            writer.close ();
        }
    }

    @Override
    public void flush () throws IOException {
        for (Writer writer : writers) {
            writer.flush ();
        }
    }
    
    public void write (Object object) throws IOException {
        this.write (Objects.toString (object));
    }
    
    @Override
    public void write (String string) throws IOException {
        for (Writer writer : writers) {
            writer.write (string);
        }
    }
    
    @Override
    public void write (char [] buffer, int offset, int length) throws IOException {
        for (Writer writer : writers) {
            writer.write (buffer, offset, length);
        }
    }
    
    public void writeLn () throws IOException {
        this.write ("\n");
    }
    
    public void writeLn (Object object) throws IOException {
        this.write (object);
        this.writeLn ();
    }
    
    public void writeLn (String string) throws IOException {
        this.write (string);
        this.writeLn ();
    }
}
