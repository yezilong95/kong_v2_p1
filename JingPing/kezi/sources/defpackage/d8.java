package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.progressindicator.BaseProgressIndicator;

/* JADX INFO: loaded from: classes.dex */
public final class d8 {
    public k3 a;
    public final /* synthetic */ int b;
    public final /* synthetic */ View c;

    public /* synthetic */ d8(View view, int i) {
        this.b = i;
        this.c = view;
    }

    public final void a(Drawable drawable) {
        switch (this.b) {
            case 0:
                BaseProgressIndicator baseProgressIndicator = (BaseProgressIndicator) this.c;
                baseProgressIndicator.setIndeterminate(false);
                baseProgressIndicator.b(baseProgressIndicator.c);
                break;
            case 1:
                BaseProgressIndicator baseProgressIndicator2 = (BaseProgressIndicator) this.c;
                if (!baseProgressIndicator2.g) {
                    baseProgressIndicator2.setVisibility(baseProgressIndicator2.h);
                }
                break;
            default:
                ColorStateList colorStateList = ((MaterialCheckBox) this.c).p;
                if (colorStateList != null) {
                    hl.h(drawable, colorStateList);
                }
                break;
        }
    }

    public void b(Drawable drawable) {
        switch (this.b) {
            case 2:
                MaterialCheckBox materialCheckBox = (MaterialCheckBox) this.c;
                ColorStateList colorStateList = materialCheckBox.p;
                if (colorStateList != null) {
                    hl.g(drawable, colorStateList.getColorForState(materialCheckBox.t, colorStateList.getDefaultColor()));
                }
                break;
        }
    }

    public final void c(Drawable drawable) {
    }
}
