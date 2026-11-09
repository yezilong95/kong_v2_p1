package androidx.work.impl.model;

import androidx.work.impl.model.WorkSpec;
import defpackage.ea0;
import defpackage.jy0;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface RawWorkInfoDao {
    List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(jy0 jy0Var);

    ea0 getWorkInfoPojosLiveData(jy0 jy0Var);
}
