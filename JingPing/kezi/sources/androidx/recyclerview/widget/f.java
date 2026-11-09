package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.en;

/* JADX INFO: loaded from: classes.dex */
public final class f extends en {
    @Override // defpackage.en
    public final int b(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        ((k) this.b).getClass();
        return view.getBottom() + ((RecyclerView.LayoutParams) view.getLayoutParams()).b.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
    }

    @Override // defpackage.en
    public final int c(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        ((k) this.b).getClass();
        Rect rect = ((RecyclerView.LayoutParams) view.getLayoutParams()).b;
        return view.getMeasuredHeight() + rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
    }

    @Override // defpackage.en
    public final int d(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        ((k) this.b).getClass();
        Rect rect = ((RecyclerView.LayoutParams) view.getLayoutParams()).b;
        return view.getMeasuredWidth() + rect.left + rect.right + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
    }

    @Override // defpackage.en
    public final int e(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        ((k) this.b).getClass();
        return (view.getTop() - ((RecyclerView.LayoutParams) view.getLayoutParams()).b.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
    }

    @Override // defpackage.en
    public final int f() {
        return ((k) this.b).o;
    }

    @Override // defpackage.en
    public final int g() {
        k kVar = (k) this.b;
        return kVar.o - kVar.B();
    }

    @Override // defpackage.en
    public final int h() {
        return ((k) this.b).B();
    }

    @Override // defpackage.en
    public final int i() {
        return ((k) this.b).m;
    }

    @Override // defpackage.en
    public final int j() {
        return ((k) this.b).l;
    }

    @Override // defpackage.en
    public final int k() {
        return ((k) this.b).E();
    }

    @Override // defpackage.en
    public final int l() {
        k kVar = (k) this.b;
        return (kVar.o - kVar.E()) - kVar.B();
    }

    @Override // defpackage.en
    public final int m(View view) {
        k kVar = (k) this.b;
        Rect rect = (Rect) this.c;
        kVar.I(rect, view);
        return rect.bottom;
    }

    @Override // defpackage.en
    public final int n(View view) {
        k kVar = (k) this.b;
        Rect rect = (Rect) this.c;
        kVar.I(rect, view);
        return rect.top;
    }

    @Override // defpackage.en
    public final void o(int i) {
        ((k) this.b).N(i);
    }
}
