package androidx.work.impl.model;

import defpackage.oy;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public interface WorkTagDao {

    public static final class DefaultImpls {
        public static void insertTags(WorkTagDao workTagDao, String str, Set<String> set) {
            oy.j(str, "id");
            oy.j(set, "tags");
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                workTagDao.insert(new WorkTag((String) it.next(), str));
            }
        }
    }

    void deleteByWorkSpecId(String str);

    List<String> getTagsForWorkSpecId(String str);

    List<String> getWorkSpecIdsWithTag(String str);

    void insert(WorkTag workTag);

    void insertTags(String str, Set<String> set);
}
