package defpackage;

import androidx.work.impl.WorkDatabaseVersions;
import kotlin.coroutines.CombinedContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class jg extends Lambda implements d10 {
    public static final jg F = new jg(2, 0);
    public static final jg G = new jg(2, 1);
    public static final jg H = new jg(2, 2);
    public static final jg I = new jg(2, 3);
    public static final jg J = new jg(2, 4);
    public static final jg K = new jg(2, 5);
    public static final jg L = new jg(2, 6);
    public static final jg M = new jg(2, 7);
    public static final jg N = new jg(2, 8);
    public static final jg O = new jg(2, 9);
    public final /* synthetic */ int E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ jg(int i, int i2) {
        super(i);
        this.E = i2;
    }

    @Override // defpackage.d10
    public final Object invoke(Object obj, Object obj2) {
        CombinedContext combinedContext;
        switch (this.E) {
            case 0:
                String str = (String) obj;
                zi ziVar = (zi) obj2;
                t40.n(str, "acc");
                t40.n(ziVar, "element");
                if (str.length() == 0) {
                    return ziVar.toString();
                }
                return str + ", " + ziVar;
            case 1:
                return new fh(obj, obj2);
            case 2:
                return obj;
            case 3:
                bj bjVar = (bj) obj;
                zi ziVar2 = (zi) obj2;
                t40.n(bjVar, "acc");
                t40.n(ziVar2, "element");
                bj bjVarMinusKey = bjVar.minusKey(ziVar2.getKey());
                EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
                if (bjVarMinusKey == emptyCoroutineContext) {
                    return ziVar2;
                }
                is isVar = is.F;
                vi viVar = (vi) bjVarMinusKey.get(isVar);
                if (viVar == null) {
                    combinedContext = new CombinedContext(bjVarMinusKey, ziVar2);
                } else {
                    bj bjVarMinusKey2 = bjVarMinusKey.minusKey(isVar);
                    if (bjVarMinusKey2 == emptyCoroutineContext) {
                        return new CombinedContext(ziVar2, viVar);
                    }
                    combinedContext = new CombinedContext(new CombinedContext(bjVarMinusKey2, ziVar2), viVar);
                }
                return combinedContext;
            case 4:
                return ((bj) obj).plus((zi) obj2);
            case 5:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                return bool;
            case 6:
                return Integer.valueOf(((Number) obj).intValue() + 1);
            case 7:
                zi ziVar3 = (zi) obj2;
                if (!(ziVar3 instanceof gj)) {
                    return obj;
                }
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                int iIntValue = num != null ? num.intValue() : 1;
                return iIntValue == 0 ? ziVar3 : Integer.valueOf(iIntValue + 1);
            case 8:
                zi ziVar4 = (zi) obj2;
                if (ziVar4 instanceof gj) {
                    return (gj) ziVar4;
                }
                return null;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return (n01) obj;
            default:
                return ((bj) obj).plus((zi) obj2);
        }
    }
}
