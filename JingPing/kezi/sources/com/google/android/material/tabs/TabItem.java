package com.google.android.material.tabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R$styleable;
import defpackage.po;

/* JADX INFO: loaded from: classes.dex */
public class TabItem extends View {
    public final CharSequence b;
    public final Drawable c;
    public final int d;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        po poVarM = po.M(context, attributeSet, R$styleable.TabItem);
        int i = R$styleable.TabItem_android_text;
        TypedArray typedArray = (TypedArray) poVarM.d;
        this.b = typedArray.getText(i);
        this.c = poVarM.y(R$styleable.TabItem_android_icon);
        this.d = typedArray.getResourceId(R$styleable.TabItem_android_layout, 0);
        poVarM.P();
    }
}
