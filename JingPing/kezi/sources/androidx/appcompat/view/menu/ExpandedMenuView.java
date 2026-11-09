package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import defpackage.b70;
import defpackage.h60;
import defpackage.i60;
import defpackage.m60;
import defpackage.po;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements h60, b70, AdapterView.OnItemClickListener {
    public static final int[] c = {R.attr.background, R.attr.divider};
    public i60 b;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // defpackage.h60
    public final boolean a(m60 m60Var) {
        return this.b.q(m60Var, null, 0);
    }

    @Override // defpackage.b70
    public final void c(i60 i60Var) {
        this.b = i60Var;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        a((m60) getAdapter().getItem(i));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        po poVarN = po.N(context, attributeSet, c, i);
        TypedArray typedArray = (TypedArray) poVarN.d;
        if (typedArray.hasValue(0)) {
            setBackgroundDrawable(poVarN.y(0));
        }
        if (typedArray.hasValue(1)) {
            setDivider(poVarN.y(1));
        }
        poVarN.P();
    }
}
