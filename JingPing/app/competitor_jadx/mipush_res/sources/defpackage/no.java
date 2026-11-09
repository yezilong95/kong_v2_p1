package defpackage;

import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.model.WorkSpec;
import com.google.android.material.carousel.MaskableFrameLayout;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.service.InfoResponse;
import java.util.List;
import org.webrtc.EglThread;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class no implements EglThread.ReleaseMonitor, JsonValueUtils.JsonValueSupplier, JsonUtils.ListAdder, yu0, yz0, j10 {
    public static final no F = new no(24);
    public static final no G = new no(25);
    public static final no H = new no(26);
    public static final no I = new no(27);
    public static final no J = new no(28);
    public final /* synthetic */ int E;

    public /* synthetic */ no(int i) {
        this.E = i;
    }

    @Override // defpackage.yu0
    public yi a(yi yiVar) {
        int i = MaskableFrameLayout.J;
        return yiVar instanceof o0 ? new bf(((o0) yiVar).a) : yiVar;
    }

    @Override // io.nats.client.support.JsonUtils.ListAdder
    public void append(StringBuilder sb, Object obj) {
        JsonUtils.lambda$addJsons$1(sb, (JsonSerializable) obj);
    }

    @Override // defpackage.j10
    public Object apply(Object obj) {
        return WorkSpec.WORK_INFO_MAPPER$lambda$1((List) obj);
    }

    @Override // io.nats.client.support.JsonValueUtils.JsonValueSupplier
    public Object get(JsonValue jsonValue) {
        switch (this.E) {
            case 3:
                return InfoResponse.lambda$new$0(jsonValue);
            case 4:
            default:
                return JsonValueUtils.lambda$readObject$1(jsonValue);
            case 5:
                return JsonValueUtils.lambda$readDate$6(jsonValue);
            case 6:
                return JsonValueUtils.lambda$readOptionalStringList$17(jsonValue);
            case 7:
                return JsonValueUtils.lambda$readString$3(jsonValue);
            case 8:
                return JsonValueUtils.lambda$readValue$0(jsonValue);
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return JsonValueUtils.lambda$readNanosList$20(jsonValue);
            case 10:
                return JsonValueUtils.lambda$readLongList$18(jsonValue);
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                return JsonValueUtils.lambda$readStringList$13(jsonValue);
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                return JsonValueUtils.lambda$readLong$9(jsonValue);
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                return JsonValueUtils.lambda$readStringListIgnoreEmpty$15(jsonValue);
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                return JsonValueUtils.lambda$readStringEmptyAsNull$4(jsonValue);
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                return JsonValueUtils.lambda$readArray$2(jsonValue);
            case 16:
                return JsonValueUtils.lambda$readInteger$7(jsonValue);
        }
    }

    @Override // org.webrtc.EglThread.ReleaseMonitor
    public boolean onRelease(EglThread eglThread) {
        return EglThread.lambda$create$1(eglThread);
    }
}
