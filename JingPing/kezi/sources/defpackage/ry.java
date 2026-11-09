package defpackage;

import java.util.Collections;
import java.util.Set;
import kotlin.collections.EmptySet;
import kotlin.collections.builders.SetBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class ry {
    public final qy a;
    public final int[] b;
    public final String[] c;
    public final Set d;

    public ry(qy qyVar, int[] iArr, String[] strArr) {
        Set setSingleton;
        this.a = qyVar;
        this.b = iArr;
        this.c = strArr;
        if (strArr.length == 0) {
            setSingleton = EmptySet.INSTANCE;
        } else {
            setSingleton = Collections.singleton(strArr[0]);
            oy.i(setSingleton, "singleton(...)");
        }
        this.d = setSingleton;
        if (iArr.length != strArr.length) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final void a(Set set) {
        Set setBuild;
        oy.j(set, "invalidatedTablesIds");
        int[] iArr = this.b;
        int length = iArr.length;
        if (length != 0) {
            int i = 0;
            if (length != 1) {
                SetBuilder setBuilder = new SetBuilder();
                int length2 = iArr.length;
                int i2 = 0;
                while (i < length2) {
                    int i3 = i2 + 1;
                    if (set.contains(Integer.valueOf(iArr[i]))) {
                        setBuilder.add(this.c[i2]);
                    }
                    i++;
                    i2 = i3;
                }
                setBuild = setBuilder.build();
            } else {
                setBuild = set.contains(Integer.valueOf(iArr[0])) ? this.d : EmptySet.INSTANCE;
            }
        } else {
            setBuild = EmptySet.INSTANCE;
        }
        if (setBuild.isEmpty()) {
            return;
        }
        this.a.a(setBuild);
    }
}
