package defpackage;

import java.util.ArrayList;
import java.util.List;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public abstract class qi {
    public static final Object b = new Object();
    public long rowID = -1;

    public static boolean checkDataExist(Class cls, String... strArr) {
        rp0.A();
        throw null;
    }

    public static <T extends qi> void clean(Class<T> cls) {
        rp0.A();
        throw null;
    }

    public static void exeSql(String str) {
        rp0.A();
        throw null;
    }

    public static <T extends qi> List<T> findAllData(Class<T> cls) {
        rp0.A();
        throw null;
    }

    public static <T extends qi> List<T> findDataByFuzzy(Class<T> cls, String str) {
        rp0.A();
        throw null;
    }

    public static <T extends qi> List<T> findDatas(Class<T> cls, String... strArr) {
        rp0.A();
        throw null;
    }

    public static <T extends qi> T findFirst(Class<T> cls) {
        List listFindAllData = findAllData(cls);
        if (listFindAllData == null || listFindAllData.size() == 0) {
            return null;
        }
        return (T) listFindAllData.get(0);
    }

    public static <T> List<T> findRelationData(Class<T> cls, String... strArr) {
        rp0.A();
        throw null;
    }

    public static <T extends qi> void insertManyData(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new NullPointerException("列表数据为空");
        }
        rp0.A();
        throw null;
    }

    public static <T extends qi> void saveAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new NullPointerException("列表数据为空");
        }
        new ArrayList();
        new ArrayList();
        rp0.A();
        throw null;
    }

    public static boolean tableExists(Class<qi> cls) {
        rp0.A();
        throw null;
    }

    public static <T extends qi> void updateManyData(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new NullPointerException("列表数据为空");
        }
        rp0.A();
        throw null;
    }

    public void deleteData() {
        deleteData(getClass(), "rowid=?", this.rowID + ExtensionRequestData.EMPTY_VALUE);
    }

    public void insert() {
        rp0.A();
        throw null;
    }

    public void save() {
        synchronized (b) {
            rp0.A();
            throw null;
        }
    }

    public void update() {
        rp0.A();
        throw null;
    }

    public static <T extends qi> void deleteData(Class<T> cls, String... strArr) {
        rp0.A();
        throw null;
    }

    public static <T extends qi> List<T> findDataByFuzzy(Class<T> cls, int i, int i2, String str) {
        rp0.A();
        throw null;
    }

    public static <T extends qi> List<T> findDatas(Class<T> cls, int i, int i2, String... strArr) {
        if (i < 1 || i2 < 1) {
            return null;
        }
        rp0.A();
        throw null;
    }

    public static <T> List<T> findRelationData(Class<T> cls, int i, int i2, String... strArr) {
        if (i < 1 || i2 < 1) {
            return null;
        }
        rp0.A();
        throw null;
    }

    public static <T extends qi> T findFirst(Class<T> cls, String... strArr) {
        rp0.A();
        throw null;
    }
}
