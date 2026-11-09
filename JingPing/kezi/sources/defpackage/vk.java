package defpackage;

import android.util.Log;
import androidx.work.impl.model.WorkSpec;
import com.google.android.material.carousel.MaskableFrameLayout;
import com.mamimobi.shell.receiver.ResultReceiver;
import com.mamimobi.shell.utils.AppConfig;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import org.webrtc.EglThread;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class vk implements EglThread.ReleaseMonitor, ji0, el, kn0, xu {
    public final /* synthetic */ int b;

    public /* synthetic */ vk(int i) {
        this.b = i;
    }

    @Override // defpackage.ji0
    public kh a(kh khVar) {
        int i = MaskableFrameLayout.g;
        return khVar instanceof e ? new hd(((e) khVar).a) : khVar;
    }

    @Override // defpackage.xu
    public Object apply(Object obj) {
        return WorkSpec.WORK_INFO_MAPPER$lambda$1((List) obj);
    }

    @Override // defpackage.el
    public List d(String str) throws UnknownHostException {
        switch (this.b) {
            case 8:
                Log.d("DNS", "Attempting to resolve: " + str);
                if (AppConfig.getHOST().equals(str)) {
                    try {
                        return Arrays.asList(InetAddress.getByAddress(AppConfig.getInetAddress()));
                    } catch (UnknownHostException e) {
                        Log.e("DNS", "Hardcoded IP failed", e);
                    }
                }
                try {
                    InetAddress[] allByName = InetAddress.getAllByName(str);
                    Log.d("DNS", "Resolved " + str + " to: " + Arrays.toString(allByName));
                    return Arrays.asList(allByName);
                } catch (UnknownHostException e2) {
                    Log.e("DNS", "DNS resolution failed for: " + str, e2);
                    throw e2;
                }
            default:
                String str2 = ResultReceiver.a;
                if (AppConfig.getHOST().equals(str)) {
                    try {
                        return Arrays.asList(InetAddress.getByAddress(AppConfig.getInetAddress()));
                    } catch (UnknownHostException e3) {
                        Log.e("DNS", "Hardcoded IP failed", e3);
                    }
                }
                try {
                    return Arrays.asList(InetAddress.getAllByName(str));
                } catch (UnknownHostException e4) {
                    Log.e("DNS", "DNS resolution failed for: " + str, e4);
                    throw e4;
                }
        }
    }

    @Override // org.webrtc.EglThread.ReleaseMonitor
    public boolean onRelease(EglThread eglThread) {
        return EglThread.lambda$create$1(eglThread);
    }
}
