package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.widget.ActivityChooserView;

/* JADX INFO: loaded from: classes.dex */
public final class s3 extends BaseAdapter {
    public final /* synthetic */ ActivityChooserView E;

    public s3(ActivityChooserView activityChooserView) {
        this.E = activityChooserView;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        throw null;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        throw null;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final int getItemViewType(int i) {
        return 0;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        ActivityChooserView activityChooserView = this.E;
        if (view == null || view.getId() != R$id.list_item) {
            view = LayoutInflater.from(activityChooserView.getContext()).inflate(R$layout.abc_activity_chooser_view_list_item, viewGroup, false);
        }
        activityChooserView.getContext().getPackageManager();
        throw null;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final int getViewTypeCount() {
        return 3;
    }
}
