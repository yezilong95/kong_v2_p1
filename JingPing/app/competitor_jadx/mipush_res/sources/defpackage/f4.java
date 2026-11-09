package defpackage;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class f4 {
    public final ContextThemeWrapper a;
    public final LayoutInflater b;
    public Drawable c;
    public CharSequence d;
    public View e;
    public ae0 f;
    public Object g;
    public DialogInterface.OnClickListener h;
    public boolean i;
    public int j = -1;

    public f4(ContextThemeWrapper contextThemeWrapper) {
        this.a = contextThemeWrapper;
        this.b = (LayoutInflater) contextThemeWrapper.getSystemService("layout_inflater");
    }
}
