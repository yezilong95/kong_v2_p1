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
import defpackage.d3;
import defpackage.f51;
import defpackage.m3;
import defpackage.o3;
import defpackage.ou;
import defpackage.p3;
import defpackage.q3;
import defpackage.r3;
import defpackage.s3;
import defpackage.t3;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup {
    public final s3 E;
    public final t3 F;
    public final View G;
    public final FrameLayout H;
    public final ImageView I;
    public final FrameLayout J;
    public m3 K;
    public final q3 L;
    public ListPopupWindow M;
    public PopupWindow.OnDismissListener N;
    public boolean O;

    public static class InnerLayout extends LinearLayout {
        public static final int[] E = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            ou ouVarO = ou.O(context, attributeSet, E);
            setBackgroundDrawable(ouVarO.A(0));
            ouVarO.S();
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
                viewTreeObserver.removeGlobalOnLayoutListener(this.L);
            }
        }
    }

    public final boolean b() {
        return getListPopupWindow().d0.isShowing();
    }

    public o3 getDataModel() {
        this.E.getClass();
        return null;
    }

    public ListPopupWindow getListPopupWindow() {
        if (this.M == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.M = listPopupWindow;
            listPopupWindow.q(this.E);
            ListPopupWindow listPopupWindow2 = this.M;
            listPopupWindow2.S = this;
            listPopupWindow2.c0 = true;
            listPopupWindow2.d0.setFocusable(true);
            ListPopupWindow listPopupWindow3 = this.M;
            t3 t3Var = this.F;
            listPopupWindow3.setOnItemClickListener(t3Var);
            this.M.setOnDismissListener(t3Var);
        }
        return this.M;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.E.getClass();
        this.O = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.E.getClass();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.L);
        }
        if (b()) {
            a();
        }
        this.O = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.G.layout(0, 0, i3 - i, i4 - i2);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.J.getVisibility() != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), 1073741824);
        }
        View view = this.G;
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(o3 o3Var) {
        s3 s3Var = this.E;
        s3Var.E.E.getClass();
        s3Var.notifyDataSetChanged();
        if (b()) {
            a();
            if (b() || !this.O) {
                return;
            }
            s3Var.getClass();
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
    }

    public void setDefaultActionButtonContentDescription(int i) {
    }

    public void setExpandActivityOverflowButtonContentDescription(int i) {
        this.I.setContentDescription(getContext().getString(i));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.I.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i) {
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.N = onDismissListener;
    }

    public void setProvider(m3 m3Var) {
        this.K = m3Var;
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2 = 0;
        new p3(this, i2);
        this.L = new q3(i2, this);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActivityChooserView, i, 0);
        f51.n(this, context, R$styleable.ActivityChooserView, attributeSet, typedArrayObtainStyledAttributes, i);
        typedArrayObtainStyledAttributes.getInt(R$styleable.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(R$layout.abc_activity_chooser_view, (ViewGroup) this, true);
        t3 t3Var = new t3(this);
        this.F = t3Var;
        View viewFindViewById = findViewById(R$id.activity_chooser_view_content);
        this.G = viewFindViewById;
        viewFindViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(R$id.default_activity_button);
        this.J = frameLayout;
        frameLayout.setOnClickListener(t3Var);
        frameLayout.setOnLongClickListener(t3Var);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R$id.expand_activities_button);
        frameLayout2.setOnClickListener(t3Var);
        frameLayout2.setAccessibilityDelegate(new r3());
        frameLayout2.setOnTouchListener(new d3(this, frameLayout2, 2));
        this.H = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(R$id.image);
        this.I = imageView;
        imageView.setImageDrawable(drawable);
        s3 s3Var = new s3(this);
        this.E = s3Var;
        s3Var.registerDataSetObserver(new p3(this, 1));
        Resources resources = context.getResources();
        Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
    }
}
