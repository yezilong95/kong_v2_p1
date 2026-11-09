package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.progressindicator.BaseProgressIndicator;

/* JADX INFO: loaded from: classes.dex */
public final class z9 {
    public p4 a;
    public final /* synthetic */ int b;
    public final /* synthetic */ View c;

    public /* synthetic */ z9(View view, int i) {
        this.b = i;
        this.c = view;
    }

    public final void a(Drawable drawable) {
        switch (this.b) {
            case 0:
                BaseProgressIndicator baseProgressIndicator = (BaseProgressIndicator) this.c;
                baseProgressIndicator.setIndeterminate(false);
                baseProgressIndicator.b(baseProgressIndicator.F);
                break;
            case 1:
                BaseProgressIndicator baseProgressIndicator2 = (BaseProgressIndicator) this.c;
                if (!baseProgressIndicator2.J) {
                    baseProgressIndicator2.setVisibility(baseProgressIndicator2.K);
                }
                break;
            default:
                ColorStateList colorStateList = ((MaterialCheckBox) this.c).S;
                if (colorStateList != null) {
                    ip.h(drawable, colorStateList);
                }
                break;
        }
    }

    public void b(Drawable drawable) {
        switch (this.b) {
            case 2:
                MaterialCheckBox materialCheckBox = (MaterialCheckBox) this.c;
                ColorStateList colorStateList = materialCheckBox.S;
                if (colorStateList != null) {
                    ip.g(drawable, colorStateList.getColorForState(materialCheckBox.W, colorStateList.getDefaultColor()));
                }
                break;
        }
    }

    public final void c(Drawable drawable) {
    }
}
