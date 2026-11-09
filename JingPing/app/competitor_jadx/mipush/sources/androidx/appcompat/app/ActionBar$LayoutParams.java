package androidx.appcompat.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class ActionBar$LayoutParams extends ViewGroup.MarginLayoutParams {
    public int a;

    public ActionBar$LayoutParams(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionBarLayout);
        this.a = typedArrayObtainStyledAttributes.getInt(R$styleable.ActionBarLayout_android_layout_gravity, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public ActionBar$LayoutParams(ActionBar$LayoutParams actionBar$LayoutParams) {
        super((ViewGroup.MarginLayoutParams) actionBar$LayoutParams);
        this.a = 0;
        this.a = actionBar$LayoutParams.a;
    }

    public ActionBar$LayoutParams(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.a = 0;
    }
}
