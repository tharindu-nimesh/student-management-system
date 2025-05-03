package com.sms.util;

public class PasswordUtil {
    // No hashing, passwords will be stored as plain text
    public static String hashPassword(String plainPassword) {
        return plainPassword; // Return plain text as-is
    }

    public static boolean verifyPassword(String plainPassword, String storedPassword) {
        // Direct string comparison for plain text passwords
        return plainPassword != null && plainPassword.equals(storedPassword);
    }
}
