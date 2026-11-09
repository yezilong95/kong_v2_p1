package defpackage;

import androidx.work.WorkRequest;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.WorkerUpdater;
import androidx.work.impl.utils.futures.SettableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import org.webrtc.EglRenderer;
import org.webrtc.VideoFileRenderer;
import org.webrtc.VideoFrame;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xm implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ xm(Object obj, Object obj2, Object obj3, int i) {
        this.b = i;
        this.c = obj;
        this.d = obj2;
        this.e = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                ((EglRenderer) this.c).lambda$removeFrameListener$2((CountDownLatch) this.d, (EglRenderer.FrameListener) this.e);
                return;
            case 1:
                g1 g1Var = (g1) this.c;
                za0 za0Var = (za0) this.d;
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) this.e;
                g1Var.getClass();
                try {
                    qs qsVarK = za0.k(g1Var.a);
                    if (qsVarK == null) {
                        throw new RuntimeException("EmojiCompat font provider not available on this device.");
                    }
                    ps psVar = (ps) ((gn) qsVarK.b);
                    synchronized (psVar.d) {
                        psVar.f = threadPoolExecutor;
                        break;
                    }
                    ((gn) qsVarK.b).a(new jn(za0Var, threadPoolExecutor));
                    return;
                } catch (Throwable th) {
                    za0Var.F(th);
                    threadPoolExecutor.shutdown();
                    return;
                }
            case 2:
                ((VideoFileRenderer) this.c).lambda$renderFrameOnRenderThread$1((VideoFrame.I420Buffer) this.d, (VideoFrame) this.e);
                return;
            default:
                WorkerUpdater.updateWorkImpl$lambda$2((SettableFuture) this.c, (WorkManagerImpl) this.d, (WorkRequest) this.e);
                return;
        }
    }
}
