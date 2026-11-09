package defpackage;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.a;

/* JADX INFO: loaded from: classes.dex */
public final class ss extends a {
    public final /* synthetic */ int a;

    public /* synthetic */ ss(int i) {
        this.a = i;
    }

    @Override // com.google.android.material.tabs.a
    public final void b(TabLayout tabLayout, View view, View view2, float f, Drawable drawable) {
        float fCos;
        float fCos2;
        switch (this.a) {
            case 0:
                RectF rectFA = a.a(tabLayout, view);
                RectF rectFA2 = a.a(tabLayout, view2);
                if (rectFA.left < rectFA2.left) {
                    double d = (((double) f) * 3.141592653589793d) / 2.0d;
                    fCos2 = (float) (1.0d - Math.cos(d));
                    fCos = (float) Math.sin(d);
                } else {
                    double d2 = (((double) f) * 3.141592653589793d) / 2.0d;
                    float fSin = (float) Math.sin(d2);
                    fCos = (float) (1.0d - Math.cos(d2));
                    fCos2 = fSin;
                }
                drawable.setBounds(y4.c((int) rectFA.left, fCos2, (int) rectFA2.left), drawable.getBounds().top, y4.c((int) rectFA.right, fCos, (int) rectFA2.right), drawable.getBounds().bottom);
                break;
            default:
                if (f >= 0.5f) {
                    view = view2;
                }
                RectF rectFA3 = a.a(tabLayout, view);
                float fB = f < 0.5f ? y4.b(1.0f, 0.0f, 0.0f, 0.5f, f) : y4.b(0.0f, 1.0f, 0.5f, 1.0f, f);
                drawable.setBounds((int) rectFA3.left, drawable.getBounds().top, (int) rectFA3.right, drawable.getBounds().bottom);
                drawable.setAlpha((int) (fB * 255.0f));
                break;
        }
    }
}
