package androidx.profileinstaller;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import defpackage.di;
import defpackage.f40;
import io.nats.client.Options;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class ProfileInstallerInitializer implements f40 {
    @Override // defpackage.f40
    public final Object create(Context context) {
        final Context applicationContext = context.getApplicationContext();
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: yl0
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                this.a.getClass();
                (Build.VERSION.SDK_INT >= 28 ? Handler.createAsync(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new l5(applicationContext, 1), new Random().nextInt(Math.max(1000, 1)) + Options.DEFAULT_MAX_MESSAGES_IN_OUTGOING_QUEUE);
            }
        });
        return new di(28);
    }

    @Override // defpackage.f40
    public final List dependencies() {
        return Collections.EMPTY_LIST;
    }
}
