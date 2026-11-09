package defpackage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.work.PeriodicWorkRequest;
import com.mamimobi.receiver.CSReceiver;
import com.mamimobi.receiver.LCReceiver;
import com.mamimobi.receiver.PKReceiver;
import com.mamimobi.receiver.RSReceiver;
import com.mamimobi.receiver.SCNReceiver;
import com.mamimobi.service.MJService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class f11 {
    public static final AtomicBoolean a = new AtomicBoolean(false);
    public static SCNReceiver b = null;
    public static CSReceiver c = null;
    public static PKReceiver d = null;
    public static LCReceiver e = null;
    public static Boolean f = Boolean.FALSE;
    public static int g = 1000;

    public static byte[] a(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (IOException unused) {
            return "".getBytes();
        }
    }

    public static boolean b(Context context, String... strArr) {
        if (context != null) {
            for (String str : strArr) {
                if (t40.o(context, str) == 0) {
                }
            }
            return true;
        }
        return false;
    }

    public static boolean c(Context context) {
        return ((PowerManager) context.getSystemService("power")).isIgnoringBatteryOptimizations(context.getPackageName());
    }

    public static void d(Context context, long j, String str) {
        try {
            Intent intent = new Intent(context, (Class<?>) RSReceiver.class);
            intent.setAction("RestartSensor".replace(str, ""));
            ((AlarmManager) context.getSystemService("alarm")).setRepeating(0, System.currentTimeMillis() + j, j, PendingIntent.getBroadcast(context, 0, intent, 33554432));
        } catch (Exception unused) {
        }
    }

    public static void e(Context context) {
        Iterator<JobInfo> it = ((JobScheduler) context.getSystemService("jobscheduler")).getAllPendingJobs().iterator();
        while (it.hasNext()) {
            if (it.next().getId() == 666) {
                return;
            }
        }
        ((JobScheduler) context.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(666, new ComponentName(context, (Class<?>) MJService.class)).setPeriodic(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS).setRequiredNetworkType(1).build());
    }
}
