package androidx.work.impl.model;

import android.database.Cursor;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import defpackage.b30;
import defpackage.c7;
import defpackage.hk;
import defpackage.jq0;
import defpackage.k40;
import defpackage.k9;
import defpackage.l40;
import defpackage.pf0;
import defpackage.sf0;
import defpackage.vl0;
import defpackage.za0;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class RawWorkInfoDao_Impl implements RawWorkInfoDao {
    private final pf0 __db;

    public RawWorkInfoDao_Impl(pf0 pf0Var) {
        this.__db = pf0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkProgressAsandroidxWorkData(c7 c7Var) throws IllegalAccessException, IOException, InvocationTargetException {
        l40 l40Var = (l40) c7Var.keySet();
        if (l40Var.isEmpty()) {
            return;
        }
        if (c7Var.d > 999) {
            c7 c7Var2 = new c7(pf0.MAX_BIND_PARAMETER_CNT);
            int i = c7Var.d;
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                c7Var2.put((String) c7Var.h(i2), (ArrayList) c7Var.j(i2));
                i2++;
                i3++;
                if (i3 == 999) {
                    __fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                    c7Var2 = new c7(pf0.MAX_BIND_PARAMETER_CNT);
                    i3 = 0;
                }
            }
            if (i3 > 0) {
                __fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                return;
            }
            return;
        }
        StringBuilder sbL = hk.l("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int iE = l40Var.c.e();
        za0.d(sbL, iE);
        sbL.append(")");
        sf0 sf0VarC = sf0.C(iE, sbL.toString());
        Iterator it = l40Var.iterator();
        int i4 = 1;
        while (true) {
            k40 k40Var = (k40) it;
            if (!k40Var.hasNext()) {
                break;
            }
            String str = (String) k40Var.next();
            if (str == null) {
                sf0VarC.r(i4);
            } else {
                sf0VarC.j(i4, str);
            }
            i4++;
        }
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            int iR = jq0.r(cursorB, "work_spec_id");
            if (iR == -1) {
                return;
            }
            while (cursorB.moveToNext()) {
                byte[] blob = null;
                ArrayList arrayList = (ArrayList) c7Var.getOrDefault(cursorB.getString(iR), null);
                if (arrayList != null) {
                    if (!cursorB.isNull(0)) {
                        blob = cursorB.getBlob(0);
                    }
                    arrayList.add(Data.fromByteArray(blob));
                }
            }
        } finally {
            cursorB.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkTagAsjavaLangString(c7 c7Var) throws IllegalAccessException, IOException, InvocationTargetException {
        l40 l40Var = (l40) c7Var.keySet();
        if (l40Var.isEmpty()) {
            return;
        }
        if (c7Var.d > 999) {
            c7 c7Var2 = new c7(pf0.MAX_BIND_PARAMETER_CNT);
            int i = c7Var.d;
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                c7Var2.put((String) c7Var.h(i2), (ArrayList) c7Var.j(i2));
                i2++;
                i3++;
                if (i3 == 999) {
                    __fetchRelationshipWorkTagAsjavaLangString(c7Var2);
                    c7Var2 = new c7(pf0.MAX_BIND_PARAMETER_CNT);
                    i3 = 0;
                }
            }
            if (i3 > 0) {
                __fetchRelationshipWorkTagAsjavaLangString(c7Var2);
                return;
            }
            return;
        }
        StringBuilder sbL = hk.l("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int iE = l40Var.c.e();
        za0.d(sbL, iE);
        sbL.append(")");
        sf0 sf0VarC = sf0.C(iE, sbL.toString());
        Iterator it = l40Var.iterator();
        int i4 = 1;
        while (true) {
            k40 k40Var = (k40) it;
            if (!k40Var.hasNext()) {
                break;
            }
            String str = (String) k40Var.next();
            if (str == null) {
                sf0VarC.r(i4);
            } else {
                sf0VarC.j(i4, str);
            }
            i4++;
        }
        Cursor cursorB = k9.B(this.__db, sf0VarC, false);
        try {
            int iR = jq0.r(cursorB, "work_spec_id");
            if (iR == -1) {
                return;
            }
            while (cursorB.moveToNext()) {
                String string = null;
                ArrayList arrayList = (ArrayList) c7Var.getOrDefault(cursorB.getString(iR), null);
                if (arrayList != null) {
                    if (!cursorB.isNull(0)) {
                        string = cursorB.getString(0);
                    }
                    arrayList.add(string);
                }
            }
        } finally {
            cursorB.close();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(vl0 vl0Var) throws IllegalAccessException, IOException, InvocationTargetException {
        Data dataFromByteArray;
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorB = k9.B(this.__db, vl0Var, true);
        try {
            int iR = jq0.r(cursorB, "id");
            int iR2 = jq0.r(cursorB, "state");
            int iR3 = jq0.r(cursorB, "output");
            int iR4 = jq0.r(cursorB, "run_attempt_count");
            int iR5 = jq0.r(cursorB, "generation");
            c7 c7Var = new c7();
            c7 c7Var2 = new c7();
            while (cursorB.moveToNext()) {
                String string = cursorB.getString(iR);
                if (((ArrayList) c7Var.getOrDefault(string, null)) == null) {
                    c7Var.put(string, new ArrayList());
                }
                String string2 = cursorB.getString(iR);
                if (((ArrayList) c7Var2.getOrDefault(string2, null)) == null) {
                    c7Var2.put(string2, new ArrayList());
                }
            }
            cursorB.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(c7Var);
            __fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                String string3 = (iR == -1 || cursorB.isNull(iR)) ? null : cursorB.getString(iR);
                WorkInfo.State stateIntToState = iR2 == -1 ? null : WorkTypeConverters.intToState(cursorB.getInt(iR2));
                if (iR3 == -1) {
                    dataFromByteArray = null;
                } else {
                    dataFromByteArray = Data.fromByteArray(cursorB.isNull(iR3) ? null : cursorB.getBlob(iR3));
                }
                int i = iR4 == -1 ? 0 : cursorB.getInt(iR4);
                int i2 = iR5 != -1 ? cursorB.getInt(iR5) : 0;
                ArrayList arrayList2 = (ArrayList) c7Var.getOrDefault(cursorB.getString(iR), null);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                ArrayList arrayList3 = arrayList2;
                ArrayList arrayList4 = (ArrayList) c7Var2.getOrDefault(cursorB.getString(iR), null);
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList();
                }
                arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
            }
            cursorB.close();
            return arrayList;
        } catch (Throwable th) {
            cursorB.close();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public b30 getWorkInfoPojosLiveData(final vl0 vl0Var) {
        return this.__db.getInvalidationTracker().a(new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, false, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl.1
            @Override // java.util.concurrent.Callable
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                Data dataFromByteArray;
                Cursor cursorB = k9.B(RawWorkInfoDao_Impl.this.__db, vl0Var, true);
                try {
                    int iR = jq0.r(cursorB, "id");
                    int iR2 = jq0.r(cursorB, "state");
                    int iR3 = jq0.r(cursorB, "output");
                    int iR4 = jq0.r(cursorB, "run_attempt_count");
                    int iR5 = jq0.r(cursorB, "generation");
                    c7 c7Var = new c7();
                    c7 c7Var2 = new c7();
                    while (cursorB.moveToNext()) {
                        String string = cursorB.getString(iR);
                        if (((ArrayList) c7Var.getOrDefault(string, null)) == null) {
                            c7Var.put(string, new ArrayList());
                        }
                        String string2 = cursorB.getString(iR);
                        if (((ArrayList) c7Var2.getOrDefault(string2, null)) == null) {
                            c7Var2.put(string2, new ArrayList());
                        }
                    }
                    cursorB.moveToPosition(-1);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(c7Var);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(c7Var2);
                    ArrayList arrayList = new ArrayList(cursorB.getCount());
                    while (cursorB.moveToNext()) {
                        String string3 = (iR == -1 || cursorB.isNull(iR)) ? null : cursorB.getString(iR);
                        WorkInfo.State stateIntToState = iR2 == -1 ? null : WorkTypeConverters.intToState(cursorB.getInt(iR2));
                        if (iR3 == -1) {
                            dataFromByteArray = null;
                        } else {
                            dataFromByteArray = Data.fromByteArray(cursorB.isNull(iR3) ? null : cursorB.getBlob(iR3));
                        }
                        int i = iR4 == -1 ? 0 : cursorB.getInt(iR4);
                        int i2 = iR5 != -1 ? cursorB.getInt(iR5) : 0;
                        ArrayList arrayList2 = (ArrayList) c7Var.getOrDefault(cursorB.getString(iR), null);
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = arrayList2;
                        ArrayList arrayList4 = (ArrayList) c7Var2.getOrDefault(cursorB.getString(iR), null);
                        if (arrayList4 == null) {
                            arrayList4 = new ArrayList();
                        }
                        arrayList.add(new WorkSpec.WorkInfoPojo(string3, stateIntToState, dataFromByteArray, i, i2, arrayList3, arrayList4));
                    }
                    cursorB.close();
                    return arrayList;
                } catch (Throwable th) {
                    cursorB.close();
                    throw th;
                }
            }
        });
    }
}
