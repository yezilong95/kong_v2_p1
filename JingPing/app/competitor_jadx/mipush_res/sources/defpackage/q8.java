package defpackage;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class q8 extends qm {
    public static void O0(Object[] objArr, Object[] objArr2, int i, int i2, int i3) {
        t40.n(objArr, "<this>");
        t40.n(objArr2, "destination");
        System.arraycopy(objArr, i2, objArr2, i, i3 - i2);
    }

    public static Object[] P0(Object[] objArr, int i, int i2) {
        t40.n(objArr, "<this>");
        int length = objArr.length;
        if (i2 <= length) {
            Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr, i, i2);
            t40.m(objArrCopyOfRange, "copyOfRange(...)");
            return objArrCopyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i2 + ") is greater than size (" + length + ").");
    }

    public static final void Q0(Object[] objArr, wy0 wy0Var, int i, int i2) {
        t40.n(objArr, "<this>");
        Arrays.fill(objArr, i, i2, wy0Var);
    }
}
