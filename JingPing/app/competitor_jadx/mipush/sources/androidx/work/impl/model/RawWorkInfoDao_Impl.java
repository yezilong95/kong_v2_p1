package androidx.work.impl.model;

import android.database.Cursor;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import defpackage.ac0;
import defpackage.ea0;
import defpackage.jy0;
import defpackage.k8;
import defpackage.kp0;
import defpackage.l21;
import defpackage.ma0;
import defpackage.np0;
import defpackage.qm;
import defpackage.zb0;
import defpackage.zn;
import io.nats.client.support.ApiConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class RawWorkInfoDao_Impl implements RawWorkInfoDao {
    private final kp0 __db;

    public RawWorkInfoDao_Impl(kp0 kp0Var) {
        this.__db = kp0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkProgressAsandroidxWorkData(k8 k8Var) throws IOException {
        ac0 ac0Var = (ac0) k8Var.keySet();
        if (ac0Var.isEmpty()) {
            return;
        }
        if (k8Var.G > 999) {
            k8 k8Var2 = new k8(kp0.MAX_BIND_PARAMETER_CNT);
            int i = k8Var.G;
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                k8Var2.put((String) k8Var.h(i2), (ArrayList) k8Var.j(i2));
                i2++;
                i3++;
                if (i3 == 999) {
                    __fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                    k8Var2 = new k8(kp0.MAX_BIND_PARAMETER_CNT);
                    i3 = 0;
                }
            }
            if (i3 > 0) {
                __fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                return;
            }
            return;
        }
        StringBuilder sbP = zn.p("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int iD = ac0Var.F.d();
        ma0.e(sbP, iD);
        sbP.append(")");
        np0 np0VarW = np0.w(iD, sbP.toString());
        Iterator it = ac0Var.iterator();
        int i4 = 1;
        while (true) {
            zb0 zb0Var = (zb0) it;
            if (!zb0Var.hasNext()) {
                break;
            }
            String str = (String) zb0Var.next();
            if (str == null) {
                np0VarW.h(i4);
            } else {
                np0VarW.e(i4, str);
            }
            i4++;
        }
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            int iT = qm.t(cursorD0, "work_spec_id");
            if (iT == -1) {
                return;
            }
            while (cursorD0.moveToNext()) {
                byte[] blob = null;
                ArrayList arrayList = (ArrayList) k8Var.getOrDefault(cursorD0.getString(iT), null);
                if (arrayList != null) {
                    if (!cursorD0.isNull(0)) {
                        blob = cursorD0.getBlob(0);
                    }
                    arrayList.add(Data.fromByteArray(blob));
                }
            }
        } finally {
            cursorD0.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkTagAsjavaLangString(k8 k8Var) throws IOException {
        ac0 ac0Var = (ac0) k8Var.keySet();
        if (ac0Var.isEmpty()) {
            return;
        }
        if (k8Var.G > 999) {
            k8 k8Var2 = new k8(kp0.MAX_BIND_PARAMETER_CNT);
            int i = k8Var.G;
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                k8Var2.put((String) k8Var.h(i2), (ArrayList) k8Var.j(i2));
                i2++;
                i3++;
                if (i3 == 999) {
                    __fetchRelationshipWorkTagAsjavaLangString(k8Var2);
                    k8Var2 = new k8(kp0.MAX_BIND_PARAMETER_CNT);
                    i3 = 0;
                }
            }
            if (i3 > 0) {
                __fetchRelationshipWorkTagAsjavaLangString(k8Var2);
                return;
            }
            return;
        }
        StringBuilder sbP = zn.p("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int iD = ac0Var.F.d();
        ma0.e(sbP, iD);
        sbP.append(")");
        np0 np0VarW = np0.w(iD, sbP.toString());
        Iterator it = ac0Var.iterator();
        int i4 = 1;
        while (true) {
            zb0 zb0Var = (zb0) it;
            if (!zb0Var.hasNext()) {
                break;
            }
            String str = (String) zb0Var.next();
            if (str == null) {
                np0VarW.h(i4);
            } else {
                np0VarW.e(i4, str);
            }
            i4++;
        }
        Cursor cursorD0 = l21.D0(this.__db, np0VarW, false);
        try {
            int iT = qm.t(cursorD0, "work_spec_id");
            if (iT == -1) {
                return;
            }
            while (cursorD0.moveToNext()) {
                String string = null;
                ArrayList arrayList = (ArrayList) k8Var.getOrDefault(cursorD0.getString(iT), null);
                if (arrayList != null) {
                    if (!cursorD0.isNull(0)) {
                        string = cursorD0.getString(0);
                    }
                    arrayList.add(string);
                }
            }
        } finally {
            cursorD0.close();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(jy0 jy0Var) throws IOException {
        Data dataFromByteArray;
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorD0 = l21.D0(this.__db, jy0Var, true);
        try {
            int iT = qm.t(cursorD0, ApiConstants.ID);
            int iT2 = qm.t(cursorD0, ApiConstants.STATE);
            int iT3 = qm.t(cursorD0, "output");
            int iT4 = qm.t(cursorD0, "run_attempt_count");
            int iT5 = qm.t(cursorD0, "generation");
            k8 k8Var = new k8();
            k8 k8Var2 = new k8();
            while (cursorD0.moveToNext()) {
                String string = cursorD0.getString(iT);
                if (((ArrayList) k8Var.getOrDefault(string, null)) == null) {
                    k8Var.put(string, new ArrayList());
                }
                String string2 = cursorD0.getString(iT);
                if (((ArrayList) k8Var2.getOrDefault(string2, null)) == null) {
                    k8Var2.put(string2, new ArrayList());
                }
            }
            cursorD0.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(k8Var);
            __fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
            ArrayList arrayList = new ArrayList(cursorD0.getCount());
            while (cursorD0.moveToNext()) {
                String string3 = (iT == -1 || cursorD0.isNull(iT)) ? null : cursorD0.getString(iT);
                WorkInfo.State stateIntToState = iT2 == -1 ? null : WorkTypeConverters.intToState(cursorD0.getInt(iT2));
                if (iT3 == -1) {
                    dataFromByteArray = null;
                } else {
                    dataFromByteArray = Data.fromByteArray(cursorD0.isNull(iT3) ? null : cursorD0.getBlob(iT3));
                }
                int i = iT4 == -1 ? 0 : cursorD0.getInt(iT4);
                int i2 = iT5 != -1 ? cursorD0.getInt(iT5) : 0;
                ArrayList arrayList2 = (ArrayList) k8Var.getOrDefault(cursorD0.getString(iT), null);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                ArrayList arrayList3 = arrayList2;
                ArrayList arrayList4 = (ArrayList) k8Var2.getOrDefault(cursorD0.getString(iT), null);
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList();
                }
                arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
            }
            cursorD0.close();
            return arrayList;
        } catch (Throwable th) {
            cursorD0.close();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public ea0 getWorkInfoPojosLiveData(final jy0 jy0Var) {
        return this.__db.getInvalidationTracker().a(new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, false, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl.1
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                Data dataFromByteArray;
                Cursor cursorD0 = l21.D0(RawWorkInfoDao_Impl.this.__db, jy0Var, true);
                try {
                    int iT = qm.t(cursorD0, ApiConstants.ID);
                    int iT2 = qm.t(cursorD0, ApiConstants.STATE);
                    int iT3 = qm.t(cursorD0, "output");
                    int iT4 = qm.t(cursorD0, "run_attempt_count");
                    int iT5 = qm.t(cursorD0, "generation");
                    k8 k8Var = new k8();
                    k8 k8Var2 = new k8();
                    while (cursorD0.moveToNext()) {
                        String string = cursorD0.getString(iT);
                        if (((ArrayList) k8Var.getOrDefault(string, null)) == null) {
                            k8Var.put(string, new ArrayList());
                        }
                        String string2 = cursorD0.getString(iT);
                        if (((ArrayList) k8Var2.getOrDefault(string2, null)) == null) {
                            k8Var2.put(string2, new ArrayList());
                        }
                    }
                    cursorD0.moveToPosition(-1);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(k8Var);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(k8Var2);
                    ArrayList arrayList = new ArrayList(cursorD0.getCount());
                    while (cursorD0.moveToNext()) {
                        String string3 = (iT == -1 || cursorD0.isNull(iT)) ? null : cursorD0.getString(iT);
                        WorkInfo.State stateIntToState = iT2 == -1 ? null : WorkTypeConverters.intToState(cursorD0.getInt(iT2));
                        if (iT3 == -1) {
                            dataFromByteArray = null;
                        } else {
                            dataFromByteArray = Data.fromByteArray(cursorD0.isNull(iT3) ? null : cursorD0.getBlob(iT3));
                        }
                        int i = iT4 == -1 ? 0 : cursorD0.getInt(iT4);
                        int i2 = iT5 != -1 ? cursorD0.getInt(iT5) : 0;
                        ArrayList arrayList2 = (ArrayList) k8Var.getOrDefault(cursorD0.getString(iT), null);
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = arrayList2;
                        ArrayList arrayList4 = (ArrayList) k8Var2.getOrDefault(cursorD0.getString(iT), null);
                        if (arrayList4 == null) {
                            arrayList4 = new ArrayList();
                        }
                        arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
                    }
                    cursorD0.close();
                    return arrayList;
                } catch (Throwable th) {
                    cursorD0.close();
                    throw th;
                }
            }
        });
    }
}
