package defpackage;

import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.b;

/* JADX INFO: loaded from: classes.dex */
public final class y1 implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
    public final /* synthetic */ ActivityChooserView b;

    public y1(ActivityChooserView activityChooserView) {
        this.b = activityChooserView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ActivityChooserView activityChooserView = this.b;
        x1 x1Var = activityChooserView.b;
        if (view == activityChooserView.g) {
            activityChooserView.a();
            x1Var.getClass();
            throw null;
        }
        if (view != activityChooserView.e) {
            throw new IllegalArgumentException();
        }
        x1Var.getClass();
        throw new IllegalStateException("No data model. Did you call #setDataModel?");
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        b bVar;
        i60 i60Var;
        ActivityChooserView activityChooserView = this.b;
        PopupWindow.OnDismissListener onDismissListener = activityChooserView.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
        r1 r1Var = activityChooserView.h;
        if (r1Var == null || (bVar = r1Var.a) == null || (i60Var = bVar.d) == null) {
            return;
        }
        i60Var.c(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        ((x1) adapterView.getAdapter()).getClass();
        ActivityChooserView activityChooserView = this.b;
        activityChooserView.a();
        activityChooserView.b.getClass();
        throw null;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        ActivityChooserView activityChooserView = this.b;
        if (view != activityChooserView.g) {
            throw new IllegalArgumentException();
        }
        activityChooserView.b.getClass();
        throw null;
    }
}
