package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.m41;

/* JADX INFO: loaded from: classes.dex */
public final class i implements m41 {
    public final /* synthetic */ k a;

    public i(k kVar) {
        this.a = kVar;
    }

    @Override // defpackage.m41
    public final int a() {
        k kVar = this.a;
        return kVar.n - kVar.F();
    }

    @Override // defpackage.m41
    public final int b(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return (view.getLeft() - ((RecyclerView.LayoutParams) view.getLayoutParams()).b.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
    }

    @Override // defpackage.m41
    public final View c(int i) {
        return this.a.u(i);
    }

    @Override // defpackage.m41
    public final int d() {
        return this.a.E();
    }

    @Override // defpackage.m41
    public final int e(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return view.getRight() + ((RecyclerView.LayoutParams) view.getLayoutParams()).b.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }
}
