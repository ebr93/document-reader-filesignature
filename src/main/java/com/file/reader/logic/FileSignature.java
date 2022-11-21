package com.file.reader.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileSignature {
    private static Map<String, int[]> Signature = new HashMap<String, int[]>(){{
        put("jpg", new int[] { 0xFF, 0xD8 });
        put("pdf", new int[] { 0x25, 0x50, 0x44, 0x46});
    }};

    // gets the file signature and returns validation through confirmSignature()
    public static String getFileType(InputStream is, byte[] data)  {
        try {
            is.read(data, 0, 4);
        } catch (IOException e) {
            System.out.println("Please enter a valid InputStream");
            e.printStackTrace();
        }

        for (String key  : Signature.keySet()) {
            if (confirmSignature(data, key)) {
                return key;
            }
        }
        return "unknown";
    }

    // checks file signature and returns validation through get()
    public static Boolean confirmSignature(byte[] fileData, String fileType) {
        int[] signatureBytes = Signature.get(fileType);

        for (int i = 0; i < signatureBytes.length; i++) {
            if (signatureBytes[i] != Byte.toUnsignedInt(fileData[i])) {
                return false;
            }
        }

        return true;
    } 
}

