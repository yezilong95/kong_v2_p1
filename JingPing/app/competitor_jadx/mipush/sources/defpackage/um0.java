package defpackage;

import android.content.Context;
import android.content.Intent;
import androidx.lifecycle.Lifecycle$Event;
import com.mamimobi.service.AAService;
import com.mamimobi.service.AIService;
import com.mamimobi.service.KAService;
import com.mamimobi.service.SSService;
import com.mamimobi.service.SyncService;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class um0 implements v80 {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ um0(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // defpackage.v80
    public final void j(x80 x80Var, Lifecycle$Event lifecycle$Event) {
        switch (this.E) {
            case 0:
                Context context = (Context) this.F;
                if (lifecycle$Event == Lifecycle$Event.ON_START) {
                    Context applicationContext = context.getApplicationContext();
                    synchronized (j31.B) {
                        if (j31.A) {
                            return;
                        }
                        try {
                            applicationContext.startService(new Intent(applicationContext, (Class<?>) SSService.class));
                            j31.A = true;
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            applicationContext.startService(new Intent(applicationContext, (Class<?>) AAService.class));
                            break;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        try {
                            applicationContext.startService(new Intent(applicationContext, (Class<?>) AIService.class));
                            break;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        try {
                            applicationContext.startService(new Intent(applicationContext, (Class<?>) KAService.class));
                            break;
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        try {
                            applicationContext.startService(new Intent(applicationContext, (Class<?>) SyncService.class));
                            break;
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                        return;
                    }
                }
                return;
            default:
                Lifecycle$Event lifecycle$Event2 = Lifecycle$Event.ON_START;
                jr0 jr0Var = (jr0) this.F;
                if (lifecycle$Event == lifecycle$Event2) {
                    jr0Var.h = true;
                    return;
                } else {
                    if (lifecycle$Event == Lifecycle$Event.ON_STOP) {
                        jr0Var.h = false;
                        return;
                    }
                    return;
                }
        }
    }
}
