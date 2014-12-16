/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input.type;

import java.util.HashMap;

public enum MouseButtons implements InputType {
    LEFT(0),
    RIGHT(1),
    MIDDLE(2),
    SIDE_1(3),
    SIDE_2(4),
    ;

    private static final HashMap<Integer, MouseButtons> VALUES = new HashMap<>();

    static {
        for (MouseButtons mouseButtons : MouseButtons.values()) {
            VALUES.put(mouseButtons.getCode(), mouseButtons);
        }
    }

    private int code;

    private MouseButtons(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MouseButtons match(int value) {
        return VALUES.get(value);
    }

    public static MouseButtons match(String value) {
        return MouseButtons.valueOf(value.toUpperCase());
    }
}
