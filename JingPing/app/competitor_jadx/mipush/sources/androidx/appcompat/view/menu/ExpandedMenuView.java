package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import defpackage.de0;
import defpackage.ou;
import defpackage.se0;
import defpackage.yd0;
import defpackage.zd0;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements yd0, se0, AdapterView.OnItemClickListener {
    public static final int[] F = {R.attr.background, R.attr.divider};
    public zd0 E;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // defpackage.yd0
    public final boolean a(de0 de0Var) {
        return this.E.q(de0Var, null, 0);
    }

    @Override // defpackage.se0
    public final void c(zd0 zd0Var) {
        this.E = zd0Var;
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
        a((de0) getAdapter().getItem(i));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        ou ouVarP = ou.P(context, attributeSet, F, i);
        TypedArray typedArray = (TypedArray) ouVarP.G;
        if (typedArray.hasValue(0)) {
            setBackgroundDrawable(ouVarP.A(0));
        }
        if (typedArray.hasValue(1)) {
            setDivider(ouVarP.A(1));
        }
        ouVarP.S();
    }
}
