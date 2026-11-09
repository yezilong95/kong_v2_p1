package defpackage;

import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class pz extends oz {
    public static final Class[] b = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    public Object a;

    public static boolean b(pz pzVar) {
        Object obj = pzVar.a;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public final Number a() {
        Object obj = this.a;
        return obj instanceof String ? new LazilyParsedNumber((String) obj) : (Number) obj;
    }

    public final void c(Object obj) {
        if (obj instanceof Character) {
            this.a = String.valueOf(((Character) obj).charValue());
            return;
        }
        if (!(obj instanceof Number) && !(obj instanceof String)) {
            Class<?> cls = obj.getClass();
            Class[] clsArr = b;
            for (int i = 0; i < 16; i++) {
                if (!clsArr[i].isAssignableFrom(cls)) {
                }
            }
            throw new IllegalArgumentException();
        }
        this.a = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || pz.class != obj.getClass()) {
            return false;
        }
        pz pzVar = (pz) obj;
        if (this.a == null) {
            return pzVar.a == null;
        }
        if (b(this) && b(pzVar)) {
            return a().longValue() == pzVar.a().longValue();
        }
        Object obj2 = this.a;
        if (!(obj2 instanceof Number) || !(pzVar.a instanceof Number)) {
            return obj2.equals(pzVar.a);
        }
        double dDoubleValue = a().doubleValue();
        double dDoubleValue2 = pzVar.a().doubleValue();
        return dDoubleValue == dDoubleValue2 || (Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2));
    }

    public final int hashCode() {
        long jDoubleToLongBits;
        if (this.a == null) {
            return 31;
        }
        if (b(this)) {
            jDoubleToLongBits = a().longValue();
        } else {
            Object obj = this.a;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(a().doubleValue());
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }
}
