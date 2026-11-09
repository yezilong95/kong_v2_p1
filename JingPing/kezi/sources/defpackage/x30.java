package defpackage;

import android.widget.Toast;
import com.mamimobi.shell.activity.MainActivity;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class x30 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ MainActivity c;

    public /* synthetic */ x30(MainActivity mainActivity, int i) {
        this.b = i;
        this.c = mainActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MainActivity mainActivity = this.c;
        switch (this.b) {
            case 0:
                ExecutorService executorService = MainActivity.P;
                mainActivity.getClass();
                Toast.makeText(mainActivity, "Installation failed.", 0).show();
                mainActivity.D.setEnabled(true);
                mainActivity.G.setVisibility(8);
                break;
            case 1:
                mainActivity.finish();
                break;
            default:
                ExecutorService executorService2 = MainActivity.P;
                mainActivity.n();
                break;
        }
    }
}
