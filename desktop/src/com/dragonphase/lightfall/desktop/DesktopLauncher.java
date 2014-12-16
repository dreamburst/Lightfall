package com.dragonphase.lightfall.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.dragonphase.lightfall.core.Game;
import com.dragonphase.lightfall.util.Assets;
import com.dragonphase.lightfall.util.Display;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.combineSubdirectories = true;
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;
        settings.fast = true;

        TexturePacker.process(settings, "textures/entities", "textures/pack", "sprites");
        TexturePacker.process(settings, "textures/screens", "textures/pack", "screens");

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "Lightfall";
        config.width = Display.VIEWPORT_SIZE.getWidth();
        config.height = Display.VIEWPORT_SIZE.getHeight();
        config.resizable = true;
        config.fullscreen = false;

        config.vSyncEnabled = true;

        new LwjglApplication(new Game(), config);
	}
}
