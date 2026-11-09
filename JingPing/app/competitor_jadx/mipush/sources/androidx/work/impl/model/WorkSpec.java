package androidx.work.impl.model;

import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import defpackage.fa0;
import defpackage.j10;
import defpackage.no;
import defpackage.t40;
import defpackage.tm;
import io.nats.client.Options;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.NatsObjectStoreUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.uuid.Uuid;
import org.bouncycastle.crypto.CryptoServicesPermission;
import org.java_websocket.WebSocketImpl;

/* JADX INFO: loaded from: classes.dex */
public final class WorkSpec {
    public static final Companion Companion = new Companion(null);
    public static final long SCHEDULE_NOT_REQUESTED_YET = -1;
    private static final String TAG;
    public static final j10 WORK_INFO_MAPPER;
    public long backoffDelayDuration;
    public BackoffPolicy backoffPolicy;
    public Constraints constraints;
    public boolean expedited;
    public long flexDuration;
    private final int generation;
    public final String id;
    public long initialDelay;
    public Data input;
    public String inputMergerClassName;
    public long intervalDuration;
    public long lastEnqueueTime;
    public long minimumRetentionDuration;
    public OutOfQuotaPolicy outOfQuotaPolicy;
    public Data output;
    private int periodCount;
    public int runAttemptCount;
    public long scheduleRequestedAt;
    public WorkInfo.State state;
    public String workerClassName;

    public static final class Companion {
        public /* synthetic */ Companion(tm tmVar) {
            this();
        }

        private Companion() {
        }
    }

    public static final class IdAndState {
        public String id;
        public WorkInfo.State state;

        public IdAndState(String str, WorkInfo.State state) {
            t40.n(str, ApiConstants.ID);
            t40.n(state, ApiConstants.STATE);
            this.id = str;
            this.state = state;
        }

        public static /* synthetic */ IdAndState copy$default(IdAndState idAndState, String str, WorkInfo.State state, int i, Object obj) {
            if ((i & 1) != 0) {
                str = idAndState.id;
            }
            if ((i & 2) != 0) {
                state = idAndState.state;
            }
            return idAndState.copy(str, state);
        }

        public final String component1() {
            return this.id;
        }

        public final WorkInfo.State component2() {
            return this.state;
        }

