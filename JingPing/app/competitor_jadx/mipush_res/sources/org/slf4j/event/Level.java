package org.slf4j.event;

import defpackage.zn;

/* JADX INFO: loaded from: classes.dex */
public enum Level {
    ERROR(40, "ERROR"),
    WARN(30, "WARN"),
    INFO(20, "INFO"),
    DEBUG(10, "DEBUG"),
    TRACE(0, "TRACE");

    public final int E;
    public final String F;

    Level(int i, String str) {
        this.E = i;
        this.F = str;
    }

    public static Level intToLevel(int i) {
        if (i == 0) {
            return TRACE;
        }
        if (i == 10) {
            return DEBUG;
        }
        if (i == 20) {
            return INFO;
        }
        if (i == 30) {
            return WARN;
        }
        if (i == 40) {
            return ERROR;
        }
        throw new IllegalArgumentException(zn.k(i, "Level integer [", "] not recognized."));
    }

    public int toInt() {
        return this.E;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.F;
    }
}
