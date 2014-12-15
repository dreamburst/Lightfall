/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

public class Assets {

    public static final Size VIEWPORT_SIZE = new Size(480, 270);

    public static final TextureAtlas SPRITES = new TextureAtlas(Gdx.files.internal("textures/pack/sprites.atlas"));
    public static final TextureAtlas SCREENS = new TextureAtlas(Gdx.files.internal("textures/pack/screens.atlas"));

    public static final float CONTROLLER_DEADZONE = 0.3f;

    private Assets() {}

    public static TextureRegion getTexture(String ref) {
        return SPRITES.findRegion(ref);
    }

    public static TextureRegion getScreen(String ref) {
        return SCREENS.findRegion(ref);
    }
}
