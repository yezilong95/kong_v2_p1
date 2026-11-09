package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public abstract class ie0 implements qv0, qe0, AdapterView.OnItemClickListener {
    public Rect E;

    public static int o(ListAdapter listAdapter, Context context, int i) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i2 = 0;
        int i3 = 0;
        FrameLayout frameLayout = null;
        View view = null;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = listAdapter.getItemViewType(i4);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            if (frameLayout == null) {
                frameLayout = new FrameLayout(context);
            }
            view = listAdapter.getView(i4, view, frameLayout);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i) {
                return i;
            }
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    public static boolean v(zd0 zd0Var) {
        int size = zd0Var.f.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = zd0Var.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.qe0
    public final boolean c(de0 de0Var) {
        return false;
    }

    @Override // defpackage.qe0
    public final boolean g(de0 de0Var) {
        return false;
    }

    @Override // defpackage.qe0
    public final int getId() {
        return 0;
    }

    public abstract void n(zd0 zd0Var);

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        (listAdapter instanceof HeaderViewListAdapter ? (wd0) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter() : (wd0) listAdapter).E.q((MenuItem) listAdapter.getItem(i), this, !(this instanceof uc) ? 0 : 4);
    }

    public abstract void p(View view);

    public abstract void q(boolean z);

    public abstract void r(int i);

    public abstract void s(int i);

    public abstract void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener);

    public abstract void t(boolean z);

    public abstract void u(int i);

    @Override // defpackage.qe0
    public final void i(Context context, zd0 zd0Var) {
    }
}
