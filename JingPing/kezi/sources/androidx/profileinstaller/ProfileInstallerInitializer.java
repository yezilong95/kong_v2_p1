package androidx.profileinstaller;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import defpackage.cy;
import defpackage.yo;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public class ProfileInstallerInitializer implements cy {
    @Override // defpackage.cy
    public final Object create(Context context) {
        final Context applicationContext = context.getApplicationContext();
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: jc0
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                this.a.getClass();
                (Build.VERSION.SDK_INT >= 28 ? Handler.createAsync(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new h4(applicationContext, 1), new Random().nextInt(Math.max(CloseFrame.NORMAL, 1)) + 5000);
            }
        });
        return new yo(14);
    }

    @Override // defpackage.cy
    public final List dependencies() {
        return Collections.EMPTY_LIST;
    }
}
