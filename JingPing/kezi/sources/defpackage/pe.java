package defpackage;

import androidx.work.impl.WorkDatabaseVersions;
import kotlin.coroutines.CombinedContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class pe extends Lambda implements ru {
    public static final pe c = new pe(2, 0);
    public static final pe d = new pe(2, 1);
    public static final pe e = new pe(2, 2);
    public static final pe f = new pe(2, 3);
    public static final pe g = new pe(2, 4);
    public static final pe h = new pe(2, 5);
    public static final pe i = new pe(2, 6);
    public static final pe j = new pe(2, 7);
    public static final pe k = new pe(2, 8);
    public static final pe l = new pe(2, 9);
    public final /* synthetic */ int b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ pe(int i2, int i3) {
        super(i2);
        this.b = i3;
    }

    @Override // defpackage.ru
    public final Object invoke(Object obj, Object obj2) {
        CombinedContext combinedContext;
        switch (this.b) {
            case 0:
                String str = (String) obj;
                lh lhVar = (lh) obj2;
                oy.j(str, "acc");
                oy.j(lhVar, "element");
                if (str.length() == 0) {
                    return lhVar.toString();
                }
                return str + ", " + lhVar;
            case 1:
                return new lf(obj, obj2);
            case 2:
                return obj;
            case 3:
                nh nhVar = (nh) obj;
                lh lhVar2 = (lh) obj2;
                oy.j(nhVar, "acc");
                oy.j(lhVar2, "element");
                nh nhVarMinusKey = nhVar.minusKey(lhVar2.getKey());
                EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
                if (nhVarMinusKey == emptyCoroutineContext) {
                    return lhVar2;
                }
                qm qmVar = qm.c;
                gh ghVar = (gh) nhVarMinusKey.get(qmVar);
                if (ghVar == null) {
                    combinedContext = new CombinedContext(nhVarMinusKey, lhVar2);
                } else {
                    nh nhVarMinusKey2 = nhVarMinusKey.minusKey(qmVar);
                    if (nhVarMinusKey2 == emptyCoroutineContext) {
                        return new CombinedContext(lhVar2, ghVar);
                    }
                    combinedContext = new CombinedContext(new CombinedContext(nhVarMinusKey2, lhVar2), ghVar);
                }
                return combinedContext;
            case 4:
                return ((nh) obj).plus((lh) obj2);
            case 5:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                return bool;
            case 6:
                return Integer.valueOf(((Number) obj).intValue() + 1);
            case 7:
                lh lhVar3 = (lh) obj2;
                if (!(lhVar3 instanceof sh)) {
                    return obj;
                }
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                int iIntValue = num != null ? num.intValue() : 1;
                return iIntValue == 0 ? lhVar3 : Integer.valueOf(iIntValue + 1);
            case 8:
                lh lhVar4 = (lh) obj2;
                if (lhVar4 instanceof sh) {
                    return (sh) lhVar4;
                }
                return null;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return (zn0) obj;
            default:
                return ((nh) obj).plus((lh) obj2);
        }
    }
}
