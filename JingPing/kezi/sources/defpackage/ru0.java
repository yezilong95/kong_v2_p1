package defpackage;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ru0 extends qu0 {
    public ru0(xu0 xu0Var, WindowInsets windowInsets) {
        super(xu0Var, windowInsets);
    }

    @Override // defpackage.uu0
    public xu0 a() {
        return xu0.h(null, this.c.consumeDisplayCutout());
    }

    @Override // defpackage.uu0
    public cl e() {
        DisplayCutout displayCutout = this.c.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new cl(displayCutout);
    }

    @Override // defpackage.pu0, defpackage.uu0
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ru0)) {
            return false;
        }
        ru0 ru0Var = (ru0) obj;
        return Objects.equals(this.c, ru0Var.c) && Objects.equals(this.g, ru0Var.g);
    }

    @Override // defpackage.uu0
    public int hashCode() {
        return this.c.hashCode();
    }
}
