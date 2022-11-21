package com.file.reader.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;

public class HashFunction {
    private byte[] inputBytes;
    private String algorithm;

    public HashFunction(byte[] inputBytes, String algorithm) {
        this.inputBytes = inputBytes;
        this.algorithm = algorithm ;
    }
    
    public String getHash() {
        String hashValue = "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(this.algorithm);
            md.update(this.inputBytes);
            byte[] digestedBytes = md.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Algorithm desired was not found");
            e.printStackTrace();
        }

        return hashValue;
    }
}
