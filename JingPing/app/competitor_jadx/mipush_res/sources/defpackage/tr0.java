package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.ScrollingTabContainerView;

/* JADX INFO: loaded from: classes.dex */
public final class tr0 extends BaseAdapter {
    public final /* synthetic */ ScrollingTabContainerView E;

    public tr0(ScrollingTabContainerView scrollingTabContainerView) {
        this.E = scrollingTabContainerView;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.E.F.getChildCount();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        ((ur0) this.E.F.getChildAt(i)).getClass();
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
        ScrollingTabContainerView scrollingTabContainerView = this.E;
        new ur0(scrollingTabContainerView, scrollingTabContainerView.getContext());
        throw null;
    }
}
