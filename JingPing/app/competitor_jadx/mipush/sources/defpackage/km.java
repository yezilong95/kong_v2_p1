package defpackage;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class km {
    public static final Object E = new Object();
    public long rowID = -1;

    public static boolean checkDataExist(Class cls, String... strArr) {
        z91.v();
        throw null;
    }

    public static <T extends km> void clean(Class<T> cls) {
        z91.v();
        throw null;
    }

    public static void exeSql(String str) {
        z91.v();
        throw null;
    }

    public static <T extends km> List<T> findAllData(Class<T> cls) {
        z91.v();
        throw null;
    }

    public static <T extends km> List<T> findDataByFuzzy(Class<T> cls, String str) {
        z91.v();
        throw null;
    }

    public static <T extends km> List<T> findDatas(Class<T> cls, String... strArr) {
        z91.v();
        throw null;
    }

    public static <T extends km> T findFirst(Class<T> cls) {
        List listFindAllData = findAllData(cls);
        if (listFindAllData == null || listFindAllData.size() == 0) {
            return null;
        }
        return (T) listFindAllData.get(0);
    }

    public static <T> List<T> findRelationData(Class<T> cls, String... strArr) {
        z91.v();
        throw null;
    }

    public static <T extends km> void insertManyData(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new NullPointerException("列表数据为空");
        }
        z91.v();
        throw null;
    }

    public static <T extends km> void saveAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new NullPointerException("列表数据为空");
        }
        new ArrayList();
        new ArrayList();
        z91.v();
        throw null;
    }

    public static boolean tableExists(Class<km> cls) {
        z91.v();
        throw null;
    }

    public static <T extends km> void updateManyData(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new NullPointerException("列表数据为空");
        }
        z91.v();
        throw null;
    }

    public void deleteData() {
        deleteData(getClass(), "rowid=?", this.rowID + "");
    }

    public void insert() {
        z91.v();
        throw null;
    }

    public void save() {
        synchronized (E) {
            z91.v();
            throw null;
        }
    }

    public void update() {
        z91.v();
        throw null;
    }

    public static <T extends km> void deleteData(Class<T> cls, String... strArr) {
        z91.v();
        throw null;
    }

    public static <T extends km> List<T> findDataByFuzzy(Class<T> cls, int i, int i2, String str) {
        z91.v();
        throw null;
    }

    public static <T extends km> List<T> findDatas(Class<T> cls, int i, int i2, String... strArr) {
        if (i < 1 || i2 < 1) {
            return null;
        }
        z91.v();
        throw null;
    }

    public static <T> List<T> findRelationData(Class<T> cls, int i, int i2, String... strArr) {
        if (i < 1 || i2 < 1) {
            return null;
        }
        z91.v();
        throw null;
    }

    public static <T extends km> T findFirst(Class<T> cls, String... strArr) {
        z91.v();
        throw null;
    }
}
