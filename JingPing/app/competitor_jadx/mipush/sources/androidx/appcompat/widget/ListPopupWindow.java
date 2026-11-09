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
import defpackage.aa0;
import defpackage.cl0;
import defpackage.f51;
import defpackage.gq;
import defpackage.qv0;
import defpackage.v90;
import defpackage.w90;
import defpackage.wj;
import defpackage.x90;
import defpackage.y90;
import defpackage.z90;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public class ListPopupWindow implements qv0 {
    public static final Method e0;
    public static final Method f0;
    public final Context E;
    public ListAdapter F;
    public gq G;
    public final int H;
    public int I;
    public int J;
    public int K;
    public final int L;
    public boolean M;
    public boolean N;
    public boolean O;
    public int P;
    public final int Q;
    public wj R;
    public View S;
    public AdapterView.OnItemClickListener T;
    public AdapterView.OnItemSelectedListener U;
    public final y90 V;
    public final aa0 W;
    public final z90 X;
    public final y90 Y;
    public final Handler Z;
    public final Rect a0;
    public Rect b0;
    public boolean c0;
    public final PopupWindow d0;

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                e0 = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                f0 = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, R$attr.listPopupWindowStyle);
    }

    public gq a(Context context, boolean z) {
        return new gq(context, z);
    }

    @Override // defpackage.qv0
    public final boolean b() {
        return this.d0.isShowing();
    }

    public final int c() {
        return this.J;
    }

    @Override // defpackage.qv0
    public final void d() {
        int i;
        int paddingBottom;
        gq gqVar;
        gq gqVar2 = this.G;
        PopupWindow popupWindow = this.d0;
        Context context = this.E;
        if (gqVar2 == null) {
            gq gqVarA = a(context, !this.c0);
            this.G = gqVarA;
            gqVarA.setAdapter(this.F);
            this.G.setOnItemClickListener(this.T);
            this.G.setFocusable(true);
            this.G.setFocusableInTouchMode(true);
            this.G.setOnItemSelectedListener(new v90(0, this));
            this.G.setOnScrollListener(this.X);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.U;
            if (onItemSelectedListener != null) {
                this.G.setOnItemSelectedListener(onItemSelectedListener);
            }
            popupWindow.setContentView(this.G);
        }
        Drawable background = popupWindow.getBackground();
        Rect rect = this.a0;
        if (background != null) {
            background.getPadding(rect);
            int i2 = rect.top;
            i = rect.bottom + i2;
            if (!this.M) {
                this.K = -i2;
            }
        } else {
            rect.setEmpty();
            i = 0;
        }
        int iA = w90.a(popupWindow, this.S, this.K, popupWindow.getInputMethodMode() == 2);
        int i3 = this.H;
        if (i3 == -1) {
            paddingBottom = iA + i;
        } else {
            int i4 = this.I;
            int iA2 = this.G.a(i4 != -2 ? i4 != -1 ? View.MeasureSpec.makeMeasureSpec(i4, 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), Integer.MIN_VALUE), iA);
            paddingBottom = iA2 + (iA2 > 0 ? this.G.getPaddingBottom() + this.G.getPaddingTop() + i : 0);
        }
        boolean z = this.d0.getInputMethodMode() == 2;
        cl0.d(popupWindow, this.L);
        if (popupWindow.isShowing()) {
            View view = this.S;
            WeakHashMap weakHashMap = f51.a;
            if (view.isAttachedToWindow()) {
                int width = this.I;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = this.S.getWidth();
                }
                if (i3 == -1) {
                    i3 = z ? paddingBottom : -1;
                    if (z) {
                        popupWindow.setWidth(this.I == -1 ? -1 : 0);
                        popupWindow.setHeight(0);
                    } else {
                        popupWindow.setWidth(this.I == -1 ? -1 : 0);
                        popupWindow.setHeight(-1);
                    }
                } else if (i3 == -2) {
                    i3 = paddingBottom;
                }
                popupWindow.setOutsideTouchable(true);
                int i5 = width;
                popupWindow.update(this.S, this.J, this.K, i5 < 0 ? -1 : i5, i3 < 0 ? -1 : i3);
                return;
            }
            return;
        }
        int width2 = this.I;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = this.S.getWidth();
        }
        if (i3 == -1) {
            i3 = -1;
        } else if (i3 == -2) {
            i3 = paddingBottom;
        }
        popupWindow.setWidth(width2);
        popupWindow.setHeight(i3);
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = e0;
            if (method != null) {
                try {
                    method.invoke(popupWindow, Boolean.TRUE);
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                }
            }
        } else {
            x90.b(popupWindow, true);
        }
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchInterceptor(this.W);
        if (this.O) {
            cl0.c(popupWindow, this.N);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method2 = f0;
            if (method2 != null) {
                try {
                    method2.invoke(popupWindow, this.b0);
                } catch (Exception e) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
        } else {
            x90.a(popupWindow, this.b0);
        }
        popupWindow.showAsDropDown(this.S, this.J, this.K, this.P);
        this.G.setSelection(-1);
        if ((!this.c0 || this.G.isInTouchMode()) && (gqVar = this.G) != null) {
            gqVar.setListSelectionHidden(true);
            gqVar.requestLayout();
        }
        if (this.c0) {
            return;
        }
        this.Z.post(this.Y);
    }

    @Override // defpackage.qv0
    public final void dismiss() {
        PopupWindow popupWindow = this.d0;
        popupWindow.dismiss();
        popupWindow.setContentView(null);
        this.G = null;
        this.Z.removeCallbacks(this.V);
    }

    public final Drawable e() {
        return this.d0.getBackground();
    }

    public final void g(Drawable drawable) {
        this.d0.setBackgroundDrawable(drawable);
    }

    @Override // defpackage.qv0
    public final gq h() {
        return this.G;
    }

    public final void k(int i) {
        this.K = i;
        this.M = true;
    }

    public final void m(int i) {
        this.J = i;
    }

    public final int o() {
        if (this.M) {
            return this.K;
        }
        return 0;
    }

    public void q(ListAdapter listAdapter) {
        wj wjVar = this.R;
        if (wjVar == null) {
            this.R = new wj(1, this);
        } else {
            ListAdapter listAdapter2 = this.F;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(wjVar);
            }
        }
        this.F = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.R);
        }
        gq gqVar = this.G;
        if (gqVar != null) {
            gqVar.setAdapter(this.F);
        }
    }

    public final void r(int i) {
        Drawable background = this.d0.getBackground();
        if (background == null) {
            this.I = i;
            return;
        }
        Rect rect = this.a0;
        background.getPadding(rect);
        this.I = rect.left + rect.right + i;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.d0.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.T = onItemClickListener;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.U = onItemSelectedListener;
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.H = -2;
        this.I = -2;
        this.L = CloseFrame.PROTOCOL_ERROR;
        this.P = 0;
        this.Q = Integer.MAX_VALUE;
        this.V = new y90(this, 1);
        this.W = new aa0(0, this);
        this.X = new z90(this);
        this.Y = new y90(this, 0);
        this.a0 = new Rect();
        this.E = context;
        this.Z = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPopupWindow, i, 0);
        this.J = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.K = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.M = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        AppCompatPopupWindow appCompatPopupWindow = new AppCompatPopupWindow(context, attributeSet, i, 0);
        appCompatPopupWindow.a(context, attributeSet, i);
        this.d0 = appCompatPopupWindow;
        appCompatPopupWindow.setInputMethodMode(1);
    }
}
