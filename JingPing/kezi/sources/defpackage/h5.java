package defpackage;

import androidx.appcompat.widget.AppCompatSpinner;

/* JADX INFO: loaded from: classes.dex */
public final class h5 extends ct {
    public final /* synthetic */ p5 k;
    public final /* synthetic */ AppCompatSpinner l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h5(AppCompatSpinner appCompatSpinner, AppCompatSpinner appCompatSpinner2, p5 p5Var) {
        super(appCompatSpinner2);
        this.l = appCompatSpinner;
        this.k = p5Var;
    }

    @Override // defpackage.ct
    public final bj0 b() {
        return this.k;
    }

    @Override // defpackage.ct
    public final boolean c() {
        AppCompatSpinner appCompatSpinner = this.l;
        if (appCompatSpinner.getInternalPopup().b()) {
            return true;
        }
        appCompatSpinner.g.e(j5.b(appCompatSpinner), j5.a(appCompatSpinner));
        return true;
    }
}
