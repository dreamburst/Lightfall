/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import java.util.HashMap;

public enum Axis implements InputType {
    LY(0),
    LX(1),
    RY(2),
    RX(3),
    Trigger(4),
    L_UP(5),
    L_DOWN(6),
    L_LEFT(7),
    L_RIGHT(8),
    R_UP(9),
    R_DOWN(10),
    R_LEFT(11),
    R_RIGHT(12),
    LT(13),
    RT(14),;

    private static final HashMap<Integer, Axis> VALUES = new HashMap<>();

    static {
        for (Axis axis : Axis.values()) {
            VALUES.put(axis.getCode(), axis);
        }
    }

    private int code;

    private Axis(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Axis match(int value) {
        return VALUES.get(value);
    }
}
