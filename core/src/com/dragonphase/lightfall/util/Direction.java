/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

import java.util.HashMap;

/**
 * An ID is assigned to each Direction to identify its index value when retrieving animations from a TextureRegion[][]
 */
public enum Direction {
    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3),
    ;

    private static final HashMap<Integer, Direction> VALUES = new HashMap<>();

    static {
        for (Direction direction : Direction.values()) {
            VALUES.put(direction.getId(), direction);
        }
    }

    private int id;

    private Direction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Direction match(int value) {
        return VALUES.get(value);
    }

    public static Direction match(String value) {
        return Direction.valueOf(value.toUpperCase());
    }
}
