package androidx.core.app.unusedapprestrictions;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface IUnusedAppRestrictionsBackportCallback extends IInterface {
    public static final String a = "androidx$core$app$unusedapprestrictions$IUnusedAppRestrictionsBackportCallback".replace('$', '.');

    public static class Default implements IUnusedAppRestrictionsBackportCallback {
        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IUnusedAppRestrictionsBackportCallback {

        public static class Proxy implements IUnusedAppRestrictionsBackportCallback {
            public IBinder c;

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.c;
            }
        }

        public Stub() {
            attachInterface(this, IUnusedAppRestrictionsBackportCallback.a);
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            String str = IUnusedAppRestrictionsBackportCallback.a;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            b(parcel.readInt() != 0, parcel.readInt() != 0);
            throw null;
        }
    }

    void b(boolean z, boolean z2);
}
