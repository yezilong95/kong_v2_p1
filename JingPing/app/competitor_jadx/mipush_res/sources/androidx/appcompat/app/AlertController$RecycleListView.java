package androidx.appcompat.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class AlertController$RecycleListView extends ListView {
    public final int E;
    public final int F;

    public AlertController$RecycleListView(Context context) {
        this(context, null);
    }

    public AlertController$RecycleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecycleListView);
        this.F = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingBottomNoButtons, -1);
        this.E = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingTopNoTitle, -1);
    }
}
