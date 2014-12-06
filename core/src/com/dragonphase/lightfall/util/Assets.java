/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

import java.awt.*;

public class Assets {

    public static final Size SCREEN_SIZE = new Size((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4);

    public static final float CONTROLLER_DEADZONE = 0.3f;

    public static final int[] MAP_BACKGROUND_LAYERS = new int[]{0};
    public static final int[] MAP_BOTTOM_LAYERS = new int[]{1};
    public static final int[] MAP_TOP_LAYERS = new int[]{2};
    public static final int[] COLLISION_LAYERS = new int[]{3};

    public static final int TILE_SIZE = 32;

    private Assets() {
    }
}
