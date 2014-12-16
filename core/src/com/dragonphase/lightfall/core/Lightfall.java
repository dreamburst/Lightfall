/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
            public void end(SplashScreen screen) {
                screen.getScreenManager().removeScreen(SplashScreen.class);

                screenManager.addScreen(splashScreen2);
                screenManager.setActiveScreen(splashScreen2);
            }
        });

        splashScreen2.onEnd(new SplashScreenEndEvent() {
            @Override
            public void end(SplashScreen screen) {
                screen.getScreenManager().removeScreen(SplashScreen.class);

                showMainMenu();
            }
        });

        screenManager.addScreen(splashScreen);
        screenManager.setActiveScreen(splashScreen);
    }

    public void showMainMenu() {

    }

    @Override
    public void update(float delta) {
        screenManager.update(delta);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        screenManager.draw(spriteBatch, delta);
    }
}
