package defpackage;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.work.impl.WorkDatabase;
import com.google.android.material.bottomsheet.BottomSheetDragHandleView;
import com.mamimobi.shell.activity.MainActivity;
import java.util.concurrent.ExecutorService;
import org.webrtc.VideoFrame;
import org.webrtc.VideoSink;
import org.webrtc.VideoSource;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h9 implements y0, f2, VideoSink, sl0 {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ h9(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // defpackage.sl0
    public tl0 a(rl0 rl0Var) {
        return WorkDatabase.Companion.create$lambda$0((Context) this.c, rl0Var);
    }

    @Override // defpackage.f2
    public void b(Object obj) {
        ActivityResult activityResult = (ActivityResult) obj;
        ExecutorService executorService = MainActivity.P;
        MainActivity mainActivity = (MainActivity) this.c;
        if (activityResult.b == -1) {
            mainActivity.I = true;
            mainActivity.L = false;
            mainActivity.o();
        } else {
            mainActivity.I = false;
            mainActivity.L = false;
            Toast.makeText(mainActivity, "VPN permission must be granted to use this app.", 1).show();
            mainActivity.B.postDelayed(new x30(mainActivity, 2), activityResult.b == 0 ? 3000L : 2000L);
        }
    }

    @Override // defpackage.y0
    public boolean c(View view) {
        int i = BottomSheetDragHandleView.n;
        return ((BottomSheetDragHandleView) this.c).c();
    }

    public boolean d(o70 o70Var, int i, Bundle bundle) {
        sg o70Var2;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 25 && (i & 1) != 0) {
            try {
                ((gy) o70Var.c).a();
                Parcelable parcelable = (Parcelable) ((gy) o70Var.c).e();
                bundle = bundle == null ? new Bundle() : new Bundle(bundle);
                bundle.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", parcelable);
            } catch (Exception e) {
                Log.w("InputConnectionCompat", "Can't insert content from IME; requestPermission() failed", e);
                return false;
            }
        }
        ClipDescription clipDescriptionD = ((gy) o70Var.c).d();
        gy gyVar = (gy) o70Var.c;
        ClipData clipData = new ClipData(clipDescriptionD, new ClipData.Item(gyVar.i()));
        if (i2 >= 31) {
            o70Var2 = new o70(clipData, 2);
        } else {
            tg tgVar = new tg();
            tgVar.c = clipData;
            tgVar.d = 2;
            o70Var2 = tgVar;
        }
        o70Var2.l(gyVar.b());
        o70Var2.j(bundle);
        return xr0.k((AppCompatEditText) this.c, o70Var2.d()) == null;
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        ((VideoSource) this.c).lambda$setVideoProcessor$1(videoFrame);
    }
}
