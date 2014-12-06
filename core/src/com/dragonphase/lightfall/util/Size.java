/*
    Copyright (C) 2014 Cookie Smack || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

public class Size extends Vector<Integer> {

    public Size(int width, int height) {
        super(width, height);
    }

    public int getWidth() {
        return getX();
    }

    public void setWidth(int width) {
        setX(width);
    }

    public int getHeight() {
        return getY();
    }

    public void setHeight(int height) {
        setY(height);
    }
}
