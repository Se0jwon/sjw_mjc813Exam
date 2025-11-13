package com.mjc813.food_web.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class MyFileUtil {
    public static final String uploadDir = "D:/upload/food";

    public String getFileExt( String fullFileName ) {
        if ( fullFileName == null ) {
            return "";
        }
        int nLastIndex = fullFileName.lastIndexOf(".");
        if ( nLastIndex <= 0 ) {
            return "";
        }
        String ext = fullFileName.substring(nLastIndex + 1);
        return ext.toLowerCase();
    }

    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        SecureRandom random = new SecureRandom(); // SecureRandom 인스턴스 생성

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }

    public byte[] getBytesFile(String readlFileName) throws IOException {
        byte[] bytes = null;
        Path path = Paths.get(uploadDir + "/" + readlFileName);
        bytes = Files.readAllBytes(path);
        return bytes;
    }
}
