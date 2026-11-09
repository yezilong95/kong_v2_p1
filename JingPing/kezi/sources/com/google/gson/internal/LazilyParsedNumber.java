package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public final class LazilyParsedNumber extends Number {
    public final String b;

    public LazilyParsedNumber(String str) {
        this.b = str;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new BigDecimal(this.b);
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.parseDouble(this.b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LazilyParsedNumber)) {
            return false;
        }
        Object obj2 = ((LazilyParsedNumber) obj).b;
        String str = this.b;
        return str == obj2 || str.equals(obj2);
    }

    @Override // java.lang.Number
    public float floatValue() {
        return Float.parseFloat(this.b);
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        String str = this.b;
        try {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                return new BigDecimal(str).intValue();
            }
        } catch (NumberFormatException unused2) {
            return (int) Long.parseLong(str);
        }
    }

    @Override // java.lang.Number
    public long longValue() {
        String str = this.b;
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return new BigDecimal(str).longValue();
        }
    }

    public String toString() {
        return this.b;
    }
}
