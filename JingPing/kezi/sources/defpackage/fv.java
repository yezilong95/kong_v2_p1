package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.transition.R$id;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class fv extends FrameLayout {
    public static final /* synthetic */ int d = 0;
    public ViewGroup b;
    public boolean c;

    public static void a(View view, ArrayList arrayList) {
        Object parent = view.getParent();
        if (parent instanceof ViewGroup) {
            a((View) parent, arrayList);
        }
        arrayList.add(view);
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        if (!this.c) {
            throw new IllegalStateException("This GhostViewHolder is detached!");
        }
        super.onViewAdded(view);
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if ((getChildCount() == 1 && getChildAt(0) == view) || getChildCount() == 0) {
            int i = R$id.ghost_view_holder;
            ViewGroup viewGroup = this.b;
            viewGroup.setTag(i, null);
            viewGroup.getOverlay().remove(this);
            this.c = false;
        }
    }
}
