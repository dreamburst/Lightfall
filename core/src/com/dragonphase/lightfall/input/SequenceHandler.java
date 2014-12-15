/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import java.util.LinkedList;

public class SequenceHandler<T extends InputType> extends InputHandler<T> {

    private LinkedList<T> activeSequence;

    private int sequenceInterval;
    private int sequenceTimer;

    public SequenceHandler(int sequenceInterval) {
        super();

        activeSequence = new LinkedList<>();

        this.sequenceInterval = sequenceInterval;
        sequenceTimer = 0;
    }

    public LinkedList<T> getActiveSequence() {
        return activeSequence;
    }

    public int getSequenceInterval() {
        return sequenceInterval;
    }

    public void setSequenceInterval(int sequenceInterval) {
        this.sequenceInterval = sequenceInterval;
    }

    public int getSequenceTimer() {
        return sequenceTimer;
    }

    public void setSequenceTimer(int sequenceTimer) {
        this.sequenceTimer = sequenceTimer;
    }

    public void updateSequenceInterval() {
        if (getActiveSequence().size() > 0) {
            if (getSequenceTimer() >= getSequenceInterval()) {
                getActiveSequence().clear();
                setSequenceTimer(0);
            } else {
                setSequenceTimer(getSequenceTimer()+1);
            }
        }
    }
}
