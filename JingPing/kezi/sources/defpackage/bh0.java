package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.ScrollingTabContainerView;

/* JADX INFO: loaded from: classes.dex */
public final class bh0 extends BaseAdapter {
    public final /* synthetic */ ScrollingTabContainerView b;

    public bh0(ScrollingTabContainerView scrollingTabContainerView) {
        this.b = scrollingTabContainerView;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.b.c.getChildCount();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        ((ch0) this.b.c.getChildAt(i)).getClass();
        return null;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view != null) {
            getItem(i);
            throw null;
        }
        getItem(i);
        ScrollingTabContainerView scrollingTabContainerView = this.b;
        new ch0(scrollingTabContainerView, scrollingTabContainerView.getContext());
        throw null;
    }
}
