package defpackage;

import android.content.res.TypedArray;
import android.util.SparseArray;
import com.google.android.material.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public final class eo {
    public final SparseArray a = new SparseArray();
    public final fo b;
    public final int c;
    public final int d;

    public eo(fo foVar, po poVar) {
        this.b = foVar;
        int i = R$styleable.TextInputLayout_endIconDrawable;
        TypedArray typedArray = (TypedArray) poVar.d;
        this.c = typedArray.getResourceId(i, 0);
        this.d = typedArray.getResourceId(R$styleable.TextInputLayout_passwordToggleDrawable, 0);
    }
}
