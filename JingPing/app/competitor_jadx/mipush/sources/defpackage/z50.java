package defpackage;

import com.google.gson.internal.LazilyParsedNumber;
import java.io.Serializable;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class z50 extends w50 {
    public static final Class[] F = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    public Serializable E;

    public z50(Number number) {
        d(number);
    }

    public static boolean c(z50 z50Var) {
        Serializable serializable = z50Var.E;
        if (!(serializable instanceof Number)) {
            return false;
        }
        Number number = (Number) serializable;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public final Number a() {
        Serializable serializable = this.E;
        return serializable instanceof String ? new LazilyParsedNumber((String) serializable) : (Number) serializable;
    }

    public final String b() {
        Serializable serializable = this.E;
        return serializable instanceof Number ? a().toString() : serializable instanceof Boolean ? ((Boolean) serializable).toString() : (String) serializable;
    }

    public final void d(Serializable serializable) {
        boolean z;
        if (serializable instanceof Character) {
            this.E = String.valueOf(((Character) serializable).charValue());
            return;
        }
        if ((serializable instanceof Number) || (serializable instanceof String)) {
            z = true;
            break;
        }
        Class<?> cls = serializable.getClass();
        Class[] clsArr = F;
        z = false;
        for (int i = 0; i < 16; i++) {
            if (clsArr[i].isAssignableFrom(cls)) {
                z = true;
                break;
            }
        }
        j31.j(z);
        this.E = serializable;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || z50.class != obj.getClass()) {
            return false;
        }
        z50 z50Var = (z50) obj;
        if (this.E == null) {
            return z50Var.E == null;
        }
        if (c(this) && c(z50Var)) {
            return a().longValue() == z50Var.a().longValue();
        }
        Serializable serializable = this.E;
        if (!(serializable instanceof Number) || !(z50Var.E instanceof Number)) {
            return serializable.equals(z50Var.E);
        }
        double dDoubleValue = a().doubleValue();
        double dDoubleValue2 = z50Var.a().doubleValue();
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    public final int hashCode() {
        long jDoubleToLongBits;
        if (this.E == null) {
            return 31;
        }
        if (c(this)) {
            jDoubleToLongBits = a().longValue();
        } else {
            Serializable serializable = this.E;
            if (!(serializable instanceof Number)) {
                return serializable.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(a().doubleValue());
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }

    public z50(String str) {
        d(str);
    }
}
