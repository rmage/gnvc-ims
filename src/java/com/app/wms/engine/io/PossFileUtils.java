/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.io;

/**
 *
 * @author programmer
 */

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PossFileUtils {
    
    

    public static File writeToFile(InputStream is) throws IOException {
        File file = File.createTempFile("temp", null);
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            is.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Error Writing/Reading Streams.");
        }
        return file;
    }

    public static File writeToFile(InputStream is, String outpath) throws IOException {
        File file = new File(outpath);
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            is.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Error Writing/Reading Streams.");
        }
        return file;
    }

    public static File copyFileToFile(File input, String outputPath, String exportName) {
        File file = new File(outputPath, exportName);
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            // Transfer bytes from in to out
            FileInputStream is = new FileInputStream(input);
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            is.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error Writing/Reading Streams.");
        }
        return file;
    }

    public static File copyStreamToFile(InputStream input, String outputPath) {
        File file = new File(outputPath);
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            // Transfer bytes from in to out
            InputStream is = input;
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            is.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Error Writing/Reading Streams.");
        }
        return file;
    }

    /**
     * Broken method
     * @param resources
     * @return directory listing of path
     * @throws IOException
     *
    public static String[] listTemplateFiles(InputStream path) throws IOException {
    File dir = writeToFile(path);
    System.out.println("context path=" + dir.getPath());
    String[] children = dir.list();


    if (children == null) {
    // Either dir does not exist or is not a directory
    } else {
    for (int i = 0; i < children.length; i++) {
    // Get filename of file or directory
    String filename = children[i];
    }
    }


    // It is also possible to filter the list of returned files.
    // This example does not return any files that start with `.'.
    FilenameFilter filter = new FilenameFilter() {

    public boolean accept(File dir, String name) {
    return !name.startsWith(".") && name.endsWith(".jrxml");
    }
    };
    children = dir.list(filter);

    /*
    // The list of files can also be retrieved as File objects
    File[] files = dir.listFiles();

    // This filter only returns directories
    FileFilter fileFilter = new FileFilter() {

    public boolean accept(File file) {
    return file.isDirectory();
    }
    };
    files = dir.listFiles(fileFilter);
     *

    return discardFilesExtension(children);
    }*/
    public static List<String> listTemplateFiles(Set<String> resources) {
        ArrayList<String> children = new ArrayList();
        Iterator it = resources.iterator();
        int i = 0;
        String tf;
        //System.out.println("set size=" + resources.size());
        while (it.hasNext()) {
            tf = it.next().toString();
            //only process jrxml

            //System.out.println(tf);
            if (getFileExtensionAsString(tf).equalsIgnoreCase("jrxml")) {
                children.add(displayFilenameOnly(tf));
                System.out.println("template:" + children.get(i));
                i++;
            }
        }
        return children;
    }

    public static List<String> listImageFiles(Set<String> resources) {
        ArrayList<String> children = new ArrayList<String>();
        Iterator it = resources.iterator();
        int i = 0;
        String tf;
        String ext;
        //System.out.println("set size=" + resources.size());
        while (it.hasNext()) {
            tf = it.next().toString();
            //only process jrxml

            //System.out.println(tf);
            ext = getFileExtensionAsString(tf);
            if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg")
                    || ext.equalsIgnoreCase("bmp") || ext.equalsIgnoreCase("gif")
                    || ext.equalsIgnoreCase("png")) {
                children.add(displayFilename(tf));
                //System.out.println("image: " + children.get(i));
                i++;
            }
        }
        return children;
    }

    /**
     * sample codes taken from http://www.exampledepot.com/egs/java.io/GetFiles.html
     * @param path
     * @return listing files of directory
     */
    public static String[] listFiles(String path) {
        File dir = new File(path);
        System.out.println("checking " + path + " exist: " + dir.exists());
        String[] children = dir.list();
        /*
        if (children == null) {
        // Either dir does not exist or is not a directory
        } else {
        for (int i = 0; i < children.length; i++) {
        // Get filename of file or directory
        String filename = children[i];
        }
        }
         * */

        // It is also possible to filter the list of returned files.
        // This example does not return any files that start with `.'.
        FilenameFilter filter = new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return !name.startsWith(".") && name.endsWith(".jrxml");
            }
        };
        children = dir.list(filter);

        /*
        // The list of files can also be retrieved as File objects
        File[] files = dir.listFiles();

        // This filter only returns directories
        FileFilter fileFilter = new FileFilter() {

        public boolean accept(File file) {
        return file.isDirectory();
        }
        };
        files = dir.listFiles(fileFilter);
         *
         */
        return discardFilesExtension(children);
    }

    /**
     * sample codes taken from http://www.exampledepot.com/egs/java.io/GetFiles.html
     * @param path
     * @return listing files of directory
     */
    public static String[] listFiles(File path) {
        File dir = path;

        String[] children = dir.list();
        if (children == null) {
            // Either dir does not exist or is not a directory
        } else {
            for (int i = 0; i < children.length; i++) {
                // Get filename of file or directory
                String filename = children[i];
            }
        }

        // It is also possible to filter the list of returned files.
        // This example does not return any files that start with `.'.
        FilenameFilter filter = new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return !name.startsWith(".") && name.endsWith(".jrxml");
            }
        };
        children = dir.list(filter);

        /*
        // The list of files can also be retrieved as File objects
        File[] files = dir.listFiles();

        // This filter only returns directories
        FileFilter fileFilter = new FileFilter() {

        public boolean accept(File file) {
        return file.isDirectory();
        }
        };
        files = dir.listFiles(fileFilter);
         *
         */
        return discardFilesExtension(children);
    }

    /**
     *
     * @param file filepath
     * @return filename without extension but directory path still intact
     */
    public static String discardFileExtension(String file) {
        int dotPos = file.indexOf(".");
        if (dotPos == -1) {
            return file;
        }
        return file.substring(0, dotPos);
    }

    /**
     * Remove the leading and trailing quotes from <code>str</code>.
     * E.g. if str is '"one two"', then 'one two' is returned.
     *
     * @param str The string from which the leading and trailing quotes
     * should be removed.
     *
     * @return The string without the leading and trailing quotes.
     */
    public static String stripLeadingAndTrailingQuotes(String str) {
        if (str.startsWith("\"")) {
            str = str.substring(1, str.length());
        }
        if (str.endsWith("\"")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     *
     * @param file path regardless with directory path or not, at this point only recognize unix format
     * @return filename without extension clean
     */
    public static String displayFilenameOnly(String file) {
        int lastSlash = file.lastIndexOf("/");
        if (lastSlash == -1) {
            return discardFileExtension(file);
        }
        //System.out.println("length="+file.length()+", index="+lastSlash);
        return discardFileExtension(file.substring(lastSlash + 1));
    }

    public static String displayFilename(String file) {
        int lastSlash = file.lastIndexOf("/");
        if (lastSlash == -1) {
            return file;
        }
        //System.out.println("length="+file.length()+", index="+lastSlash);
        return file.substring(lastSlash + 1);
    }

    public static String[] discardFilesExtension(String[] files) {
        for (int i = 0; i < files.length; i++) {
            files[i] = discardFileExtension(files[i]);
        }
        return files;
    }

    public static FileExtension getFileExtension(String file) {
        int dotPos = file.indexOf(".");
        if (dotPos == -1) {
            System.out.println("couldn't found any extension");
        }
        
        System.out.println(">>>>>>> "+file.substring(dotPos + 1).toUpperCase());
        System.out.println(">>>>>>> "+file.substring(file.length()-3).toUpperCase());
        
        return FileExtension.valueOf(file.substring(file.length()-3).toUpperCase());
    }

    public static String getFileExtensionAsString(String file) {
        int dotPos = file.indexOf(".");
        return file.substring(dotPos + 1);
    }

    public static boolean isFileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
    
}
