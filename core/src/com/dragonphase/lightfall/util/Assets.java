/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonphase.lightfall.entity.EntityState;
import com.dragonphase.lightfall.input.Gamepad;
import com.dragonphase.lightfall.input.InputManager;
import com.dragonphase.lightfall.input.Keyboard;
import com.dragonphase.lightfall.input.control.ControlMap;

import java.util.EnumMap;
import java.util.Map;

public final class Assets {

    // RENDERING:
    public static final TextureAtlas SPRITES = new TextureAtlas(Gdx.files.internal("textures/pack/entities.atlas"));
    public static final TextureAtlas SCREENS = new TextureAtlas(Gdx.files.internal("textures/pack/screens.atlas"));

    public static final float ANIMATION_FRAME_DURATION = 1/15f;

    // INPUT:
    public static final int SEQUENCE_DELAY = 128;
    public static final float CONTROLLER_DEADZONE = 0.3f;

    public static final ControlMap CONTROLS = new ControlMap();
    public static final InputManager INPUT = new InputManager(
            new Keyboard(SEQUENCE_DELAY),
            new Gamepad(SEQUENCE_DELAY)
    );

    public static final String[] CONTROLLER_NAMES = new String[] {"xbox", "playstation"};

    private Assets() {}

    public static Map<EntityState, TextureAtlas.AtlasRegion> getSprite(String ref) {
        Map<EntityState, TextureAtlas.AtlasRegion> sprite = new EnumMap<>(EntityState.class);

        for (TextureAtlas.AtlasRegion region : SPRITES.getRegions()) {
            if (region.name.startsWith(ref + "/")) {
                sprite.put(EntityState.match(region.name.replace(ref + "/", "")), region);
            }
        }

        return sprite;
    }

    public static TextureAtlas.AtlasRegion getSpriteSheet(String ref) {
        return SPRITES.findRegion(ref);
    }

    public static TextureAtlas.AtlasRegion getScreen(String ref) {
        return SCREENS.findRegion(ref);
    }
}
