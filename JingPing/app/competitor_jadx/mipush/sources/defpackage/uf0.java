package defpackage;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import androidx.room.IMultiInstanceInvalidationCallback;
import androidx.room.MultiInstanceInvalidationService;

/* JADX INFO: loaded from: classes.dex */
public final class uf0 extends RemoteCallbackList {
    public final /* synthetic */ MultiInstanceInvalidationService a;

    public uf0(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.a = multiInstanceInvalidationService;
    }

    @Override // android.os.RemoteCallbackList
    public final void onCallbackDied(IInterface iInterface, Object obj) {
        t40.n((IMultiInstanceInvalidationCallback) iInterface, "callback");
        t40.n(obj, "cookie");
        this.a.F.remove((Integer) obj);
    }
}
