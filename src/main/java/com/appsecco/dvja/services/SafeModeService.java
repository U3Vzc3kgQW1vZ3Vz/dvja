package com.appsecco.dvja.services;

public class SafeModeService {
    private static boolean safe = false;

    public static boolean isSafe() {
        return safe;
    }

    public static void setSafe(boolean safe) {
        SafeModeService.safe = safe;
    }
}
