package defpackage;

import android.view.ViewTreeObserver;
import com.mamimobi.videostream.view.PatternLockView;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public final class ok0 implements ViewTreeObserver.OnWindowAttachListener {
    public final /* synthetic */ CountDownLatch a;

    public ok0(CountDownLatch countDownLatch) {
        this.a = countDownLatch;
    }

    @Override // android.view.ViewTreeObserver.OnWindowAttachListener
    public final void onWindowAttached() {
        pk0.h.set(true);
        this.a.countDown();
    }

    @Override // android.view.ViewTreeObserver.OnWindowAttachListener
    public final void onWindowDetached() {
        pk0.h.set(false);
        try {
            WeakReference weakReference = pk0.b;
            PatternLockView patternLockView = weakReference != null ? (PatternLockView) weakReference.get() : null;
            if (patternLockView == null || !patternLockView.getViewTreeObserver().isAlive()) {
                return;
            }
            patternLockView.getViewTreeObserver().removeOnWindowAttachListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
