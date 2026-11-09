package defpackage;

import org.webrtc.MediaStreamTrack;
import org.webrtc.ScreenCapturerAndroid;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

/* JADX INFO: loaded from: classes.dex */
public final class or0 {
    public ScreenCapturerAndroid a;
    public VideoSource b;
    public VideoTrack c;
    public SurfaceTextureHelper d;

    /* JADX WARN: Removed duplicated region for block: B:19:0x001e A[Catch: all -> 0x0009, TRY_LEAVE, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0005, B:11:0x000f, B:14:0x0014, B:18:0x001c, B:19:0x001e, B:21:0x0022, B:25:0x002a, B:26:0x002c, B:28:0x0030, B:30:0x0035, B:32:0x0039, B:36:0x0041, B:35:0x003e, B:29:0x0033, B:24:0x0027, B:17:0x0019, B:10:0x000c), top: B:48:0x0001, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x002c A[Catch: all -> 0x0009, TRY_LEAVE, TryCatch #3 {, blocks: (B:3:0x0001, B:5:0x0005, B:11:0x000f, B:14:0x0014, B:18:0x001c, B:19:0x001e, B:21:0x0022, B:25:0x002a, B:26:0x002c, B:28:0x0030, B:30:0x0035, B:32:0x0039, B:36:0x0041, B:35:0x003e, B:29:0x0033, B:24:0x0027, B:17:0x0019, B:10:0x000c), top: B:48:0x0001, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0014 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0022 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized void a() {
        /*
            r2 = this;
            monitor-enter(r2)
            org.webrtc.ScreenCapturerAndroid r0 = r2.a     // Catch: java.lang.Throwable -> L9
            if (r0 == 0) goto Lf
            r0.stopCapture()     // Catch: java.lang.Throwable -> L9 java.lang.Exception -> Lb
            goto Lf
        L9:
            r0 = move-exception
            goto L45
        Lb:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L9
        Lf:
            org.webrtc.ScreenCapturerAndroid r0 = r2.a     // Catch: java.lang.Throwable -> L9
            r1 = 0
            if (r0 == 0) goto L1e
            r0.dispose()     // Catch: java.lang.Throwable -> L9 java.lang.Exception -> L18
            goto L1c
        L18:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L9
        L1c:
            r2.a = r1     // Catch: java.lang.Throwable -> L9
        L1e:
            org.webrtc.VideoSource r0 = r2.b     // Catch: java.lang.Throwable -> L9
            if (r0 == 0) goto L2c
            r0.dispose()     // Catch: java.lang.Throwable -> L9 java.lang.Exception -> L26
            goto L2a
        L26:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L9
        L2a:
            r2.b = r1     // Catch: java.lang.Throwable -> L9
        L2c:
            org.webrtc.VideoTrack r0 = r2.c     // Catch: java.lang.Throwable -> L9
            if (r0 == 0) goto L35
            r0.dispose()     // Catch: java.lang.Throwable -> L9 java.lang.Exception -> L33
        L33:
            r2.c = r1     // Catch: java.lang.Throwable -> L9
        L35:
            org.webrtc.SurfaceTextureHelper r0 = r2.d     // Catch: java.lang.Throwable -> L9
            if (r0 == 0) goto L43
            r0.dispose()     // Catch: java.lang.Throwable -> L9 java.lang.Exception -> L3d
            goto L41
        L3d:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L9
        L41:
            r2.d = r1     // Catch: java.lang.Throwable -> L9
        L43:
            monitor-exit(r2)
            return
        L45:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L9
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.or0.a():void");
    }

    public final boolean b() {
        try {
            VideoTrack videoTrack = this.c;
            if (videoTrack == null || videoTrack.state() == MediaStreamTrack.State.ENDED) {
                return false;
            }
            return this.c.state() == MediaStreamTrack.State.LIVE;
        } catch (IllegalStateException | Exception unused) {
            return false;
        }
    }
}
