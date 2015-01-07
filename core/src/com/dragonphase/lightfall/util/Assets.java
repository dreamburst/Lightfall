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
import com.dragonphase.lightfall.input.Input;
import com.dragonphase.lightfall.input.InputManager;
import com.dragonphase.lightfall.input.Keyboard;
import com.dragonphase.lightfall.input.control.ControlMap;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.input.type.Axis;
import com.dragonphase.lightfall.input.type.Buttons;
import com.dragonphase.lightfall.input.type.Keys;
import com.dragonphase.lightfall.input.type.MouseButtons;

import java.util.EnumMap;
import java.util.Map;

public final class Assets {

    // RENDERING:
    public static final TextureAtlas SPRITES = new TextureAtlas(Gdx.files.internal("textures/pack/entities.atlas"));
    public static final TextureAtlas SCREENS = new TextureAtlas(Gdx.files.internal("textures/pack/screens.atlas"));

    public static final float ANIMATION_FRAME_DURATION = 1/15f;

    // INPUT:
    public static final ControlMap DEFAULT_CONTROLS = new ControlMap();
    static {
        DEFAULT_CONTROLS.setControl(Controls.MOVE_UP, Keys.W, Axis.L_UP);
        DEFAULT_CONTROLS.setControl(Controls.MOVE_DOWN, Keys.S, Axis.L_DOWN);
        DEFAULT_CONTROLS.setControl(Controls.MOVE_LEFT, Keys.A, Axis.L_LEFT);
        DEFAULT_CONTROLS.setControl(Controls.MOVE_RIGHT, Keys.D, Axis.L_RIGHT);
        DEFAULT_CONTROLS.setControl(Controls.SPRINT, Keys.SPACE, Buttons.L3);
        DEFAULT_CONTROLS.setControl(Controls.ATTACK, MouseButtons.LEFT, Axis.RT);
        DEFAULT_CONTROLS.setControl(Controls.INTERACT, Keys.E, Buttons.A);
        DEFAULT_CONTROLS.setControl(Controls.USE_ITEM, MouseButtons.RIGHT, Buttons.RB);
        DEFAULT_CONTROLS.setControl(Controls.OPEN_INVENTORY, Keys.Q, Buttons.Y);
        DEFAULT_CONTROLS.setControl(Controls.OPEN_MAP, Keys.C, Buttons.BACK);
        DEFAULT_CONTROLS.setControl(Controls.PAUSE, Keys.ESCAPE, Buttons.START);
    }

    //public static final String[] CONTROLLER_NAMES = new String[] {"xbox", "playstation"};

    // PLAYER DEFAULTS:

    public static final Vector<Float> PLAYER_SPEED = new Vector<>(1f, 1f);

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
