package androidx.work.impl.model;

import defpackage.ea0;

/* JADX INFO: loaded from: classes.dex */
public interface PreferenceDao {
    Long getLongValue(String str);

    ea0 getObservableLongValue(String str);

    void insertPreference(Preference preference);
}
