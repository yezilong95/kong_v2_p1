package androidx.work.impl.model;

import androidx.work.impl.model.WorkSpec;
import defpackage.b30;
import defpackage.vl0;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface RawWorkInfoDao {
    List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(vl0 vl0Var);

    b30 getWorkInfoPojosLiveData(vl0 vl0Var);
}
