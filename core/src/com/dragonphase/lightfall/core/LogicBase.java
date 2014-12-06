/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface LogicBase {

    public void update(float delta);

    public void draw(SpriteBatch spriteBatch, float delta);
}
