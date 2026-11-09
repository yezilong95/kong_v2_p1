package androidx.work.impl.model;

import android.database.Cursor;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import defpackage.aj0;
import defpackage.b30;
import defpackage.c7;
import defpackage.hk;
import defpackage.ho;
import defpackage.io;
import defpackage.jq0;
import defpackage.k40;
import defpackage.k9;
import defpackage.l40;
import defpackage.oy;
import defpackage.pf0;
import defpackage.sf0;
import defpackage.wl0;
import defpackage.za0;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class WorkSpecDao_Impl implements WorkSpecDao {
    private final pf0 __db;
    private final io __insertionAdapterOfWorkSpec;
    private final aj0 __preparedStmtOfDelete;
    private final aj0 __preparedStmtOfIncrementGeneration;
    private final aj0 __preparedStmtOfIncrementPeriodCount;
    private final aj0 __preparedStmtOfIncrementWorkSpecRunAttemptCount;
    private final aj0 __preparedStmtOfMarkWorkSpecScheduled;
    private final aj0 __preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast;
    private final aj0 __preparedStmtOfResetScheduledState;
    private final aj0 __preparedStmtOfResetWorkSpecRunAttemptCount;
    private final aj0 __preparedStmtOfSetLastEnqueuedTime;
    private final aj0 __preparedStmtOfSetOutput;
    private final aj0 __preparedStmtOfSetState;
    private final ho __updateAdapterOfWorkSpec;

    public WorkSpecDao_Impl(pf0 pf0Var) {
        this.__db = pf0Var;
        this.__insertionAdapterOfWorkSpec = new io(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.1
            @Override // defpackage.aj0
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`last_enqueue_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`period_count`,`generation`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // defpackage.io
            public void bind(wl0 wl0Var, WorkSpec workSpec) throws Throwable {
                String str = workSpec.id;
                if (str == null) {
                    wl0Var.r(1);
                } else {
                    wl0Var.j(1, str);
                }
                WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                wl0Var.x(WorkTypeConverters.stateToInt(workSpec.state), 2);
                String str2 = workSpec.workerClassName;
                if (str2 == null) {
                    wl0Var.r(3);
                } else {
                    wl0Var.j(3, str2);
                }
                String str3 = workSpec.inputMergerClassName;
                if (str3 == null) {
                    wl0Var.r(4);
                } else {
                    wl0Var.j(4, str3);
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(workSpec.input);
                if (byteArrayInternal == null) {
                    wl0Var.r(5);
                } else {
                    wl0Var.B(byteArrayInternal, 5);
                }
                byte[] byteArrayInternal2 = Data.toByteArrayInternal(workSpec.output);
                if (byteArrayInternal2 == null) {
                    wl0Var.r(6);
                } else {
                    wl0Var.B(byteArrayInternal2, 6);
                }
                wl0Var.x(workSpec.initialDelay, 7);
                wl0Var.x(workSpec.intervalDuration, 8);
                wl0Var.x(workSpec.flexDuration, 9);
                wl0Var.x(workSpec.runAttemptCount, 10);
                wl0Var.x(WorkTypeConverters.backoffPolicyToInt(workSpec.backoffPolicy), 11);
                wl0Var.x(workSpec.backoffDelayDuration, 12);
                wl0Var.x(workSpec.lastEnqueueTime, 13);
                wl0Var.x(workSpec.minimumRetentionDuration, 14);
                wl0Var.x(workSpec.scheduleRequestedAt, 15);
                wl0Var.x(workSpec.expedited ? 1L : 0L, 16);
                wl0Var.x(WorkTypeConverters.outOfQuotaPolicyToInt(workSpec.outOfQuotaPolicy), 17);
                wl0Var.x(workSpec.getPeriodCount(), 18);
                wl0Var.x(workSpec.getGeneration(), 19);
                Constraints constraints = workSpec.constraints;
                if (constraints == null) {
                    wl0Var.r(20);
                    wl0Var.r(21);
                    wl0Var.r(22);
                    wl0Var.r(23);
                    wl0Var.r(24);
                    wl0Var.r(25);
                    wl0Var.r(26);
                    wl0Var.r(27);
                    return;
                }
                wl0Var.x(WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()), 20);
                wl0Var.x(constraints.requiresCharging() ? 1L : 0L, 21);
                wl0Var.x(constraints.requiresDeviceIdle() ? 1L : 0L, 22);
                wl0Var.x(constraints.requiresBatteryNotLow() ? 1L : 0L, 23);
                wl0Var.x(constraints.requiresStorageNotLow() ? 1L : 0L, 24);
                wl0Var.x(constraints.getContentTriggerUpdateDelayMillis(), 25);
                wl0Var.x(constraints.getContentTriggerMaxDelayMillis(), 26);
                byte[] ofTriggersToByteArray = WorkTypeConverters.setOfTriggersToByteArray(constraints.getContentUriTriggers());
                if (ofTriggersToByteArray == null) {
                    wl0Var.r(27);
                } else {
                    wl0Var.B(ofTriggersToByteArray, 27);
                }
            }
        };
        this.__updateAdapterOfWorkSpec = new ho(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(pf0Var);
                oy.j(pf0Var, "database");
            }

            @Override // defpackage.aj0
            public String createQuery() {
                return "UPDATE OR ABORT `WorkSpec` SET `id` = ?,`state` = ?,`worker_class_name` = ?,`input_merger_class_name` = ?,`input` = ?,`output` = ?,`initial_delay` = ?,`interval_duration` = ?,`flex_duration` = ?,`run_attempt_count` = ?,`backoff_policy` = ?,`backoff_delay_duration` = ?,`last_enqueue_time` = ?,`minimum_retention_duration` = ?,`schedule_requested_at` = ?,`run_in_foreground` = ?,`out_of_quota_policy` = ?,`period_count` = ?,`generation` = ?,`required_network_type` = ?,`requires_charging` = ?,`requires_device_idle` = ?,`requires_battery_not_low` = ?,`requires_storage_not_low` = ?,`trigger_content_update_delay` = ?,`trigger_max_content_delay` = ?,`content_uri_triggers` = ? WHERE `id` = ?";
            }

            @Override // defpackage.ho
            public void bind(wl0 wl0Var, WorkSpec workSpec) throws Throwable {
                String str = workSpec.id;
                if (str == null) {
                    wl0Var.r(1);
                } else {
                    wl0Var.j(1, str);
                }
                WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                wl0Var.x(WorkTypeConverters.stateToInt(workSpec.state), 2);
                String str2 = workSpec.workerClassName;
                if (str2 == null) {
                    wl0Var.r(3);
                } else {
                    wl0Var.j(3, str2);
                }
                String str3 = workSpec.inputMergerClassName;
                if (str3 == null) {
                    wl0Var.r(4);
                } else {
                    wl0Var.j(4, str3);
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(workSpec.input);
                if (byteArrayInternal == null) {
                    wl0Var.r(5);
                } else {
                    wl0Var.B(byteArrayInternal, 5);
                }
                byte[] byteArrayInternal2 = Data.toByteArrayInternal(workSpec.output);
                if (byteArrayInternal2 == null) {
                    wl0Var.r(6);
                } else {
                    wl0Var.B(byteArrayInternal2, 6);
                }
                wl0Var.x(workSpec.initialDelay, 7);
                wl0Var.x(workSpec.intervalDuration, 8);
                wl0Var.x(workSpec.flexDuration, 9);
                wl0Var.x(workSpec.runAttemptCount, 10);
                wl0Var.x(WorkTypeConverters.backoffPolicyToInt(workSpec.backoffPolicy), 11);
                wl0Var.x(workSpec.backoffDelayDuration, 12);
                wl0Var.x(workSpec.lastEnqueueTime, 13);
                wl0Var.x(workSpec.minimumRetentionDuration, 14);
                wl0Var.x(workSpec.scheduleRequestedAt, 15);
                wl0Var.x(workSpec.expedited ? 1L : 0L, 16);
                wl0Var.x(WorkTypeConverters.outOfQuotaPolicyToInt(workSpec.outOfQuotaPolicy), 17);
                wl0Var.x(workSpec.getPeriodCount(), 18);
                wl0Var.x(workSpec.getGeneration(), 19);
                Constraints constraints = workSpec.constraints;
                if (constraints != null) {
                    wl0Var.x(WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()), 20);
                    wl0Var.x(constraints.requiresCharging() ? 1L : 0L, 21);
                    wl0Var.x(constraints.requiresDeviceIdle() ? 1L : 0L, 22);
                    wl0Var.x(constraints.requiresBatteryNotLow() ? 1L : 0L, 23);
                    wl0Var.x(constraints.requiresStorageNotLow() ? 1L : 0L, 24);
                    wl0Var.x(constraints.getContentTriggerUpdateDelayMillis(), 25);
                    wl0Var.x(constraints.getContentTriggerMaxDelayMillis(), 26);
                    byte[] ofTriggersToByteArray = WorkTypeConverters.setOfTriggersToByteArray(constraints.getContentUriTriggers());
                    if (ofTriggersToByteArray == null) {
                        wl0Var.r(27);
                    } else {
                        wl0Var.B(ofTriggersToByteArray, 27);
                    }
                } else {
                    wl0Var.r(20);
                    wl0Var.r(21);
                    wl0Var.r(22);
                    wl0Var.r(23);
                    wl0Var.r(24);
                    wl0Var.r(25);
                    wl0Var.r(26);
                    wl0Var.r(27);
                }
                String str4 = workSpec.id;
                if (str4 == null) {
                    wl0Var.r(28);
                } else {
                    wl0Var.j(28, str4);
                }
            }
        };
        this.__preparedStmtOfDelete = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.3
            @Override // defpackage.aj0
            public String createQuery() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.__preparedStmtOfSetState = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.4
            @Override // defpackage.aj0
            public String createQuery() {
                return "UPDATE workspec SET state=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementPeriodCount = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.5
            @Override // defpackage.aj0
            public String createQuery() {
                return "UPDATE workspec SET period_count=period_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfSetOutput = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.6
            @Override // defpackage.aj0
            public String createQuery() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetLastEnqueuedTime = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.7
            @Override // defpackage.aj0
            public String createQuery() {
                return "UPDATE workspec SET last_enqueue_time=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementWorkSpecRunAttemptCount = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.8
            @Override // defpackage.aj0
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecRunAttemptCount = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.9
            @Override // defpackage.aj0
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.__preparedStmtOfMarkWorkSpecScheduled = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.10
            @Override // defpackage.aj0
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetScheduledState = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.11
            @Override // defpackage.aj0
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.12
            @Override // defpackage.aj0
            public String createQuery() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
        this.__preparedStmtOfIncrementGeneration = new aj0(pf0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.13
            @Override // defpackage.aj0
            public String createQuery() {
                return "UPDATE workspec SET generation=generation+1 WHERE id=?";
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkProgressAsandroidxWorkData(c7 c7Var) throws IllegalAccessException, IOException, InvocationTargetException {
        l40 l40Var = (l40) c7Var.keySet();
        if (l40Var.isEmpty()) {
            return;
        }
        if (c7Var.d > 999) {
            c7 c7Var2 = new c7(pf0.MAX_BIND_PARAMETER_CNT);
            int i = c7Var.d;
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                c7Var2.put((String) c7Var.h(i2), (ArrayList) c7Var.j(i2));
                i2++;
                i3++;
                if (i3 == 999) {
                    __fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                    c7Var2 = new c7(pf0.MAX_BIND_PARAMETER_CNT);
                    i3 = 0;
                }
            }
            if (i3 > 0) {
                __fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                return;
            }
            return;
        }
        StringBuilder sbL = hk.l("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int iE = l40Var.c.e();
        za0.d(sbL, iE);
        sbL.append(")");
        sf0 sf0VarC = sf0.C(iE, sbL.toString());
        Iterator it = l40Var.iterator();
        int i4 = 1;
        while (true) {
            k40 k40Var = (k40) it;
            if (!k40Var.hasNext()) {
                break;
            }
            String str = (String) k40Var.next();
            if (str == null) {
                sf0VarC.r(i4);
            } else {
                sf0VarC.j(i4, str);
            }
            i4++;
        }
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            int iR = jq0.r(cursorB, "work_spec_id");
            if (iR == -1) {
                return;
            }
            while (cursorB.moveToNext()) {
                byte[] blob = null;
                ArrayList arrayList = (ArrayList) c7Var.getOrDefault(cursorB.getString(iR), null);
                if (arrayList != null) {
                    if (!cursorB.isNull(0)) {
                        blob = cursorB.getBlob(0);
                    }
                    arrayList.add(Data.fromByteArray(blob));
                }
            }
        } finally {
            cursorB.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkTagAsjavaLangString(c7 c7Var) throws IllegalAccessException, IOException, InvocationTargetException {
        l40 l40Var = (l40) c7Var.keySet();
        if (l40Var.isEmpty()) {
            return;
        }
        if (c7Var.d > 999) {
            c7 c7Var2 = new c7(pf0.MAX_BIND_PARAMETER_CNT);
            int i = c7Var.d;
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                c7Var2.put((String) c7Var.h(i2), (ArrayList) c7Var.j(i2));
                i2++;
                i3++;
                if (i3 == 999) {
                    __fetchRelationshipWorkTagAsjavaLangString(c7Var2);
                    c7Var2 = new c7(pf0.MAX_BIND_PARAMETER_CNT);
                    i3 = 0;
                }
            }
            if (i3 > 0) {
                __fetchRelationshipWorkTagAsjavaLangString(c7Var2);
                return;
            }
            return;
        }
        StringBuilder sbL = hk.l("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int iE = l40Var.c.e();
        za0.d(sbL, iE);
        sbL.append(")");
        sf0 sf0VarC = sf0.C(iE, sbL.toString());
        Iterator it = l40Var.iterator();
        int i4 = 1;
        while (true) {
            k40 k40Var = (k40) it;
            if (!k40Var.hasNext()) {
                break;
            }
            String str = (String) k40Var.next();
            if (str == null) {
                sf0VarC.r(i4);
            } else {
                sf0VarC.j(i4, str);
            }
            i4++;
        }
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            int iR = jq0.r(cursorB, "work_spec_id");
            if (iR == -1) {
                return;
            }
            while (cursorB.moveToNext()) {
                String string = null;
                ArrayList arrayList = (ArrayList) c7Var.getOrDefault(cursorB.getString(iR), null);
                if (arrayList != null) {
                    if (!cursorB.isNull(0)) {
                        string = cursorB.getString(0);
                    }
                    arrayList.add(string);
                }
            }
        } finally {
            cursorB.close();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void delete(String str) {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfDelete.acquire();
        if (str == null) {
            wl0VarAcquire.r(1);
        } else {
            wl0VarAcquire.j(1, str);
        }
        this.__db.beginTransaction();
        try {
            wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getAllEligibleWorkSpecsForScheduling(int i) throws Throwable {
        sf0 sf0Var;
        sf0 sf0VarC = sf0.C(1, "SELECT * FROM workspec WHERE state=0 ORDER BY last_enqueue_time LIMIT ?");
        sf0VarC.x(i, 1);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            int iS = jq0.s(cursorB, "id");
            int iS2 = jq0.s(cursorB, "state");
            int iS3 = jq0.s(cursorB, "worker_class_name");
            int iS4 = jq0.s(cursorB, "input_merger_class_name");
            int iS5 = jq0.s(cursorB, "input");
            int iS6 = jq0.s(cursorB, "output");
            int iS7 = jq0.s(cursorB, "initial_delay");
            int iS8 = jq0.s(cursorB, "interval_duration");
            int iS9 = jq0.s(cursorB, "flex_duration");
            int iS10 = jq0.s(cursorB, "run_attempt_count");
            int iS11 = jq0.s(cursorB, "backoff_policy");
            int iS12 = jq0.s(cursorB, "backoff_delay_duration");
            int iS13 = jq0.s(cursorB, "last_enqueue_time");
            int iS14 = jq0.s(cursorB, "minimum_retention_duration");
            sf0Var = sf0VarC;
            try {
                int iS15 = jq0.s(cursorB, "schedule_requested_at");
                int iS16 = jq0.s(cursorB, "run_in_foreground");
                int iS17 = jq0.s(cursorB, "out_of_quota_policy");
                int iS18 = jq0.s(cursorB, "period_count");
                int iS19 = jq0.s(cursorB, "generation");
                int iS20 = jq0.s(cursorB, "required_network_type");
                int iS21 = jq0.s(cursorB, "requires_charging");
                int iS22 = jq0.s(cursorB, "requires_device_idle");
                int iS23 = jq0.s(cursorB, "requires_battery_not_low");
                int iS24 = jq0.s(cursorB, "requires_storage_not_low");
                int iS25 = jq0.s(cursorB, "trigger_content_update_delay");
                int iS26 = jq0.s(cursorB, "trigger_max_content_delay");
                int iS27 = jq0.s(cursorB, "content_uri_triggers");
                int i2 = iS14;
                ArrayList arrayList = new ArrayList(cursorB.getCount());
                while (cursorB.moveToNext()) {
                    byte[] blob = null;
                    String string = cursorB.isNull(iS) ? null : cursorB.getString(iS);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(iS2));
                    String string2 = cursorB.isNull(iS3) ? null : cursorB.getString(iS3);
                    String string3 = cursorB.isNull(iS4) ? null : cursorB.getString(iS4);
                    Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(iS5) ? null : cursorB.getBlob(iS5));
                    Data dataFromByteArray2 = Data.fromByteArray(cursorB.isNull(iS6) ? null : cursorB.getBlob(iS6));
                    long j = cursorB.getLong(iS7);
                    long j2 = cursorB.getLong(iS8);
                    long j3 = cursorB.getLong(iS9);
                    int i3 = cursorB.getInt(iS10);
                    BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorB.getInt(iS11));
                    long j4 = cursorB.getLong(iS12);
                    long j5 = cursorB.getLong(iS13);
                    int i4 = i2;
                    long j6 = cursorB.getLong(i4);
                    int i5 = iS;
                    int i6 = iS15;
                    long j7 = cursorB.getLong(i6);
                    iS15 = i6;
                    int i7 = iS16;
                    boolean z = cursorB.getInt(i7) != 0;
                    iS16 = i7;
                    int i8 = iS17;
                    OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorB.getInt(i8));
                    iS17 = i8;
                    int i9 = iS18;
                    int i10 = cursorB.getInt(i9);
                    iS18 = i9;
                    int i11 = iS19;
                    int i12 = cursorB.getInt(i11);
                    iS19 = i11;
                    int i13 = iS20;
                    NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorB.getInt(i13));
                    iS20 = i13;
                    int i14 = iS21;
                    boolean z2 = cursorB.getInt(i14) != 0;
                    iS21 = i14;
                    int i15 = iS22;
                    boolean z3 = cursorB.getInt(i15) != 0;
                    iS22 = i15;
                    int i16 = iS23;
                    boolean z4 = cursorB.getInt(i16) != 0;
                    iS23 = i16;
                    int i17 = iS24;
                    boolean z5 = cursorB.getInt(i17) != 0;
                    iS24 = i17;
                    int i18 = iS25;
                    long j8 = cursorB.getLong(i18);
                    iS25 = i18;
                    int i19 = iS26;
                    long j9 = cursorB.getLong(i19);
                    iS26 = i19;
                    int i20 = iS27;
                    if (!cursorB.isNull(i20)) {
                        blob = cursorB.getBlob(i20);
                    }
                    iS27 = i20;
                    arrayList.add(new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j, j2, j3, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i3, backoffPolicyIntToBackoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i10, i12));
                    iS = i5;
                    i2 = i4;
                }
                cursorB.close();
                sf0Var.D();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorB.close();
                sf0Var.D();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            sf0Var = sf0VarC;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getAllUnfinishedWork() throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(0, "SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                arrayList.add(cursorB.isNull(0) ? null : cursorB.getString(0));
            }
            return arrayList;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getAllWorkSpecIds() throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(0, "SELECT id FROM workspec");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                arrayList.add(cursorB.isNull(0) ? null : cursorB.getString(0));
            }
            return arrayList;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public b30 getAllWorkSpecIdsLiveData() {
        final sf0 sf0VarC = sf0.C(0, "SELECT id FROM workspec");
        return this.__db.getInvalidationTracker().a(new String[]{"workspec"}, true, new Callable<List<String>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.14
            public void finalize() {
                sf0VarC.D();
            }

            @Override // java.util.concurrent.Callable
            public List<String> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorB = k9.B(WorkSpecDao_Impl.this.__db, sf0VarC, false);
                    try {
                        ArrayList arrayList = new ArrayList(cursorB.getCount());
                        while (cursorB.moveToNext()) {
                            arrayList.add(cursorB.isNull(0) ? null : cursorB.getString(0));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        cursorB.close();
                        return arrayList;
                    } catch (Throwable th) {
                        cursorB.close();
                        throw th;
                    }
                } finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getEligibleWorkForScheduling(int i) throws Throwable {
        sf0 sf0Var;
        sf0 sf0VarC = sf0.C(1, "SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY last_enqueue_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))");
        sf0VarC.x(i, 1);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            int iS = jq0.s(cursorB, "id");
            int iS2 = jq0.s(cursorB, "state");
            int iS3 = jq0.s(cursorB, "worker_class_name");
            int iS4 = jq0.s(cursorB, "input_merger_class_name");
            int iS5 = jq0.s(cursorB, "input");
            int iS6 = jq0.s(cursorB, "output");
            int iS7 = jq0.s(cursorB, "initial_delay");
            int iS8 = jq0.s(cursorB, "interval_duration");
            int iS9 = jq0.s(cursorB, "flex_duration");
            int iS10 = jq0.s(cursorB, "run_attempt_count");
            int iS11 = jq0.s(cursorB, "backoff_policy");
            int iS12 = jq0.s(cursorB, "backoff_delay_duration");
            int iS13 = jq0.s(cursorB, "last_enqueue_time");
            int iS14 = jq0.s(cursorB, "minimum_retention_duration");
            sf0Var = sf0VarC;
            try {
                int iS15 = jq0.s(cursorB, "schedule_requested_at");
                int iS16 = jq0.s(cursorB, "run_in_foreground");
                int iS17 = jq0.s(cursorB, "out_of_quota_policy");
                int iS18 = jq0.s(cursorB, "period_count");
                int iS19 = jq0.s(cursorB, "generation");
                int iS20 = jq0.s(cursorB, "required_network_type");
                int iS21 = jq0.s(cursorB, "requires_charging");
                int iS22 = jq0.s(cursorB, "requires_device_idle");
                int iS23 = jq0.s(cursorB, "requires_battery_not_low");
                int iS24 = jq0.s(cursorB, "requires_storage_not_low");
                int iS25 = jq0.s(cursorB, "trigger_content_update_delay");
                int iS26 = jq0.s(cursorB, "trigger_max_content_delay");
                int iS27 = jq0.s(cursorB, "content_uri_triggers");
                int i2 = iS14;
                ArrayList arrayList = new ArrayList(cursorB.getCount());
                while (cursorB.moveToNext()) {
                    byte[] blob = null;
                    String string = cursorB.isNull(iS) ? null : cursorB.getString(iS);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(iS2));
                    String string2 = cursorB.isNull(iS3) ? null : cursorB.getString(iS3);
                    String string3 = cursorB.isNull(iS4) ? null : cursorB.getString(iS4);
                    Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(iS5) ? null : cursorB.getBlob(iS5));
                    Data dataFromByteArray2 = Data.fromByteArray(cursorB.isNull(iS6) ? null : cursorB.getBlob(iS6));
                    long j = cursorB.getLong(iS7);
                    long j2 = cursorB.getLong(iS8);
                    long j3 = cursorB.getLong(iS9);
                    int i3 = cursorB.getInt(iS10);
                    BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorB.getInt(iS11));
                    long j4 = cursorB.getLong(iS12);
                    long j5 = cursorB.getLong(iS13);
                    int i4 = i2;
                    long j6 = cursorB.getLong(i4);
                    int i5 = iS;
                    int i6 = iS15;
                    long j7 = cursorB.getLong(i6);
                    iS15 = i6;
                    int i7 = iS16;
                    boolean z = cursorB.getInt(i7) != 0;
                    iS16 = i7;
                    int i8 = iS17;
                    OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorB.getInt(i8));
                    iS17 = i8;
                    int i9 = iS18;
                    int i10 = cursorB.getInt(i9);
                    iS18 = i9;
                    int i11 = iS19;
                    int i12 = cursorB.getInt(i11);
                    iS19 = i11;
                    int i13 = iS20;
                    NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorB.getInt(i13));
                    iS20 = i13;
                    int i14 = iS21;
                    boolean z2 = cursorB.getInt(i14) != 0;
                    iS21 = i14;
                    int i15 = iS22;
                    boolean z3 = cursorB.getInt(i15) != 0;
                    iS22 = i15;
                    int i16 = iS23;
                    boolean z4 = cursorB.getInt(i16) != 0;
                    iS23 = i16;
                    int i17 = iS24;
                    boolean z5 = cursorB.getInt(i17) != 0;
                    iS24 = i17;
                    int i18 = iS25;
                    long j8 = cursorB.getLong(i18);
                    iS25 = i18;
                    int i19 = iS26;
                    long j9 = cursorB.getLong(i19);
                    iS26 = i19;
                    int i20 = iS27;
                    if (!cursorB.isNull(i20)) {
                        blob = cursorB.getBlob(i20);
                    }
                    iS27 = i20;
                    arrayList.add(new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j, j2, j3, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i3, backoffPolicyIntToBackoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i10, i12));
                    iS = i5;
                    i2 = i4;
                }
                cursorB.close();
                sf0Var.D();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorB.close();
                sf0Var.D();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            sf0Var = sf0VarC;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<Data> getInputsFromPrerequisites(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT output FROM workspec WHERE id IN\n             (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                arrayList.add(Data.fromByteArray(cursorB.isNull(0) ? null : cursorB.getBlob(0)));
            }
            return arrayList;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getRecentlyCompletedWork(long j) throws Throwable {
        sf0 sf0Var;
        int iS;
        int iS2;
        int iS3;
        int iS4;
        int iS5;
        int iS6;
        int iS7;
        int iS8;
        int iS9;
        int iS10;
        int iS11;
        int iS12;
        int iS13;
        int iS14;
        sf0 sf0VarC = sf0.C(1, "SELECT * FROM workspec WHERE last_enqueue_time >= ? AND state IN (2, 3, 5) ORDER BY last_enqueue_time DESC");
        sf0VarC.x(j, 1);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            iS = jq0.s(cursorB, "id");
            iS2 = jq0.s(cursorB, "state");
            iS3 = jq0.s(cursorB, "worker_class_name");
            iS4 = jq0.s(cursorB, "input_merger_class_name");
            iS5 = jq0.s(cursorB, "input");
            iS6 = jq0.s(cursorB, "output");
            iS7 = jq0.s(cursorB, "initial_delay");
            iS8 = jq0.s(cursorB, "interval_duration");
            iS9 = jq0.s(cursorB, "flex_duration");
            iS10 = jq0.s(cursorB, "run_attempt_count");
            iS11 = jq0.s(cursorB, "backoff_policy");
            iS12 = jq0.s(cursorB, "backoff_delay_duration");
            iS13 = jq0.s(cursorB, "last_enqueue_time");
            iS14 = jq0.s(cursorB, "minimum_retention_duration");
            sf0Var = sf0VarC;
        } catch (Throwable th) {
            th = th;
            sf0Var = sf0VarC;
        }
        try {
            int iS15 = jq0.s(cursorB, "schedule_requested_at");
            int iS16 = jq0.s(cursorB, "run_in_foreground");
            int iS17 = jq0.s(cursorB, "out_of_quota_policy");
            int iS18 = jq0.s(cursorB, "period_count");
            int iS19 = jq0.s(cursorB, "generation");
            int iS20 = jq0.s(cursorB, "required_network_type");
            int iS21 = jq0.s(cursorB, "requires_charging");
            int iS22 = jq0.s(cursorB, "requires_device_idle");
            int iS23 = jq0.s(cursorB, "requires_battery_not_low");
            int iS24 = jq0.s(cursorB, "requires_storage_not_low");
            int iS25 = jq0.s(cursorB, "trigger_content_update_delay");
            int iS26 = jq0.s(cursorB, "trigger_max_content_delay");
            int iS27 = jq0.s(cursorB, "content_uri_triggers");
            int i = iS14;
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                byte[] blob = null;
                String string = cursorB.isNull(iS) ? null : cursorB.getString(iS);
                WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(iS2));
                String string2 = cursorB.isNull(iS3) ? null : cursorB.getString(iS3);
                String string3 = cursorB.isNull(iS4) ? null : cursorB.getString(iS4);
                Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(iS5) ? null : cursorB.getBlob(iS5));
                Data dataFromByteArray2 = Data.fromByteArray(cursorB.isNull(iS6) ? null : cursorB.getBlob(iS6));
                long j2 = cursorB.getLong(iS7);
                long j3 = cursorB.getLong(iS8);
                long j4 = cursorB.getLong(iS9);
                int i2 = cursorB.getInt(iS10);
                BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorB.getInt(iS11));
                long j5 = cursorB.getLong(iS12);
                long j6 = cursorB.getLong(iS13);
                int i3 = i;
                long j7 = cursorB.getLong(i3);
                int i4 = iS;
                int i5 = iS15;
                long j8 = cursorB.getLong(i5);
                iS15 = i5;
                int i6 = iS16;
                boolean z = cursorB.getInt(i6) != 0;
                iS16 = i6;
                int i7 = iS17;
                OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorB.getInt(i7));
                iS17 = i7;
                int i8 = iS18;
                int i9 = cursorB.getInt(i8);
                iS18 = i8;
                int i10 = iS19;
                int i11 = cursorB.getInt(i10);
                iS19 = i10;
                int i12 = iS20;
                NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorB.getInt(i12));
                iS20 = i12;
                int i13 = iS21;
                boolean z2 = cursorB.getInt(i13) != 0;
                iS21 = i13;
                int i14 = iS22;
                boolean z3 = cursorB.getInt(i14) != 0;
                iS22 = i14;
                int i15 = iS23;
                boolean z4 = cursorB.getInt(i15) != 0;
                iS23 = i15;
                int i16 = iS24;
                boolean z5 = cursorB.getInt(i16) != 0;
                iS24 = i16;
                int i17 = iS25;
                long j9 = cursorB.getLong(i17);
                iS25 = i17;
                int i18 = iS26;
                long j10 = cursorB.getLong(i18);
                iS26 = i18;
                int i19 = iS27;
                if (!cursorB.isNull(i19)) {
                    blob = cursorB.getBlob(i19);
                }
                iS27 = i19;
                arrayList.add(new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j2, j3, j4, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j9, j10, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i2, backoffPolicyIntToBackoffPolicy, j5, j6, j7, j8, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i9, i11));
                iS = i4;
                i = i3;
            }
            cursorB.close();
            sf0Var.D();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorB.close();
            sf0Var.D();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getRunningWork() throws Throwable {
        sf0 sf0Var;
        int iS;
        int iS2;
        int iS3;
        int iS4;
        int iS5;
        int iS6;
        int iS7;
        int iS8;
        int iS9;
        int iS10;
        int iS11;
        int iS12;
        int iS13;
        int iS14;
        sf0 sf0VarC = sf0.C(0, "SELECT * FROM workspec WHERE state=1");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            iS = jq0.s(cursorB, "id");
            iS2 = jq0.s(cursorB, "state");
            iS3 = jq0.s(cursorB, "worker_class_name");
            iS4 = jq0.s(cursorB, "input_merger_class_name");
            iS5 = jq0.s(cursorB, "input");
            iS6 = jq0.s(cursorB, "output");
            iS7 = jq0.s(cursorB, "initial_delay");
            iS8 = jq0.s(cursorB, "interval_duration");
            iS9 = jq0.s(cursorB, "flex_duration");
            iS10 = jq0.s(cursorB, "run_attempt_count");
            iS11 = jq0.s(cursorB, "backoff_policy");
            iS12 = jq0.s(cursorB, "backoff_delay_duration");
            iS13 = jq0.s(cursorB, "last_enqueue_time");
            iS14 = jq0.s(cursorB, "minimum_retention_duration");
            sf0Var = sf0VarC;
        } catch (Throwable th) {
            th = th;
            sf0Var = sf0VarC;
        }
        try {
            int iS15 = jq0.s(cursorB, "schedule_requested_at");
            int iS16 = jq0.s(cursorB, "run_in_foreground");
            int iS17 = jq0.s(cursorB, "out_of_quota_policy");
            int iS18 = jq0.s(cursorB, "period_count");
            int iS19 = jq0.s(cursorB, "generation");
            int iS20 = jq0.s(cursorB, "required_network_type");
            int iS21 = jq0.s(cursorB, "requires_charging");
            int iS22 = jq0.s(cursorB, "requires_device_idle");
            int iS23 = jq0.s(cursorB, "requires_battery_not_low");
            int iS24 = jq0.s(cursorB, "requires_storage_not_low");
            int iS25 = jq0.s(cursorB, "trigger_content_update_delay");
            int iS26 = jq0.s(cursorB, "trigger_max_content_delay");
            int iS27 = jq0.s(cursorB, "content_uri_triggers");
            int i = iS14;
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                byte[] blob = null;
                String string = cursorB.isNull(iS) ? null : cursorB.getString(iS);
                WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(iS2));
                String string2 = cursorB.isNull(iS3) ? null : cursorB.getString(iS3);
                String string3 = cursorB.isNull(iS4) ? null : cursorB.getString(iS4);
                Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(iS5) ? null : cursorB.getBlob(iS5));
                Data dataFromByteArray2 = Data.fromByteArray(cursorB.isNull(iS6) ? null : cursorB.getBlob(iS6));
                long j = cursorB.getLong(iS7);
                long j2 = cursorB.getLong(iS8);
                long j3 = cursorB.getLong(iS9);
                int i2 = cursorB.getInt(iS10);
                BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorB.getInt(iS11));
                long j4 = cursorB.getLong(iS12);
                long j5 = cursorB.getLong(iS13);
                int i3 = i;
                long j6 = cursorB.getLong(i3);
                int i4 = iS;
                int i5 = iS15;
                long j7 = cursorB.getLong(i5);
                iS15 = i5;
                int i6 = iS16;
                boolean z = cursorB.getInt(i6) != 0;
                iS16 = i6;
                int i7 = iS17;
                OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorB.getInt(i7));
                iS17 = i7;
                int i8 = iS18;
                int i9 = cursorB.getInt(i8);
                iS18 = i8;
                int i10 = iS19;
                int i11 = cursorB.getInt(i10);
                iS19 = i10;
                int i12 = iS20;
                NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorB.getInt(i12));
                iS20 = i12;
                int i13 = iS21;
                boolean z2 = cursorB.getInt(i13) != 0;
                iS21 = i13;
                int i14 = iS22;
                boolean z3 = cursorB.getInt(i14) != 0;
                iS22 = i14;
                int i15 = iS23;
                boolean z4 = cursorB.getInt(i15) != 0;
                iS23 = i15;
                int i16 = iS24;
                boolean z5 = cursorB.getInt(i16) != 0;
                iS24 = i16;
                int i17 = iS25;
                long j8 = cursorB.getLong(i17);
                iS25 = i17;
                int i18 = iS26;
                long j9 = cursorB.getLong(i18);
                iS26 = i18;
                int i19 = iS27;
                if (!cursorB.isNull(i19)) {
                    blob = cursorB.getBlob(i19);
                }
                iS27 = i19;
                arrayList.add(new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j, j2, j3, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i2, backoffPolicyIntToBackoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i9, i11));
                iS = i4;
                i = i3;
            }
            cursorB.close();
            sf0Var.D();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorB.close();
            sf0Var.D();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public b30 getScheduleRequestedAtLiveData(String str) {
        final sf0 sf0VarC = sf0.C(1, "SELECT schedule_requested_at FROM workspec WHERE id=?");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        return this.__db.getInvalidationTracker().a(new String[]{"workspec"}, false, new Callable<Long>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.18
            public void finalize() {
                sf0VarC.D();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                Cursor cursorB = k9.B(WorkSpecDao_Impl.this.__db, sf0VarC, false);
                try {
                    Long lValueOf = Long.valueOf(cursorB.moveToFirst() ? cursorB.getLong(0) : 0L);
                    cursorB.close();
                    return lValueOf;
                } catch (Throwable th) {
                    cursorB.close();
                    throw th;
                }
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getScheduledWork() throws Throwable {
        sf0 sf0Var;
        int iS;
        int iS2;
        int iS3;
        int iS4;
        int iS5;
        int iS6;
        int iS7;
        int iS8;
        int iS9;
        int iS10;
        int iS11;
        int iS12;
        int iS13;
        int iS14;
        sf0 sf0VarC = sf0.C(0, "SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            iS = jq0.s(cursorB, "id");
            iS2 = jq0.s(cursorB, "state");
            iS3 = jq0.s(cursorB, "worker_class_name");
            iS4 = jq0.s(cursorB, "input_merger_class_name");
            iS5 = jq0.s(cursorB, "input");
            iS6 = jq0.s(cursorB, "output");
            iS7 = jq0.s(cursorB, "initial_delay");
            iS8 = jq0.s(cursorB, "interval_duration");
            iS9 = jq0.s(cursorB, "flex_duration");
            iS10 = jq0.s(cursorB, "run_attempt_count");
            iS11 = jq0.s(cursorB, "backoff_policy");
            iS12 = jq0.s(cursorB, "backoff_delay_duration");
            iS13 = jq0.s(cursorB, "last_enqueue_time");
            iS14 = jq0.s(cursorB, "minimum_retention_duration");
            sf0Var = sf0VarC;
        } catch (Throwable th) {
            th = th;
            sf0Var = sf0VarC;
        }
        try {
            int iS15 = jq0.s(cursorB, "schedule_requested_at");
            int iS16 = jq0.s(cursorB, "run_in_foreground");
            int iS17 = jq0.s(cursorB, "out_of_quota_policy");
            int iS18 = jq0.s(cursorB, "period_count");
            int iS19 = jq0.s(cursorB, "generation");
            int iS20 = jq0.s(cursorB, "required_network_type");
            int iS21 = jq0.s(cursorB, "requires_charging");
            int iS22 = jq0.s(cursorB, "requires_device_idle");
            int iS23 = jq0.s(cursorB, "requires_battery_not_low");
            int iS24 = jq0.s(cursorB, "requires_storage_not_low");
            int iS25 = jq0.s(cursorB, "trigger_content_update_delay");
            int iS26 = jq0.s(cursorB, "trigger_max_content_delay");
            int iS27 = jq0.s(cursorB, "content_uri_triggers");
            int i = iS14;
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                byte[] blob = null;
                String string = cursorB.isNull(iS) ? null : cursorB.getString(iS);
                WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(iS2));
                String string2 = cursorB.isNull(iS3) ? null : cursorB.getString(iS3);
                String string3 = cursorB.isNull(iS4) ? null : cursorB.getString(iS4);
                Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(iS5) ? null : cursorB.getBlob(iS5));
                Data dataFromByteArray2 = Data.fromByteArray(cursorB.isNull(iS6) ? null : cursorB.getBlob(iS6));
                long j = cursorB.getLong(iS7);
                long j2 = cursorB.getLong(iS8);
                long j3 = cursorB.getLong(iS9);
                int i2 = cursorB.getInt(iS10);
                BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorB.getInt(iS11));
                long j4 = cursorB.getLong(iS12);
                long j5 = cursorB.getLong(iS13);
                int i3 = i;
                long j6 = cursorB.getLong(i3);
                int i4 = iS;
                int i5 = iS15;
                long j7 = cursorB.getLong(i5);
                iS15 = i5;
                int i6 = iS16;
                boolean z = cursorB.getInt(i6) != 0;
                iS16 = i6;
                int i7 = iS17;
                OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorB.getInt(i7));
                iS17 = i7;
                int i8 = iS18;
                int i9 = cursorB.getInt(i8);
                iS18 = i8;
                int i10 = iS19;
                int i11 = cursorB.getInt(i10);
                iS19 = i10;
                int i12 = iS20;
                NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorB.getInt(i12));
                iS20 = i12;
                int i13 = iS21;
                boolean z2 = cursorB.getInt(i13) != 0;
                iS21 = i13;
                int i14 = iS22;
                boolean z3 = cursorB.getInt(i14) != 0;
                iS22 = i14;
                int i15 = iS23;
                boolean z4 = cursorB.getInt(i15) != 0;
                iS23 = i15;
                int i16 = iS24;
                boolean z5 = cursorB.getInt(i16) != 0;
                iS24 = i16;
                int i17 = iS25;
                long j8 = cursorB.getLong(i17);
                iS25 = i17;
                int i18 = iS26;
                long j9 = cursorB.getLong(i18);
                iS26 = i18;
                int i19 = iS27;
                if (!cursorB.isNull(i19)) {
                    blob = cursorB.getBlob(i19);
                }
                iS27 = i19;
                arrayList.add(new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j, j2, j3, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i2, backoffPolicyIntToBackoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i9, i11));
                iS = i4;
                i = i3;
            }
            cursorB.close();
            sf0Var.D();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorB.close();
            sf0Var.D();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkInfo.State getState(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT state FROM workspec WHERE id=?");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            WorkInfo.State stateIntToState = null;
            if (cursorB.moveToFirst()) {
                Integer numValueOf = cursorB.isNull(0) ? null : Integer.valueOf(cursorB.getInt(0));
                if (numValueOf != null) {
                    WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                    stateIntToState = WorkTypeConverters.intToState(numValueOf.intValue());
                }
            }
            return stateIntToState;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getUnfinishedWorkWithName(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                arrayList.add(cursorB.isNull(0) ? null : cursorB.getString(0));
            }
            return arrayList;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getUnfinishedWorkWithTag(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                arrayList.add(cursorB.isNull(0) ? null : cursorB.getString(0));
            }
            return arrayList;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec getWorkSpec(String str) throws Throwable {
        sf0 sf0Var;
        int iS;
        int iS2;
        int iS3;
        int iS4;
        int iS5;
        int iS6;
        int iS7;
        int iS8;
        int iS9;
        int iS10;
        int iS11;
        int iS12;
        int iS13;
        int iS14;
        sf0 sf0VarC = sf0.C(1, "SELECT * FROM workspec WHERE id=?");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            iS = jq0.s(cursorB, "id");
            iS2 = jq0.s(cursorB, "state");
            iS3 = jq0.s(cursorB, "worker_class_name");
            iS4 = jq0.s(cursorB, "input_merger_class_name");
            iS5 = jq0.s(cursorB, "input");
            iS6 = jq0.s(cursorB, "output");
            iS7 = jq0.s(cursorB, "initial_delay");
            iS8 = jq0.s(cursorB, "interval_duration");
            iS9 = jq0.s(cursorB, "flex_duration");
            iS10 = jq0.s(cursorB, "run_attempt_count");
            iS11 = jq0.s(cursorB, "backoff_policy");
            iS12 = jq0.s(cursorB, "backoff_delay_duration");
            iS13 = jq0.s(cursorB, "last_enqueue_time");
            iS14 = jq0.s(cursorB, "minimum_retention_duration");
            sf0Var = sf0VarC;
        } catch (Throwable th) {
            th = th;
            sf0Var = sf0VarC;
        }
        try {
            int iS15 = jq0.s(cursorB, "schedule_requested_at");
            int iS16 = jq0.s(cursorB, "run_in_foreground");
            int iS17 = jq0.s(cursorB, "out_of_quota_policy");
            int iS18 = jq0.s(cursorB, "period_count");
            int iS19 = jq0.s(cursorB, "generation");
            int iS20 = jq0.s(cursorB, "required_network_type");
            int iS21 = jq0.s(cursorB, "requires_charging");
            int iS22 = jq0.s(cursorB, "requires_device_idle");
            int iS23 = jq0.s(cursorB, "requires_battery_not_low");
            int iS24 = jq0.s(cursorB, "requires_storage_not_low");
            int iS25 = jq0.s(cursorB, "trigger_content_update_delay");
            int iS26 = jq0.s(cursorB, "trigger_max_content_delay");
            int iS27 = jq0.s(cursorB, "content_uri_triggers");
            WorkSpec workSpec = null;
            byte[] blob = null;
            if (cursorB.moveToFirst()) {
                String string = cursorB.isNull(iS) ? null : cursorB.getString(iS);
                WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(iS2));
                String string2 = cursorB.isNull(iS3) ? null : cursorB.getString(iS3);
                String string3 = cursorB.isNull(iS4) ? null : cursorB.getString(iS4);
                Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(iS5) ? null : cursorB.getBlob(iS5));
                Data dataFromByteArray2 = Data.fromByteArray(cursorB.isNull(iS6) ? null : cursorB.getBlob(iS6));
                long j = cursorB.getLong(iS7);
                long j2 = cursorB.getLong(iS8);
                long j3 = cursorB.getLong(iS9);
                int i = cursorB.getInt(iS10);
                BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorB.getInt(iS11));
                long j4 = cursorB.getLong(iS12);
                long j5 = cursorB.getLong(iS13);
                long j6 = cursorB.getLong(iS14);
                long j7 = cursorB.getLong(iS15);
                boolean z = cursorB.getInt(iS16) != 0;
                OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorB.getInt(iS17));
                int i2 = cursorB.getInt(iS18);
                int i3 = cursorB.getInt(iS19);
                NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorB.getInt(iS20));
                boolean z2 = cursorB.getInt(iS21) != 0;
                boolean z3 = cursorB.getInt(iS22) != 0;
                boolean z4 = cursorB.getInt(iS23) != 0;
                boolean z5 = cursorB.getInt(iS24) != 0;
                long j8 = cursorB.getLong(iS25);
                long j9 = cursorB.getLong(iS26);
                if (!cursorB.isNull(iS27)) {
                    blob = cursorB.getBlob(iS27);
                }
                workSpec = new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j, j2, j3, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i, backoffPolicyIntToBackoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i2, i3);
            }
            cursorB.close();
            sf0Var.D();
            return workSpec;
        } catch (Throwable th2) {
            th = th2;
            cursorB.close();
            sf0Var.D();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(String str) throws IllegalAccessException, IOException, InvocationTargetException {
        sf0 sf0VarC = sf0.C(1, "SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                arrayList.add(new WorkSpec.IdAndState(cursorB.isNull(0) ? null : cursorB.getString(0), WorkTypeConverters.intToState(cursorB.getInt(1))));
            }
            return arrayList;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec.WorkInfoPojo getWorkStatusPojoForId(String str) {
        WorkSpec.WorkInfoPojo workInfoPojo;
        sf0 sf0VarC = sf0.C(1, "SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id=?");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorB = k9.B(this.__db, sf0VarC, true);
            try {
                c7 c7Var = new c7();
                c7 c7Var2 = new c7();
                while (true) {
                    workInfoPojo = null;
                    if (!cursorB.moveToNext()) {
                        break;
                    }
                    String string = cursorB.getString(0);
                    if (((ArrayList) c7Var.getOrDefault(string, null)) == null) {
                        c7Var.put(string, new ArrayList());
                    }
                    String string2 = cursorB.getString(0);
                    if (((ArrayList) c7Var2.getOrDefault(string2, null)) == null) {
                        c7Var2.put(string2, new ArrayList());
                    }
                }
                cursorB.moveToPosition(-1);
                __fetchRelationshipWorkTagAsjavaLangString(c7Var);
                __fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                if (cursorB.moveToFirst()) {
                    String string3 = cursorB.isNull(0) ? null : cursorB.getString(0);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(1));
                    Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(2) ? null : cursorB.getBlob(2));
                    int i = cursorB.getInt(3);
                    int i2 = cursorB.getInt(4);
                    ArrayList arrayList = (ArrayList) c7Var.getOrDefault(cursorB.getString(0), null);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    ArrayList arrayList2 = arrayList;
                    ArrayList arrayList3 = (ArrayList) c7Var2.getOrDefault(cursorB.getString(0), null);
                    if (arrayList3 == null) {
                        arrayList3 = new ArrayList();
                    }
                    workInfoPojo = new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList2, arrayList3);
                }
                this.__db.setTransactionSuccessful();
                cursorB.close();
                sf0VarC.D();
                return workInfoPojo;
            } catch (Throwable th) {
                cursorB.close();
                sf0VarC.D();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForIds(List<String> list) {
        StringBuilder sbL = hk.l("SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN (");
        int size = list.size();
        za0.d(sbL, size);
        sbL.append(")");
        sf0 sf0VarC = sf0.C(size, sbL.toString());
        int i = 1;
        for (String str : list) {
            if (str == null) {
                sf0VarC.r(i);
            } else {
                sf0VarC.j(i, str);
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorB = k9.B(this.__db, sf0VarC, true);
            try {
                c7 c7Var = new c7();
                c7 c7Var2 = new c7();
                while (cursorB.moveToNext()) {
                    String string = cursorB.getString(0);
                    if (((ArrayList) c7Var.getOrDefault(string, null)) == null) {
                        c7Var.put(string, new ArrayList());
                    }
                    String string2 = cursorB.getString(0);
                    if (((ArrayList) c7Var2.getOrDefault(string2, null)) == null) {
                        c7Var2.put(string2, new ArrayList());
                    }
                }
                cursorB.moveToPosition(-1);
                __fetchRelationshipWorkTagAsjavaLangString(c7Var);
                __fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                ArrayList arrayList = new ArrayList(cursorB.getCount());
                while (cursorB.moveToNext()) {
                    String string3 = cursorB.isNull(0) ? null : cursorB.getString(0);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(1));
                    Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(2) ? null : cursorB.getBlob(2));
                    int i2 = cursorB.getInt(3);
                    int i3 = cursorB.getInt(4);
                    ArrayList arrayList2 = (ArrayList) c7Var.getOrDefault(cursorB.getString(0), null);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    ArrayList arrayList3 = arrayList2;
                    ArrayList arrayList4 = (ArrayList) c7Var2.getOrDefault(cursorB.getString(0), null);
                    if (arrayList4 == null) {
                        arrayList4 = new ArrayList();
                    }
                    arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i2, i3, arrayList3, arrayList4));
                }
                this.__db.setTransactionSuccessful();
                cursorB.close();
                sf0VarC.D();
                return arrayList;
            } catch (Throwable th) {
                cursorB.close();
                sf0VarC.D();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForName(String str) {
        sf0 sf0VarC = sf0.C(1, "SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorB = k9.B(this.__db, sf0VarC, true);
            try {
                c7 c7Var = new c7();
                c7 c7Var2 = new c7();
                while (cursorB.moveToNext()) {
                    String string = cursorB.getString(0);
                    if (((ArrayList) c7Var.getOrDefault(string, null)) == null) {
                        c7Var.put(string, new ArrayList());
                    }
                    String string2 = cursorB.getString(0);
                    if (((ArrayList) c7Var2.getOrDefault(string2, null)) == null) {
                        c7Var2.put(string2, new ArrayList());
                    }
                }
                cursorB.moveToPosition(-1);
                __fetchRelationshipWorkTagAsjavaLangString(c7Var);
                __fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                ArrayList arrayList = new ArrayList(cursorB.getCount());
                while (cursorB.moveToNext()) {
                    String string3 = cursorB.isNull(0) ? null : cursorB.getString(0);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(1));
                    Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(2) ? null : cursorB.getBlob(2));
                    int i = cursorB.getInt(3);
                    int i2 = cursorB.getInt(4);
                    ArrayList arrayList2 = (ArrayList) c7Var.getOrDefault(cursorB.getString(0), null);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    ArrayList arrayList3 = arrayList2;
                    ArrayList arrayList4 = (ArrayList) c7Var2.getOrDefault(cursorB.getString(0), null);
                    if (arrayList4 == null) {
                        arrayList4 = new ArrayList();
                    }
                    arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
                }
                this.__db.setTransactionSuccessful();
                cursorB.close();
                sf0VarC.D();
                return arrayList;
            } catch (Throwable th) {
                cursorB.close();
                sf0VarC.D();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForTag(String str) {
        sf0 sf0VarC = sf0.C(1, "SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN\n            (SELECT work_spec_id FROM worktag WHERE tag=?)");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorB = k9.B(this.__db, sf0VarC, true);
            try {
                c7 c7Var = new c7();
                c7 c7Var2 = new c7();
                while (cursorB.moveToNext()) {
                    String string = cursorB.getString(0);
                    if (((ArrayList) c7Var.getOrDefault(string, null)) == null) {
                        c7Var.put(string, new ArrayList());
                    }
                    String string2 = cursorB.getString(0);
                    if (((ArrayList) c7Var2.getOrDefault(string2, null)) == null) {
                        c7Var2.put(string2, new ArrayList());
                    }
                }
                cursorB.moveToPosition(-1);
                __fetchRelationshipWorkTagAsjavaLangString(c7Var);
                __fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                ArrayList arrayList = new ArrayList(cursorB.getCount());
                while (cursorB.moveToNext()) {
                    String string3 = cursorB.isNull(0) ? null : cursorB.getString(0);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(1));
                    Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(2) ? null : cursorB.getBlob(2));
                    int i = cursorB.getInt(3);
                    int i2 = cursorB.getInt(4);
                    ArrayList arrayList2 = (ArrayList) c7Var.getOrDefault(cursorB.getString(0), null);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    ArrayList arrayList3 = arrayList2;
                    ArrayList arrayList4 = (ArrayList) c7Var2.getOrDefault(cursorB.getString(0), null);
                    if (arrayList4 == null) {
                        arrayList4 = new ArrayList();
                    }
                    arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
                }
                this.__db.setTransactionSuccessful();
                cursorB.close();
                sf0VarC.D();
                return arrayList;
            } catch (Throwable th) {
                cursorB.close();
                sf0VarC.D();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public b30 getWorkStatusPojoLiveDataForIds(List<String> list) {
        StringBuilder sbL = hk.l("SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN (");
        int size = list.size();
        za0.d(sbL, size);
        sbL.append(")");
        final sf0 sf0VarC = sf0.C(size, sbL.toString());
        int i = 1;
        for (String str : list) {
            if (str == null) {
                sf0VarC.r(i);
            } else {
                sf0VarC.j(i, str);
            }
            i++;
        }
        return this.__db.getInvalidationTracker().a(new String[]{"WorkTag", "WorkProgress", "workspec"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.15
            public void finalize() {
                sf0VarC.D();
            }

            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorB = k9.B(WorkSpecDao_Impl.this.__db, sf0VarC, true);
                    try {
                        c7 c7Var = new c7();
                        c7 c7Var2 = new c7();
                        while (cursorB.moveToNext()) {
                            String string = cursorB.getString(0);
                            if (((ArrayList) c7Var.getOrDefault(string, null)) == null) {
                                c7Var.put(string, new ArrayList());
                            }
                            String string2 = cursorB.getString(0);
                            if (((ArrayList) c7Var2.getOrDefault(string2, null)) == null) {
                                c7Var2.put(string2, new ArrayList());
                            }
                        }
                        cursorB.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(c7Var);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                        ArrayList arrayList = new ArrayList(cursorB.getCount());
                        while (cursorB.moveToNext()) {
                            String string3 = cursorB.isNull(0) ? null : cursorB.getString(0);
                            WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(1));
                            Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(2) ? null : cursorB.getBlob(2));
                            int i2 = cursorB.getInt(3);
                            int i3 = cursorB.getInt(4);
                            ArrayList arrayList2 = (ArrayList) c7Var.getOrDefault(cursorB.getString(0), null);
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            ArrayList arrayList3 = arrayList2;
                            ArrayList arrayList4 = (ArrayList) c7Var2.getOrDefault(cursorB.getString(0), null);
                            if (arrayList4 == null) {
                                arrayList4 = new ArrayList();
                            }
                            arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i2, i3, arrayList3, arrayList4));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        cursorB.close();
                        return arrayList;
                    } catch (Throwable th) {
                        cursorB.close();
                        throw th;
                    }
                } finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public b30 getWorkStatusPojoLiveDataForName(String str) {
        final sf0 sf0VarC = sf0.C(1, "SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        return this.__db.getInvalidationTracker().a(new String[]{"WorkTag", "WorkProgress", "workspec", "workname"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.17
            public void finalize() {
                sf0VarC.D();
            }

            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorB = k9.B(WorkSpecDao_Impl.this.__db, sf0VarC, true);
                    try {
                        c7 c7Var = new c7();
                        c7 c7Var2 = new c7();
                        while (cursorB.moveToNext()) {
                            String string = cursorB.getString(0);
                            if (((ArrayList) c7Var.getOrDefault(string, null)) == null) {
                                c7Var.put(string, new ArrayList());
                            }
                            String string2 = cursorB.getString(0);
                            if (((ArrayList) c7Var2.getOrDefault(string2, null)) == null) {
                                c7Var2.put(string2, new ArrayList());
                            }
                        }
                        cursorB.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(c7Var);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                        ArrayList arrayList = new ArrayList(cursorB.getCount());
                        while (cursorB.moveToNext()) {
                            String string3 = cursorB.isNull(0) ? null : cursorB.getString(0);
                            WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(1));
                            Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(2) ? null : cursorB.getBlob(2));
                            int i = cursorB.getInt(3);
                            int i2 = cursorB.getInt(4);
                            ArrayList arrayList2 = (ArrayList) c7Var.getOrDefault(cursorB.getString(0), null);
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            ArrayList arrayList3 = arrayList2;
                            ArrayList arrayList4 = (ArrayList) c7Var2.getOrDefault(cursorB.getString(0), null);
                            if (arrayList4 == null) {
                                arrayList4 = new ArrayList();
                            }
                            arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        cursorB.close();
                        return arrayList;
                    } catch (Throwable th) {
                        cursorB.close();
                        throw th;
                    }
                } finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public b30 getWorkStatusPojoLiveDataForTag(String str) {
        final sf0 sf0VarC = sf0.C(1, "SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN\n            (SELECT work_spec_id FROM worktag WHERE tag=?)");
        if (str == null) {
            sf0VarC.r(1);
        } else {
            sf0VarC.j(1, str);
        }
        return this.__db.getInvalidationTracker().a(new String[]{"WorkTag", "WorkProgress", "workspec", "worktag"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.16
            public void finalize() {
                sf0VarC.D();
            }

            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorB = k9.B(WorkSpecDao_Impl.this.__db, sf0VarC, true);
                    try {
                        c7 c7Var = new c7();
                        c7 c7Var2 = new c7();
                        while (cursorB.moveToNext()) {
                            String string = cursorB.getString(0);
                            if (((ArrayList) c7Var.getOrDefault(string, null)) == null) {
                                c7Var.put(string, new ArrayList());
                            }
                            String string2 = cursorB.getString(0);
                            if (((ArrayList) c7Var2.getOrDefault(string2, null)) == null) {
                                c7Var2.put(string2, new ArrayList());
                            }
                        }
                        cursorB.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(c7Var);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                        ArrayList arrayList = new ArrayList(cursorB.getCount());
                        while (cursorB.moveToNext()) {
                            String string3 = cursorB.isNull(0) ? null : cursorB.getString(0);
                            WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorB.getInt(1));
                            Data dataFromByteArray = Data.fromByteArray(cursorB.isNull(2) ? null : cursorB.getBlob(2));
                            int i = cursorB.getInt(3);
                            int i2 = cursorB.getInt(4);
                            ArrayList arrayList2 = (ArrayList) c7Var.getOrDefault(cursorB.getString(0), null);
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            ArrayList arrayList3 = arrayList2;
                            ArrayList arrayList4 = (ArrayList) c7Var2.getOrDefault(cursorB.getString(0), null);
                            if (arrayList4 == null) {
                                arrayList4 = new ArrayList();
                            }
                            arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        cursorB.close();
                        return arrayList;
                    } catch (Throwable th) {
                        cursorB.close();
                        throw th;
                    }
                } finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public boolean hasUnfinishedWork() throws IllegalAccessException, IOException, InvocationTargetException {
        boolean z = false;
        sf0 sf0VarC = sf0.C(0, "SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            if (cursorB.moveToFirst()) {
                if (cursorB.getInt(0) != 0) {
                    z = true;
                }
            }
            return z;
        } finally {
            cursorB.close();
            sf0VarC.D();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void incrementGeneration(String str) {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfIncrementGeneration.acquire();
        if (str == null) {
            wl0VarAcquire.r(1);
        } else {
            wl0VarAcquire.j(1, str);
        }
        this.__db.beginTransaction();
        try {
            wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementGeneration.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void incrementPeriodCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfIncrementPeriodCount.acquire();
        if (str == null) {
            wl0VarAcquire.r(1);
        } else {
            wl0VarAcquire.j(1, str);
        }
        this.__db.beginTransaction();
        try {
            wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementPeriodCount.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int incrementWorkSpecRunAttemptCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.acquire();
        if (str == null) {
            wl0VarAcquire.r(1);
        } else {
            wl0VarAcquire.j(1, str);
        }
        this.__db.beginTransaction();
        try {
            int iN = wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
            return iN;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void insertWorkSpec(WorkSpec workSpec) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkSpec.insert(workSpec);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int markWorkSpecScheduled(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfMarkWorkSpecScheduled.acquire();
        wl0VarAcquire.x(j, 1);
        if (str == null) {
            wl0VarAcquire.r(2);
        } else {
            wl0VarAcquire.j(2, str);
        }
        this.__db.beginTransaction();
        try {
            int iN = wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
            return iN;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkWorkSpecScheduled.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast() {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.acquire();
        this.__db.beginTransaction();
        try {
            wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetScheduledState() {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfResetScheduledState.acquire();
        this.__db.beginTransaction();
        try {
            int iN = wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
            return iN;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetScheduledState.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetWorkSpecRunAttemptCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfResetWorkSpecRunAttemptCount.acquire();
        if (str == null) {
            wl0VarAcquire.r(1);
        } else {
            wl0VarAcquire.j(1, str);
        }
        this.__db.beginTransaction();
        try {
            int iN = wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
            return iN;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setLastEnqueuedTime(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfSetLastEnqueuedTime.acquire();
        wl0VarAcquire.x(j, 1);
        if (str == null) {
            wl0VarAcquire.r(2);
        } else {
            wl0VarAcquire.j(2, str);
        }
        this.__db.beginTransaction();
        try {
            wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetLastEnqueuedTime.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setOutput(String str, Data data) throws Throwable {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfSetOutput.acquire();
        byte[] byteArrayInternal = Data.toByteArrayInternal(data);
        if (byteArrayInternal == null) {
            wl0VarAcquire.r(1);
        } else {
            wl0VarAcquire.B(byteArrayInternal, 1);
        }
        if (str == null) {
            wl0VarAcquire.r(2);
        } else {
            wl0VarAcquire.j(2, str);
        }
        this.__db.beginTransaction();
        try {
            wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetOutput.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int setState(WorkInfo.State state, String str) {
        this.__db.assertNotSuspendingTransaction();
        wl0 wl0VarAcquire = this.__preparedStmtOfSetState.acquire();
        wl0VarAcquire.x(WorkTypeConverters.stateToInt(state), 1);
        if (str == null) {
            wl0VarAcquire.r(2);
        } else {
            wl0VarAcquire.j(2, str);
        }
        this.__db.beginTransaction();
        try {
            int iN = wl0VarAcquire.n();
            this.__db.setTransactionSuccessful();
            return iN;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetState.release(wl0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void updateWorkSpec(WorkSpec workSpec) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfWorkSpec.handle(workSpec);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
