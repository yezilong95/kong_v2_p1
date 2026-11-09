package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ListMenuItemView;
import defpackage.ce0;
import defpackage.de0;
import defpackage.gq;
import defpackage.oe0;
import defpackage.p50;
import defpackage.wd0;
import defpackage.zd0;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class d extends ListPopupWindow implements ce0 {
    public static final Method h0;
    public p50 g0;

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                h0 = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.appcompat.widget.MenuPopupWindow$MenuDropDownListView, gq] */
    @Override // androidx.appcompat.widget.ListPopupWindow
    public final gq a(final Context context, final boolean z) {
        ?? r0 = new gq(context, z) { // from class: androidx.appcompat.widget.MenuPopupWindow$MenuDropDownListView
            public final int Q;
            public final int R;
            public ce0 S;
            public de0 T;

            {
                super(context, z);
                if (1 == oe0.a(context.getResources().getConfiguration())) {
                    this.Q = 21;
                    this.R = 22;
                } else {
                    this.Q = 22;
                    this.R = 21;
                }
            }

            @Override // defpackage.gq, android.view.View
            public final boolean onHoverEvent(MotionEvent motionEvent) {
                wd0 wd0Var;
                int headersCount;
                int iPointToPosition;
                int i;
                if (this.S != null) {
                    ListAdapter adapter = getAdapter();
                    if (adapter instanceof HeaderViewListAdapter) {
                        HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                        headersCount = headerViewListAdapter.getHeadersCount();
                        wd0Var = (wd0) headerViewListAdapter.getWrappedAdapter();
                    } else {
                        wd0Var = (wd0) adapter;
                        headersCount = 0;
                    }
                    de0 de0VarB = (motionEvent.getAction() == 10 || (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i = iPointToPosition - headersCount) < 0 || i >= wd0Var.getCount()) ? null : wd0Var.b(i);
                    de0 de0Var = this.T;
                    if (de0Var != de0VarB) {
                        zd0 zd0Var = wd0Var.E;
                        if (de0Var != null) {
                            this.S.i(zd0Var, de0Var);
                        }
                        this.T = de0VarB;
                        if (de0VarB != null) {
                            this.S.j(zd0Var, de0VarB);
                        }
                    }
                }
                return super.onHoverEvent(motionEvent);
            }

            @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
            public final boolean onKeyDown(int i, KeyEvent keyEvent) {
                ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
                if (listMenuItemView != null && i == this.Q) {
                    if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                        performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                    }
                    return true;
                }
                if (listMenuItemView == null || i != this.R) {
                    return super.onKeyDown(i, keyEvent);
                }
                setSelection(-1);
                ListAdapter adapter = getAdapter();
                (adapter instanceof HeaderViewListAdapter ? (wd0) ((HeaderViewListAdapter) adapter).getWrappedAdapter() : (wd0) adapter).E.c(false);
                return true;
            }

            public void setHoverListener(ce0 ce0Var) {
                this.S = ce0Var;
            }

            @Override // defpackage.gq, android.widget.AbsListView
            public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
                super.setSelector(drawable);
            }
        };
        r0.setHoverListener(this);
        return r0;
    }

    @Override // defpackage.ce0
    public final void i(zd0 zd0Var, de0 de0Var) {
        p50 p50Var = this.g0;
        if (p50Var != null) {
            p50Var.i(zd0Var, de0Var);
        }
    }

    @Override // defpackage.ce0
    public final void j(zd0 zd0Var, de0 de0Var) {
        p50 p50Var = this.g0;
        if (p50Var != null) {
            p50Var.j(zd0Var, de0Var);
        }
    }
}
