package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.IMultiInstanceInvalidationCallback;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public interface IMultiInstanceInvalidationService extends IInterface {

    public static class Default implements IMultiInstanceInvalidationService {
        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IMultiInstanceInvalidationService {

        public static class Proxy implements IMultiInstanceInvalidationService {
            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return null;
            }
        }

        public Stub() {
            attachInterface(this, "androidx.room.IMultiInstanceInvalidationService");
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("androidx.room.IMultiInstanceInvalidationService");
            }
            if (i == 1598968902) {
                parcel2.writeString("androidx.room.IMultiInstanceInvalidationService");
                return true;
            }
            IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback = null;
            IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback2 = null;
            if (i == 1) {
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("androidx.room.IMultiInstanceInvalidationCallback");
                    if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IMultiInstanceInvalidationCallback)) {
                        IMultiInstanceInvalidationCallback.Stub.Proxy proxy = new IMultiInstanceInvalidationCallback.Stub.Proxy();
                        proxy.c = strongBinder;
                        iMultiInstanceInvalidationCallback = proxy;
                    } else {
                        iMultiInstanceInvalidationCallback = (IMultiInstanceInvalidationCallback) iInterfaceQueryLocalInterface;
                    }
                }
                String string = parcel.readString();
                MultiInstanceInvalidationService$binder$1 multiInstanceInvalidationService$binder$1 = (MultiInstanceInvalidationService$binder$1) this;
                oy.j(iMultiInstanceInvalidationCallback, "callback");
                int i3 = 0;
                if (string != null) {
                    MultiInstanceInvalidationService multiInstanceInvalidationService = multiInstanceInvalidationService$binder$1.c;
                    synchronized (multiInstanceInvalidationService.d) {
                        try {
                            int i4 = multiInstanceInvalidationService.b + 1;
                            multiInstanceInvalidationService.b = i4;
                            if (multiInstanceInvalidationService.d.register(iMultiInstanceInvalidationCallback, Integer.valueOf(i4))) {
                                multiInstanceInvalidationService.c.put(Integer.valueOf(i4), string);
                                i3 = i4;
                            } else {
                                multiInstanceInvalidationService.b--;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
                parcel2.writeNoException();
                parcel2.writeInt(i3);
                return true;
            }
            if (i == 2) {
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("androidx.room.IMultiInstanceInvalidationCallback");
                    if (iInterfaceQueryLocalInterface2 == null || !(iInterfaceQueryLocalInterface2 instanceof IMultiInstanceInvalidationCallback)) {
                        IMultiInstanceInvalidationCallback.Stub.Proxy proxy2 = new IMultiInstanceInvalidationCallback.Stub.Proxy();
                        proxy2.c = strongBinder2;
                        iMultiInstanceInvalidationCallback2 = proxy2;
                    } else {
                        iMultiInstanceInvalidationCallback2 = (IMultiInstanceInvalidationCallback) iInterfaceQueryLocalInterface2;
                    }
                }
                int i5 = parcel.readInt();
                oy.j(iMultiInstanceInvalidationCallback2, "callback");
                MultiInstanceInvalidationService multiInstanceInvalidationService2 = ((MultiInstanceInvalidationService$binder$1) this).c;
                synchronized (multiInstanceInvalidationService2.d) {
                    multiInstanceInvalidationService2.d.unregister(iMultiInstanceInvalidationCallback2);
                }
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            int i6 = parcel.readInt();
            String[] strArrCreateStringArray = parcel.createStringArray();
            oy.j(strArrCreateStringArray, "tables");
            MultiInstanceInvalidationService multiInstanceInvalidationService3 = ((MultiInstanceInvalidationService$binder$1) this).c;
            synchronized (multiInstanceInvalidationService3.d) {
                String str = (String) multiInstanceInvalidationService3.c.get(Integer.valueOf(i6));
                if (str == null) {
                    Log.w("ROOM", "Remote invalidation client ID not registered");
                } else {
                    int iBeginBroadcast = multiInstanceInvalidationService3.d.beginBroadcast();
                    for (int i7 = 0; i7 < iBeginBroadcast; i7++) {
                        try {
                            Object broadcastCookie = multiInstanceInvalidationService3.d.getBroadcastCookie(i7);
                            oy.h(broadcastCookie, "null cannot be cast to non-null type kotlin.Int");
                            Integer num = (Integer) broadcastCookie;
                            int iIntValue = num.intValue();
                            String str2 = (String) multiInstanceInvalidationService3.c.get(num);
                            if (i6 != iIntValue && str.equals(str2)) {
                                try {
                                    ((IMultiInstanceInvalidationCallback) multiInstanceInvalidationService3.d.getBroadcastItem(i7)).c(strArrCreateStringArray);
                                } catch (RemoteException e) {
                                    Log.w("ROOM", "Error invoking a remote callback", e);
                                }
                            }
                        } finally {
                            multiInstanceInvalidationService3.d.finishBroadcast();
                        }
                    }
                }
            }
            return true;
        }
    }
}
