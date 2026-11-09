package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class g7 extends xi {
    public static List W(Object[] objArr) {
        oy.j(objArr, "<this>");
        List listAsList = Arrays.asList(objArr);
        oy.i(listAsList, "asList(...)");
        return listAsList;
    }

    public static void X(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        oy.j(bArr, "<this>");
        oy.j(bArr2, "destination");
        System.arraycopy(bArr, i2, bArr2, i, i3 - i2);
    }

    public static void Y(Object[] objArr, Object[] objArr2, int i, int i2, int i3) {
        oy.j(objArr, "<this>");
        oy.j(objArr2, "destination");
        System.arraycopy(objArr, i2, objArr2, i, i3 - i2);
    }

    public static byte[] Z(byte[] bArr, int i, int i2) {
        oy.j(bArr, "<this>");
        xi.l(i2, bArr.length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i2);
        oy.i(bArrCopyOfRange, "copyOfRange(...)");
        return bArrCopyOfRange;
    }

    public static Object[] a0(Object[] objArr, int i, int i2) {
        oy.j(objArr, "<this>");
        xi.l(i2, objArr.length);
        Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr, i, i2);
        oy.i(objArrCopyOfRange, "copyOfRange(...)");
        return objArrCopyOfRange;
    }

    public static void b0(Object[] objArr, nm nmVar, int i, int i2) {
        oy.j(objArr, "<this>");
        Arrays.fill(objArr, i, i2, nmVar);
    }

    public static ArrayList d0(Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static char e0(char[] cArr) {
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }
}
