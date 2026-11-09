package defpackage;

import androidx.work.impl.utils.IdGenerator;
import java.util.concurrent.Callable;
import org.webrtc.TextureBufferImpl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class lx implements Callable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ lx(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.a) {
            case 0:
                return IdGenerator.nextAlarmManagerId$lambda$1((IdGenerator) this.b);
            default:
                return ((TextureBufferImpl) this.b).lambda$toI420$1();
        }
    }
}
