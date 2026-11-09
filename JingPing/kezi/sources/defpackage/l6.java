package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.widget.Toast;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.utils.WorkForegroundRunnable;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import com.google.android.material.R$string;
import com.google.android.material.datepicker.g;
import com.google.android.material.textfield.TextInputLayout;
import com.mamimobi.shell.R$mipmap;
import com.mamimobi.shell.activity.MainActivity;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import org.webrtc.EglRenderer;
import org.webrtc.VideoFileRenderer;
import org.webrtc.VideoFrame;
import org.webrtc.VideoSource;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l6 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ l6(Object obj, int i, Object obj2) {
        this.b = i;
        this.c = obj;
        this.d = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 8;
        int i2 = 1;
        Object obj = this.d;
        Object obj2 = this.c;
        switch (this.b) {
            case 0:
                Runnable runnable = (Runnable) obj;
                ((m6) obj2).getClass();
                try {
                    runnable.run();
                    return;
                } finally {
                }
            case 1:
                ConstraintTracker._set_state_$lambda$4$lambda$3((List) obj2, (ConstraintTracker) obj);
                return;
            case 2:
                ConstraintTrackingWorker.setupAndRunConstraintTrackingWork$lambda$2((ConstraintTrackingWorker) obj2, (x20) obj);
                return;
            case 3:
                g gVar = (g) obj2;
                TextInputLayout textInputLayout = gVar.b;
                Context context = textInputLayout.getContext();
                textInputLayout.setError(context.getString(R$string.mtrl_picker_invalid_format) + "\n" + String.format(context.getString(R$string.mtrl_picker_invalid_format_use), ((String) obj).replace(' ', (char) 160)) + "\n" + String.format(context.getString(R$string.mtrl_picker_invalid_format_example), gVar.d.format(new Date(fq0.f().getTimeInMillis())).replace(' ', (char) 160)));
                gVar.a();
                return;
            case 4:
                ((EglRenderer) obj2).lambda$releaseEglSurface$3((Runnable) obj);
                return;
            case 5:
                ((EglRenderer) obj2).lambda$release$0((CountDownLatch) obj);
                return;
            case 6:
                MainActivity mainActivity = (MainActivity) obj2;
                mainActivity.C.setImageResource(R$mipmap.ic_launcher);
                HashMap map = (HashMap) obj;
                if (map.get("versionName") != null) {
                    mainActivity.E.setText(mainActivity.getString(com.mamimobi.shell.R$string.new_version) + map.get("versionName"));
                }
                if (map.get("fileSize") != null) {
                    mainActivity.F.setText(mainActivity.getString(com.mamimobi.shell.R$string.update_size) + map.get("fileSize"));
                }
                mainActivity.H.setText(mainActivity.getString(com.mamimobi.shell.R$string.description));
                mainActivity.G.setVisibility(8);
                if (mainActivity.k() != null) {
                    bu0 bu0VarK = mainActivity.k();
                    if (bu0VarK.p) {
                        return;
                    }
                    bu0VarK.p = true;
                    bu0VarK.f(false);
                    return;
                }
                return;
            case 7:
                sd sdVar = (sd) obj;
                MainActivity mainActivity2 = (MainActivity) obj2;
                Handler handler = mainActivity2.B;
                try {
                    String strO = oy.o(mainActivity2);
                    oy.L(strO, sdVar);
                    handler.post(new w30(mainActivity2, strO, i2));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.post(new l6(mainActivity2, i, e));
                    return;
                }
            case 8:
                ExecutorService executorService = MainActivity.P;
                MainActivity mainActivity3 = (MainActivity) obj2;
                mainActivity3.getClass();
                Toast.makeText(mainActivity3, "Initialization failed: " + ((Exception) obj).getMessage(), 1).show();
                return;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                ((rp0) obj2).M((Typeface) obj);
                return;
            case WorkDatabaseVersions.VERSION_10 /* 10 */:
                Runnable runnable2 = (Runnable) obj;
                oy.j(runnable2, "$command");
                oy.j((m6) obj2, "this$0");
                try {
                    runnable2.run();
                    return;
                } finally {
                }
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                ((VideoFileRenderer) obj2).lambda$onFrame$0((VideoFrame) obj);
                return;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                ((VideoFileRenderer) obj2).lambda$release$2((CountDownLatch) obj);
                return;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                ((VideoSource) obj2).lambda$setVideoProcessor$0((VideoFrame) obj);
                return;
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                ((WorkForegroundRunnable) obj2).lambda$run$0((SettableFuture) obj);
                return;
            default:
                ((WorkerWrapper) obj2).lambda$runWorker$0((x20) obj);
                return;
        }
    }

    public /* synthetic */ l6(Runnable runnable, m6 m6Var) {
        this.b = 10;
        this.d = runnable;
        this.c = m6Var;
    }
}
