package defpackage;

import android.net.ConnectivityManager;
import android.net.Network;
import android.util.Log;
import io.nats.client.Connection;

/* JADX INFO: loaded from: classes.dex */
public final class hg0 extends ConnectivityManager.NetworkCallback {
    public final /* synthetic */ jg0 a;

    public hg0(jg0 jg0Var) {
        this.a = jg0Var;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        Log.d("NatsHelper", "Network available");
        Connection connection = this.a.d;
        if (connection == null || connection.getStatus() != Connection.Status.CONNECTED) {
            jg0 jg0Var = this.a;
            jg0Var.a(jg0Var.h);
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        jg0 jg0Var = this.a;
        jg0Var.getClass();
        jg0Var.b.execute(new e(21, jg0Var));
    }
}
