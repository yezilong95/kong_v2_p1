package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ws;

/* JADX INFO: loaded from: classes.dex */
public final class e extends ws {
    @Override // defpackage.ws
    public final int b(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        ((k) this.b).getClass();
        return view.getRight() + ((RecyclerView.LayoutParams) view.getLayoutParams()).b.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }

    @Override // defpackage.ws
    public final int c(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        ((k) this.b).getClass();
        return k.A(view) + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }

    @Override // defpackage.ws
    public final int d(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        ((k) this.b).getClass();
        return k.z(view) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
    }

    @Override // defpackage.ws
    public final int e(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        ((k) this.b).getClass();
        return (view.getLeft() - ((RecyclerView.LayoutParams) view.getLayoutParams()).b.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
    }

    @Override // defpackage.ws
    public final int f() {
        return ((k) this.b).n;
    }

    @Override // defpackage.ws
    public final int g() {
        k kVar = (k) this.b;
        return kVar.n - kVar.F();
    }

    @Override // defpackage.ws
    public final int h() {
        return ((k) this.b).F();
    }

    @Override // defpackage.ws
    public final int i() {
        return ((k) this.b).l;
    }

    @Override // defpackage.ws
    public final int j() {
        return ((k) this.b).m;
    }

    @Override // defpackage.ws
    public final int k() {
        return ((k) this.b).E();
    }

    @Override // defpackage.ws
    public final int l() {
        k kVar = (k) this.b;
        return (kVar.n - kVar.E()) - kVar.F();
    }

    @Override // defpackage.ws
    public final int m(View view) {
        k kVar = (k) this.b;
        Rect rect = (Rect) this.c;
        kVar.K(rect, view);
        return rect.right;
    }

    @Override // defpackage.ws
    public final int n(View view) {
        k kVar = (k) this.b;
        Rect rect = (Rect) this.c;
        kVar.K(rect, view);
        return rect.left;
    }

    @Override // defpackage.ws
    public final void o(int i) {
        ((k) this.b).O(i);
    }
}
