package org.webrtc;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import defpackage.no;
import defpackage.ps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.webrtc.EglBase;
import org.webrtc.EglThread;
import org.webrtc.RenderSynchronizer;

/* JADX INFO: loaded from: classes.dex */
public class EglThread implements RenderSynchronizer.Listener {
    private final EglBase.EglConnection eglConnection;
    private final HandlerWithExceptionCallbacks handler;
    private final ReleaseMonitor releaseMonitor;
    private final RenderSynchronizer renderSynchronizer;
    private final List<RenderUpdate> pendingRenderUpdates = new ArrayList();
    private boolean renderWindowOpen = true;

    public static class HandlerWithExceptionCallbacks extends Handler {
        private final Object callbackLock;
        private final List<Runnable> exceptionCallbacks;

        public HandlerWithExceptionCallbacks(Looper looper) {
            super(looper);
            this.callbackLock = new Object();
            this.exceptionCallbacks = new ArrayList();
        }

        public void addExceptionCallback(Runnable runnable) {
            synchronized (this.callbackLock) {
                this.exceptionCallbacks.add(runnable);
            }
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) throws Exception {
            try {
                super.dispatchMessage(message);
            } catch (Exception e) {
                Logging.e("EglThread", "Exception on EglThread", e);
                synchronized (this.callbackLock) {
                    try {
                        Iterator<Runnable> it = this.exceptionCallbacks.iterator();
                        while (it.hasNext()) {
                            it.next().run();
                        }
                        throw e;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        public void removeExceptionCallback(Runnable runnable) {
            synchronized (this.callbackLock) {
                this.exceptionCallbacks.remove(runnable);
            }
        }
    }

    public interface ReleaseMonitor {
        boolean onRelease(EglThread eglThread);
    }

    public interface RenderUpdate {
        void update(boolean z);
    }

    private EglThread(ReleaseMonitor releaseMonitor, HandlerWithExceptionCallbacks handlerWithExceptionCallbacks, EglBase.EglConnection eglConnection, RenderSynchronizer renderSynchronizer) {
        this.releaseMonitor = releaseMonitor;
        this.handler = handlerWithExceptionCallbacks;
        this.eglConnection = eglConnection;
        this.renderSynchronizer = renderSynchronizer;
        if (renderSynchronizer != null) {
            renderSynchronizer.registerListener(this);
        }
    }

    public static EglThread create(ReleaseMonitor releaseMonitor, final EglBase.Context context, final int[] iArr, RenderSynchronizer renderSynchronizer) {
        HandlerThread handlerThread = new HandlerThread("EglThread");
        handlerThread.start();
        HandlerWithExceptionCallbacks handlerWithExceptionCallbacks = new HandlerWithExceptionCallbacks(handlerThread.getLooper());
        EglBase.EglConnection eglConnection = (EglBase.EglConnection) ThreadUtils.invokeAtFrontUninterruptibly(handlerWithExceptionCallbacks, new Callable() { // from class: os
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return EglThread.lambda$create$0(context, iArr);
            }
        });
        if (releaseMonitor == null) {
            releaseMonitor = new no(2);
        }
        return new EglThread(releaseMonitor, handlerWithExceptionCallbacks, eglConnection, renderSynchronizer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ EglBase.EglConnection lambda$create$0(EglBase.Context context, int[] iArr) throws Exception {
        return context == null ? EglBase.EglConnection.createEgl10(iArr) : EglBase.EglConnection.create(context, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$create$1(EglThread eglThread) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onRenderWindowClose$3() {
        this.renderWindowOpen = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onRenderWindowOpen$2() {
        this.renderWindowOpen = true;
        Iterator<RenderUpdate> it = this.pendingRenderUpdates.iterator();
        while (it.hasNext()) {
            it.next().update(false);
        }
        this.pendingRenderUpdates.clear();
    }

    public void addExceptionCallback(Runnable runnable) {
        this.handler.addExceptionCallback(runnable);
    }

    public EglBase createEglBaseWithSharedConnection() {
        return EglBase.create(this.eglConnection);
    }

    public Handler getHandler() {
        return this.handler;
    }

    @Override // org.webrtc.RenderSynchronizer.Listener
    public void onRenderWindowClose() {
        this.handler.post(new ps(this, 1));
    }

    @Override // org.webrtc.RenderSynchronizer.Listener
    public void onRenderWindowOpen() {
        this.handler.post(new ps(this, 0));
    }

    public void release() {
        if (this.releaseMonitor.onRelease(this)) {
            RenderSynchronizer renderSynchronizer = this.renderSynchronizer;
            if (renderSynchronizer != null) {
                renderSynchronizer.removeListener(this);
            }
            HandlerWithExceptionCallbacks handlerWithExceptionCallbacks = this.handler;
            EglBase.EglConnection eglConnection = this.eglConnection;
            Objects.requireNonNull(eglConnection);
            handlerWithExceptionCallbacks.post(new defpackage.e(17, eglConnection));
            this.handler.getLooper().quitSafely();
        }
    }

    public void removeExceptionCallback(Runnable runnable) {
        this.handler.removeExceptionCallback(runnable);
    }

    public void scheduleRenderUpdate(RenderUpdate renderUpdate) {
        if (this.renderWindowOpen) {
            renderUpdate.update(true);
        } else {
            this.pendingRenderUpdates.add(renderUpdate);
        }
    }

    public static EglThread create(ReleaseMonitor releaseMonitor, EglBase.Context context, int[] iArr) {
        return create(releaseMonitor, context, iArr, null);
    }
}
