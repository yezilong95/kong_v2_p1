package androidx.work.impl.model;

import androidx.work.Data;

/* JADX INFO: loaded from: classes.dex */
public interface WorkProgressDao {
    void delete(String str);

    void deleteAll();

    Data getProgressForWorkSpecId(String str);

    void insert(WorkProgress workProgress);
}
