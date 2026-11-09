package defpackage;

import android.widget.Toast;
import com.mamimobi.shell.activity.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public final class a40 implements Runnable {
    public final /* synthetic */ long b;
    public final /* synthetic */ MainActivity c;

    public a40(MainActivity mainActivity, long j) {
        this.c = mainActivity;
        this.b = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MainActivity mainActivity = this.c;
        try {
            Object obj = mainActivity.J.b.e;
            if (obj == b30.k) {
                obj = null;
            }
            sd sdVar = (sd) obj;
            if (sdVar != null) {
                if (za0.y(mainActivity.getApplicationContext(), sdVar.c, sdVar.a)) {
                    Toast.makeText(mainActivity, "Installation successful, starting...", 0).show();
                    za0.z(mainActivity, sdVar.a, sdVar.b);
                    mainActivity.B.postDelayed(new z1(14, this), 1000L);
                    return;
                }
            }
            if (System.currentTimeMillis() - this.b < 60000) {
                mainActivity.B.postDelayed(this, 3000L);
                return;
            }
            Toast.makeText(mainActivity, "Installation timed out, please start the app manually.", 1).show();
            mainActivity.D.setEnabled(true);
            mainActivity.G.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mainActivity, "Failed to check installation status.", 0).show();
            mainActivity.D.setEnabled(true);
            mainActivity.G.setVisibility(8);
        }
    }
}
