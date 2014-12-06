package com.dragonphase.lightfall.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.dragonphase.lightfall.core.Game;
import com.dragonphase.lightfall.util.Assets;
import com.dragonphase.lightfall.util.Size;

public class DesktopLauncher {
	public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        Size screenSize = Assets.SCREEN_SIZE;

        config.title = "Lightfall";
        config.width = screenSize.getWidth();
        config.height = screenSize.getHeight();
        config.resizable = false;
        config.fullscreen = false;

        config.vSyncEnabled = true;

        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;
        settings.fast = true;

        TexturePacker.process(settings, "textures/entities", "textures/pack", "sprites");

        new LwjglApplication(new Game(), config);
	}
}
