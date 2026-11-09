package defpackage;

import android.text.Editable;

/* JADX INFO: loaded from: classes.dex */
public final class ut extends h01 {
    public final /* synthetic */ xt E;

    public ut(xt xtVar) {
        this.E = xtVar;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        this.E.b().a();
    }

    @Override // defpackage.h01, android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.E.b().b();
    }
}
