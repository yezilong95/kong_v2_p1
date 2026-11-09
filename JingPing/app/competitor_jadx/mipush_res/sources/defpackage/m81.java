package defpackage;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class m81 extends l81 {
    public m81(s81 s81Var, WindowInsets windowInsets) {
        super(s81Var, windowInsets);
    }

    @Override // defpackage.p81
    public s81 a() {
        return s81.h(null, this.c.consumeDisplayCutout());
    }

    @Override // defpackage.p81
    public zo e() {
        DisplayCutout displayCutout = this.c.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new zo(displayCutout);
    }

    @Override // defpackage.k81, defpackage.p81
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m81)) {
            return false;
        }
        m81 m81Var = (m81) obj;
        return Objects.equals(this.c, m81Var.c) && Objects.equals(this.g, m81Var.g);
    }

    @Override // defpackage.p81
    public int hashCode() {
        return this.c.hashCode();
    }
}
