package defpackage;

import com.mamimobi.activity.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pb0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ MainActivity F;

    public /* synthetic */ pb0(MainActivity mainActivity, int i) {
        this.E = i;
        this.F = mainActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MainActivity mainActivity = this.F;
        switch (this.E) {
            case 0:
                MainActivity.a(mainActivity);
                break;
            case 1:
                int i = MainActivity.J;
                mainActivity.b();
                break;
            case 2:
                int i2 = MainActivity.J;
                t40.a1(mainActivity, new String[]{"android.permission.CAMERA"});
                break;
            default:
                mainActivity.finish();
                break;
        }
    }
}
