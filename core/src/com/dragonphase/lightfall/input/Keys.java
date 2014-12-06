/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import java.util.HashMap;

public enum Keys implements InputType {
    ANY(-1),
    UP(19),
    DOWN(20),
    LEFT(21),
    RIGHT(22),
    NUM_0(7),
    NUM_1(8),
    NUM_2(9),
    NUM_3(10),
    NUM_4(11),
    NUM_5(12),
    NUM_6(13),
    NUM_7(14),
    NUM_8(15),
    NUM_9(16),
    NUMPAD_0(144),
    NUMPAD_1(145),
    NUMPAD_2(146),
    NUMPAD_3(147),
    NUMPAD_4(148),
    NUMPAD_5(149),
    NUMPAD_6(150),
    NUMPAD_7(151),
    NUMPAD_8(152),
    NUMPAD_9(153),
    F1(244),
    F2(245),
    F3(246),
    F4(247),
    F5(248),
    F6(249),
    F7(250),
    F8(251),
    F9(252),
    F10(253),
    F11(254),
    F12(255),
    A(29),
    B(30),
    C(31),
    D(32),
    E(33),
    F(34),
    G(35),
    H(36),
    I(37),
    J(38),
    K(39),
    L(40),
    M(41),
    N(42),
    O(43),
    P(44),
    Q(45),
    R(46),
    S(47),
    T(48),
    U(49),
    V(50),
    W(51),
    X(52),
    Y(53),
    Z(54),
    MULTIPLY(17),
    MINUS(69),
    PLUS(81),
    NUMLOCK(78),
    PERIOD(56),
    COMMA(55),
    SPACE(62),
    SHIFT_LEFT(59),
    SHIFT_RIGHT(60),
    BACKSLASH(73),
    SLASH(76),
    BACKSPACE(67),
    ENTER(66),
    TAB(61),
    SEMICOLON(74),
    APOSTROPHE(68),
    DASH(69),
    EQUALS(70),
    HASH(75),
    LBRACKET(71),
    RBRACKET(72),
    GRAVE(68),
    LCONTROL(129),
    WIN(63),
    LALT(57),
    RALT(58),
    RCONTROL(130),
    INSERT(133),
    HOME(3),
    PGUP(92),
    DELETE(112),
    END(132),
    PGDN(93),
    ESCAPE(131)
    ;

    private static final HashMap<Integer, Keys> VALUES = new HashMap<>();

    static {
        for (Keys key : Keys.values()) {
            VALUES.put(key.getCode(), key);
        }
    }

    private int code;

    private Keys(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Keys match(int value) {
        return VALUES.get(value);
    }

    public static Keys match(String value) {
        return Keys.valueOf(value.toUpperCase());
    }
}
