/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

public class Vector<T extends Number> {

    private T x, y;

    public Vector(T x, T y) {
        set(x, y);
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public void set(T x, T y) {
        setX(x);
        setY(y);
    }

    public void add(Vector<T> vector) {
        add(vector.getX(), vector.getY());
    }

    public void subtract(Vector<T> vector) {
        subtract(vector.getX(), vector.getY());
    }

    public void multiply(Vector<T> vector) {
        multiply(vector.getX(), vector.getY());
    }

    public void divide(Vector<T> vector) {
        divide(vector.getX(), vector.getY());
    }

    public void add(T value) {
        add(value, value);
    }

    public void subtract(T value) {
        subtract(value, value);
    }

    public void multiply(T value) {
        multiply(value, value);
    }

    public void divide(T value) {
        divide(value, value);
    }

    public void add(T x, T y) {
        set(
                (T) (Number)(getX().doubleValue() + x.doubleValue()),
                (T) (Number)(getY().doubleValue() + y.doubleValue())
        );
    }

    public void subtract(T x, T y) {
        set(
                (T) (Number)(getX().doubleValue() - x.doubleValue()),
                (T) (Number)(getY().doubleValue() - y.doubleValue())
        );
    }

    public void multiply(T x, T y) {
        set(
                (T) (Number)(getX().doubleValue() * x.doubleValue()),
                (T) (Number)(getY().doubleValue() * y.doubleValue())
        );
    }

    public void divide(T x, T y) {
        set(
                (T) (Number)(getX().doubleValue() / x.doubleValue()),
                (T) (Number)(getY().doubleValue() / y.doubleValue())
        );
    }

    public String toString() {
        return "x: " + getX() + "    y: " + getY();
    }
}
