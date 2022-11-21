package com.file.reader;

import java.util.Scanner;

import com.file.reader.ui.TextUI;

public final class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        TextUI newUI  = new TextUI(scanner);
        
        newUI.start();
        
        /* 
        Testing Variables used
        String folderPath = "/Users/ebarcenas/Downloads";
        String outputPath = "/Users/ebarcenas/outputFolder/fileTestcopy16.csv";
        String flag = "y";
        */
    }
}
