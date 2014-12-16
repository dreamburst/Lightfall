/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonphase.lightfall.input.Gamepad;
import com.dragonphase.lightfall.input.InputManager;
import com.dragonphase.lightfall.input.Keyboard;
import com.dragonphase.lightfall.input.control.ControlMap;

public final class Assets {

    // RENDERING:
    public static final TextureAtlas SPRITES = new TextureAtlas(Gdx.files.internal("textures/pack/sprites.atlas"));
    public static final TextureAtlas SCREENS = new TextureAtlas(Gdx.files.internal("textures/pack/screens.atlas"));

    // INPUT:
    public static final int SEQUENCE_DELAY = 128;
    public static final float CONTROLLER_DEADZONE = 0.3f;

    public static final ControlMap CONTROLS = new ControlMap();
    public static final InputManager INPUT = new InputManager(
            new Keyboard(SEQUENCE_DELAY),
            new Gamepad(SEQUENCE_DELAY)
    );

    private Assets() {}

    public static TextureRegion getTexture(String ref) {
        return SPRITES.findRegion(ref);
    }

    public static TextureRegion getScreen(String ref) {
        return SCREENS.findRegion(ref);
    }
}
