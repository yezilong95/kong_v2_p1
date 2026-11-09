package defpackage;

import android.text.TextUtils;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class p41 extends ub0 {
    public final /* synthetic */ int I;

    public p41(int i, Class cls, int i2, int i3, int i4) {
        this.I = i4;
        this.E = i;
        this.H = cls;
        this.G = i2;
        this.F = i3;
    }

    @Override // defpackage.ub0
    public final Object c(View view) {
        switch (this.I) {
            case 0:
                return Boolean.valueOf(y41.d(view));
            case 1:
                return y41.b(view);
            case 2:
                return a51.b(view);
            default:
                return Boolean.valueOf(y41.c(view));
        }
    }

    @Override // defpackage.ub0
    public final void d(View view, Object obj) {
        switch (this.I) {
            case 0:
                y41.j(view, ((Boolean) obj).booleanValue());
                break;
            case 1:
                y41.h(view, (CharSequence) obj);
                break;
            case 2:
                a51.f(view, (CharSequence) obj);
                break;
            default:
                y41.g(view, ((Boolean) obj).booleanValue());
                break;
        }
    }

    @Override // defpackage.ub0
    public final boolean g(Object obj, Object obj2) {
        switch (this.I) {
            case 0:
                Boolean bool = (Boolean) obj;
                Boolean bool2 = (Boolean) obj2;
                return !((bool != null && bool.booleanValue()) == (bool2 != null && bool2.booleanValue()));
            case 1:
                return !TextUtils.equals((CharSequence) obj, (CharSequence) obj2);
            case 2:
                return !TextUtils.equals((CharSequence) obj, (CharSequence) obj2);
            default:
                Boolean bool3 = (Boolean) obj;
                Boolean bool4 = (Boolean) obj2;
                return !((bool3 != null && bool3.booleanValue()) == (bool4 != null && bool4.booleanValue()));
        }
    }
}
