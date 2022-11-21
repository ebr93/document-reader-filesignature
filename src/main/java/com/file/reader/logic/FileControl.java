package com.file.reader.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class FileControl {

    // iterates through folder and nested folders depending on flag value
    // makes use of recursion for nested folders
    public static void iterateFolder (String directoryPath, String outputPath, String flag) {
        File directory = new File(directoryPath);
        File destination = new File(outputPath);

        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                findFilesWithExtension(file, destination);
            } else {
                if (file.isDirectory() && flag.equals("Y") 
                    || file.isDirectory() && flag.equals("y") ) {
                    iterateFolder(file.getPath(), outputPath, flag);
                }
            }
        }
        InputData.writeData(destination); // writes data on output file after all iterations have finished
    }

    // determines which values are jpg or pdf
    // implements File Signature and InputData classes
    public static void findFilesWithExtension(File file, File destination)  {
        try (FileInputStream is = new FileInputStream(file)) {
            byte[] data = new byte[4];
            byte[] allBytes = Files.readAllBytes(file.toPath());
            String fileType = FileSignature.getFileType(is, data);
            is.close();
            System.out.println(fileType + " " + file.getName()); // leaving this just to see file signature results
            if (fileType.equals("jpg")) {
                InputData.dataCollector(file, fileType, allBytes);     
            } else if (fileType.equals("pdf")) {
                InputData.dataCollector(file, fileType, allBytes); 
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("FileInputStream was not created. File was null or not valid");
            e.printStackTrace();
        }

    }
}
