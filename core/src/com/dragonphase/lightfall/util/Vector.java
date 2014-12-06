/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

public class Vector<T extends Number> {

    private T x, y;

    public Vector(T x, T y) {
        set(x, y);
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return this.y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public void set(T x, T y) {
        setX(x);
        setY(y);
    }
}
