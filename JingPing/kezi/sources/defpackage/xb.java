package defpackage;

import java.util.LinkedHashMap;
import java.util.List;
import kotlin.jvm.internal.Lambda;
import kotlin.text.CharDirectionality;

/* JADX INFO: loaded from: classes.dex */
public final class xb extends Lambda implements ou {
    public static final xb c = new xb(0, 0);
    public static final xb d = new xb(0, 1);
    public final /* synthetic */ int b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ xb(int i, int i2) {
        super(i);
        this.b = i2;
    }

    @Override // defpackage.ou
    public final Object invoke() {
        switch (this.b) {
            case 0:
                List entries = CharDirectionality.getEntries();
                int iU0 = o40.u0(ce.Z(entries));
                if (iU0 < 16) {
                    iU0 = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(iU0);
                i iVar = new i((l) entries);
                while (iVar.hasNext()) {
                    Object next = iVar.next();
                    linkedHashMap.put(Integer.valueOf(((CharDirectionality) next).getValue()), next);
                }
                return linkedHashMap;
            default:
                return null;
        }
    }
}
