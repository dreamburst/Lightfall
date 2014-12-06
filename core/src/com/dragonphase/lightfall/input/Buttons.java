/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import java.util.HashMap;

public enum Buttons implements InputType {
    A(0),
    B(1),
    X(2),
    Y(3),
    LB(4),
    RB(5),
    Back(6),
    Start(7),
    L3(8),
    R3(9),
    UP(10),
    DOWN(11),
    LEFT(12),
    RIGHT(13);

    private static final HashMap<Integer, Buttons> VALUES = new HashMap<>();

    static {
        for (Buttons control : Buttons.values()) {
            VALUES.put(control.getCode(), control);
        }
    }

    private int code;

    private Buttons(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Buttons match(int value) {
        return VALUES.get(value);
    }

    public static Buttons match(String value) {
        return Buttons.valueOf(value.toUpperCase());
    }
}
