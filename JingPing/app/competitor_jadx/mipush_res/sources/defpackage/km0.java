package defpackage;

import android.content.pm.PackageManager;
import android.os.Build;
import com.mamimobi.videostream.model.Event;
import com.mamimobi.videostream.model.MessageType;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import org.java_websocket.client.WebSocketClient;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class km0 extends WebSocketClient {
    public final /* synthetic */ lm0 E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public km0(lm0 lm0Var, URI uri) {
        super(uri);
        this.E = lm0Var;
    }

    @Override // org.java_websocket.client.WebSocketClient
    public final void onClose(int i, String str, boolean z) throws PackageManager.NameNotFoundException {
        lm0 lm0Var = this.E;
        lm0Var.i.set(false);
        Event event = new Event();
        event.code = i;
        event.message = str;
        lm0.a(lm0Var, MessageType.SOCKET_CLOSE, event);
        vl0 vl0Var = lm0Var.h;
        synchronized (vl0Var) {
            double d = vl0Var.a;
            vl0Var.a = ((0.0d - d) * 0.15d) + d;
        }
        lm0Var.f.removeCallbacks(lm0Var.l);
        lm0Var.g();
    }

    @Override // org.java_websocket.client.WebSocketClient
    public final void onError(Exception exc) throws PackageManager.NameNotFoundException {
        Event event = new Event();
        event.message = exc.getMessage();
        MessageType messageType = MessageType.SOCKET_ERROR;
        lm0 lm0Var = this.E;
        lm0.a(lm0Var, messageType, event);
        km0 km0Var = lm0Var.a;
        if (km0Var == null || km0Var.isOpen()) {
            return;
        }
        lm0Var.f.removeCallbacks(lm0Var.l);
        vl0 vl0Var = lm0Var.h;
        synchronized (vl0Var) {
            double d = vl0Var.a;
            vl0Var.a = ((0.0d - d) * 0.15d) + d;
        }
        lm0Var.g();
    }

    @Override // org.java_websocket.client.WebSocketClient
    public final void onMessage(String str) {
        this.E.k.execute(new p7(this, 13, str));
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0061  */
    @Override // org.java_websocket.client.WebSocketClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onOpen(org.java_websocket.handshake.ServerHandshake r6) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.km0.onOpen(org.java_websocket.handshake.ServerHandshake):void");
    }

    @Override // org.java_websocket.client.WebSocketClient
    public final void onMessage(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            JSONObject jSONObject = new JSONObject(new String(bArr));
            String str = Build.MANUFACTURER;
            HashMap map = str.equalsIgnoreCase("oppo") ? e70.a : str.equalsIgnoreCase("samsung") ? e70.b : str.equalsIgnoreCase("xiaomi") ? e70.c : str.equalsIgnoreCase("oneplus") ? e70.d : null;
            if (map != null) {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    String[] strArrSplit = jSONObject.getString(next).split(",");
                    map.put(next, new float[]{Float.parseFloat(strArrSplit[0]), Float.parseFloat(strArrSplit[1]), d91.c(36.0f)});
                }
            }
        } catch (Exception unused) {
        }
    }
}
