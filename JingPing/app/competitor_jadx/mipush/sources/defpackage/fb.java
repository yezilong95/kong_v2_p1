package defpackage;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.work.impl.WorkDatabase;
import com.google.android.material.bottomsheet.BottomSheetDragHandleView;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.service.Service;
import io.nats.service.ServiceMessage;
import io.nats.service.ServiceMessageHandler;
import org.webrtc.VideoFrame;
import org.webrtc.VideoSink;
import org.webrtc.VideoSource;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fb implements u2, JsonUtils.ListAdder, JsonValueUtils.JsonValueSupplier, ServiceMessageHandler, VideoSink, gy0 {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ fb(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // defpackage.gy0
    public hy0 a(fy0 fy0Var) {
        return WorkDatabase.Companion.create$lambda$0((Context) this.F, fy0Var);
    }

    @Override // io.nats.client.support.JsonUtils.ListAdder
    public void append(StringBuilder sb, Object obj) {
        JsonUtils.lambda$_addStrings$0((StringBuilder) this.F, sb, (String) obj);
    }

    @Override // defpackage.u2
    public boolean b(View view) {
        int i = BottomSheetDragHandleView.Q;
        return ((BottomSheetDragHandleView) this.F).c();
    }

    public boolean c(p50 p50Var, int i, Bundle bundle) {
        hi p50Var2;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 25 && (i & 1) != 0) {
            try {
                ((j40) p50Var.F).f();
                Parcelable parcelable = (Parcelable) ((j40) p50Var.F).b();
                bundle = bundle == null ? new Bundle() : new Bundle(bundle);
                bundle.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", parcelable);
            } catch (Exception e) {
                Log.w("InputConnectionCompat", "Can't insert content from IME; requestPermission() failed", e);
                return false;
            }
        }
        ClipDescription clipDescriptionA = ((j40) p50Var.F).a();
        j40 j40Var = (j40) p50Var.F;
        ClipData clipData = new ClipData(clipDescriptionA, new ClipData.Item(j40Var.e()));
        if (i2 >= 31) {
            p50Var2 = new p50(clipData, 2);
        } else {
            ii iiVar = new ii();
            iiVar.F = clipData;
            iiVar.G = 2;
            p50Var2 = iiVar;
        }
        p50Var2.n(j40Var.h());
        p50Var2.e(bundle);
        return f51.k((AppCompatEditText) this.F, p50Var2.a()) == null;
    }

    @Override // io.nats.client.support.JsonValueUtils.JsonValueSupplier
    public Object get(JsonValue jsonValue) {
        switch (this.E) {
            case 4:
                return JsonValueUtils.lambda$readBoolean$11((Boolean) this.F, jsonValue);
            default:
                return JsonValueUtils.lambda$readString$5((String) this.F, jsonValue);
        }
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        ((VideoSource) this.F).lambda$setVideoProcessor$1(videoFrame);
    }

    @Override // io.nats.service.ServiceMessageHandler
    public void onMessage(ServiceMessage serviceMessage) {
        ((Service) this.F).lambda$addStatsContexts$1(serviceMessage);
    }
}
