package defpackage;

import android.content.res.Resources;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ze0 {
    public final Resources a;
    public final Resources.Theme b;

    public ze0(Resources resources, Resources.Theme theme) {
        this.a = resources;
        this.b = theme;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ze0.class == obj.getClass()) {
            ze0 ze0Var = (ze0) obj;
            if (this.a.equals(ze0Var.a) && Objects.equals(this.b, ze0Var.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }
}
