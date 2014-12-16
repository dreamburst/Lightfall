/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import com.dragonphase.lightfall.input.type.MouseButtons;
import com.dragonphase.lightfall.util.Vector;

public class Mouse extends SequenceHandler<MouseButtons> {

    private Vector<Integer> position;

    public Mouse(int sequenceInterval) {
        super(sequenceInterval);

        position = new Vector<>(0, 0);
    }

    public Vector<Integer> getPosition() {
        return position;
    }
}
