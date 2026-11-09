package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import androidx.work.impl.utils.IdGenerator;
import defpackage.lx;
import defpackage.oy;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class IdGenerator {
    private final WorkDatabase workDatabase;

    public IdGenerator(WorkDatabase workDatabase) {
        oy.j(workDatabase, "workDatabase");
        this.workDatabase = workDatabase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer nextAlarmManagerId$lambda$1(IdGenerator idGenerator) {
        oy.j(idGenerator, "this$0");
        return Integer.valueOf(IdGeneratorKt.nextId(idGenerator.workDatabase, IdGeneratorKt.NEXT_ALARM_MANAGER_ID_KEY));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer nextJobSchedulerIdWithRange$lambda$0(IdGenerator idGenerator, int i, int i2) {
        oy.j(idGenerator, "this$0");
        int iNextId = IdGeneratorKt.nextId(idGenerator.workDatabase, IdGeneratorKt.NEXT_JOB_SCHEDULER_ID_KEY);
        if (i > iNextId || iNextId > i2) {
            IdGeneratorKt.updatePreference(idGenerator.workDatabase, IdGeneratorKt.NEXT_JOB_SCHEDULER_ID_KEY, i + 1);
        } else {
            i = iNextId;
        }
        return Integer.valueOf(i);
    }

    public final int nextAlarmManagerId() {
        Object objRunInTransaction = this.workDatabase.runInTransaction(new lx(0, this));
        oy.i(objRunInTransaction, "workDatabase.runInTransa…ANAGER_ID_KEY)\n        })");
        return ((Number) objRunInTransaction).intValue();
    }

    public final int nextJobSchedulerIdWithRange(final int i, final int i2) {
        Object objRunInTransaction = this.workDatabase.runInTransaction((Callable<Object>) new Callable() { // from class: mx
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return IdGenerator.nextJobSchedulerIdWithRange$lambda$0(this.a, i, i2);
            }
        });
        oy.i(objRunInTransaction, "workDatabase.runInTransa…            id\n        })");
        return ((Number) objRunInTransaction).intValue();
    }
}
