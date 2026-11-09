package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.fr0;

/* JADX INFO: loaded from: classes.dex */
public final class j implements fr0 {
    public final /* synthetic */ k a;

    public j(k kVar) {
        this.a = kVar;
    }

    @Override // defpackage.fr0
    public final View a(int i) {
        return this.a.u(i);
    }

    @Override // defpackage.fr0
    public final int b() {
        k kVar = this.a;
        return kVar.o - kVar.B();
    }

    @Override // defpackage.fr0
    public final int c() {
        return this.a.E();
    }

    @Override // defpackage.fr0
    public final int d(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return view.getBottom() + ((RecyclerView.LayoutParams) view.getLayoutParams()).b.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
    }

    @Override // defpackage.fr0
    public final int e(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return (view.getTop() - ((RecyclerView.LayoutParams) view.getLayoutParams()).b.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
    }
}
