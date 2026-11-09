package defpackage;

import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ms0 {
    public final View a;
    public int b;
    public int c;
    public int d;

    public ms0(View view) {
        this.a = view;
    }

    public final void a() {
        int i = this.d;
        View view = this.a;
        int top = i - (view.getTop() - this.b);
        WeakHashMap weakHashMap = xr0.a;
        view.offsetTopAndBottom(top);
        view.offsetLeftAndRight(0 - (view.getLeft() - this.c));
    }

    public final boolean b(int i) {
        if (this.d == i) {
            return false;
        }
        this.d = i;
        a();
        return true;
    }
}
