/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

import java.math.BigDecimal;

public final class Utils {

    private Utils() {}

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
