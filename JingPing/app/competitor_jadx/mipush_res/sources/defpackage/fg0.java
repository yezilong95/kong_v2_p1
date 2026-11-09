package defpackage;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fg0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ jg0 F;
    public final /* synthetic */ String G;

    public /* synthetic */ fg0(jg0 jg0Var, String str, int i) {
        this.E = i;
        this.F = jg0Var;
        this.G = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                jg0 jg0Var = this.F;
                String str = this.G;
                if (jg0Var.f.compareAndSet(false, true)) {
                    try {
                        try {
                            synchronized (jg0Var.j) {
                                Connection connection = jg0Var.d;
                                if (connection == null || connection.getStatus() != Connection.Status.CONNECTED) {
                                    Options.Builder builderReconnectWait = new Options.Builder().server("nats://app.mamimobi.vip:4222").pingInterval(Duration.ofSeconds(20L)).maxPingsOut(5).maxReconnects(-1).reconnectWait(Duration.ofSeconds(2L));
                                    builderReconnectWait.connectionTimeout(Duration.ofSeconds(5L));
                                    builderReconnectWait.errorListener(new ig0());
                                    jg0Var.d = Nats.connect(builderReconnectWait.build());
                                    jg0Var.i = 0;
                                    jg0Var.c(str);
                                }
                            }
                            break;
                        } catch (Exception e) {
                            e.getMessage();
                            if (o80.K(jg0Var.a)) {
                                int i = jg0Var.i;
                                int i2 = i + 1;
                                jg0Var.i = i2;
                                if (i2 <= 10) {
                                    jg0Var.c.postDelayed(new fg0(jg0Var, str, 1), Math.min((1 << i) * 3000, 30000L));
                                    break;
                                }
                            }
                        }
                        return;
                    } finally {
                        jg0Var.f.set(false);
                    }
                }
                return;
            default:
                this.F.a(this.G);
                return;
        }
    }
}
