/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

import java.awt.*;

public final class Display {

    //public static final Size VIEWPORT_SIZE = new Size(480, 270);
    public static final Size VIEWPORT_SIZE = new Size((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4);

    public static ScreenViewport VIEWPORT;

    private Display() {}
}
