package com.jh.triplepoint.util;

import java.time.ZonedDateTime;

public class TimeUtil {
    public static ZonedDateTime nowWithSecond() {
        return ZonedDateTime.now().withNano(0);
    }
}
