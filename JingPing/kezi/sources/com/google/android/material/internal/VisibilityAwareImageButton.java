package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class VisibilityAwareImageButton extends ImageButton {
    public int b;

    public VisibilityAwareImageButton(Context context) {
        this(context, null);
    }

    public final void a(int i, boolean z) {
        super.setVisibility(i);
        if (z) {
            this.b = i;
        }
    }

    public final int getUserSetVisibility() {
        return this.b;
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        a(i, true);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = getVisibility();
    }
}
