package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class oa0 implements ka {
    public final ot b;
    public final /* synthetic */ qa0 c;

    public oa0(qa0 qa0Var, ot otVar) {
        oy.j(otVar, "onBackPressedCallback");
        this.c = qa0Var;
        this.b = otVar;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.internal.FunctionReferenceImpl, ou] */
    @Override // defpackage.ka
    public final void cancel() {
        qa0 qa0Var = this.c;
        z6 z6Var = qa0Var.b;
        ot otVar = this.b;
        z6Var.remove(otVar);
        if (oy.c(qa0Var.c, otVar)) {
            otVar.getClass();
            qa0Var.c = null;
        }
        otVar.b.remove(this);
        ?? r0 = otVar.c;
        if (r0 != 0) {
            r0.invoke();
        }
        otVar.c = null;
    }
}
