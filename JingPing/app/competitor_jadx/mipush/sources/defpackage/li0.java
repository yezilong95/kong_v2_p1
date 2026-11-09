package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class li0 implements Handler.Callback, ServiceConnection {
    public final Context a;
    public final Handler b;
    public final HashMap c = new HashMap();
    public HashSet d = new HashSet();

    public li0(Context context) {
        this.a = context;
        HandlerThread handlerThread = new HandlerThread("NotificationManagerCompat");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper(), this);
    }

    public final void a(ki0 ki0Var) {
        boolean z;
        ArrayDeque arrayDeque;
        boolean zIsLoggable = Log.isLoggable("NotifManCompat", 3);
        ComponentName componentName = ki0Var.a;
        if (zIsLoggable) {
            Log.d("NotifManCompat", "Processing component " + componentName + ", " + ki0Var.d.size() + " queued tasks");
        }
        if (ki0Var.d.isEmpty()) {
            return;
        }
        if (ki0Var.b) {
            z = true;
        } else {
            Intent component = new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(componentName);
            Context context = this.a;
            boolean zBindService = context.bindService(component, this, 33);
            ki0Var.b = zBindService;
            if (zBindService) {
                ki0Var.e = 0;
            } else {
                Log.w("NotifManCompat", "Unable to bind to listener " + componentName);
                context.unbindService(this);
            }
            z = ki0Var.b;
        }
        if (!z || ki0Var.c == null) {
            b(ki0Var);
            return;
        }
        while (true) {
            arrayDeque = ki0Var.d;
            ii0 ii0Var = (ii0) arrayDeque.peek();
            if (ii0Var == null) {
                break;
            }
            try {
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Sending task " + ii0Var);
                }
                ki0Var.c.notify(ii0Var.a, 101, null, ii0Var.b);
                arrayDeque.remove();
            } catch (DeadObjectException unused) {
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Remote service has died: " + componentName);
                }
            } catch (RemoteException e) {
                Log.w("NotifManCompat", "RemoteException communicating with " + componentName, e);
            }
        }
        if (arrayDeque.isEmpty()) {
            return;
        }
        b(ki0Var);
    }

    public final void b(ki0 ki0Var) {
        Handler handler = this.b;
        ComponentName componentName = ki0Var.a;
        if (handler.hasMessages(3, componentName)) {
            return;
        }
        int i = ki0Var.e;
        int i2 = i + 1;
        ki0Var.e = i2;
        if (i2 <= 6) {
            int i3 = (1 << i) * 1000;
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Scheduling retry for " + i3 + " ms");
            }
            handler.sendMessageDelayed(handler.obtainMessage(3, componentName), i3);
            return;
        }
        StringBuilder sb = new StringBuilder("Giving up on delivering ");
        ArrayDeque arrayDeque = ki0Var.d;
        sb.append(arrayDeque.size());
        sb.append(" tasks to ");
        sb.append(componentName);
        sb.append(" after ");
        sb.append(ki0Var.e);
        sb.append(" retries");
        Log.w("NotifManCompat", sb.toString());
        arrayDeque.clear();
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        HashSet hashSet;
        int i = message.what;
        if (i == 0) {
            ii0 ii0Var = (ii0) message.obj;
            String string = Settings.Secure.getString(this.a.getContentResolver(), "enabled_notification_listeners");
            synchronized (mi0.b) {
                if (string != null) {
                    try {
                        if (!string.equals(mi0.c)) {
                            String[] strArrSplit = string.split(":", -1);
                            HashSet hashSet2 = new HashSet(strArrSplit.length);
                            for (String str : strArrSplit) {
                                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                                if (componentNameUnflattenFromString != null) {
                                    hashSet2.add(componentNameUnflattenFromString.getPackageName());
                                }
                            }
                            mi0.d = hashSet2;
                            mi0.c = string;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                hashSet = mi0.d;
            }
            if (!hashSet.equals(this.d)) {
                this.d = hashSet;
                List<ResolveInfo> listQueryIntentServices = this.a.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
                HashSet<ComponentName> hashSet3 = new HashSet();
                for (ResolveInfo resolveInfo : listQueryIntentServices) {
                    if (hashSet.contains(resolveInfo.serviceInfo.packageName)) {
                        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                        ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                        if (resolveInfo.serviceInfo.permission != null) {
                            Log.w("NotifManCompat", "Permission present on component " + componentName + ", not adding listener record.");
                        } else {
                            hashSet3.add(componentName);
                        }
                    }
                }
                for (ComponentName componentName2 : hashSet3) {
                    if (!this.c.containsKey(componentName2)) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Adding listener record for " + componentName2);
                        }
                        this.c.put(componentName2, new ki0(componentName2));
                    }
                }
                Iterator it = this.c.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    if (!hashSet3.contains(entry.getKey())) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Removing listener record for " + entry.getKey());
                        }
                        ki0 ki0Var = (ki0) entry.getValue();
                        if (ki0Var.b) {
                            this.a.unbindService(this);
                            ki0Var.b = false;
                        }
                        ki0Var.c = null;
                        it.remove();
                    }
                }
            }
            for (ki0 ki0Var2 : this.c.values()) {
                ki0Var2.d.add(ii0Var);
                a(ki0Var2);
            }
        } else if (i == 1) {
            ji0 ji0Var = (ji0) message.obj;
            ComponentName componentName3 = ji0Var.a;
            IBinder iBinder = ji0Var.b;
            ki0 ki0Var3 = (ki0) this.c.get(componentName3);
            if (ki0Var3 != null) {
                ki0Var3.c = INotificationSideChannel.Stub.asInterface(iBinder);
                ki0Var3.e = 0;
                a(ki0Var3);
                return true;
            }
        } else if (i == 2) {
            ki0 ki0Var4 = (ki0) this.c.get((ComponentName) message.obj);
            if (ki0Var4 != null) {
                if (ki0Var4.b) {
                    this.a.unbindService(this);
                    ki0Var4.b = false;
                }
                ki0Var4.c = null;
                return true;
            }
        } else {
            if (i != 3) {
                return false;
            }
            ki0 ki0Var5 = (ki0) this.c.get((ComponentName) message.obj);
            if (ki0Var5 != null) {
                a(ki0Var5);
                return true;
            }
        }
        return true;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Connected to service " + componentName);
        }
        this.b.obtainMessage(1, new ji0(componentName, iBinder)).sendToTarget();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Disconnected from service " + componentName);
        }
        this.b.obtainMessage(2, componentName).sendToTarget();
    }
}
