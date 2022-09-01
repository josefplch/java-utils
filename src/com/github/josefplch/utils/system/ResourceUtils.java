package com.github.josefplch.utils.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Resource utilities.
 * 
 * Some code was adopted from StackOverflow (by user Bostone, real name probably
 * Robert Stone): How to make a JAR file that includes DLL files?
 * <br>https://stackoverflow.com/questions/1611357/how-to-make-a-jar-file-that-includes-dll-files
 * 
 * FileUtils source code:
 * <br>https://github.com/apache/commons-io/blob/master/src/main/java/org/apache/commons/io/FileUtils.java
 * 
 * @author  Josef Plch, Robert Stone
 * @since   2018-02-28
 * @version 2020-10-12
 */
public abstract class ResourceUtils {
    // private static final ClassLoader CLASS_LOADER = ClassLoader.getSystemClassLoader ();
    private static final ClassLoader CLASS_LOADER = ResourceUtils.class.getClassLoader ();
    private static final String CLASS_NAME = ResourceUtils.class.getSimpleName ();
    private static final Logger LOGGER = Logger.getLogger (CLASS_NAME);
    private static final String NAME_DELIMITER = "_";
    
    /**
     * Copy the resource file into the selected directory. All directories in
     * the resource path will be created if they don't exist.
     * 
     * This method works even inside a JAR/WAR archive.
     * 
     * @param resourceFilePath       Relative file path of the resource.
     * @param targetDirectory        The directory to create the copy in.
     * @return                       The created file copy.
     * @throws FileNotFoundException If the resource is not found.
     * @throws IOException           If the resource cannot be copied.
     */
    public static File createResourceCopy (
        String resourceFilePath,
        File targetDirectory
    ) throws FileNotFoundException, IOException {
        return createResourceCopy (resourceFilePath, targetDirectory, false);
    }
    
    /**
     * Copy the resource file into the selected directory. All directories in
     * the resource path will be created if they don't exist.
     * 
     * This method works even inside a JAR/WAR archive.
     * 
     * @param resourceFilePath       Relative file path of the resource.
     * @param targetDirectory        The directory to create the copy in.
     * @param timeInCopyName         Add the current time to the copy file name.
     * @return                       The created file copy.
     * @throws FileNotFoundException If the resource is not found.
     * @throws IOException           If the resource cannot be copied.
     */
    public static File createResourceCopy (
        String resourceFilePath,
        File targetDirectory,
        Boolean timeInCopyName
    ) throws FileNotFoundException, IOException {
        InputStream originalResourceStream = readStream (resourceFilePath);
        
        // Split the resource file path into directory and file name.
        // "images/shapes/circle.png" -> ("images/shapes", "circle.png")
        String resourceDirectory;
        String resourceFileName;
        int lastSlashIndex = resourceFilePath.lastIndexOf ("/");
        if (lastSlashIndex == -1) {
            resourceDirectory = "";
            resourceFileName = resourceFilePath;
        }
        else {
            resourceDirectory = resourceFilePath.substring (0, lastSlashIndex);
            resourceFileName = resourceFilePath.substring (lastSlashIndex + 1);
        }
        
        // Define file path of library copy.
        String copyFileName;
        if (timeInCopyName) {
            Long currentTime = Calendar.getInstance ().getTimeInMillis ();
            copyFileName = currentTime + NAME_DELIMITER + resourceFileName;
        }
        else {
            copyFileName = resourceFileName;
        }
        String resourceCopyPath;
        if (resourceDirectory.isEmpty ()) {
            resourceCopyPath = targetDirectory + "/" + copyFileName;
        }
        else {
            resourceCopyPath = targetDirectory + "/" + resourceDirectory + "/" + copyFileName;
        }
        File resourceCopy = new File (resourceCopyPath);
        
        // Create all the necessary directory hierarchy.
        resourceCopy.getParentFile ().mkdirs ();
        
        // Copy the file.
        OutputStream resourceCopyStream = new FileOutputStream (resourceCopy);
        IoUtils.copy (originalResourceStream, resourceCopyStream);
        resourceCopyStream.close ();
        
        originalResourceStream.close ();
        
        return resourceCopy;
    }
    
    /**
     * Delete all file copies (both with and without time prefix) in selected
     * directory.
     * 
     * @param fileName        The name of the original file.
     * @param targetDirectory The directory to delete the copies from.
     */
    public static void deleteTempCopies (String fileName, File targetDirectory) {
        // Get list of file copies.
        File [] listOfFileCopies =
            targetDirectory.listFiles (
                file -> file.getName ().matches (
                    "^([0-9]+" + NAME_DELIMITER + ")?" + fileName + "$"
                )
            );
        
        if (listOfFileCopies.length == 0) {
            LOGGER.log (Level.FINE, CLASS_NAME + ".deleteTempCopies: no copies found");
        }
        else {
            // Delete the copies one by one.
            for (File fileCopy : listOfFileCopies) {
                try {
                    fileCopy.delete ();
                    LOGGER.log (Level.FINE, CLASS_NAME + ".deleteTempCopies: copy deleted: " + fileCopy);
                }
                catch (SecurityException exception) {
                    // Try it next time (it may be in use now).
                    LOGGER.log (Level.FINE, CLASS_NAME + ".deleteTempCopies: copy could not be deleted: " + fileCopy);
                }
            }
        }
    }
    
    // Read text resource by lines.
    public static Stream <String> readLines (String relativePath) throws FileNotFoundException {
        return (
            new BufferedReader (
                new InputStreamReader (ResourceUtils.readStream (relativePath))
            )
            .lines ()
        );
    }
    
    // Open an input stream for the original file (it may be packed in some
    // archive like JAR or WAR).
    public static InputStream readStream (String relativePath) throws FileNotFoundException {
        InputStream stream = CLASS_LOADER.getResourceAsStream (relativePath);
        if (Objects.isNull (stream)) {
            throw new FileNotFoundException (
                "Resource not found: " + relativePath + "."
                + " Check it is present in the resource directory."
            );
        }
        else {
            return stream;
        }
    }
}
