package com.file.reader.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class InputData {
    private static List<String[]> dataList = new ArrayList<String[]>();

    // prints data into Output File
    public static void writeData(File outputFile) {
        try { 
            // create FileWriter object with file as parameter 
            FileWriter oFile = new FileWriter(outputFile); 
            // create CSVWriter object filewriter object as parameter 
            CSVWriter writer = new CSVWriter(oFile); 

            String[] topString = {"Name", "Pathway", "File Type", "MD5"};
            writer.writeNext(topString);
            for (String[] data : dataList) {
                writer.writeNext(data);
            }
            writer.close();
        } 
        catch (IOException e) { 
            System.out.println("Output File was not found");
            e.printStackTrace(); 
        } 
    }

    // collects data of each file, adds them to dataList
    public static void dataCollector(File file, String fileType, byte[] allBytes) {
        HashFunction hashFunction = new HashFunction(allBytes, "MD5");
        String[] stringData = {file.getName(), file.getPath(), fileType, hashFunction.getHash()};
        dataList.add(stringData);
    }
}
