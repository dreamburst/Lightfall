/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonphase.lightfall.core.LogicBase;

import java.util.LinkedHashSet;

public class ScreenManager implements LogicBase {

    private LinkedHashSet<Screen> screens;

    private Screen activeScreen;

    public ScreenManager() {
        screens = new LinkedHashSet<>();
    }

    public LinkedHashSet<Screen> getScreens() {
        return screens;
    }

    public <T extends Screen> T getScreen(Class<T> type) {
        for (Screen screen : getScreens()) {
            if (screen.getClass() == type) {
                return type.cast(screen);
            }
        }
        return null;
    }

    public void removeScreen(Screen screen) {
        getScreens().remove(screen);
    }

    public <T extends Screen> void removeScreen(Class<T> type) {
        getScreens().remove(getScreen(type));
    }

    public Screen getActiveScreen() {
        return activeScreen;
    }

    public <T extends Screen> T getActiveScreen(Class<T> type) {
        return getActiveScreen().getClass() == type ? type.cast(getActiveScreen()) : null;
    }

    public void setActiveScreen(Screen screen) {
        this.activeScreen = screen;
    }

    public void addScreen(Screen screen) {
        getScreens().add(screen);
        if (getActiveScreen() == null) {
            setActiveScreen(screen);
        }
    }

    public void update(float delta) {
        if (getActiveScreen() != null) {
            getActiveScreen().update(delta);
        }
    }

    public void draw(SpriteBatch spriteBatch, float delta) {
        for (Screen screen : getScreens()) {
            if (screen.isVisible()) {
                screen.draw(spriteBatch, delta);
            }
        }
    }
}
