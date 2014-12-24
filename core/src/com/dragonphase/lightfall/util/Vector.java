/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Vector<T extends Number> {

    private Class<T> clazz;

    private T x, y;

    public Vector(T x, T y) {
        set(x, y);

        clazz = (Class<T>) x.getClass();
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
        if (clazz == Double.class) {
            set(
                    clazz.cast((getX().doubleValue() + x.doubleValue())),
                    clazz.cast((getY().doubleValue() + y.doubleValue()))
            );
        }
        if (clazz == Float.class) {
            set(
                    clazz.cast((getX().floatValue() + x.floatValue())),
                    clazz.cast((getY().floatValue() + y.floatValue()))
            );
        }
        if (clazz == Long.class) {
            set(
                    clazz.cast(getX().longValue() + x.longValue()),
                    clazz.cast(getY().longValue() + y.longValue())
            );
        }
        if (clazz == Integer.class) {
            set(
                    clazz.cast(getX().intValue() + x.intValue()),
                    clazz.cast(getY().intValue() + y.intValue())
            );
        }
        if (clazz == Short.class) {
            set(
                    clazz.cast(getX().shortValue() + x.shortValue()),
                    clazz.cast(getY().shortValue() + y.shortValue())
            );
        }
        if (clazz == Byte.class) {
            set(
                    clazz.cast(getX().byteValue() + x.byteValue()),
                    clazz.cast(getY().byteValue() + y.byteValue())
            );
        }
    }

    public void subtract(T x, T y) {
        if (clazz == Double.class) {
            set(
                    clazz.cast((getX().doubleValue() + x.doubleValue())),
                    clazz.cast((getY().doubleValue() + y.doubleValue()))
            );
        }
        if (clazz == Float.class) {
            set(
                    clazz.cast((getX().floatValue() - x.floatValue())),
                    clazz.cast((getY().floatValue() - y.floatValue()))
            );
        }
        if (clazz == Long.class) {
            set(
                    clazz.cast(getX().longValue() - x.longValue()),
                    clazz.cast(getY().longValue() - y.longValue())
            );
        }
        if (clazz == Integer.class) {
            set(
                    clazz.cast(getX().intValue() - x.intValue()),
                    clazz.cast(getY().intValue() - y.intValue())
            );
        }
        if (clazz == Short.class) {
            set(
                    clazz.cast(getX().shortValue() - x.shortValue()),
                    clazz.cast(getY().shortValue() - y.shortValue())
            );
        }
        if (clazz == Byte.class) {
            set(
                    clazz.cast(getX().byteValue() - x.byteValue()),
                    clazz.cast(getY().byteValue() - y.byteValue())
            );
        }
    }

    public void multiply(T x, T y) {
        if (clazz == Double.class) {
            set(
                    clazz.cast((getX().doubleValue() * x.doubleValue())),
                    clazz.cast((getY().doubleValue() * y.doubleValue()))
            );
        }
        if (clazz == Float.class) {
            set(
                    clazz.cast((getX().floatValue() * x.floatValue())),
                    clazz.cast((getY().floatValue() * y.floatValue()))
            );
        }
        if (clazz == Long.class) {
            set(
                    clazz.cast(getX().longValue() * x.longValue()),
                    clazz.cast(getY().longValue() * y.longValue())
            );
        }
        if (clazz == Integer.class) {
            set(
                    clazz.cast(getX().intValue() * x.intValue()),
                    clazz.cast(getY().intValue() * y.intValue())
            );
        }
        if (clazz == Short.class) {
            set(
                    clazz.cast(getX().shortValue() * x.shortValue()),
                    clazz.cast(getY().shortValue() * y.shortValue())
            );
        }
        if (clazz == Byte.class) {
            set(
                    clazz.cast(getX().byteValue() * x.byteValue()),
                    clazz.cast(getY().byteValue() * y.byteValue())
            );
        }
    }

    public void divide(T x, T y) {
        if (clazz == Double.class) {
            set(
                    clazz.cast((getX().doubleValue() / x.doubleValue())),
                    clazz.cast((getY().doubleValue() / y.doubleValue()))
            );
        }
        if (clazz == Float.class) {
            set(
                    clazz.cast((getX().floatValue() / x.floatValue())),
                    clazz.cast((getY().floatValue() / y.floatValue()))
            );
        }
        if (clazz == Long.class) {
            set(
                    clazz.cast(getX().longValue() / x.longValue()),
                    clazz.cast(getY().longValue() / y.longValue())
            );
        }
        if (clazz == Integer.class) {
            set(
                    clazz.cast(getX().intValue() / x.intValue()),
                    clazz.cast(getY().intValue() / y.intValue())
            );
        }
        if (clazz == Short.class) {
            set(
                    clazz.cast(getX().shortValue() / x.shortValue()),
                    clazz.cast(getY().shortValue() / y.shortValue())
            );
        }
        if (clazz == Byte.class) {
            set(
                    clazz.cast(getX().byteValue() / x.byteValue()),
                    clazz.cast(getY().byteValue() / y.byteValue())
            );
        }
    }

    public String toString() {
        return "x: " + getX() + "    y: " + getY();
    }

    public static Type[] getParameterizedTypes(Object object) {
        Type superclassType = object.getClass().getGenericSuperclass();
        if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
            return null;
        }
        return ((ParameterizedType)superclassType).getActualTypeArguments();
    }
}
