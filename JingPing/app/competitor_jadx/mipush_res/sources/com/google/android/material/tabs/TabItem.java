package com.google.android.material.tabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R$styleable;
import defpackage.ou;

/* JADX INFO: loaded from: classes.dex */
public class TabItem extends View {
    public final CharSequence E;
    public final Drawable F;
    public final int G;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ou ouVarO = ou.O(context, attributeSet, R$styleable.TabItem);
        int i = R$styleable.TabItem_android_text;
        TypedArray typedArray = (TypedArray) ouVarO.G;
        this.E = typedArray.getText(i);
        this.F = ouVarO.A(R$styleable.TabItem_android_icon);
        this.G = typedArray.getResourceId(R$styleable.TabItem_android_layout, 0);
        ouVarO.S();
    }
}
