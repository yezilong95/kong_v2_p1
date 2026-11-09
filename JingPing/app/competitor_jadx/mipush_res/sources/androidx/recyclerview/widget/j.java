package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.m41;

/* JADX INFO: loaded from: classes.dex */
public final class j implements m41 {
    public final /* synthetic */ k a;

    public j(k kVar) {
        this.a = kVar;
    }

    @Override // defpackage.m41
    public final int a() {
        k kVar = this.a;
        return kVar.o - kVar.D();
    }

    @Override // defpackage.m41
    public final int b(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return (view.getTop() - ((RecyclerView.LayoutParams) view.getLayoutParams()).b.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
    }

    @Override // defpackage.m41
    public final View c(int i) {
        return this.a.u(i);
    }

    @Override // defpackage.m41
    public final int d() {
        return this.a.G();
    }

    @Override // defpackage.m41
    public final int e(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        this.a.getClass();
        return view.getBottom() + ((RecyclerView.LayoutParams) view.getLayoutParams()).b.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
    }
}
