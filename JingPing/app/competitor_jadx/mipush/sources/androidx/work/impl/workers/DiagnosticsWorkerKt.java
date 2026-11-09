package androidx.work.impl.workers;

import androidx.work.Logger;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.model.WorkTagDao;
import defpackage.t40;
import defpackage.vf;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DiagnosticsWorkerKt {
    private static final String TAG;

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("DiagnosticsWrkr");
        t40.m(strTagWithPrefix, "tagWithPrefix(\"DiagnosticsWrkr\")");
        TAG = strTagWithPrefix;
    }

    private static final String workSpecRow(WorkSpec workSpec, String str, Integer num, String str2) {
        return "\n" + workSpec.id + "\t " + workSpec.workerClassName + "\t " + num + "\t " + workSpec.state.name() + "\t " + str + "\t " + str2 + '\t';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String workSpecRows(WorkNameDao workNameDao, WorkTagDao workTagDao, SystemIdInfoDao systemIdInfoDao, List<WorkSpec> list) {
        StringBuilder sb = new StringBuilder("\n Id \t Class Name\t Job Id\t State\t Unique Name\t Tags\t");
        for (WorkSpec workSpec : list) {
            SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(WorkSpecKt.generationalId(workSpec));
            sb.append(workSpecRow(workSpec, vf.k1(workNameDao.getNamesForWorkSpecId(workSpec.id), ",", null, 62), systemIdInfo != null ? Integer.valueOf(systemIdInfo.systemId) : null, vf.k1(workTagDao.getTagsForWorkSpecId(workSpec.id), ",", null, 62)));
        }
        String string = sb.toString();
        t40.m(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
