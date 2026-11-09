package defpackage;

import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Build;
import android.os.Handler;
import android.view.MotionEvent;
import androidx.work.impl.Scheduler;
import com.mamimobi.AccessService;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public abstract class uk0 {
    public static final ExecutorService a = Executors.newFixedThreadPool(5);
    public static final Handler b = new Handler(new m1(1));
    public static LinkedList c;
    public static di d;

    public static void a() {
        long jNextInt;
        long eventTime;
        if (AccessService.e0 == null) {
            return;
        }
        LinkedList linkedList = (LinkedList) c.poll();
        long jNextInt2 = new Random().nextInt(20) + 40;
        if (linkedList.isEmpty()) {
            jNextInt = new Random().nextInt(20) + Scheduler.MAX_GREEDY_SCHEDULER_LIMIT;
            eventTime = 0;
        } else {
            eventTime = ((MotionEvent) linkedList.getLast()).getEventTime();
            jNextInt = eventTime - ((MotionEvent) linkedList.getFirst()).getEventTime();
        }
        GestureDescription.Builder builder = new GestureDescription.Builder();
        MotionEvent motionEvent = (MotionEvent) linkedList.poll();
        Path path = new Path();
        path.moveTo(motionEvent.getRawX(), motionEvent.getRawY() + s90.y(AccessService.e0));
        while (!linkedList.isEmpty()) {
            MotionEvent motionEvent2 = (MotionEvent) linkedList.poll();
            path.lineTo(motionEvent2.getRawX(), motionEvent2.getRawY() + s90.y(AccessService.e0));
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (jNextInt == 0) {
                jNextInt = new Random().nextInt(20) + Scheduler.MAX_GREEDY_SCHEDULER_LIMIT;
            }
            builder.addStroke(oj0.b(path, jNextInt2, jNextInt));
        }
        AccessService.e0.dispatchGesture(builder.build(), new tk0(eventTime), null);
    }
}
