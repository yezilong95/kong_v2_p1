package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class ll {
    public static final wk a;
    public static final nl b;

    static {
        wk wkVar = new wk();
        wkVar.H = -1;
        a = wkVar;
        nl nlVar = new nl();
        nlVar.H = -1;
        b = nlVar;
    }

    public static wk a(u uVar) {
        if (uVar.b < 1) {
            return a;
        }
        wk wkVar = new wk(uVar, 1);
        wkVar.H = -1;
        return wkVar;
    }

    public static nl b(u uVar) {
        if (uVar.b < 1) {
            return b;
        }
        nl nlVar = new nl(uVar, false);
        nlVar.H = -1;
        return nlVar;
    }
}
