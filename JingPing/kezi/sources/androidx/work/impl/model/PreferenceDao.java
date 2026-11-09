package androidx.work.impl.model;

import defpackage.b30;

/* JADX INFO: loaded from: classes.dex */
public interface PreferenceDao {
    Long getLongValue(String str);

    b30 getObservableLongValue(String str);

    void insertPreference(Preference preference);
}
