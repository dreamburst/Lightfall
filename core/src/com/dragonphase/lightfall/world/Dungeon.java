/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonphase.lightfall.core.LogicBase;

import java.util.ArrayList;
import java.util.List;

public class Dungeon implements LogicBase {

    private List<Zone> zones;

    // TODO: Dungeon-wide properties

    public Dungeon() {
        zones = new ArrayList<>();
    }

    public List<Zone> getZones() {
        return zones;
    }

    @Override
    public void update(float delta) {
        for (Zone zone : getZones()) {
            zone.update(delta);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        for (Zone zone : getZones()) {
            zone.draw(spriteBatch, delta);
        }
    }
}
