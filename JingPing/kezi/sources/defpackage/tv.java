package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class tv implements Iterable, wz {
    public final String[] b;

    public tv(String[] strArr) {
        this.b = strArr;
    }

    public final String a(String str) {
        oy.j(str, "name");
        String[] strArr = this.b;
        int length = strArr.length - 2;
        int iA = xi.A(length, 0, -2);
        if (iA > length) {
            return null;
        }
        while (!str.equalsIgnoreCase(strArr[length])) {
            if (length == iA) {
                return null;
            }
            length -= 2;
        }
        return strArr[length + 1];
    }

    public final String b(int i) {
        return this.b[i * 2];
    }

    public final o70 c() {
        o70 o70Var = new o70(23);
        ArrayList arrayList = (ArrayList) o70Var.c;
        oy.j(arrayList, "<this>");
        String[] strArr = this.b;
        oy.j(strArr, "elements");
        arrayList.addAll(g7.W(strArr));
        return o70Var;
    }

    public final String d(int i) {
        return this.b[(i * 2) + 1];
    }

    public final boolean equals(Object obj) {
        if (obj instanceof tv) {
            return Arrays.equals(this.b, ((tv) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        int size = size();
        Pair[] pairArr = new Pair[size];
        for (int i = 0; i < size; i++) {
            pairArr[i] = new Pair(b(i), d(i));
        }
        return k9.w(pairArr);
    }

    public final int size() {
        return this.b.length / 2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i = 0; i < size; i++) {
            String strB = b(i);
            String strD = d(i);
            sb.append(strB);
            sb.append(": ");
            if (kq0.q(strB)) {
                strD = "██";
            }
            sb.append(strD);
            sb.append("\n");
        }
        String string = sb.toString();
        oy.i(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
