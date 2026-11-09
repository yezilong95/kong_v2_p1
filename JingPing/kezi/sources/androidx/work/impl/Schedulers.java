package androidx.work.impl;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.PackageManagerHelper;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Schedulers {
    public static final String GCM_SCHEDULER = "androidx.work.impl.background.gcm.GcmScheduler";
    private static final String TAG = Logger.tagWithPrefix("Schedulers");

    private Schedulers() {
    }

    public static Scheduler createBestAvailableBackgroundScheduler(Context context, WorkManagerImpl workManagerImpl) {
        SystemJobScheduler systemJobScheduler = new SystemJobScheduler(context, workManagerImpl);
        PackageManagerHelper.setComponentEnabled(context, SystemJobService.class, true);
        Logger.get().debug(TAG, "Created SystemJobScheduler and enabled SystemJobService");
        return systemJobScheduler;
    }

    public static void schedule(Configuration configuration, WorkDatabase workDatabase, List<Scheduler> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        workDatabase.beginTransaction();
        try {
            List<WorkSpec> eligibleWorkForScheduling = workSpecDao.getEligibleWorkForScheduling(configuration.getMaxSchedulerLimit());
            List<WorkSpec> allEligibleWorkSpecsForScheduling = workSpecDao.getAllEligibleWorkSpecsForScheduling(Scheduler.MAX_GREEDY_SCHEDULER_LIMIT);
            if (eligibleWorkForScheduling != null && eligibleWorkForScheduling.size() > 0) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Iterator<WorkSpec> it = eligibleWorkForScheduling.iterator();
                while (it.hasNext()) {
                    workSpecDao.markWorkSpecScheduled(it.next().id, jCurrentTimeMillis);
                }
            }
            workDatabase.setTransactionSuccessful();
            workDatabase.endTransaction();
            if (eligibleWorkForScheduling != null && eligibleWorkForScheduling.size() > 0) {
                WorkSpec[] workSpecArr = (WorkSpec[]) eligibleWorkForScheduling.toArray(new WorkSpec[eligibleWorkForScheduling.size()]);
                for (Scheduler scheduler : list) {
                    if (scheduler.hasLimitedSchedulingSlots()) {
                        scheduler.schedule(workSpecArr);
                    }
                }
            }
            if (allEligibleWorkSpecsForScheduling == null || allEligibleWorkSpecsForScheduling.size() <= 0) {
                return;
            }
            WorkSpec[] workSpecArr2 = (WorkSpec[]) allEligibleWorkSpecsForScheduling.toArray(new WorkSpec[allEligibleWorkSpecsForScheduling.size()]);
            for (Scheduler scheduler2 : list) {
                if (!scheduler2.hasLimitedSchedulingSlots()) {
                    scheduler2.schedule(workSpecArr2);
                }
            }
        } catch (Throwable th) {
            workDatabase.endTransaction();
            throw th;
        }
    }

    private static Scheduler tryCreateGcmBasedScheduler(Context context) {
        try {
            Scheduler scheduler = (Scheduler) Class.forName(GCM_SCHEDULER).getConstructor(Context.class).newInstance(context);
            Logger.get().debug(TAG, "Created androidx.work.impl.background.gcm.GcmScheduler");
            return scheduler;
        } catch (Throwable th) {
            Logger.get().debug(TAG, "Unable to create GCM Scheduler", th);
            return null;
        }
    }
}
