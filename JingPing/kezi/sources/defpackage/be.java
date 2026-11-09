package defpackage;

import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public abstract class be extends xi {
    public static int W(List list) {
        return list.size() - 1;
    }

    public static List X(Object... objArr) {
        oy.j(objArr, "elements");
        return objArr.length > 0 ? g7.W(objArr) : EmptyList.INSTANCE;
    }

    public static void Y() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
