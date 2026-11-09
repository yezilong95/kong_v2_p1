package defpackage;

import androidx.appcompat.widget.AppCompatSpinner;

/* JADX INFO: loaded from: classes.dex */
public final class l6 extends qz {
    public final /* synthetic */ t6 N;
    public final /* synthetic */ AppCompatSpinner O;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l6(AppCompatSpinner appCompatSpinner, AppCompatSpinner appCompatSpinner2, t6 t6Var) {
        super(appCompatSpinner2);
        this.O = appCompatSpinner;
        this.N = t6Var;
    }

    @Override // defpackage.qz
    public final qv0 b() {
        return this.N;
    }

    @Override // defpackage.qz
    public final boolean c() {
        AppCompatSpinner appCompatSpinner = this.O;
        if (appCompatSpinner.getInternalPopup().b()) {
            return true;
        }
        appCompatSpinner.J.n(n6.b(appCompatSpinner), n6.a(appCompatSpinner));
        return true;
    }
}