        public final IdAndState copy(String str, WorkInfo.State state) {
            t40.n(str, ApiConstants.ID);
            t40.n(state, ApiConstants.STATE);
            return new IdAndState(str, state);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IdAndState)) {
                return false;
            }
            IdAndState idAndState = (IdAndState) obj;
            return t40.f(this.id, idAndState.id) && this.state == idAndState.state;
        }

        public int hashCode() {
            return this.state.hashCode() + (this.id.hashCode() * 31);
        }

        public String toString() {
            return "IdAndState(id=" + this.id + ", state=" + this.state + ')';
        }
    }

    public static final class WorkInfoPojo {
        private final int generation;
        private String id;
        private Data output;
        private List<Data> progress;
        private int runAttemptCount;
        private WorkInfo.State state;
        private List<String> tags;

        public WorkInfoPojo(String str, WorkInfo.State state, Data data, int i, int i2, List<String> list, List<Data> list2) {
            t40.n(str, ApiConstants.ID);
            t40.n(state, ApiConstants.STATE);
            t40.n(data, "output");
            t40.n(list, ApiConstants.TAGS);
            t40.n(list2, "progress");
            this.id = str;
            this.state = state;
            this.output = data;
            this.runAttemptCount = i;
            this.generation = i2;
            this.tags = list;
            this.progress = list2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ WorkInfoPojo copy$default(WorkInfoPojo workInfoPojo, String str, WorkInfo.State state, Data data, int i, int i2, List list, List list2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = workInfoPojo.id;
            }
            if ((i3 & 2) != 0) {
                state = workInfoPojo.state;
            }
            if ((i3 & 4) != 0) {
                data = workInfoPojo.output;
            }
            if ((i3 & 8) != 0) {
                i = workInfoPojo.runAttemptCount;
            }
            if ((i3 & 16) != 0) {
                i2 = workInfoPojo.generation;
            }
            if ((i3 & 32) != 0) {
                list = workInfoPojo.tags;
            }
            if ((i3 & 64) != 0) {
                list2 = workInfoPojo.progress;
            }
            List list3 = list;
            List list4 = list2;
            int i4 = i2;
            Data data2 = data;
            return workInfoPojo.copy(str, state, data2, i, i4, list3, list4);
        }

        public final String component1() {
            return this.id;
        }

        public final WorkInfo.State component2() {
            return this.state;
        }

        public final Data component3() {
            return this.output;
        }

        public final int component4() {
            return this.runAttemptCount;
        }

        public final int component5() {
            return this.generation;
        }

        public final List<String> component6() {
            return this.tags;
        }

        public final List<Data> component7() {
            return this.progress;
        }

        public final WorkInfoPojo copy(String str, WorkInfo.State state, Data data, int i, int i2, List<String> list, List<Data> list2) {
            t40.n(str, ApiConstants.ID);
            t40.n(state, ApiConstants.STATE);
            t40.n(data, "output");
            t40.n(list, ApiConstants.TAGS);
            t40.n(list2, "progress");
            return new WorkInfoPojo(str, state, data, i, i2, list, list2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WorkInfoPojo)) {
                return false;
            }
            WorkInfoPojo workInfoPojo = (WorkInfoPojo) obj;
            return t40.f(this.id, workInfoPojo.id) && this.state == workInfoPojo.state && t40.f(this.output, workInfoPojo.output) && this.runAttemptCount == workInfoPojo.runAttemptCount && this.generation == workInfoPojo.generation && t40.f(this.tags, workInfoPojo.tags) && t40.f(this.progress, workInfoPojo.progress);
        }

        public final int getGeneration() {
            return this.generation;
        }

        public final String getId() {
            return this.id;
        }

        public final Data getOutput() {
            return this.output;
        }

        public final List<Data> getProgress() {
            return this.progress;
        }

        public final int getRunAttemptCount() {
            return this.runAttemptCount;
        }

        public final WorkInfo.State getState() {
            return this.state;
        }

        public final List<String> getTags() {
            return this.tags;
        }

        public int hashCode() {
            return this.progress.hashCode() + ((this.tags.hashCode() + ((Integer.hashCode(this.generation) + ((Integer.hashCode(this.runAttemptCount) + ((this.output.hashCode() + ((this.state.hashCode() + (this.id.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
        }

        public final void setId(String str) {
            t40.n(str, "<set-?>");
            this.id = str;
        }

        public final void setOutput(Data data) {
            t40.n(data, "<set-?>");
            this.output = data;
        }

        public final void setProgress(List<Data> list) {
            t40.n(list, "<set-?>");
            this.progress = list;
        }

        public final void setRunAttemptCount(int i) {
            this.runAttemptCount = i;
        }

        public final void setState(WorkInfo.State state) {
            t40.n(state, "<set-?>");
            this.state = state;
        }

        public final void setTags(List<String> list) {
            t40.n(list, "<set-?>");
            this.tags = list;
        }

        public String toString() {
            return "WorkInfoPojo(id=" + this.id + ", state=" + this.state + ", output=" + this.output + ", runAttemptCount=" + this.runAttemptCount + ", generation=" + this.generation + ", tags=" + this.tags + ", progress=" + this.progress + ')';
        }

        public final WorkInfo toWorkInfo() {
            return new WorkInfo(UUID.fromString(this.id), this.state, this.output, this.tags, !this.progress.isEmpty() ? this.progress.get(0) : Data.EMPTY, this.runAttemptCount, this.generation);
        }
    }

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("WorkSpec");
        t40.m(strTagWithPrefix, "tagWithPrefix(\"WorkSpec\")");
        TAG = strTagWithPrefix;
        WORK_INFO_MAPPER = new no(29);
    }

    public WorkSpec(String str, WorkInfo.State state, String str2, String str3, Data data, Data data2, long j, long j2, long j3, Constraints constraints, int i, BackoffPolicy backoffPolicy, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy, int i2, int i3) {
        t40.n(str, ApiConstants.ID);
        t40.n(state, ApiConstants.STATE);
        t40.n(str2, "workerClassName");
        t40.n(data, "input");
        t40.n(data2, "output");
        t40.n(constraints, CryptoServicesPermission.CONSTRAINTS);
        t40.n(backoffPolicy, "backoffPolicy");
        t40.n(outOfQuotaPolicy, "outOfQuotaPolicy");
        this.id = str;
        this.state = state;
        this.workerClassName = str2;
        this.inputMergerClassName = str3;
        this.input = data;
        this.output = data2;
        this.initialDelay = j;
        this.intervalDuration = j2;
        this.flexDuration = j3;
        this.constraints = constraints;
        this.runAttemptCount = i;
        this.backoffPolicy = backoffPolicy;
        this.backoffDelayDuration = j4;
        this.lastEnqueueTime = j5;
        this.minimumRetentionDuration = j6;
        this.scheduleRequestedAt = j7;
        this.expedited = z;
        this.outOfQuotaPolicy = outOfQuotaPolicy;
        this.periodCount = i2;
        this.generation = i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List WORK_INFO_MAPPER$lambda$1(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((WorkInfoPojo) it.next()).toWorkInfo());
        }
        return arrayList;
    }

    public static /* synthetic */ WorkSpec copy$default(WorkSpec workSpec, String str, WorkInfo.State state, String str2, String str3, Data data, Data data2, long j, long j2, long j3, Constraints constraints, int i, BackoffPolicy backoffPolicy, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy, int i2, int i3, int i4, Object obj) {
        int i5;
        OutOfQuotaPolicy outOfQuotaPolicy2;
        String str4 = (i4 & 1) != 0 ? workSpec.id : str;
        WorkInfo.State state2 = (i4 & 2) != 0 ? workSpec.state : state;
        String str5 = (i4 & 4) != 0 ? workSpec.workerClassName : str2;
        String str6 = (i4 & 8) != 0 ? workSpec.inputMergerClassName : str3;
        Data data3 = (i4 & 16) != 0 ? workSpec.input : data;
        Data data4 = (i4 & 32) != 0 ? workSpec.output : data2;
        long j8 = (i4 & 64) != 0 ? workSpec.initialDelay : j;
        long j9 = (i4 & Uuid.SIZE_BITS) != 0 ? workSpec.intervalDuration : j2;
        long j10 = (i4 & NatsJetStreamConstants.MAX_PULL_SIZE) != 0 ? workSpec.flexDuration : j3;
        Constraints constraints2 = (i4 & 512) != 0 ? workSpec.constraints : constraints;
        int i6 = (i4 & 1024) != 0 ? workSpec.runAttemptCount : i;
        String str7 = str4;
        BackoffPolicy backoffPolicy2 = (i4 & 2048) != 0 ? workSpec.backoffPolicy : backoffPolicy;
        WorkInfo.State state3 = state2;
        long j11 = (i4 & Options.DEFAULT_MAX_CONTROL_LINE) != 0 ? workSpec.backoffDelayDuration : j4;
        long j12 = (i4 & 8192) != 0 ? workSpec.lastEnqueueTime : j5;
        long j13 = (i4 & WebSocketImpl.RCVBUF) != 0 ? workSpec.minimumRetentionDuration : j6;
        long j14 = (i4 & 32768) != 0 ? workSpec.scheduleRequestedAt : j7;
        boolean z2 = (i4 & Options.DEFAULT_BUFFER_SIZE) != 0 ? workSpec.expedited : z;
        long j15 = j14;
        OutOfQuotaPolicy outOfQuotaPolicy3 = (i4 & NatsObjectStoreUtil.DEFAULT_CHUNK_SIZE) != 0 ? workSpec.outOfQuotaPolicy : outOfQuotaPolicy;
        int i7 = (i4 & 262144) != 0 ? workSpec.periodCount : i2;
        if ((i4 & 524288) != 0) {
            outOfQuotaPolicy2 = outOfQuotaPolicy3;
            i5 = workSpec.generation;
        } else {
            i5 = i3;
            outOfQuotaPolicy2 = outOfQuotaPolicy3;
        }
        return workSpec.copy(str7, state3, str5, str6, data3, data4, j8, j9, j10, constraints2, i6, backoffPolicy2, j11, j12, j13, j15, z2, outOfQuotaPolicy2, i7, i5);
    }

    public final long calculateNextRunTime() {
        if (isBackedOff()) {
            long jScalb = this.backoffPolicy == BackoffPolicy.LINEAR ? this.backoffDelayDuration * ((long) this.runAttemptCount) : (long) Math.scalb(this.backoffDelayDuration, this.runAttemptCount - 1);
            long j = this.lastEnqueueTime;
            if (jScalb > WorkRequest.MAX_BACKOFF_MILLIS) {
                jScalb = 18000000;
            }
            return j + jScalb;
        }
        if (!isPeriodic()) {
            long jCurrentTimeMillis = this.lastEnqueueTime;
            if (jCurrentTimeMillis == 0) {
                jCurrentTimeMillis = System.currentTimeMillis();
            }
            return jCurrentTimeMillis + this.initialDelay;
        }
        int i = this.periodCount;
        long j2 = this.lastEnqueueTime;
        if (i == 0) {
            j2 += this.initialDelay;
        }
        long j3 = this.flexDuration;
        long j4 = this.intervalDuration;
        if (j3 != j4) {
            return j2 + j4 + (i == 0 ? ((long) (-1)) * j3 : 0L);
        }
        return j2 + (i != 0 ? j4 : 0L);
    }

    public final String component1() {
        return this.id;
    }

    public final Constraints component10() {
        return this.constraints;
    }

    public final int component11() {
        return this.runAttemptCount;
    }

    public final BackoffPolicy component12() {
        return this.backoffPolicy;
    }

    public final long component13() {
        return this.backoffDelayDuration;
    }

    public final long component14() {
        return this.lastEnqueueTime;
    }

    public final long component15() {
        return this.minimumRetentionDuration;
    }

    public final long component16() {
        return this.scheduleRequestedAt;
    }

    public final boolean component17() {
        return this.expedited;
    }

    public final OutOfQuotaPolicy component18() {
        return this.outOfQuotaPolicy;
    }

    public final int component19() {
        return this.periodCount;
    }

    public final WorkInfo.State component2() {
        return this.state;
    }

    public final int component20() {
        return this.generation;
    }

    public final String component3() {
        return this.workerClassName;
    }

    public final String component4() {
        return this.inputMergerClassName;
    }

    public final Data component5() {
        return this.input;
    }

    public final Data component6() {
        return this.output;
    }

    public final long component7() {
        return this.initialDelay;
    }

    public final long component8() {
        return this.intervalDuration;
    }

    public final long component9() {
        return this.flexDuration;
    }

    public final WorkSpec copy(String str, WorkInfo.State state, String str2, String str3, Data data, Data data2, long j, long j2, long j3, Constraints constraints, int i, BackoffPolicy backoffPolicy, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy, int i2, int i3) {
        t40.n(str, ApiConstants.ID);
        t40.n(state, ApiConstants.STATE);
        t40.n(str2, "workerClassName");
        t40.n(data, "input");
        t40.n(data2, "output");
        t40.n(constraints, CryptoServicesPermission.CONSTRAINTS);
        t40.n(backoffPolicy, "backoffPolicy");
        t40.n(outOfQuotaPolicy, "outOfQuotaPolicy");
        return new WorkSpec(str, state, str2, str3, data, data2, j, j2, j3, constraints, i, backoffPolicy, j4, j5, j6, j7, z, outOfQuotaPolicy, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorkSpec)) {
            return false;
        }
        WorkSpec workSpec = (WorkSpec) obj;
        return t40.f(this.id, workSpec.id) && this.state == workSpec.state && t40.f(this.workerClassName, workSpec.workerClassName) && t40.f(this.inputMergerClassName, workSpec.inputMergerClassName) && t40.f(this.input, workSpec.input) && t40.f(this.output, workSpec.output) && this.initialDelay == workSpec.initialDelay && this.intervalDuration == workSpec.intervalDuration && this.flexDuration == workSpec.flexDuration && t40.f(this.constraints, workSpec.constraints) && this.runAttemptCount == workSpec.runAttemptCount && this.backoffPolicy == workSpec.backoffPolicy && this.backoffDelayDuration == workSpec.backoffDelayDuration && this.lastEnqueueTime == workSpec.lastEnqueueTime && this.minimumRetentionDuration == workSpec.minimumRetentionDuration && this.scheduleRequestedAt == workSpec.scheduleRequestedAt && this.expedited == workSpec.expedited && this.outOfQuotaPolicy == workSpec.outOfQuotaPolicy && this.periodCount == workSpec.periodCount && this.generation == workSpec.generation;
    }

    public final int getGeneration() {
        return this.generation;
    }

    public final int getPeriodCount() {
        return this.periodCount;
    }

    public final boolean hasConstraints() {
        return !t40.f(Constraints.NONE, this.constraints);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v32, types: [int] */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v42 */
    public int hashCode() {
        int iHashCode = (this.workerClassName.hashCode() + ((this.state.hashCode() + (this.id.hashCode() * 31)) * 31)) * 31;
        String str = this.inputMergerClassName;
        int iHashCode2 = (Long.hashCode(this.scheduleRequestedAt) + ((Long.hashCode(this.minimumRetentionDuration) + ((Long.hashCode(this.lastEnqueueTime) + ((Long.hashCode(this.backoffDelayDuration) + ((this.backoffPolicy.hashCode() + ((Integer.hashCode(this.runAttemptCount) + ((this.constraints.hashCode() + ((Long.hashCode(this.flexDuration) + ((Long.hashCode(this.intervalDuration) + ((Long.hashCode(this.initialDelay) + ((this.output.hashCode() + ((this.input.hashCode() + ((iHashCode + (str == null ? 0 : str.hashCode())) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        boolean z = this.expedited;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return Integer.hashCode(this.generation) + ((Integer.hashCode(this.periodCount) + ((this.outOfQuotaPolicy.hashCode() + ((iHashCode2 + r1) * 31)) * 31)) * 31);
    }

    public final boolean isBackedOff() {
        return this.state == WorkInfo.State.ENQUEUED && this.runAttemptCount > 0;
    }

    public final boolean isPeriodic() {
        return this.intervalDuration != 0;
    }

    public final void setBackoffDelayDuration(long j) {
        if (j > WorkRequest.MAX_BACKOFF_MILLIS) {
            Logger.get().warning(TAG, "Backoff delay duration exceeds maximum value");
        }
        if (j < WorkRequest.MIN_BACKOFF_MILLIS) {
            Logger.get().warning(TAG, "Backoff delay duration less than minimum value");
        }
        this.backoffDelayDuration = fa0.p(j, WorkRequest.MIN_BACKOFF_MILLIS, WorkRequest.MAX_BACKOFF_MILLIS);
    }

    public final void setPeriodCount(int i) {
        this.periodCount = i;
    }

    public final void setPeriodic(long j) {
        if (j < PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) {
            Logger.get().warning(TAG, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        long j2 = j < PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS ? 900000L : j;
        if (j < PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) {
            j = 900000;
        }
        setPeriodic(j2, j);
    }

    public String toString() {
        return "{WorkSpec: " + this.id + '}';
    }

    public final void setPeriodic(long j, long j2) {
        long j3 = PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;
        if (j < PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) {
            Logger.get().warning(TAG, "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        if (j >= PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS) {
            j3 = j;
        }
        this.intervalDuration = j3;
        if (j2 < PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
            Logger.get().warning(TAG, "Flex duration lesser than minimum allowed value; Changed to 300000");
        }
        if (j2 > this.intervalDuration) {
            Logger.get().warning(TAG, "Flex duration greater than interval duration; Changed to " + j);
        }
        this.flexDuration = fa0.p(j2, PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS, this.intervalDuration);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ WorkSpec(String str, WorkInfo.State state, String str2, String str3, Data data, Data data2, long j, long j2, long j3, Constraints constraints, int i, BackoffPolicy backoffPolicy, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy, int i2, int i3, int i4, tm tmVar) {
        Data data3;
        Data data4;
        WorkInfo.State state2 = (i4 & 2) != 0 ? WorkInfo.State.ENQUEUED : state;
        String str4 = (i4 & 8) != 0 ? null : str3;
        if ((i4 & 16) != 0) {
            Data data5 = Data.EMPTY;
            t40.m(data5, "EMPTY");
            data3 = data5;
        } else {
            data3 = data;
        }
        if ((i4 & 32) != 0) {
            Data data6 = Data.EMPTY;
            t40.m(data6, "EMPTY");
            data4 = data6;
        } else {
            data4 = data2;
        }
        this(str, state2, str2, str4, data3, data4, (i4 & 64) != 0 ? 0L : j, (i4 & Uuid.SIZE_BITS) != 0 ? 0L : j2, (i4 & NatsJetStreamConstants.MAX_PULL_SIZE) != 0 ? 0L : j3, (i4 & 512) != 0 ? Constraints.NONE : constraints, (i4 & 1024) != 0 ? 0 : i, (i4 & 2048) != 0 ? BackoffPolicy.EXPONENTIAL : backoffPolicy, (i4 & Options.DEFAULT_MAX_CONTROL_LINE) != 0 ? 30000L : j4, (i4 & 8192) != 0 ? 0L : j5, (i4 & WebSocketImpl.RCVBUF) != 0 ? 0L : j6, (32768 & i4) != 0 ? -1L : j7, (65536 & i4) != 0 ? false : z, (131072 & i4) != 0 ? OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST : outOfQuotaPolicy, (262144 & i4) != 0 ? 0 : i2, (i4 & 524288) != 0 ? 0 : i3);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WorkSpec(String str, String str2) {
        this(str, null, str2, null, null, null, 0L, 0L, 0L, null, 0, null, 0L, 0L, 0L, 0L, false, null, 0, 0, 1048570, null);
        t40.n(str, ApiConstants.ID);
        t40.n(str2, "workerClassName_");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WorkSpec(String str, WorkSpec workSpec) {
        this(str, workSpec.state, workSpec.workerClassName, workSpec.inputMergerClassName, new Data(workSpec.input), new Data(workSpec.output), workSpec.initialDelay, workSpec.intervalDuration, workSpec.flexDuration, new Constraints(workSpec.constraints), workSpec.runAttemptCount, workSpec.backoffPolicy, workSpec.backoffDelayDuration, workSpec.lastEnqueueTime, workSpec.minimumRetentionDuration, workSpec.scheduleRequestedAt, workSpec.expedited, workSpec.outOfQuotaPolicy, workSpec.periodCount, 0, 524288, null);
        t40.n(str, "newId");
        t40.n(workSpec, "other");
    }
}
