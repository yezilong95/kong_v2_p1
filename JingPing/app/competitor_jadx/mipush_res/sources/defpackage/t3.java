package defpackage;

import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.b;

/* JADX INFO: loaded from: classes.dex */
public final class t3 implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
    public final /* synthetic */ ActivityChooserView E;

    public t3(ActivityChooserView activityChooserView) {
        this.E = activityChooserView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ActivityChooserView activityChooserView = this.E;
        s3 s3Var = activityChooserView.E;
        if (view == activityChooserView.J) {
            activityChooserView.a();
            s3Var.getClass();
            throw null;
        }
        if (view != activityChooserView.H) {
            throw new IllegalArgumentException();
        }
        s3Var.getClass();
        throw new IllegalStateException("No data model. Did you call #setDataModel?");
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        b bVar;
        zd0 zd0Var;
        ActivityChooserView activityChooserView = this.E;
        PopupWindow.OnDismissListener onDismissListener = activityChooserView.N;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
        m3 m3Var = activityChooserView.K;
        if (m3Var == null || (bVar = m3Var.a) == null || (zd0Var = bVar.G) == null) {
            return;
        }
        zd0Var.c(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        ((s3) adapterView.getAdapter()).getClass();
        ActivityChooserView activityChooserView = this.E;
        activityChooserView.a();
        activityChooserView.E.getClass();
        throw null;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        ActivityChooserView activityChooserView = this.E;
        if (view != activityChooserView.J) {
            throw new IllegalArgumentException();
        }
        activityChooserView.E.getClass();
        throw null;
    }
}
