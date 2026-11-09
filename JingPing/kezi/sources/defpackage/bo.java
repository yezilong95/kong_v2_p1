package defpackage;

import android.text.Editable;

/* JADX INFO: loaded from: classes.dex */
public final class bo extends tn0 {
    public final /* synthetic */ fo b;

    public bo(fo foVar) {
        this.b = foVar;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        this.b.b().a();
    }

    @Override // defpackage.tn0, android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.b.b().b();
    }
}
