package org.example.notetaker.util;

import java.time.LocalDateTime;
import java.util.UUID;

public class AppUtil {
    public static String createNoteId(){
        return "NOTE-" + UUID.randomUUID();
    }
}
