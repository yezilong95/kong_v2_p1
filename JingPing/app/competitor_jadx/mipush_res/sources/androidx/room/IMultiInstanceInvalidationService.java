package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.IMultiInstanceInvalidationCallback;
import defpackage.t40;

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
                t40.n(iMultiInstanceInvalidationCallback, "callback");
                int i3 = 0;
                if (string != null) {
                    MultiInstanceInvalidationService multiInstanceInvalidationService = multiInstanceInvalidationService$binder$1.c;
                    synchronized (multiInstanceInvalidationService.G) {
                        try {
                            int i4 = multiInstanceInvalidationService.E + 1;
                            multiInstanceInvalidationService.E = i4;
                            if (multiInstanceInvalidationService.G.register(iMultiInstanceInvalidationCallback, Integer.valueOf(i4))) {
                                multiInstanceInvalidationService.F.put(Integer.valueOf(i4), string);
                                i3 = i4;
                            } else {
                                multiInstanceInvalidationService.E--;
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
                t40.n(iMultiInstanceInvalidationCallback2, "callback");
                MultiInstanceInvalidationService multiInstanceInvalidationService2 = ((MultiInstanceInvalidationService$binder$1) this).c;
                synchronized (multiInstanceInvalidationService2.G) {
                    multiInstanceInvalidationService2.G.unregister(iMultiInstanceInvalidationCallback2);
                }
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            int i6 = parcel.readInt();
            String[] strArrCreateStringArray = parcel.createStringArray();
            t40.n(strArrCreateStringArray, "tables");
            MultiInstanceInvalidationService multiInstanceInvalidationService3 = ((MultiInstanceInvalidationService$binder$1) this).c;
            synchronized (multiInstanceInvalidationService3.G) {
                String str = (String) multiInstanceInvalidationService3.F.get(Integer.valueOf(i6));
                if (str == null) {
                    Log.w("ROOM", "Remote invalidation client ID not registered");
                } else {
                    int iBeginBroadcast = multiInstanceInvalidationService3.G.beginBroadcast();
                    for (int i7 = 0; i7 < iBeginBroadcast; i7++) {
                        try {
                            Object broadcastCookie = multiInstanceInvalidationService3.G.getBroadcastCookie(i7);
                            t40.l(broadcastCookie, "null cannot be cast to non-null type kotlin.Int");
                            Integer num = (Integer) broadcastCookie;
                            int iIntValue = num.intValue();
                            String str2 = (String) multiInstanceInvalidationService3.F.get(num);
                            if (i6 != iIntValue && str.equals(str2)) {
                                try {
                                    ((IMultiInstanceInvalidationCallback) multiInstanceInvalidationService3.G.getBroadcastItem(i7)).a(strArrCreateStringArray);
                                } catch (RemoteException e) {
                                    Log.w("ROOM", "Error invoking a remote callback", e);
                                }
                            }
                        } finally {
                            multiInstanceInvalidationService3.G.finishBroadcast();
                        }
                    }
                }
            }
            return true;
        }
    }
}
