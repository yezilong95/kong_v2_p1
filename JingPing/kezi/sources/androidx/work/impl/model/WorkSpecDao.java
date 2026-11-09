package androidx.work.impl.model;

import android.annotation.SuppressLint;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import defpackage.b30;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public interface WorkSpecDao {
    void delete(String str);

    List<WorkSpec> getAllEligibleWorkSpecsForScheduling(int i);

    List<String> getAllUnfinishedWork();

    List<String> getAllWorkSpecIds();

    b30 getAllWorkSpecIdsLiveData();

    List<WorkSpec> getEligibleWorkForScheduling(int i);

    List<Data> getInputsFromPrerequisites(String str);

    List<WorkSpec> getRecentlyCompletedWork(long j);

    List<WorkSpec> getRunningWork();

    b30 getScheduleRequestedAtLiveData(String str);

    List<WorkSpec> getScheduledWork();

    WorkInfo.State getState(String str);

    List<String> getUnfinishedWorkWithName(String str);

    List<String> getUnfinishedWorkWithTag(String str);

    WorkSpec getWorkSpec(String str);

    List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(String str);

    WorkSpec.WorkInfoPojo getWorkStatusPojoForId(String str);

    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForIds(List<String> list);

    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForName(String str);

    List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForTag(String str);

    b30 getWorkStatusPojoLiveDataForIds(List<String> list);

    b30 getWorkStatusPojoLiveDataForName(String str);

    b30 getWorkStatusPojoLiveDataForTag(String str);

    boolean hasUnfinishedWork();

    void incrementGeneration(String str);

    void incrementPeriodCount(String str);

    int incrementWorkSpecRunAttemptCount(String str);

    void insertWorkSpec(WorkSpec workSpec);

    int markWorkSpecScheduled(String str, long j);

    void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast();

    int resetScheduledState();

    int resetWorkSpecRunAttemptCount(String str);

    void setLastEnqueuedTime(String str, long j);

    void setOutput(String str, Data data);

    int setState(WorkInfo.State state, String str);

    void updateWorkSpec(WorkSpec workSpec);
}
