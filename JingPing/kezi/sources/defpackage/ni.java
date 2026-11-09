package defpackage;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class ni extends ThreadLocal {
    public final /* synthetic */ int a;

    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        switch (this.a) {
            case 0:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                simpleDateFormat.setLenient(false);
                simpleDateFormat.setTimeZone(kq0.e);
                return simpleDateFormat;
            default:
                return new Random();
        }
    }
}
