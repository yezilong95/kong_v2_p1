package defpackage;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import androidx.room.IMultiInstanceInvalidationCallback;
import androidx.room.MultiInstanceInvalidationService;

/* JADX INFO: loaded from: classes.dex */
public final class a80 extends RemoteCallbackList {
    public final /* synthetic */ MultiInstanceInvalidationService a;

    public a80(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.a = multiInstanceInvalidationService;
    }

    @Override // android.os.RemoteCallbackList
    public final void onCallbackDied(IInterface iInterface, Object obj) {
        oy.j((IMultiInstanceInvalidationCallback) iInterface, "callback");
        oy.j(obj, "cookie");
        this.a.c.remove((Integer) obj);
    }
}
