package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class cn0 {
    public final /* synthetic */ RecyclerView a;

    public void a(int i) {
        RecyclerView recyclerView = this.a;
        View childAt = recyclerView.getChildAt(i);
        if (childAt != null) {
            recyclerView.n(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeViewAt(i);
    }
}
