package com.github.josefplch.utils.system;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * General I/O stream utilities.
 * 
 * @author  Josef Plch
 * @since   2018-02-20
 * @version 2020-10-09
 */
public abstract class IoUtils {
    /**
     * The default buffer size to use for copyWithBuffer.
     * 
     * Source: org.apache.commons.io.IOUtils
     */
    private static final int DEFAULT_BUFFER_SIZE = 4 * 1024;
    
    /**
     * Represents the end-of-file (or stream).
     */
    private static final int EOF = -1;
    
    /**
     * Copies bytes from an InputStream to an OutputStream.
     * 
     * This method buffers the input internally, so there is no need to use a
     * BufferedInputStream.
     * 
     * Source: org.apache.commons.io.IOUtils
     * 
     * @param input        The InputStream to read from.
     * @param output       The OutputStream to write to.
     * @return             The number of bytes copied.
     * @throws IOException If an I/O error occurs.
     */
    public static long copy (final InputStream input, final OutputStream output) throws IOException {
        byte [] buffer = new byte [DEFAULT_BUFFER_SIZE];
        return copyWithBuffer (input, output, buffer);
    }
    
    /**
     * Copies bytes from InputStream to an OutputStream.
     * 
     * This method uses the provided buffer, so there is no need to use a
     * BufferedInputStream.
     * 
     * Source: org.apache.commons.io.IOUtils
     * 
     * @param input        The InputStream to read from.
     * @param output       The OutputStream to write to.
     * @param buffer       The buffer to use for the copy.
     * @return             The number of bytes copied.
     * @throws IOException If an I/O error occurs.
     */
    public static long copyWithBuffer (final InputStream input, final OutputStream output, final byte [] buffer) throws IOException {
        long numberOfBytes = 0;
        int n;
        while ((n = input.read (buffer)) != EOF) {
            output.write (buffer, 0, n);
            numberOfBytes += n;
        }
        return numberOfBytes;
    }
    
    /**
     * Return temporary-file directory specified by the system property
     * "java.io.tmpdir". On UNIX systems, the default value of this property is
     * typically "/tmp" or "/var/tmp"; on Windows, it is typically "C:\temp",
     * or C:\Users\...\Temp, in newer versions.
     * 
     * The default value may be overriden:
     * <br>java -Djava.io.tmpdir=path-to-directory
     * 
     * @return Temporary-file directory path.
     */
    public static File getTempDirectory () {
        String path = System.getProperty ("java.io.tmpdir");
        return (new File (path));
    }
}
