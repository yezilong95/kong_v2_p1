package defpackage;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.R$id;
import androidx.transition.Transition;
import androidx.transition.Visibility;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ot0 extends hp0 {
    public final /* synthetic */ ViewGroup a;
    public final /* synthetic */ View b;
    public final /* synthetic */ View c;
    public final /* synthetic */ Visibility d;

    public ot0(Visibility visibility, ViewGroup viewGroup, View view, View view2) {
        this.d = visibility;
        this.a = viewGroup;
        this.b = view;
        this.c = view2;
    }

    @Override // defpackage.hp0, defpackage.gp0
    public final void c() {
        this.a.getOverlay().remove(this.b);
    }

    @Override // defpackage.gp0
    public final void d(Transition transition) {
        this.c.setTag(R$id.save_overlay_view, null);
        this.a.getOverlay().remove(this.b);
        transition.u(this);
    }

    @Override // defpackage.hp0, defpackage.gp0
    public final void e() {
        View view = this.b;
        if (view.getParent() == null) {
            this.a.getOverlay().add(view);
            return;
        }
        Visibility visibility = this.d;
        ArrayList arrayList = visibility.n;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((Animator) arrayList.get(size)).cancel();
        }
        ArrayList arrayList2 = visibility.r;
        if (arrayList2 == null || arrayList2.size() <= 0) {
            return;
        }
        ArrayList arrayList3 = (ArrayList) visibility.r.clone();
        int size2 = arrayList3.size();
        for (int i = 0; i < size2; i++) {
            ((gp0) arrayList3.get(i)).a();
        }
    }
}
