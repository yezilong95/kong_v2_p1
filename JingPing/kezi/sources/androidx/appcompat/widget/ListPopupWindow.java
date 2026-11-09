package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import defpackage.bj0;
import defpackage.cm;
import defpackage.di;
import defpackage.q20;
import defpackage.r20;
import defpackage.s20;
import defpackage.t20;
import defpackage.u20;
import defpackage.v20;
import defpackage.xb0;
import defpackage.xr0;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public class ListPopupWindow implements bj0 {
    public static final Method B;
    public static final Method C;
    public final PopupWindow A;
    public final Context b;
    public ListAdapter c;
    public cm d;
    public final int e;
    public int f;
    public int g;
    public int h;
    public final int i;
    public boolean j;
    public boolean k;
    public boolean l;
    public int m;
    public final int n;
    public di o;
    public View p;
    public AdapterView.OnItemClickListener q;
    public AdapterView.OnItemSelectedListener r;
    public final t20 s;
    public final v20 t;
    public final u20 u;
    public final t20 v;
    public final Handler w;
    public final Rect x;
    public Rect y;
    public boolean z;

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                B = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                C = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, R$attr.listPopupWindowStyle);
    }

    public cm a(Context context, boolean z) {
        return new cm(context, z);
    }

    @Override // defpackage.bj0
    public final boolean b() {
        return this.A.isShowing();
    }

    public final void c(int i) {
        this.g = i;
    }

    public final int d() {
        return this.g;
    }

    @Override // defpackage.bj0
    public final void dismiss() {
        PopupWindow popupWindow = this.A;
        popupWindow.dismiss();
        popupWindow.setContentView(null);
        this.d = null;
        this.w.removeCallbacks(this.s);
    }

    @Override // defpackage.bj0
    public final void f() {
        int i;
        int paddingBottom;
        cm cmVar;
        cm cmVar2 = this.d;
        PopupWindow popupWindow = this.A;
        Context context = this.b;
        if (cmVar2 == null) {
            cm cmVarA = a(context, !this.z);
            this.d = cmVarA;
            cmVarA.setAdapter(this.c);
            this.d.setOnItemClickListener(this.q);
            this.d.setFocusable(true);
            this.d.setFocusableInTouchMode(true);
            this.d.setOnItemSelectedListener(new q20(0, this));
            this.d.setOnScrollListener(this.u);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.r;
            if (onItemSelectedListener != null) {
                this.d.setOnItemSelectedListener(onItemSelectedListener);
            }
            popupWindow.setContentView(this.d);
        }
        Drawable background = popupWindow.getBackground();
        Rect rect = this.x;
        if (background != null) {
            background.getPadding(rect);
            int i2 = rect.top;
            i = rect.bottom + i2;
            if (!this.j) {
                this.h = -i2;
            }
        } else {
            rect.setEmpty();
            i = 0;
        }
        int iA = r20.a(popupWindow, this.p, this.h, popupWindow.getInputMethodMode() == 2);
        int i3 = this.e;
        if (i3 == -1) {
            paddingBottom = iA + i;
        } else {
            int i4 = this.f;
            int iA2 = this.d.a(i4 != -2 ? i4 != -1 ? View.MeasureSpec.makeMeasureSpec(i4, 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), Integer.MIN_VALUE), iA);
            paddingBottom = iA2 + (iA2 > 0 ? this.d.getPaddingBottom() + this.d.getPaddingTop() + i : 0);
        }
        boolean z = this.A.getInputMethodMode() == 2;
        xb0.d(popupWindow, this.i);
        if (popupWindow.isShowing()) {
            View view = this.p;
            WeakHashMap weakHashMap = xr0.a;
            if (view.isAttachedToWindow()) {
                int width = this.f;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = this.p.getWidth();
                }
                if (i3 == -1) {
                    i3 = z ? paddingBottom : -1;
                    if (z) {
                        popupWindow.setWidth(this.f == -1 ? -1 : 0);
                        popupWindow.setHeight(0);
                    } else {
                        popupWindow.setWidth(this.f == -1 ? -1 : 0);
                        popupWindow.setHeight(-1);
                    }
                } else if (i3 == -2) {
                    i3 = paddingBottom;
                }
                popupWindow.setOutsideTouchable(true);
                int i5 = width;
                popupWindow.update(this.p, this.g, this.h, i5 < 0 ? -1 : i5, i3 < 0 ? -1 : i3);
                return;
            }
            return;
        }
        int width2 = this.f;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = this.p.getWidth();
        }
        if (i3 == -1) {
            i3 = -1;
        } else if (i3 == -2) {
            i3 = paddingBottom;
        }
        popupWindow.setWidth(width2);
        popupWindow.setHeight(i3);
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = B;
            if (method != null) {
                try {
                    method.invoke(popupWindow, Boolean.TRUE);
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                }
            }
        } else {
            s20.b(popupWindow, true);
        }
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchInterceptor(this.t);
        if (this.l) {
            xb0.c(popupWindow, this.k);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method2 = C;
            if (method2 != null) {
                try {
                    method2.invoke(popupWindow, this.y);
                } catch (Exception e) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
        } else {
            s20.a(popupWindow, this.y);
        }
        popupWindow.showAsDropDown(this.p, this.g, this.h, this.m);
        this.d.setSelection(-1);
        if ((!this.z || this.d.isInTouchMode()) && (cmVar = this.d) != null) {
            cmVar.setListSelectionHidden(true);
            cmVar.requestLayout();
        }
        if (this.z) {
            return;
        }
        this.w.post(this.v);
    }

    public final int g() {
        if (this.j) {
            return this.h;
        }
        return 0;
    }

    public final Drawable h() {
        return this.A.getBackground();
    }

    @Override // defpackage.bj0
    public final cm j() {
        return this.d;
    }

    public final void l(Drawable drawable) {
        this.A.setBackgroundDrawable(drawable);
    }

    public final void m(int i) {
        this.h = i;
        this.j = true;
    }

    public void n(ListAdapter listAdapter) {
        di diVar = this.o;
        if (diVar == null) {
            this.o = new di(1, this);
        } else {
            ListAdapter listAdapter2 = this.c;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(diVar);
            }
        }
        this.c = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.o);
        }
        cm cmVar = this.d;
        if (cmVar != null) {
            cmVar.setAdapter(this.c);
        }
    }

    public final void r(int i) {
        Drawable background = this.A.getBackground();
        if (background == null) {
            this.f = i;
            return;
        }
        Rect rect = this.x;
        background.getPadding(rect);
        this.f = rect.left + rect.right + i;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.A.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.q = onItemClickListener;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.r = onItemSelectedListener;
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.e = -2;
        this.f = -2;
        this.i = CloseFrame.PROTOCOL_ERROR;
        this.m = 0;
        this.n = Integer.MAX_VALUE;
        this.s = new t20(this, 1);
        this.t = new v20(0, this);
        this.u = new u20(this);
        this.v = new t20(this, 0);
        this.x = new Rect();
        this.b = context;
        this.w = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPopupWindow, i, 0);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.h = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.j = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        AppCompatPopupWindow appCompatPopupWindow = new AppCompatPopupWindow(context, attributeSet, i, 0);
        appCompatPopupWindow.a(context, attributeSet, i);
        this.A = appCompatPopupWindow;
        appCompatPopupWindow.setInputMethodMode(1);
    }
}
