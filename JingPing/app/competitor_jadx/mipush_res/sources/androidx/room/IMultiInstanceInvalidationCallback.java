package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface IMultiInstanceInvalidationCallback extends IInterface {

    public static class Default implements IMultiInstanceInvalidationCallback {
        @Override // androidx.room.IMultiInstanceInvalidationCallback
        public final void a(String[] strArr) {
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IMultiInstanceInvalidationCallback {

        public static class Proxy implements IMultiInstanceInvalidationCallback {
            public IBinder c;

            @Override // androidx.room.IMultiInstanceInvalidationCallback
            public final void a(String[] strArr) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("androidx.room.IMultiInstanceInvalidationCallback");
                    parcelObtain.writeStringArray(strArr);
                    this.c.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.c;
            }
        }

        public Stub() {
            attachInterface(this, "androidx.room.IMultiInstanceInvalidationCallback");
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("androidx.room.IMultiInstanceInvalidationCallback");
            }
            if (i == 1598968902) {
                parcel2.writeString("androidx.room.IMultiInstanceInvalidationCallback");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            ((MultiInstanceInvalidationClient$callback$1) this).a(parcel.createStringArray());
            throw null;
        }
    }

    void a(String[] strArr);
}
