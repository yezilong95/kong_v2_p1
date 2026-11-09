package defpackage;

import android.content.res.TypedArray;
import android.util.SparseArray;
import com.google.android.material.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public final class wt {
    public final SparseArray a = new SparseArray();
    public final xt b;
    public final int c;
    public final int d;

    public wt(xt xtVar, ou ouVar) {
        this.b = xtVar;
        int i = R$styleable.TextInputLayout_endIconDrawable;
        TypedArray typedArray = (TypedArray) ouVar.G;
        this.c = typedArray.getResourceId(i, 0);
        this.d = typedArray.getResourceId(R$styleable.TextInputLayout_passwordToggleDrawable, 0);
    }
}
