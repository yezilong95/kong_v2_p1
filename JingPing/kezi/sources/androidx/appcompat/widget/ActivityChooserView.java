package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import defpackage.i1;
import defpackage.po;
import defpackage.r1;
import defpackage.t1;
import defpackage.u1;
import defpackage.v1;
import defpackage.w1;
import defpackage.x1;
import defpackage.xr0;
import defpackage.y1;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup {
    public final x1 b;
    public final y1 c;
    public final View d;
    public final FrameLayout e;
    public final ImageView f;
    public final FrameLayout g;
    public r1 h;
    public final v1 i;
    public ListPopupWindow j;
    public PopupWindow.OnDismissListener k;
    public boolean l;

    public static class InnerLayout extends LinearLayout {
        public static final int[] b = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            po poVarM = po.M(context, attributeSet, b);
            setBackgroundDrawable(poVarM.y(0));
            poVarM.P();
        }
    }

    public ActivityChooserView(Context context) {
        this(context, null);
    }

    public final void a() {
        if (b()) {
            getListPopupWindow().dismiss();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this.i);
            }
        }
    }

    public final boolean b() {
        return getListPopupWindow().A.isShowing();
    }

    public t1 getDataModel() {
        this.b.getClass();
        return null;
    }

    public ListPopupWindow getListPopupWindow() {
        if (this.j == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.j = listPopupWindow;
            listPopupWindow.n(this.b);
            ListPopupWindow listPopupWindow2 = this.j;
            listPopupWindow2.p = this;
            listPopupWindow2.z = true;
            listPopupWindow2.A.setFocusable(true);
            ListPopupWindow listPopupWindow3 = this.j;
            y1 y1Var = this.c;
            listPopupWindow3.setOnItemClickListener(y1Var);
            this.j.setOnDismissListener(y1Var);
        }
        return this.j;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b.getClass();
        this.l = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b.getClass();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.i);
        }
        if (b()) {
            a();
        }
        this.l = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.d.layout(0, 0, i3 - i, i4 - i2);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.g.getVisibility() != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), 1073741824);
        }
        View view = this.d;
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(t1 t1Var) {
        x1 x1Var = this.b;
        x1Var.b.b.getClass();
        x1Var.notifyDataSetChanged();
        if (b()) {
            a();
            if (b() || !this.l) {
                return;
            }
            x1Var.getClass();
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
    }

    public void setDefaultActionButtonContentDescription(int i) {
    }

    public void setExpandActivityOverflowButtonContentDescription(int i) {
        this.f.setContentDescription(getContext().getString(i));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.f.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i) {
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void setProvider(r1 r1Var) {
        this.h = r1Var;
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2 = 0;
        new u1(this, i2);
        this.i = new v1(i2, this);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActivityChooserView, i, 0);
        xr0.o(this, context, R$styleable.ActivityChooserView, attributeSet, typedArrayObtainStyledAttributes, i);
        typedArrayObtainStyledAttributes.getInt(R$styleable.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(R$layout.abc_activity_chooser_view, (ViewGroup) this, true);
        y1 y1Var = new y1(this);
        this.c = y1Var;
        View viewFindViewById = findViewById(R$id.activity_chooser_view_content);
        this.d = viewFindViewById;
        viewFindViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(R$id.default_activity_button);
        this.g = frameLayout;
        frameLayout.setOnClickListener(y1Var);
        frameLayout.setOnLongClickListener(y1Var);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R$id.expand_activities_button);
        frameLayout2.setOnClickListener(y1Var);
        frameLayout2.setAccessibilityDelegate(new w1());
        frameLayout2.setOnTouchListener(new i1(this, frameLayout2, 2));
        this.e = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(R$id.image);
        this.f = imageView;
        imageView.setImageDrawable(drawable);
        x1 x1Var = new x1(this);
        this.b = x1Var;
        x1Var.registerDataSetObserver(new u1(this, 1));
        Resources resources = context.getResources();
        Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
    }
}
