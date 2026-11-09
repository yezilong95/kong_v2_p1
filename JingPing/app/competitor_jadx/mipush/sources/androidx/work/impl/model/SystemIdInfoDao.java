package androidx.work.impl.model;

import defpackage.t40;
import io.nats.client.support.ApiConstants;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface SystemIdInfoDao {

    public static final class DefaultImpls {
        public static SystemIdInfo getSystemIdInfo(SystemIdInfoDao systemIdInfoDao, WorkGenerationalId workGenerationalId) {
            t40.n(workGenerationalId, ApiConstants.ID);
            return systemIdInfoDao.getSystemIdInfo(workGenerationalId.getWorkSpecId(), workGenerationalId.getGeneration());
        }

        public static void removeSystemIdInfo(SystemIdInfoDao systemIdInfoDao, WorkGenerationalId workGenerationalId) {
            t40.n(workGenerationalId, ApiConstants.ID);
            systemIdInfoDao.removeSystemIdInfo(workGenerationalId.getWorkSpecId(), workGenerationalId.getGeneration());
        }
    }

    SystemIdInfo getSystemIdInfo(WorkGenerationalId workGenerationalId);

    SystemIdInfo getSystemIdInfo(String str, int i);

    List<String> getWorkSpecIds();

    void insertSystemIdInfo(SystemIdInfo systemIdInfo);

    void removeSystemIdInfo(WorkGenerationalId workGenerationalId);

    void removeSystemIdInfo(String str);

    void removeSystemIdInfo(String str, int i);
}
