package com.github.josefplch.utils.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Native libraries (.dll, .so) loader. The libraries shall be placed in
 * resource directory (in Maven, it is ./src/main/resources).
 * 
 * Some code was adopted from StackOverflow (by user Bostone, real name probably
 * Robert Stone): How to make a JAR file that includes DLL files?
 * <br>https://stackoverflow.com/questions/1611357/how-to-make-a-jar-file-that-includes-dll-files
 * 
 * @author  Josef Plch, Robert Stone
 * @since   2018-02-19
 * @version 2020-10-09
 */
public abstract class LibraryUtils {
    private static final String CLASS_NAME = LibraryUtils.class.getSimpleName ();
    private static final Logger LOGGER = Logger.getLogger (CLASS_NAME);
    
    /**
     * Load library by exact file name. It shall be placed in resource directory
     * (in Maven, it is ./src/main/resources).
     * 
     * This method works even inside a JAR/WAR archive.
     * 
     * @param fileName               File name (not path) of the library file.
     * @return                       The file the library was loaded from.
     * @throws FileNotFoundException If the library is not found.
     * @throws IOException           If the library cannot be copied.
     */
    public static File loadLibraryByFileName (String fileName) throws FileNotFoundException, IOException {
        File tempDirectory = IoUtils.getTempDirectory ();
        
        // Delete the old copies.
        ResourceUtils.deleteTempCopies (fileName, tempDirectory);
        
        // Copy the library into a file.
        File tempLibraryCopy = ResourceUtils.createResourceCopy (fileName, tempDirectory, true);
        
        // Load it.
        System.load (tempLibraryCopy.getAbsolutePath ());
        
        return tempLibraryCopy;
    }
    
    /**
     * Load native library. If it is possible, it will be loaded directly from
     * the library path; if not (e.g. when using a JAR/WAR archive), the library
     * will be copied to the system temporary directory and loaded from there.
     * 
     * The library name will be mapped according to the host operating system:
     * <br>Linux:   "name" -&gt; "libname.so"
     * <br>Windows: "name" -&gt; "name.dll"
     * 
     * The library file shall be placed in resource directory (in Maven, it is
     * ./src/main/resources).
     * 
     * @param libraryName            Simple name of the library to be loaded.
     * @throws FileNotFoundException If the library is not found.
     * @throws IOException           If the library cannot be copied.
     */
    public static void loadLibraryByName (String libraryName) throws FileNotFoundException, IOException {
        try {
            System.loadLibrary (libraryName);
            LOGGER.log (
                Level.INFO,
                CLASS_NAME + ".loadLibraryByName: library loaded from library path"
            );
        }
        catch (UnsatisfiedLinkError error) {
            String mappedName = System.mapLibraryName (libraryName);
            File file = loadLibraryByFileName (mappedName);
            LOGGER.log (
                Level.INFO,
                CLASS_NAME + ".loadLibraryByName: library loaded from temporary copy: "
                    + file.getAbsolutePath ()
            );
        }
    }
}
