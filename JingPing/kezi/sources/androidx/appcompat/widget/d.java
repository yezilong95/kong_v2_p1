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
import defpackage.cm;
import defpackage.f60;
import defpackage.i60;
import defpackage.l60;
import defpackage.m60;
import defpackage.o70;
import defpackage.x60;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class d extends ListPopupWindow implements l60 {
    public static final Method E;
    public o70 D;

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                E = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.appcompat.widget.MenuPopupWindow$MenuDropDownListView, cm] */
    @Override // androidx.appcompat.widget.ListPopupWindow
    public final cm a(final Context context, final boolean z) {
        ?? r0 = new cm(context, z) { // from class: androidx.appcompat.widget.MenuPopupWindow$MenuDropDownListView
            public final int n;
            public final int o;
            public l60 p;
            public m60 q;

            {
                super(context, z);
                if (1 == x60.a(context.getResources().getConfiguration())) {
                    this.n = 21;
                    this.o = 22;
                } else {
                    this.n = 22;
                    this.o = 21;
                }
            }

            @Override // defpackage.cm, android.view.View
            public final boolean onHoverEvent(MotionEvent motionEvent) {
                f60 f60Var;
                int headersCount;
                int iPointToPosition;
                int i;
                if (this.p != null) {
                    ListAdapter adapter = getAdapter();
                    if (adapter instanceof HeaderViewListAdapter) {
                        HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                        headersCount = headerViewListAdapter.getHeadersCount();
                        f60Var = (f60) headerViewListAdapter.getWrappedAdapter();
                    } else {
                        f60Var = (f60) adapter;
                        headersCount = 0;
                    }
                    m60 m60VarB = (motionEvent.getAction() == 10 || (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i = iPointToPosition - headersCount) < 0 || i >= f60Var.getCount()) ? null : f60Var.b(i);
                    m60 m60Var = this.q;
                    if (m60Var != m60VarB) {
                        i60 i60Var = f60Var.b;
                        if (m60Var != null) {
                            this.p.o(i60Var, m60Var);
                        }
                        this.q = m60VarB;
                        if (m60VarB != null) {
                            this.p.q(i60Var, m60VarB);
                        }
                    }
                }
                return super.onHoverEvent(motionEvent);
            }

            @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
            public final boolean onKeyDown(int i, KeyEvent keyEvent) {
                ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
                if (listMenuItemView != null && i == this.n) {
                    if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                        performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                    }
                    return true;
                }
                if (listMenuItemView == null || i != this.o) {
                    return super.onKeyDown(i, keyEvent);
                }
                setSelection(-1);
                ListAdapter adapter = getAdapter();
                (adapter instanceof HeaderViewListAdapter ? (f60) ((HeaderViewListAdapter) adapter).getWrappedAdapter() : (f60) adapter).b.c(false);
                return true;
            }

            public void setHoverListener(l60 l60Var) {
                this.p = l60Var;
            }

            @Override // defpackage.cm, android.widget.AbsListView
            public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
                super.setSelector(drawable);
            }
        };
        r0.setHoverListener(this);
        return r0;
    }

    @Override // defpackage.l60
    public final void o(i60 i60Var, m60 m60Var) {
        o70 o70Var = this.D;
        if (o70Var != null) {
            o70Var.o(i60Var, m60Var);
        }
    }

    @Override // defpackage.l60
    public final void q(i60 i60Var, m60 m60Var) {
        o70 o70Var = this.D;
        if (o70Var != null) {
            o70Var.q(i60Var, m60Var);
        }
    }
}
