/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonphase.lightfall.screen.GameplayScreen;
import com.dragonphase.lightfall.screen.ScreenManager;
import com.dragonphase.lightfall.screen.SplashScreen;
import com.dragonphase.lightfall.event.screen.SplashScreenEndEvent;

public class Lightfall implements LogicBase {

    private ScreenManager screenManager;

    public Lightfall() {
        screenManager = new ScreenManager();

        showSplashScreens();
    }

    public void showSplashScreens() {
        final SplashScreen splashScreen = new SplashScreen(screenManager, "splash1");
        final SplashScreen splashScreen2 = new SplashScreen(screenManager, "splash2");

        splashScreen.onEnd(new SplashScreenEndEvent() {
            @Override
            public void end() {
                splashScreen.getScreenManager().removeScreen(SplashScreen.class);

                getScreenManager().addScreen(splashScreen2);
                getScreenManager().setActiveScreen(splashScreen2);
            }
        });

        splashScreen2.onEnd(new SplashScreenEndEvent() {
            @Override
            public void end() {
                splashScreen2.getScreenManager().removeScreen(SplashScreen.class);

                showMainMenu();
            }
        });

        getScreenManager().addScreen(splashScreen);
        getScreenManager().setActiveScreen(splashScreen);
    }

    public void showMainMenu() {
        GameplayScreen screen = new GameplayScreen(screenManager);
        getScreenManager().addScreen(screen);
        getScreenManager().setActiveScreen(screen);
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }

    @Override
    public void update(float delta) {
        getScreenManager().update(delta);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        getScreenManager().draw(spriteBatch, delta);
    }
}
