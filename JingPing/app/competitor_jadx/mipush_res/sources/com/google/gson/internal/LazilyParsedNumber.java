package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public final class LazilyParsedNumber extends Number {
    public final String E;

    public LazilyParsedNumber(String str) {
        this.E = str;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new BigDecimal(this.E);
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.parseDouble(this.E);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LazilyParsedNumber)) {
            return false;
        }
        Object obj2 = ((LazilyParsedNumber) obj).E;
        String str = this.E;
        return str == obj2 || str.equals(obj2);
    }

    @Override // java.lang.Number
    public float floatValue() {
        return Float.parseFloat(this.E);
    }

    public int hashCode() {
        return this.E.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        String str = this.E;
        try {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                return (int) Long.parseLong(str);
            }
        } catch (NumberFormatException unused2) {
            return new BigDecimal(str).intValue();
        }
    }

    @Override // java.lang.Number
    public long longValue() {
        String str = this.E;
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return new BigDecimal(str).longValue();
        }
    }

    public String toString() {
        return this.E;
    }
}
