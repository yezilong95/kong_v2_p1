package androidx.work.impl.model;

import android.database.Cursor;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import defpackage.ac0;
import defpackage.au;
import defpackage.bu;
import defpackage.ea0;
import defpackage.k8;
import defpackage.kp0;
import defpackage.ky0;
import defpackage.l21;
import defpackage.ma0;
import defpackage.np0;
import defpackage.pv0;
import defpackage.qm;
import defpackage.t40;
import defpackage.zb0;
import defpackage.zn;
import io.nats.client.support.ApiConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class WorkSpecDao_Impl implements WorkSpecDao {
    private final kp0 __db;
    private final bu __insertionAdapterOfWorkSpec;
    private final pv0 __preparedStmtOfDelete;
    private final pv0 __preparedStmtOfIncrementGeneration;
    private final pv0 __preparedStmtOfIncrementPeriodCount;
    private final pv0 __preparedStmtOfIncrementWorkSpecRunAttemptCount;
    private final pv0 __preparedStmtOfMarkWorkSpecScheduled;
    private final pv0 __preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast;
    private final pv0 __preparedStmtOfResetScheduledState;
    private final pv0 __preparedStmtOfResetWorkSpecRunAttemptCount;
    private final pv0 __preparedStmtOfSetLastEnqueuedTime;
    private final pv0 __preparedStmtOfSetOutput;
    private final pv0 __preparedStmtOfSetState;
    private final au __updateAdapterOfWorkSpec;

    public WorkSpecDao_Impl(kp0 kp0Var) {
        this.__db = kp0Var;
        this.__insertionAdapterOfWorkSpec = new bu(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.1
            @Override // defpackage.pv0
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`last_enqueue_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`period_count`,`generation`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // defpackage.bu
            public void bind(ky0 ky0Var, WorkSpec workSpec) throws Throwable {
                String str = workSpec.id;
                if (str == null) {
                    ky0Var.h(1);
                } else {
                    ky0Var.e(1, str);
                }
                WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                ky0Var.n(2, WorkTypeConverters.stateToInt(workSpec.state));
                String str2 = workSpec.workerClassName;
                if (str2 == null) {
                    ky0Var.h(3);
                } else {
                    ky0Var.e(3, str2);
                }
                String str3 = workSpec.inputMergerClassName;
                if (str3 == null) {
                    ky0Var.h(4);
                } else {
                    ky0Var.e(4, str3);
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(workSpec.input);
                if (byteArrayInternal == null) {
                    ky0Var.h(5);
                } else {
                    ky0Var.s(5, byteArrayInternal);
                }
                byte[] byteArrayInternal2 = Data.toByteArrayInternal(workSpec.output);
                if (byteArrayInternal2 == null) {
                    ky0Var.h(6);
                } else {
                    ky0Var.s(6, byteArrayInternal2);
                }
                ky0Var.n(7, workSpec.initialDelay);
                ky0Var.n(8, workSpec.intervalDuration);
                ky0Var.n(9, workSpec.flexDuration);
                ky0Var.n(10, workSpec.runAttemptCount);
                ky0Var.n(11, WorkTypeConverters.backoffPolicyToInt(workSpec.backoffPolicy));
                ky0Var.n(12, workSpec.backoffDelayDuration);
                ky0Var.n(13, workSpec.lastEnqueueTime);
                ky0Var.n(14, workSpec.minimumRetentionDuration);
                ky0Var.n(15, workSpec.scheduleRequestedAt);
                ky0Var.n(16, workSpec.expedited ? 1L : 0L);
                ky0Var.n(17, WorkTypeConverters.outOfQuotaPolicyToInt(workSpec.outOfQuotaPolicy));
                ky0Var.n(18, workSpec.getPeriodCount());
                ky0Var.n(19, workSpec.getGeneration());
                Constraints constraints = workSpec.constraints;
                if (constraints == null) {
                    ky0Var.h(20);
                    ky0Var.h(21);
                    ky0Var.h(22);
                    ky0Var.h(23);
                    ky0Var.h(24);
                    ky0Var.h(25);
                    ky0Var.h(26);
                    ky0Var.h(27);
                    return;
                }
                ky0Var.n(20, WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()));
                ky0Var.n(21, constraints.requiresCharging() ? 1L : 0L);
                ky0Var.n(22, constraints.requiresDeviceIdle() ? 1L : 0L);
                ky0Var.n(23, constraints.requiresBatteryNotLow() ? 1L : 0L);
                ky0Var.n(24, constraints.requiresStorageNotLow() ? 1L : 0L);
                ky0Var.n(25, constraints.getContentTriggerUpdateDelayMillis());
                ky0Var.n(26, constraints.getContentTriggerMaxDelayMillis());
                byte[] ofTriggersToByteArray = WorkTypeConverters.setOfTriggersToByteArray(constraints.getContentUriTriggers());
                if (ofTriggersToByteArray == null) {
                    ky0Var.h(27);
                } else {
                    ky0Var.s(27, ofTriggersToByteArray);
                }
            }
        };
        this.__updateAdapterOfWorkSpec = new au(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(kp0Var);
                t40.n(kp0Var, "database");
            }

            @Override // defpackage.pv0
            public String createQuery() {
                return "UPDATE OR ABORT `WorkSpec` SET `id` = ?,`state` = ?,`worker_class_name` = ?,`input_merger_class_name` = ?,`input` = ?,`output` = ?,`initial_delay` = ?,`interval_duration` = ?,`flex_duration` = ?,`run_attempt_count` = ?,`backoff_policy` = ?,`backoff_delay_duration` = ?,`last_enqueue_time` = ?,`minimum_retention_duration` = ?,`schedule_requested_at` = ?,`run_in_foreground` = ?,`out_of_quota_policy` = ?,`period_count` = ?,`generation` = ?,`required_network_type` = ?,`requires_charging` = ?,`requires_device_idle` = ?,`requires_battery_not_low` = ?,`requires_storage_not_low` = ?,`trigger_content_update_delay` = ?,`trigger_max_content_delay` = ?,`content_uri_triggers` = ? WHERE `id` = ?";
            }

            @Override // defpackage.au
            public void bind(ky0 ky0Var, WorkSpec workSpec) throws Throwable {
                String str = workSpec.id;
                if (str == null) {
                    ky0Var.h(1);
                } else {
                    ky0Var.e(1, str);
                }
                WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                ky0Var.n(2, WorkTypeConverters.stateToInt(workSpec.state));
                String str2 = workSpec.workerClassName;
                if (str2 == null) {
                    ky0Var.h(3);
                } else {
                    ky0Var.e(3, str2);
                }
                String str3 = workSpec.inputMergerClassName;
                if (str3 == null) {
                    ky0Var.h(4);
                } else {
                    ky0Var.e(4, str3);
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(workSpec.input);
                if (byteArrayInternal == null) {
                    ky0Var.h(5);
                } else {
                    ky0Var.s(5, byteArrayInternal);
                }
                byte[] byteArrayInternal2 = Data.toByteArrayInternal(workSpec.output);
                if (byteArrayInternal2 == null) {
                    ky0Var.h(6);
                } else {
                    ky0Var.s(6, byteArrayInternal2);
                }
                ky0Var.n(7, workSpec.initialDelay);
                ky0Var.n(8, workSpec.intervalDuration);
                ky0Var.n(9, workSpec.flexDuration);
                ky0Var.n(10, workSpec.runAttemptCount);
                ky0Var.n(11, WorkTypeConverters.backoffPolicyToInt(workSpec.backoffPolicy));
                ky0Var.n(12, workSpec.backoffDelayDuration);
                ky0Var.n(13, workSpec.lastEnqueueTime);
                ky0Var.n(14, workSpec.minimumRetentionDuration);
                ky0Var.n(15, workSpec.scheduleRequestedAt);
                ky0Var.n(16, workSpec.expedited ? 1L : 0L);
                ky0Var.n(17, WorkTypeConverters.outOfQuotaPolicyToInt(workSpec.outOfQuotaPolicy));
                ky0Var.n(18, workSpec.getPeriodCount());
                ky0Var.n(19, workSpec.getGeneration());
                Constraints constraints = workSpec.constraints;
                if (constraints != null) {
                    ky0Var.n(20, WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()));
                    ky0Var.n(21, constraints.requiresCharging() ? 1L : 0L);
                    ky0Var.n(22, constraints.requiresDeviceIdle() ? 1L : 0L);
                    ky0Var.n(23, constraints.requiresBatteryNotLow() ? 1L : 0L);
                    ky0Var.n(24, constraints.requiresStorageNotLow() ? 1L : 0L);
                    ky0Var.n(25, constraints.getContentTriggerUpdateDelayMillis());
                    ky0Var.n(26, constraints.getContentTriggerMaxDelayMillis());
                    byte[] ofTriggersToByteArray = WorkTypeConverters.setOfTriggersToByteArray(constraints.getContentUriTriggers());
                    if (ofTriggersToByteArray == null) {
                        ky0Var.h(27);
                    } else {
                        ky0Var.s(27, ofTriggersToByteArray);
                    }
                } else {
                    ky0Var.h(20);
                    ky0Var.h(21);
                    ky0Var.h(22);
                    ky0Var.h(23);
                    ky0Var.h(24);
                    ky0Var.h(25);
                    ky0Var.h(26);
                    ky0Var.h(27);
                }
                String str4 = workSpec.id;
                if (str4 == null) {
                    ky0Var.h(28);
                } else {
                    ky0Var.e(28, str4);
                }
            }
        };
        this.__preparedStmtOfDelete = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.3
            @Override // defpackage.pv0
            public String createQuery() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.__preparedStmtOfSetState = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.4
            @Override // defpackage.pv0
            public String createQuery() {
                return "UPDATE workspec SET state=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementPeriodCount = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.5
            @Override // defpackage.pv0
            public String createQuery() {
                return "UPDATE workspec SET period_count=period_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfSetOutput = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.6
            @Override // defpackage.pv0
            public String createQuery() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetLastEnqueuedTime = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.7
            @Override // defpackage.pv0
            public String createQuery() {
                return "UPDATE workspec SET last_enqueue_time=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementWorkSpecRunAttemptCount = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.8
            @Override // defpackage.pv0
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecRunAttemptCount = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.9
            @Override // defpackage.pv0
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.__preparedStmtOfMarkWorkSpecScheduled = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.10
            @Override // defpackage.pv0
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetScheduledState = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.11
            @Override // defpackage.pv0
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.12
            @Override // defpackage.pv0
            public String createQuery() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
        this.__preparedStmtOfIncrementGeneration = new pv0(kp0Var) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.13
            @Override // defpackage.pv0
            public String createQuery() {
                return "UPDATE workspec SET generation=generation+1 WHERE id=?";
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkProgressAsandroidxWorkData(k8 k8Var) throws IOException {
        ac0 ac0Var = (ac0) k8Var.keySet();
        if (ac0Var.isEmpty()) {
            return;
        }
        if (k8Var.G > 999) {
            k8 k8Var2 = new k8(kp0.MAX_BIND_PARAMETER_CNT);
            int i = k8Var.G;
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                k8Var2.put((String) k8Var.h(i2), (ArrayList) k8Var.j(i2));
                i2++;
                i3++;
                if (i3 == 999) {
                    __fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                    k8Var2 = new k8(kp0.MAX_BIND_PARAMETER_CNT);
                    i3 = 0;
                }
            }
            if (i3 > 0) {
                __fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                return;
            }
            return;
        }
        StringBuilder sbP = zn.p("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int iD = ac0Var.F.d();
        ma0.e(sbP, iD);
        sbP.append(")");
        np0 np0VarW = np0.w(iD, sbP.toString());
        Iterator it = ac0Var.iterator();
        int i4 = 1;
        while (true) {
            zb0 zb0Var = (zb0) it;
            if (!zb0Var.hasNext()) {
                break;
            }
            String str = (String) zb0Var.next();
            if (str == null) {
                np0VarW.h(i4);
            } else {
                np0VarW.e(i4, str);
            }
            i4++;
        }
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            int iT = qm.t(cursorD0, "work_spec_id");
            if (iT == -1) {
                return;
            }
            while (cursorD0.moveToNext()) {
                byte[] blob = null;
                ArrayList arrayList = (ArrayList) k8Var.getOrDefault(cursorD0.getString(iT), null);
                if (arrayList != null) {
                    if (!cursorD0.isNull(0)) {
                        blob = cursorD0.getBlob(0);
                    }
                    arrayList.add(Data.fromByteArray(blob));
                }
            }
        } finally {
            cursorD0.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkTagAsjavaLangString(k8 k8Var) throws IOException {
        ac0 ac0Var = (ac0) k8Var.keySet();
        if (ac0Var.isEmpty()) {
            return;
        }
        if (k8Var.G > 999) {
            k8 k8Var2 = new k8(kp0.MAX_BIND_PARAMETER_CNT);
            int i = k8Var.G;
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                k8Var2.put((String) k8Var.h(i2), (ArrayList) k8Var.j(i2));
                i2++;
                i3++;
                if (i3 == 999) {
                    __fetchRelationshipWorkTagAsjavaLangString(k8Var2);
                    k8Var2 = new k8(kp0.MAX_BIND_PARAMETER_CNT);
                    i3 = 0;
                }
            }
            if (i3 > 0) {
                __fetchRelationshipWorkTagAsjavaLangString(k8Var2);
                return;
            }
            return;
        }
        StringBuilder sbP = zn.p("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int iD = ac0Var.F.d();
        ma0.e(sbP, iD);
        sbP.append(")");
        np0 np0VarW = np0.w(iD, sbP.toString());
        Iterator it = ac0Var.iterator();
        int i4 = 1;
        while (true) {
            zb0 zb0Var = (zb0) it;
            if (!zb0Var.hasNext()) {
                break;
            }
            String str = (String) zb0Var.next();
            if (str == null) {
                np0VarW.h(i4);
            } else {
                np0VarW.e(i4, str);
            }
            i4++;
        }
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            int iT = qm.t(cursorD0, "work_spec_id");
            if (iT == -1) {
                return;
            }
            while (cursorD0.moveToNext()) {
                String string = null;
                ArrayList arrayList = (ArrayList) k8Var.getOrDefault(cursorD0.getString(iT), null);
                if (arrayList != null) {
                    if (!cursorD0.isNull(0)) {
                        string = cursorD0.getString(0);
                    }
                    arrayList.add(string);
                }
            }
        } finally {
            cursorD0.close();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void delete(String str) {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfDelete.acquire();
        if (str == null) {
            ky0VarAcquire.h(1);
        } else {
            ky0VarAcquire.e(1, str);
        }
        this.__db.beginTransaction();
        try {
            ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getAllEligibleWorkSpecsForScheduling(int i) throws Throwable {
        np0 np0Var;
        int iU;
        int iU2;
        int iU3;
        int iU4;
        int iU5;
        int iU6;
        int iU7;
        int iU8;
        int iU9;
        int iU10;
        int iU11;
        int iU12;
        int iU13;
        int iU14;
        np0 np0VarW = np0.w(1, "SELECT * FROM workspec WHERE state=0 ORDER BY last_enqueue_time LIMIT ?");
        np0VarW.n(1, i);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            iU = qm.u(cursorD0, ApiConstants.ID);
            iU2 = qm.u(cursorD0, ApiConstants.STATE);
            iU3 = qm.u(cursorD0, "worker_class_name");
            iU4 = qm.u(cursorD0, "input_merger_class_name");
            iU5 = qm.u(cursorD0, "input");
            iU6 = qm.u(cursorD0, "output");
            iU7 = qm.u(cursorD0, "initial_delay");
            iU8 = qm.u(cursorD0, "interval_duration");
            iU9 = qm.u(cursorD0, "flex_duration");
            iU10 = qm.u(cursorD0, "run_attempt_count");
            iU11 = qm.u(cursorD0, "backoff_policy");
            iU12 = qm.u(cursorD0, "backoff_delay_duration");
            iU13 = qm.u(cursorD0, "last_enqueue_time");
            iU14 = qm.u(cursorD0, "minimum_retention_duration");
            np0Var = np0VarW;
        } catch (Throwable th) {
            th = th;
            np0Var = np0VarW;
        }
        try {
            int iU15 = qm.u(cursorD0, "schedule_requested_at");
            int iU16 = qm.u(cursorD0, "run_in_foreground");
            int iU17 = qm.u(cursorD0, "out_of_quota_policy");
            int iU18 = qm.u(cursorD0, "period_count");
            int iU19 = qm.u(cursorD0, "generation");
            int iU20 = qm.u(cursorD0, "required_network_type");
            int iU21 = qm.u(cursorD0, "requires_charging");
            int iU22 = qm.u(cursorD0, "requires_device_idle");
            int iU23 = qm.u(cursorD0, "requires_battery_not_low");
            int iU24 = qm.u(cursorD0, "requires_storage_not_low");
            int iU25 = qm.u(cursorD0, "trigger_content_update_delay");
            int iU26 = qm.u(cursorD0, "trigger_max_content_delay");
            int iU27 = qm.u(cursorD0, "content_uri_triggers");
            int i2 = iU14;
            ArrayList arrayList = new ArrayList(cursorD0.getCount());
            while (cursorD0.moveToNext()) {
                byte[] blob = null;
                String string = cursorD0.isNull(iU) ? null : cursorD0.getString(iU);
                WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(iU2));
                String string2 = cursorD0.isNull(iU3) ? null : cursorD0.getString(iU3);
                String string3 = cursorD0.isNull(iU4) ? null : cursorD0.getString(iU4);
                Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(iU5) ? null : cursorD0.getBlob(iU5));
                Data dataFromByteArray2 = Data.fromByteArray(cursorD0.isNull(iU6) ? null : cursorD0.getBlob(iU6));
                long j = cursorD0.getLong(iU7);
                long j2 = cursorD0.getLong(iU8);
                long j3 = cursorD0.getLong(iU9);
                int i3 = cursorD0.getInt(iU10);
                BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorD0.getInt(iU11));
                long j4 = cursorD0.getLong(iU12);
                long j5 = cursorD0.getLong(iU13);
                int i4 = i2;
                long j6 = cursorD0.getLong(i4);
                int i5 = iU;
                int i6 = iU15;
                long j7 = cursorD0.getLong(i6);
                iU15 = i6;
                int i7 = iU16;
                boolean z = cursorD0.getInt(i7) != 0;
                iU16 = i7;
                int i8 = iU17;
                OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorD0.getInt(i8));
                iU17 = i8;
                int i9 = iU18;
                int i10 = cursorD0.getInt(i9);
                iU18 = i9;
                int i11 = iU19;
                int i12 = cursorD0.getInt(i11);
                iU19 = i11;
                int i13 = iU20;
                NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorD0.getInt(i13));
                iU20 = i13;
                int i14 = iU21;
                boolean z2 = cursorD0.getInt(i14) != 0;
                iU21 = i14;
                int i15 = iU22;
                boolean z3 = cursorD0.getInt(i15) != 0;
                iU22 = i15;
                int i16 = iU23;
                boolean z4 = cursorD0.getInt(i16) != 0;
                iU23 = i16;
                int i17 = iU24;
                boolean z5 = cursorD0.getInt(i17) != 0;
                iU24 = i17;
                int i18 = iU25;
                long j8 = cursorD0.getLong(i18);
                iU25 = i18;
                int i19 = iU26;
                long j9 = cursorD0.getLong(i19);
                iU26 = i19;
                int i20 = iU27;
                if (!cursorD0.isNull(i20)) {
                    blob = cursorD0.getBlob(i20);
                }
                iU27 = i20;
                arrayList.add(new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j, j2, j3, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i3, backoffPolicyIntToBackoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i10, i12));
                iU = i5;
                i2 = i4;
            }
            cursorD0.close();
            np0Var.x();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorD0.close();
            np0Var.x();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getAllUnfinishedWork() throws IOException {
        np0 np0VarW = np0.w(0, "SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            ArrayList arrayList = new ArrayList(cursorD0.getCount());
            while (cursorD0.moveToNext()) {
                arrayList.add(cursorD0.isNull(0) ? null : cursorD0.getString(0));
            }
            return arrayList;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getAllWorkSpecIds() throws IOException {
        np0 np0VarW = np0.w(0, "SELECT id FROM workspec");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            ArrayList arrayList = new ArrayList(cursorD0.getCount());
            while (cursorD0.moveToNext()) {
                arrayList.add(cursorD0.isNull(0) ? null : cursorD0.getString(0));
            }
            return arrayList;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public ea0 getAllWorkSpecIdsLiveData() {
        final np0 np0VarW = np0.w(0, "SELECT id FROM workspec");
        return this.__db.getInvalidationTracker().a(new String[]{"workspec"}, true, new Callable<List<String>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.14
            public void finalize() {
                np0VarW.x();
            }

            @Override // java.util.concurrent.Callable
            public List<String> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorD0 = l21.D0(WorkSpecDao_Impl.this.__db, np0VarW, false);
                    try {
                        ArrayList arrayList = new ArrayList(cursorD0.getCount());
                        while (cursorD0.moveToNext()) {
                            arrayList.add(cursorD0.isNull(0) ? null : cursorD0.getString(0));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        cursorD0.close();
                        return arrayList;
                    } catch (Throwable th) {
                        cursorD0.close();
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
        np0 np0Var;
        int iU;
        int iU2;
        int iU3;
        int iU4;
        int iU5;
        int iU6;
        int iU7;
        int iU8;
        int iU9;
        int iU10;
        int iU11;
        int iU12;
        int iU13;
        int iU14;
        np0 np0VarW = np0.w(1, "SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY last_enqueue_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))");
        np0VarW.n(1, i);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            iU = qm.u(cursorD0, ApiConstants.ID);
            iU2 = qm.u(cursorD0, ApiConstants.STATE);
            iU3 = qm.u(cursorD0, "worker_class_name");
            iU4 = qm.u(cursorD0, "input_merger_class_name");
            iU5 = qm.u(cursorD0, "input");
            iU6 = qm.u(cursorD0, "output");
            iU7 = qm.u(cursorD0, "initial_delay");
            iU8 = qm.u(cursorD0, "interval_duration");
            iU9 = qm.u(cursorD0, "flex_duration");
            iU10 = qm.u(cursorD0, "run_attempt_count");
            iU11 = qm.u(cursorD0, "backoff_policy");
            iU12 = qm.u(cursorD0, "backoff_delay_duration");
            iU13 = qm.u(cursorD0, "last_enqueue_time");
            iU14 = qm.u(cursorD0, "minimum_retention_duration");
            np0Var = np0VarW;
        } catch (Throwable th) {
            th = th;
            np0Var = np0VarW;
        }
        try {
            int iU15 = qm.u(cursorD0, "schedule_requested_at");
            int iU16 = qm.u(cursorD0, "run_in_foreground");
            int iU17 = qm.u(cursorD0, "out_of_quota_policy");
            int iU18 = qm.u(cursorD0, "period_count");
            int iU19 = qm.u(cursorD0, "generation");
            int iU20 = qm.u(cursorD0, "required_network_type");
            int iU21 = qm.u(cursorD0, "requires_charging");
            int iU22 = qm.u(cursorD0, "requires_device_idle");
            int iU23 = qm.u(cursorD0, "requires_battery_not_low");
            int iU24 = qm.u(cursorD0, "requires_storage_not_low");
            int iU25 = qm.u(cursorD0, "trigger_content_update_delay");
            int iU26 = qm.u(cursorD0, "trigger_max_content_delay");
            int iU27 = qm.u(cursorD0, "content_uri_triggers");
            int i2 = iU14;
            ArrayList arrayList = new ArrayList(cursorD0.getCount());
            while (cursorD0.moveToNext()) {
                byte[] blob = null;
                String string = cursorD0.isNull(iU) ? null : cursorD0.getString(iU);
                WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(iU2));
                String string2 = cursorD0.isNull(iU3) ? null : cursorD0.getString(iU3);
                String string3 = cursorD0.isNull(iU4) ? null : cursorD0.getString(iU4);
                Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(iU5) ? null : cursorD0.getBlob(iU5));
                Data dataFromByteArray2 = Data.fromByteArray(cursorD0.isNull(iU6) ? null : cursorD0.getBlob(iU6));
                long j = cursorD0.getLong(iU7);
                long j2 = cursorD0.getLong(iU8);
                long j3 = cursorD0.getLong(iU9);
                int i3 = cursorD0.getInt(iU10);
                BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorD0.getInt(iU11));
                long j4 = cursorD0.getLong(iU12);
                long j5 = cursorD0.getLong(iU13);
                int i4 = i2;
                long j6 = cursorD0.getLong(i4);
                int i5 = iU;
                int i6 = iU15;
                long j7 = cursorD0.getLong(i6);
                iU15 = i6;
                int i7 = iU16;
                boolean z = cursorD0.getInt(i7) != 0;
                iU16 = i7;
                int i8 = iU17;
                OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorD0.getInt(i8));
                iU17 = i8;
                int i9 = iU18;
                int i10 = cursorD0.getInt(i9);
                iU18 = i9;
                int i11 = iU19;
                int i12 = cursorD0.getInt(i11);
                iU19 = i11;
                int i13 = iU20;
                NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorD0.getInt(i13));
                iU20 = i13;
                int i14 = iU21;
                boolean z2 = cursorD0.getInt(i14) != 0;
                iU21 = i14;
                int i15 = iU22;
                boolean z3 = cursorD0.getInt(i15) != 0;
                iU22 = i15;
                int i16 = iU23;
                boolean z4 = cursorD0.getInt(i16) != 0;
                iU23 = i16;
                int i17 = iU24;
                boolean z5 = cursorD0.getInt(i17) != 0;
                iU24 = i17;
                int i18 = iU25;
                long j8 = cursorD0.getLong(i18);
                iU25 = i18;
                int i19 = iU26;
                long j9 = cursorD0.getLong(i19);
                iU26 = i19;
                int i20 = iU27;
                if (!cursorD0.isNull(i20)) {
                    blob = cursorD0.getBlob(i20);
                }
                iU27 = i20;
                arrayList.add(new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j, j2, j3, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i3, backoffPolicyIntToBackoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i10, i12));
                iU = i5;
                i2 = i4;
            }
            cursorD0.close();
            np0Var.x();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorD0.close();
            np0Var.x();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<Data> getInputsFromPrerequisites(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT output FROM workspec WHERE id IN\n             (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            ArrayList arrayList = new ArrayList(cursorD0.getCount());
            while (cursorD0.moveToNext()) {
                arrayList.add(Data.fromByteArray(cursorD0.isNull(0) ? null : cursorD0.getBlob(0)));
            }
            return arrayList;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getRecentlyCompletedWork(long j) throws Throwable {
        np0 np0Var;
        np0 np0VarW = np0.w(1, "SELECT * FROM workspec WHERE last_enqueue_time >= ? AND state IN (2, 3, 5) ORDER BY last_enqueue_time DESC");
        np0VarW.n(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            int iU = qm.u(cursorD0, ApiConstants.ID);
            int iU2 = qm.u(cursorD0, ApiConstants.STATE);
            int iU3 = qm.u(cursorD0, "worker_class_name");
            int iU4 = qm.u(cursorD0, "input_merger_class_name");
            int iU5 = qm.u(cursorD0, "input");
            int iU6 = qm.u(cursorD0, "output");
            int iU7 = qm.u(cursorD0, "initial_delay");
            int iU8 = qm.u(cursorD0, "interval_duration");
            int iU9 = qm.u(cursorD0, "flex_duration");
            int iU10 = qm.u(cursorD0, "run_attempt_count");
            int iU11 = qm.u(cursorD0, "backoff_policy");
            int iU12 = qm.u(cursorD0, "backoff_delay_duration");
            int iU13 = qm.u(cursorD0, "last_enqueue_time");
            int iU14 = qm.u(cursorD0, "minimum_retention_duration");
            np0Var = np0VarW;
            try {
                int iU15 = qm.u(cursorD0, "schedule_requested_at");
                int iU16 = qm.u(cursorD0, "run_in_foreground");
                int iU17 = qm.u(cursorD0, "out_of_quota_policy");
                int iU18 = qm.u(cursorD0, "period_count");
                int iU19 = qm.u(cursorD0, "generation");
                int iU20 = qm.u(cursorD0, "required_network_type");
                int iU21 = qm.u(cursorD0, "requires_charging");
                int iU22 = qm.u(cursorD0, "requires_device_idle");
                int iU23 = qm.u(cursorD0, "requires_battery_not_low");
                int iU24 = qm.u(cursorD0, "requires_storage_not_low");
                int iU25 = qm.u(cursorD0, "trigger_content_update_delay");
                int iU26 = qm.u(cursorD0, "trigger_max_content_delay");
                int iU27 = qm.u(cursorD0, "content_uri_triggers");
                int i = iU14;
                ArrayList arrayList = new ArrayList(cursorD0.getCount());
                while (cursorD0.moveToNext()) {
                    byte[] blob = null;
                    String string = cursorD0.isNull(iU) ? null : cursorD0.getString(iU);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(iU2));
                    String string2 = cursorD0.isNull(iU3) ? null : cursorD0.getString(iU3);
                    String string3 = cursorD0.isNull(iU4) ? null : cursorD0.getString(iU4);
                    Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(iU5) ? null : cursorD0.getBlob(iU5));
                    Data dataFromByteArray2 = Data.fromByteArray(cursorD0.isNull(iU6) ? null : cursorD0.getBlob(iU6));
                    long j2 = cursorD0.getLong(iU7);
                    long j3 = cursorD0.getLong(iU8);
                    long j4 = cursorD0.getLong(iU9);
                    int i2 = cursorD0.getInt(iU10);
                    BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorD0.getInt(iU11));
                    long j5 = cursorD0.getLong(iU12);
                    long j6 = cursorD0.getLong(iU13);
                    int i3 = i;
                    long j7 = cursorD0.getLong(i3);
                    int i4 = iU;
                    int i5 = iU15;
                    long j8 = cursorD0.getLong(i5);
                    iU15 = i5;
                    int i6 = iU16;
                    boolean z = cursorD0.getInt(i6) != 0;
                    iU16 = i6;
                    int i7 = iU17;
                    OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorD0.getInt(i7));
                    iU17 = i7;
                    int i8 = iU18;
                    int i9 = cursorD0.getInt(i8);
                    iU18 = i8;
                    int i10 = iU19;
                    int i11 = cursorD0.getInt(i10);
                    iU19 = i10;
                    int i12 = iU20;
                    NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorD0.getInt(i12));
                    iU20 = i12;
                    int i13 = iU21;
                    boolean z2 = cursorD0.getInt(i13) != 0;
                    iU21 = i13;
                    int i14 = iU22;
                    boolean z3 = cursorD0.getInt(i14) != 0;
                    iU22 = i14;
                    int i15 = iU23;
                    boolean z4 = cursorD0.getInt(i15) != 0;
                    iU23 = i15;
                    int i16 = iU24;
                    boolean z5 = cursorD0.getInt(i16) != 0;
                    iU24 = i16;
                    int i17 = iU25;
                    long j9 = cursorD0.getLong(i17);
                    iU25 = i17;
                    int i18 = iU26;
                    long j10 = cursorD0.getLong(i18);
                    iU26 = i18;
                    int i19 = iU27;
                    if (!cursorD0.isNull(i19)) {
                        blob = cursorD0.getBlob(i19);
                    }
                    iU27 = i19;
                    arrayList.add(new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j2, j3, j4, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j9, j10, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i2, backoffPolicyIntToBackoffPolicy, j5, j6, j7, j8, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i9, i11));
                    iU = i4;
                    i = i3;
                }
                cursorD0.close();
                np0Var.x();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorD0.close();
                np0Var.x();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            np0Var = np0VarW;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getRunningWork() throws Throwable {
        np0 np0Var;
        np0 np0VarW = np0.w(0, "SELECT * FROM workspec WHERE state=1");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            int iU = qm.u(cursorD0, ApiConstants.ID);
            int iU2 = qm.u(cursorD0, ApiConstants.STATE);
            int iU3 = qm.u(cursorD0, "worker_class_name");
            int iU4 = qm.u(cursorD0, "input_merger_class_name");
            int iU5 = qm.u(cursorD0, "input");
            int iU6 = qm.u(cursorD0, "output");
            int iU7 = qm.u(cursorD0, "initial_delay");
            int iU8 = qm.u(cursorD0, "interval_duration");
            int iU9 = qm.u(cursorD0, "flex_duration");
            int iU10 = qm.u(cursorD0, "run_attempt_count");
            int iU11 = qm.u(cursorD0, "backoff_policy");
            int iU12 = qm.u(cursorD0, "backoff_delay_duration");
            int iU13 = qm.u(cursorD0, "last_enqueue_time");
            int iU14 = qm.u(cursorD0, "minimum_retention_duration");
            np0Var = np0VarW;
            try {
                int iU15 = qm.u(cursorD0, "schedule_requested_at");
                int iU16 = qm.u(cursorD0, "run_in_foreground");
                int iU17 = qm.u(cursorD0, "out_of_quota_policy");
                int iU18 = qm.u(cursorD0, "period_count");
                int iU19 = qm.u(cursorD0, "generation");
                int iU20 = qm.u(cursorD0, "required_network_type");
                int iU21 = qm.u(cursorD0, "requires_charging");
                int iU22 = qm.u(cursorD0, "requires_device_idle");
                int iU23 = qm.u(cursorD0, "requires_battery_not_low");
                int iU24 = qm.u(cursorD0, "requires_storage_not_low");
                int iU25 = qm.u(cursorD0, "trigger_content_update_delay");
                int iU26 = qm.u(cursorD0, "trigger_max_content_delay");
                int iU27 = qm.u(cursorD0, "content_uri_triggers");
                int i = iU14;
                ArrayList arrayList = new ArrayList(cursorD0.getCount());
                while (cursorD0.moveToNext()) {
                    byte[] blob = null;
                    String string = cursorD0.isNull(iU) ? null : cursorD0.getString(iU);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(iU2));
                    String string2 = cursorD0.isNull(iU3) ? null : cursorD0.getString(iU3);
                    String string3 = cursorD0.isNull(iU4) ? null : cursorD0.getString(iU4);
                    Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(iU5) ? null : cursorD0.getBlob(iU5));
                    Data dataFromByteArray2 = Data.fromByteArray(cursorD0.isNull(iU6) ? null : cursorD0.getBlob(iU6));
                    long j = cursorD0.getLong(iU7);
                    long j2 = cursorD0.getLong(iU8);
                    long j3 = cursorD0.getLong(iU9);
                    int i2 = cursorD0.getInt(iU10);
                    BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorD0.getInt(iU11));
                    long j4 = cursorD0.getLong(iU12);
                    long j5 = cursorD0.getLong(iU13);
                    int i3 = i;
                    long j6 = cursorD0.getLong(i3);
                    int i4 = iU;
                    int i5 = iU15;
                    long j7 = cursorD0.getLong(i5);
                    iU15 = i5;
                    int i6 = iU16;
                    boolean z = cursorD0.getInt(i6) != 0;
                    iU16 = i6;
                    int i7 = iU17;
                    OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorD0.getInt(i7));
                    iU17 = i7;
                    int i8 = iU18;
                    int i9 = cursorD0.getInt(i8);
                    iU18 = i8;
                    int i10 = iU19;
                    int i11 = cursorD0.getInt(i10);
                    iU19 = i10;
                    int i12 = iU20;
                    NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorD0.getInt(i12));
                    iU20 = i12;
                    int i13 = iU21;
                    boolean z2 = cursorD0.getInt(i13) != 0;
                    iU21 = i13;
                    int i14 = iU22;
                    boolean z3 = cursorD0.getInt(i14) != 0;
                    iU22 = i14;
                    int i15 = iU23;
                    boolean z4 = cursorD0.getInt(i15) != 0;
                    iU23 = i15;
                    int i16 = iU24;
                    boolean z5 = cursorD0.getInt(i16) != 0;
                    iU24 = i16;
                    int i17 = iU25;
                    long j8 = cursorD0.getLong(i17);
                    iU25 = i17;
                    int i18 = iU26;
                    long j9 = cursorD0.getLong(i18);
                    iU26 = i18;
                    int i19 = iU27;
                    if (!cursorD0.isNull(i19)) {
                        blob = cursorD0.getBlob(i19);
                    }
                    iU27 = i19;
                    arrayList.add(new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j, j2, j3, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i2, backoffPolicyIntToBackoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i9, i11));
                    iU = i4;
                    i = i3;
                }
                cursorD0.close();
                np0Var.x();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorD0.close();
                np0Var.x();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            np0Var = np0VarW;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public ea0 getScheduleRequestedAtLiveData(String str) {
        final np0 np0VarW = np0.w(1, "SELECT schedule_requested_at FROM workspec WHERE id=?");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        return this.__db.getInvalidationTracker().a(new String[]{"workspec"}, false, new Callable<Long>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.18
            public void finalize() {
                np0VarW.x();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                Cursor cursorD0 = l21.D0(WorkSpecDao_Impl.this.__db, np0VarW, false);
                try {
                    Long lValueOf = Long.valueOf(cursorD0.moveToFirst() ? cursorD0.getLong(0) : 0L);
                    cursorD0.close();
                    return lValueOf;
                } catch (Throwable th) {
                    cursorD0.close();
                    throw th;
                }
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getScheduledWork() throws Throwable {
        np0 np0Var;
        np0 np0VarW = np0.w(0, "SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            int iU = qm.u(cursorD0, ApiConstants.ID);
            int iU2 = qm.u(cursorD0, ApiConstants.STATE);
            int iU3 = qm.u(cursorD0, "worker_class_name");
            int iU4 = qm.u(cursorD0, "input_merger_class_name");
            int iU5 = qm.u(cursorD0, "input");
            int iU6 = qm.u(cursorD0, "output");
            int iU7 = qm.u(cursorD0, "initial_delay");
            int iU8 = qm.u(cursorD0, "interval_duration");
            int iU9 = qm.u(cursorD0, "flex_duration");
            int iU10 = qm.u(cursorD0, "run_attempt_count");
            int iU11 = qm.u(cursorD0, "backoff_policy");
            int iU12 = qm.u(cursorD0, "backoff_delay_duration");
            int iU13 = qm.u(cursorD0, "last_enqueue_time");
            int iU14 = qm.u(cursorD0, "minimum_retention_duration");
            np0Var = np0VarW;
            try {
                int iU15 = qm.u(cursorD0, "schedule_requested_at");
                int iU16 = qm.u(cursorD0, "run_in_foreground");
                int iU17 = qm.u(cursorD0, "out_of_quota_policy");
                int iU18 = qm.u(cursorD0, "period_count");
                int iU19 = qm.u(cursorD0, "generation");
                int iU20 = qm.u(cursorD0, "required_network_type");
                int iU21 = qm.u(cursorD0, "requires_charging");
                int iU22 = qm.u(cursorD0, "requires_device_idle");
                int iU23 = qm.u(cursorD0, "requires_battery_not_low");
                int iU24 = qm.u(cursorD0, "requires_storage_not_low");
                int iU25 = qm.u(cursorD0, "trigger_content_update_delay");
                int iU26 = qm.u(cursorD0, "trigger_max_content_delay");
                int iU27 = qm.u(cursorD0, "content_uri_triggers");
                int i = iU14;
                ArrayList arrayList = new ArrayList(cursorD0.getCount());
                while (cursorD0.moveToNext()) {
                    byte[] blob = null;
                    String string = cursorD0.isNull(iU) ? null : cursorD0.getString(iU);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(iU2));
                    String string2 = cursorD0.isNull(iU3) ? null : cursorD0.getString(iU3);
                    String string3 = cursorD0.isNull(iU4) ? null : cursorD0.getString(iU4);
                    Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(iU5) ? null : cursorD0.getBlob(iU5));
                    Data dataFromByteArray2 = Data.fromByteArray(cursorD0.isNull(iU6) ? null : cursorD0.getBlob(iU6));
                    long j = cursorD0.getLong(iU7);
                    long j2 = cursorD0.getLong(iU8);
                    long j3 = cursorD0.getLong(iU9);
                    int i2 = cursorD0.getInt(iU10);
                    BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorD0.getInt(iU11));
                    long j4 = cursorD0.getLong(iU12);
                    long j5 = cursorD0.getLong(iU13);
                    int i3 = i;
                    long j6 = cursorD0.getLong(i3);
                    int i4 = iU;
                    int i5 = iU15;
                    long j7 = cursorD0.getLong(i5);
                    iU15 = i5;
                    int i6 = iU16;
                    boolean z = cursorD0.getInt(i6) != 0;
                    iU16 = i6;
                    int i7 = iU17;
                    OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorD0.getInt(i7));
                    iU17 = i7;
                    int i8 = iU18;
                    int i9 = cursorD0.getInt(i8);
                    iU18 = i8;
                    int i10 = iU19;
                    int i11 = cursorD0.getInt(i10);
                    iU19 = i10;
                    int i12 = iU20;
                    NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorD0.getInt(i12));
                    iU20 = i12;
                    int i13 = iU21;
                    boolean z2 = cursorD0.getInt(i13) != 0;
                    iU21 = i13;
                    int i14 = iU22;
                    boolean z3 = cursorD0.getInt(i14) != 0;
                    iU22 = i14;
                    int i15 = iU23;
                    boolean z4 = cursorD0.getInt(i15) != 0;
                    iU23 = i15;
                    int i16 = iU24;
                    boolean z5 = cursorD0.getInt(i16) != 0;
                    iU24 = i16;
                    int i17 = iU25;
                    long j8 = cursorD0.getLong(i17);
                    iU25 = i17;
                    int i18 = iU26;
                    long j9 = cursorD0.getLong(i18);
                    iU26 = i18;
                    int i19 = iU27;
                    if (!cursorD0.isNull(i19)) {
                        blob = cursorD0.getBlob(i19);
                    }
                    iU27 = i19;
                    arrayList.add(new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j, j2, j3, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i2, backoffPolicyIntToBackoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i9, i11));
                    iU = i4;
                    i = i3;
                }
                cursorD0.close();
                np0Var.x();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorD0.close();
                np0Var.x();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            np0Var = np0VarW;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkInfo.State getState(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT state FROM workspec WHERE id=?");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            WorkInfo.State stateIntToState = null;
            if (cursorD0.moveToFirst()) {
                Integer numValueOf = cursorD0.isNull(0) ? null : Integer.valueOf(cursorD0.getInt(0));
                if (numValueOf != null) {
                    WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                    stateIntToState = WorkTypeConverters.intToState(numValueOf.intValue());
                }
            }
            return stateIntToState;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getUnfinishedWorkWithName(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            ArrayList arrayList = new ArrayList(cursorD0.getCount());
            while (cursorD0.moveToNext()) {
                arrayList.add(cursorD0.isNull(0) ? null : cursorD0.getString(0));
            }
            return arrayList;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getUnfinishedWorkWithTag(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            ArrayList arrayList = new ArrayList(cursorD0.getCount());
            while (cursorD0.moveToNext()) {
                arrayList.add(cursorD0.isNull(0) ? null : cursorD0.getString(0));
            }
            return arrayList;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec getWorkSpec(String str) throws Throwable {
        np0 np0Var;
        np0 np0VarW = np0.w(1, "SELECT * FROM workspec WHERE id=?");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            int iU = qm.u(cursorD0, ApiConstants.ID);
            int iU2 = qm.u(cursorD0, ApiConstants.STATE);
            int iU3 = qm.u(cursorD0, "worker_class_name");
            int iU4 = qm.u(cursorD0, "input_merger_class_name");
            int iU5 = qm.u(cursorD0, "input");
            int iU6 = qm.u(cursorD0, "output");
            int iU7 = qm.u(cursorD0, "initial_delay");
            int iU8 = qm.u(cursorD0, "interval_duration");
            int iU9 = qm.u(cursorD0, "flex_duration");
            int iU10 = qm.u(cursorD0, "run_attempt_count");
            int iU11 = qm.u(cursorD0, "backoff_policy");
            int iU12 = qm.u(cursorD0, "backoff_delay_duration");
            int iU13 = qm.u(cursorD0, "last_enqueue_time");
            int iU14 = qm.u(cursorD0, "minimum_retention_duration");
            np0Var = np0VarW;
            try {
                int iU15 = qm.u(cursorD0, "schedule_requested_at");
                int iU16 = qm.u(cursorD0, "run_in_foreground");
                int iU17 = qm.u(cursorD0, "out_of_quota_policy");
                int iU18 = qm.u(cursorD0, "period_count");
                int iU19 = qm.u(cursorD0, "generation");
                int iU20 = qm.u(cursorD0, "required_network_type");
                int iU21 = qm.u(cursorD0, "requires_charging");
                int iU22 = qm.u(cursorD0, "requires_device_idle");
                int iU23 = qm.u(cursorD0, "requires_battery_not_low");
                int iU24 = qm.u(cursorD0, "requires_storage_not_low");
                int iU25 = qm.u(cursorD0, "trigger_content_update_delay");
                int iU26 = qm.u(cursorD0, "trigger_max_content_delay");
                int iU27 = qm.u(cursorD0, "content_uri_triggers");
                WorkSpec workSpec = null;
                byte[] blob = null;
                if (cursorD0.moveToFirst()) {
                    String string = cursorD0.isNull(iU) ? null : cursorD0.getString(iU);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(iU2));
                    String string2 = cursorD0.isNull(iU3) ? null : cursorD0.getString(iU3);
                    String string3 = cursorD0.isNull(iU4) ? null : cursorD0.getString(iU4);
                    Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(iU5) ? null : cursorD0.getBlob(iU5));
                    Data dataFromByteArray2 = Data.fromByteArray(cursorD0.isNull(iU6) ? null : cursorD0.getBlob(iU6));
                    long j = cursorD0.getLong(iU7);
                    long j2 = cursorD0.getLong(iU8);
                    long j3 = cursorD0.getLong(iU9);
                    int i = cursorD0.getInt(iU10);
                    BackoffPolicy backoffPolicyIntToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(cursorD0.getInt(iU11));
                    long j4 = cursorD0.getLong(iU12);
                    long j5 = cursorD0.getLong(iU13);
                    long j6 = cursorD0.getLong(iU14);
                    long j7 = cursorD0.getLong(iU15);
                    boolean z = cursorD0.getInt(iU16) != 0;
                    OutOfQuotaPolicy outOfQuotaPolicyIntToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(cursorD0.getInt(iU17));
                    int i2 = cursorD0.getInt(iU18);
                    int i3 = cursorD0.getInt(iU19);
                    NetworkType networkTypeIntToNetworkType = WorkTypeConverters.intToNetworkType(cursorD0.getInt(iU20));
                    boolean z2 = cursorD0.getInt(iU21) != 0;
                    boolean z3 = cursorD0.getInt(iU22) != 0;
                    boolean z4 = cursorD0.getInt(iU23) != 0;
                    boolean z5 = cursorD0.getInt(iU24) != 0;
                    long j8 = cursorD0.getLong(iU25);
                    long j9 = cursorD0.getLong(iU26);
                    if (!cursorD0.isNull(iU27)) {
                        blob = cursorD0.getBlob(iU27);
                    }
                    workSpec = new WorkSpec(string, stateIntToState, string2, string3, dataFromByteArray, dataFromByteArray2, j, j2, j3, new Constraints(networkTypeIntToNetworkType, z2, z3, z4, z5, j8, j9, WorkTypeConverters.byteArrayToSetOfTriggers(blob)), i, backoffPolicyIntToBackoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicyIntToOutOfQuotaPolicy, i2, i3);
                }
                cursorD0.close();
                np0Var.x();
                return workSpec;
            } catch (Throwable th) {
                th = th;
                cursorD0.close();
                np0Var.x();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            np0Var = np0VarW;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(String str) throws IOException {
        np0 np0VarW = np0.w(1, "SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            ArrayList arrayList = new ArrayList(cursorD0.getCount());
            while (cursorD0.moveToNext()) {
                arrayList.add(new WorkSpec.IdAndState(cursorD0.isNull(0) ? null : cursorD0.getString(0), WorkTypeConverters.intToState(cursorD0.getInt(1))));
            }
            return arrayList;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec.WorkInfoPojo getWorkStatusPojoForId(String str) {
        WorkSpec.WorkInfoPojo workInfoPojo;
        np0 np0VarW = np0.w(1, "SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id=?");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorD0 = l21.D0(this.__db, np0VarW, true);
            try {
                k8 k8Var = new k8();
                k8 k8Var2 = new k8();
                while (true) {
                    workInfoPojo = null;
                    if (!cursorD0.moveToNext()) {
                        break;
                    }
                    String string = cursorD0.getString(0);
                    if (((ArrayList) k8Var.getOrDefault(string, null)) == null) {
                        k8Var.put(string, new ArrayList());
                    }
                    String string2 = cursorD0.getString(0);
                    if (((ArrayList) k8Var2.getOrDefault(string2, null)) == null) {
                        k8Var2.put(string2, new ArrayList());
                    }
                }
                cursorD0.moveToPosition(-1);
                __fetchRelationshipWorkTagAsjavaLangString(k8Var);
                __fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                if (cursorD0.moveToFirst()) {
                    String string3 = cursorD0.isNull(0) ? null : cursorD0.getString(0);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(1));
                    Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(2) ? null : cursorD0.getBlob(2));
                    int i = cursorD0.getInt(3);
                    int i2 = cursorD0.getInt(4);
                    ArrayList arrayList = (ArrayList) k8Var.getOrDefault(cursorD0.getString(0), null);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    ArrayList arrayList2 = arrayList;
                    ArrayList arrayList3 = (ArrayList) k8Var2.getOrDefault(cursorD0.getString(0), null);
                    if (arrayList3 == null) {
                        arrayList3 = new ArrayList();
                    }
                    workInfoPojo = new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList2, arrayList3);
                }
                this.__db.setTransactionSuccessful();
                cursorD0.close();
                np0VarW.x();
                return workInfoPojo;
            } catch (Throwable th) {
                cursorD0.close();
                np0VarW.x();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForIds(List<String> list) {
        StringBuilder sbP = zn.p("SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN (");
        int size = list.size();
        ma0.e(sbP, size);
        sbP.append(")");
        np0 np0VarW = np0.w(size, sbP.toString());
        int i = 1;
        for (String str : list) {
            if (str == null) {
                np0VarW.h(i);
            } else {
                np0VarW.e(i, str);
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorD0 = l21.D0(this.__db, np0VarW, true);
            try {
                k8 k8Var = new k8();
                k8 k8Var2 = new k8();
                while (cursorD0.moveToNext()) {
                    String string = cursorD0.getString(0);
                    if (((ArrayList) k8Var.getOrDefault(string, null)) == null) {
                        k8Var.put(string, new ArrayList());
                    }
                    String string2 = cursorD0.getString(0);
                    if (((ArrayList) k8Var2.getOrDefault(string2, null)) == null) {
                        k8Var2.put(string2, new ArrayList());
                    }
                }
                cursorD0.moveToPosition(-1);
                __fetchRelationshipWorkTagAsjavaLangString(k8Var);
                __fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                ArrayList arrayList = new ArrayList(cursorD0.getCount());
                while (cursorD0.moveToNext()) {
                    String string3 = cursorD0.isNull(0) ? null : cursorD0.getString(0);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(1));
                    Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(2) ? null : cursorD0.getBlob(2));
                    int i2 = cursorD0.getInt(3);
                    int i3 = cursorD0.getInt(4);
                    ArrayList arrayList2 = (ArrayList) k8Var.getOrDefault(cursorD0.getString(0), null);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    ArrayList arrayList3 = arrayList2;
                    ArrayList arrayList4 = (ArrayList) k8Var2.getOrDefault(cursorD0.getString(0), null);
                    if (arrayList4 == null) {
                        arrayList4 = new ArrayList();
                    }
                    arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i2, i3, arrayList3, arrayList4));
                }
                this.__db.setTransactionSuccessful();
                cursorD0.close();
                np0VarW.x();
                return arrayList;
            } catch (Throwable th) {
                cursorD0.close();
                np0VarW.x();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForName(String str) {
        np0 np0VarW = np0.w(1, "SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorD0 = l21.D0(this.__db, np0VarW, true);
            try {
                k8 k8Var = new k8();
                k8 k8Var2 = new k8();
                while (cursorD0.moveToNext()) {
                    String string = cursorD0.getString(0);
                    if (((ArrayList) k8Var.getOrDefault(string, null)) == null) {
                        k8Var.put(string, new ArrayList());
                    }
                    String string2 = cursorD0.getString(0);
                    if (((ArrayList) k8Var2.getOrDefault(string2, null)) == null) {
                        k8Var2.put(string2, new ArrayList());
                    }
                }
                cursorD0.moveToPosition(-1);
                __fetchRelationshipWorkTagAsjavaLangString(k8Var);
                __fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                ArrayList arrayList = new ArrayList(cursorD0.getCount());
                while (cursorD0.moveToNext()) {
                    String string3 = cursorD0.isNull(0) ? null : cursorD0.getString(0);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(1));
                    Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(2) ? null : cursorD0.getBlob(2));
                    int i = cursorD0.getInt(3);
                    int i2 = cursorD0.getInt(4);
                    ArrayList arrayList2 = (ArrayList) k8Var.getOrDefault(cursorD0.getString(0), null);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    ArrayList arrayList3 = arrayList2;
                    ArrayList arrayList4 = (ArrayList) k8Var2.getOrDefault(cursorD0.getString(0), null);
                    if (arrayList4 == null) {
                        arrayList4 = new ArrayList();
                    }
                    arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
                }
                this.__db.setTransactionSuccessful();
                cursorD0.close();
                np0VarW.x();
                return arrayList;
            } catch (Throwable th) {
                cursorD0.close();
                np0VarW.x();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForTag(String str) {
        np0 np0VarW = np0.w(1, "SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN\n            (SELECT work_spec_id FROM worktag WHERE tag=?)");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorD0 = l21.D0(this.__db, np0VarW, true);
            try {
                k8 k8Var = new k8();
                k8 k8Var2 = new k8();
                while (cursorD0.moveToNext()) {
                    String string = cursorD0.getString(0);
                    if (((ArrayList) k8Var.getOrDefault(string, null)) == null) {
                        k8Var.put(string, new ArrayList());
                    }
                    String string2 = cursorD0.getString(0);
                    if (((ArrayList) k8Var2.getOrDefault(string2, null)) == null) {
                        k8Var2.put(string2, new ArrayList());
                    }
                }
                cursorD0.moveToPosition(-1);
                __fetchRelationshipWorkTagAsjavaLangString(k8Var);
                __fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                ArrayList arrayList = new ArrayList(cursorD0.getCount());
                while (cursorD0.moveToNext()) {
                    String string3 = cursorD0.isNull(0) ? null : cursorD0.getString(0);
                    WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(1));
                    Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(2) ? null : cursorD0.getBlob(2));
                    int i = cursorD0.getInt(3);
                    int i2 = cursorD0.getInt(4);
                    ArrayList arrayList2 = (ArrayList) k8Var.getOrDefault(cursorD0.getString(0), null);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    ArrayList arrayList3 = arrayList2;
                    ArrayList arrayList4 = (ArrayList) k8Var2.getOrDefault(cursorD0.getString(0), null);
                    if (arrayList4 == null) {
                        arrayList4 = new ArrayList();
                    }
                    arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
                }
                this.__db.setTransactionSuccessful();
                cursorD0.close();
                np0VarW.x();
                return arrayList;
            } catch (Throwable th) {
                cursorD0.close();
                np0VarW.x();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public ea0 getWorkStatusPojoLiveDataForIds(List<String> list) {
        StringBuilder sbP = zn.p("SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN (");
        int size = list.size();
        ma0.e(sbP, size);
        sbP.append(")");
        final np0 np0VarW = np0.w(size, sbP.toString());
        int i = 1;
        for (String str : list) {
            if (str == null) {
                np0VarW.h(i);
            } else {
                np0VarW.e(i, str);
            }
            i++;
        }
        return this.__db.getInvalidationTracker().a(new String[]{"WorkTag", "WorkProgress", "workspec"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.15
            public void finalize() {
                np0VarW.x();
            }

            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorD0 = l21.D0(WorkSpecDao_Impl.this.__db, np0VarW, true);
                    try {
                        k8 k8Var = new k8();
                        k8 k8Var2 = new k8();
                        while (cursorD0.moveToNext()) {
                            String string = cursorD0.getString(0);
                            if (((ArrayList) k8Var.getOrDefault(string, null)) == null) {
                                k8Var.put(string, new ArrayList());
                            }
                            String string2 = cursorD0.getString(0);
                            if (((ArrayList) k8Var2.getOrDefault(string2, null)) == null) {
                                k8Var2.put(string2, new ArrayList());
                            }
                        }
                        cursorD0.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(k8Var);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                        ArrayList arrayList = new ArrayList(cursorD0.getCount());
                        while (cursorD0.moveToNext()) {
                            String string3 = cursorD0.isNull(0) ? null : cursorD0.getString(0);
                            WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(1));
                            Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(2) ? null : cursorD0.getBlob(2));
                            int i2 = cursorD0.getInt(3);
                            int i3 = cursorD0.getInt(4);
                            ArrayList arrayList2 = (ArrayList) k8Var.getOrDefault(cursorD0.getString(0), null);
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            ArrayList arrayList3 = arrayList2;
                            ArrayList arrayList4 = (ArrayList) k8Var2.getOrDefault(cursorD0.getString(0), null);
                            if (arrayList4 == null) {
                                arrayList4 = new ArrayList();
                            }
                            arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i2, i3, arrayList3, arrayList4));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        cursorD0.close();
                        return arrayList;
                    } catch (Throwable th) {
                        cursorD0.close();
                        throw th;
                    }
                } finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public ea0 getWorkStatusPojoLiveDataForName(String str) {
        final np0 np0VarW = np0.w(1, "SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        return this.__db.getInvalidationTracker().a(new String[]{"WorkTag", "WorkProgress", "workspec", "workname"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.17
            public void finalize() {
                np0VarW.x();
            }

            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorD0 = l21.D0(WorkSpecDao_Impl.this.__db, np0VarW, true);
                    try {
                        k8 k8Var = new k8();
                        k8 k8Var2 = new k8();
                        while (cursorD0.moveToNext()) {
                            String string = cursorD0.getString(0);
                            if (((ArrayList) k8Var.getOrDefault(string, null)) == null) {
                                k8Var.put(string, new ArrayList());
                            }
                            String string2 = cursorD0.getString(0);
                            if (((ArrayList) k8Var2.getOrDefault(string2, null)) == null) {
                                k8Var2.put(string2, new ArrayList());
                            }
                        }
                        cursorD0.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(k8Var);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                        ArrayList arrayList = new ArrayList(cursorD0.getCount());
                        while (cursorD0.moveToNext()) {
                            String string3 = cursorD0.isNull(0) ? null : cursorD0.getString(0);
                            WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(1));
                            Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(2) ? null : cursorD0.getBlob(2));
                            int i = cursorD0.getInt(3);
                            int i2 = cursorD0.getInt(4);
                            ArrayList arrayList2 = (ArrayList) k8Var.getOrDefault(cursorD0.getString(0), null);
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            ArrayList arrayList3 = arrayList2;
                            ArrayList arrayList4 = (ArrayList) k8Var2.getOrDefault(cursorD0.getString(0), null);
                            if (arrayList4 == null) {
                                arrayList4 = new ArrayList();
                            }
                            arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        cursorD0.close();
                        return arrayList;
                    } catch (Throwable th) {
                        cursorD0.close();
                        throw th;
                    }
                } finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public ea0 getWorkStatusPojoLiveDataForTag(String str) {
        final np0 np0VarW = np0.w(1, "SELECT id, state, output, run_attempt_count, generation FROM workspec WHERE id IN\n            (SELECT work_spec_id FROM worktag WHERE tag=?)");
        if (str == null) {
            np0VarW.h(1);
        } else {
            np0VarW.e(1, str);
        }
        return this.__db.getInvalidationTracker().a(new String[]{"WorkTag", "WorkProgress", "workspec", "worktag"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.WorkSpecDao_Impl.16
            public void finalize() {
                np0VarW.x();
            }

            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorD0 = l21.D0(WorkSpecDao_Impl.this.__db, np0VarW, true);
                    try {
                        k8 k8Var = new k8();
                        k8 k8Var2 = new k8();
                        while (cursorD0.moveToNext()) {
                            String string = cursorD0.getString(0);
                            if (((ArrayList) k8Var.getOrDefault(string, null)) == null) {
                                k8Var.put(string, new ArrayList());
                            }
                            String string2 = cursorD0.getString(0);
                            if (((ArrayList) k8Var2.getOrDefault(string2, null)) == null) {
                                k8Var2.put(string2, new ArrayList());
                            }
                        }
                        cursorD0.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(k8Var);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                        ArrayList arrayList = new ArrayList(cursorD0.getCount());
                        while (cursorD0.moveToNext()) {
                            String string3 = cursorD0.isNull(0) ? null : cursorD0.getString(0);
                            WorkInfo.State stateIntToState = WorkTypeConverters.intToState(cursorD0.getInt(1));
                            Data dataFromByteArray = Data.fromByteArray(cursorD0.isNull(2) ? null : cursorD0.getBlob(2));
                            int i = cursorD0.getInt(3);
                            int i2 = cursorD0.getInt(4);
                            ArrayList arrayList2 = (ArrayList) k8Var.getOrDefault(cursorD0.getString(0), null);
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            ArrayList arrayList3 = arrayList2;
                            ArrayList arrayList4 = (ArrayList) k8Var2.getOrDefault(cursorD0.getString(0), null);
                            if (arrayList4 == null) {
                                arrayList4 = new ArrayList();
                            }
                            arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        cursorD0.close();
                        return arrayList;
                    } catch (Throwable th) {
                        cursorD0.close();
                        throw th;
                    }
                } finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public boolean hasUnfinishedWork() throws IOException {
        boolean z = false;
        np0 np0VarW = np0.w(0, "SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1");
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            if (cursorD0.moveToFirst()) {
                if (cursorD0.getInt(0) != 0) {
                    z = true;
                }
            }
            return z;
        } finally {
            cursorD0.close();
            np0VarW.x();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void incrementGeneration(String str) {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfIncrementGeneration.acquire();
        if (str == null) {
            ky0VarAcquire.h(1);
        } else {
            ky0VarAcquire.e(1, str);
        }
        this.__db.beginTransaction();
        try {
            ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementGeneration.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void incrementPeriodCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfIncrementPeriodCount.acquire();
        if (str == null) {
            ky0VarAcquire.h(1);
        } else {
            ky0VarAcquire.e(1, str);
        }
        this.__db.beginTransaction();
        try {
            ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementPeriodCount.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int incrementWorkSpecRunAttemptCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.acquire();
        if (str == null) {
            ky0VarAcquire.h(1);
        } else {
            ky0VarAcquire.e(1, str);
        }
        this.__db.beginTransaction();
        try {
            int iF = ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
            return iF;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(ky0VarAcquire);
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
        ky0 ky0VarAcquire = this.__preparedStmtOfMarkWorkSpecScheduled.acquire();
        ky0VarAcquire.n(1, j);
        if (str == null) {
            ky0VarAcquire.h(2);
        } else {
            ky0VarAcquire.e(2, str);
        }
        this.__db.beginTransaction();
        try {
            int iF = ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
            return iF;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkWorkSpecScheduled.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast() {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.acquire();
        this.__db.beginTransaction();
        try {
            ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetScheduledState() {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfResetScheduledState.acquire();
        this.__db.beginTransaction();
        try {
            int iF = ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
            return iF;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetScheduledState.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetWorkSpecRunAttemptCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfResetWorkSpecRunAttemptCount.acquire();
        if (str == null) {
            ky0VarAcquire.h(1);
        } else {
            ky0VarAcquire.e(1, str);
        }
        this.__db.beginTransaction();
        try {
            int iF = ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
            return iF;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setLastEnqueuedTime(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfSetLastEnqueuedTime.acquire();
        ky0VarAcquire.n(1, j);
        if (str == null) {
            ky0VarAcquire.h(2);
        } else {
            ky0VarAcquire.e(2, str);
        }
        this.__db.beginTransaction();
        try {
            ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetLastEnqueuedTime.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setOutput(String str, Data data) throws Throwable {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfSetOutput.acquire();
        byte[] byteArrayInternal = Data.toByteArrayInternal(data);
        if (byteArrayInternal == null) {
            ky0VarAcquire.h(1);
        } else {
            ky0VarAcquire.s(1, byteArrayInternal);
        }
        if (str == null) {
            ky0VarAcquire.h(2);
        } else {
            ky0VarAcquire.e(2, str);
        }
        this.__db.beginTransaction();
        try {
            ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetOutput.release(ky0VarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int setState(WorkInfo.State state, String str) {
        this.__db.assertNotSuspendingTransaction();
        ky0 ky0VarAcquire = this.__preparedStmtOfSetState.acquire();
        ky0VarAcquire.n(1, WorkTypeConverters.stateToInt(state));
        if (str == null) {
            ky0VarAcquire.h(2);
        } else {
            ky0VarAcquire.e(2, str);
        }
        this.__db.beginTransaction();
        try {
            int iF = ky0VarAcquire.f();
            this.__db.setTransactionSuccessful();
            return iF;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetState.release(ky0VarAcquire);
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
