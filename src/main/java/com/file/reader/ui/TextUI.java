package com.file.reader.ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.file.reader.logic.FileControl;

public class TextUI {
    //private FileReader reader;
    private Scanner scanner;

    public TextUI(Scanner scanner) {
        this.scanner = scanner;
    }

    // starts file
    public void start() {
        startAssetControl();
    }

    public void startAssetControl() {
        System.out.println("File Reader");
        System.out.println("-----------");
        
        String dPath = setDirectoryPath();
        String oPath = setOutputFile();
        String flag = setFlagSubdirectories();
        
        FileControl.iterateFolder(dPath, oPath, flag);    
    
        System.out.println("All tasks are done");
    }

    public String setDirectoryPath() {
        while (true) {
            System.out.println("Provide directory path: ");
            String directory = scanner.nextLine();

            if (directory == null || directory.length() <= 0) {
                System.out.println("Please input a directory path");
            } else if (checkPath(directory, "directory")){
                return directory;
            } else {
                System.out.println("Please input a directory path");
            }
        }
    }

    public String setOutputFile() {
        while (true) {
            System.out.println("Provide CSV file output path: ");
            String outputFile = scanner.nextLine();

            if (outputFile == null || outputFile.length() <= 0) {
                System.out.println("Please input the CSV file output path");
                System.out.println("Path must contain file name and extension");
            } else if (checkFile(outputFile, "file")) {
                return outputFile;
            } else {
                System.out.println("Please input the CSV file output path");
                System.out.println("Path must contain file name and extension");
            }
        }
    }

    public String setFlagSubdirectories() {
        while (true) {
            System.out.println("Do you want to include subdirectories? Y/N");
            String flag = scanner.nextLine();
            if (flag.equals("Y") || flag.equals("y") || flag.equals("N") || flag.equals("n")) {
                return flag;
            } else {
                System.out.println("Please type Y for Yes or N for No");
            }
        }
    }

    // checks path and type validity 
    public Boolean checkPath(String directoryPath, String type) {
        File directory = new File(directoryPath);

        if (directory.isDirectory() && type == "directory") {
            return true;
        } else {
            System.out.println("Path does not exists");
        }

        return false;
    }

    public Boolean checkFile(String directoryPath, String type) {
        try {
            File directory = new File(directoryPath);
            if (directory.createNewFile()) {
                System.out.println("File created: " + directory.getName());
                return true;
            } else {
                System.out.println("File already exists.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("");
            e.printStackTrace();
        }

        return false;
        
    }

}
