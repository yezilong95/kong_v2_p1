package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.fr0;

/* JADX INFO: loaded from: classes.dex */
public final class i implements fr0 {
    public final /* synthetic */ k a;

    public i(k kVar) {
        this.a = kVar;
    }

    @Override // defpackage.fr0
    public final View a(int i) {
        return this.a.u(i);
    }

    @Override // defpackage.fr0
    public final int b() {
        k kVar = this.a;
        return kVar.n - kVar.D();
    }

    @Override // defpackage.fr0
    public final int c() {
        return this.a.C();
    }

    @Override // defpackage.fr0
    public final int d(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return view.getRight() + ((RecyclerView.LayoutParams) view.getLayoutParams()).b.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }

    @Override // defpackage.fr0
    public final int e(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return (view.getLeft() - ((RecyclerView.LayoutParams) view.getLayoutParams()).b.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
    }
}
