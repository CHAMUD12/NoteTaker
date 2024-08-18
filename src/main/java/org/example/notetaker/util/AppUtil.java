package org.example.notetaker.util;

import java.util.UUID;

public class AppUtil {
    public static String genarateid(){
        return "Note" + UUID.randomUUID();
    }
}
