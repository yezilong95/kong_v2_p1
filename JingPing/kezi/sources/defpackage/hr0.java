package defpackage;

import android.text.TextUtils;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class hr0 extends f40 {
    public final /* synthetic */ int f;

    public hr0(int i, Class cls, int i2, int i3, int i4) {
        this.f = i4;
        this.b = i;
        this.e = cls;
        this.d = i2;
        this.c = i3;
    }

    @Override // defpackage.f40
    public final Object c(View view) {
        switch (this.f) {
            case 0:
                return Boolean.valueOf(qr0.d(view));
            case 1:
                return qr0.b(view);
            case 2:
                return sr0.b(view);
            default:
                return Boolean.valueOf(qr0.c(view));
        }
    }

    @Override // defpackage.f40
    public final void d(View view, Object obj) {
        switch (this.f) {
            case 0:
                qr0.j(view, ((Boolean) obj).booleanValue());
                break;
            case 1:
                qr0.h(view, (CharSequence) obj);
                break;
            case 2:
                sr0.f(view, (CharSequence) obj);
                break;
            default:
                qr0.g(view, ((Boolean) obj).booleanValue());
                break;
        }
    }

    @Override // defpackage.f40
    public final boolean g(Object obj, Object obj2) {
        switch (this.f) {
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
