package defpackage;

import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class f extends Lambda implements pu {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(int i, Object obj) {
        super(1);
        this.b = i;
        this.c = obj;
    }

    @Override // defpackage.pu
    public final Object invoke(Object obj) {
        switch (this.b) {
            case 0:
                return obj == ((l) this.c) ? "(this Collection)" : String.valueOf(obj);
            default:
                my myVar = (my) obj;
                oy.j(myVar, "it");
                String str = (String) this.c;
                oy.j(str, "<this>");
                return str.subSequence(myVar.b, myVar.c + 1).toString();
        }
    }
}
