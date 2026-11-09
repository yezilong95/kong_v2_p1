package defpackage;

import java.util.LinkedHashMap;
import java.util.List;
import kotlin.jvm.internal.Lambda;
import kotlin.text.CharDirectionality;

/* JADX INFO: loaded from: classes.dex */
public final class rd extends Lambda implements a10 {
    public static final rd F = new rd(0, 0);
    public static final rd G = new rd(0, 1);
    public final /* synthetic */ int E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ rd(int i, int i2) {
        super(i);
        this.E = i2;
    }

    @Override // defpackage.a10
    public final Object invoke() {
        switch (this.E) {
            case 0:
                List entries = CharDirectionality.getEntries();
                int iK0 = ec0.k0(xf.i1(entries));
                if (iK0 < 16) {
                    iK0 = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(iK0);
                v0 v0Var = (v0) entries;
                v0Var.getClass();
                s0 s0Var = new s0(v0Var);
                while (s0Var.hasNext()) {
                    Object next = s0Var.next();
                    linkedHashMap.put(Integer.valueOf(((CharDirectionality) next).getValue()), next);
                }
                return linkedHashMap;
            default:
                return null;
        }
    }
}
