/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.screen;

import com.dragonphase.lightfall.core.LogicBase;

public abstract class Screen implements LogicBase {

    private ScreenManager screenManager;

    private boolean visible;

    public Screen(ScreenManager screenManager) {
        setScreenManager(screenManager);
        setVisible(true);
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
