package org.slf4j.event;

import defpackage.hk;

/* JADX INFO: loaded from: classes.dex */
public enum Level {
    ERROR("ERROR", "ERROR"),
    WARN("WARN", "WARN"),
    INFO("INFO", "INFO"),
    DEBUG("DEBUG", "DEBUG"),
    TRACE("TRACE", "TRACE");

    public final int b;
    public final String c;

    Level(String str, String str2) {
        this.b = i;
        this.c = str2;
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
        throw new IllegalArgumentException(hk.h(i, "Level integer [", "] not recognized."));
    }

    public int toInt() {
        return this.b;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.c;
    }
}
