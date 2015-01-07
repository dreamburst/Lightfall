/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.world;

import com.badlogic.gdx.math.Rectangle;
import com.dragonphase.lightfall.util.Display;
import com.dragonphase.lightfall.util.Size;
import com.dragonphase.lightfall.util.Vector;

public class Area {

    private Rectangle bounds;

    public Area(Vector<Integer> position, Size size) {
        this(new Rectangle(position.getX(), position.getY(), size.getWidth(), size.getHeight()));
    }

    public Area(Rectangle bounds) {
        setBounds(bounds);
    }

    public Area() {
        this(new Rectangle(0, 0, 0, 0));
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
    
    public void focus() {
        Display.VIEWPORT.setBounds(getBounds());
    }
}
