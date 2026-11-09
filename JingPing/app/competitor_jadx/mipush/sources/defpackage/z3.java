package defpackage;

import android.content.Intent;
import androidx.activity.result.ActivityResult;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class z3 extends l21 {
    public final /* synthetic */ int U;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z3(int i) {
        super(14);
        this.U = i;
    }

    @Override // defpackage.l21
    public final Object t0(Intent intent, int i) {
        switch (this.U) {
            case 0:
                st stVar = st.E;
                if (i == -1 && intent != null) {
                    String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                    int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
                    if (intArrayExtra != null && stringArrayExtra != null) {
                        ArrayList arrayList = new ArrayList(intArrayExtra.length);
                        for (int i2 : intArrayExtra) {
                            arrayList.add(Boolean.valueOf(i2 == 0));
                        }
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : stringArrayExtra) {
                            if (str != null) {
                                arrayList2.add(str);
                            }
                        }
                        Iterator it = arrayList2.iterator();
                        Iterator it2 = arrayList.iterator();
                        ArrayList arrayList3 = new ArrayList(Math.min(arrayList2.size(), arrayList.size()));
                        while (it.hasNext() && it2.hasNext()) {
                            arrayList3.add(new Pair(it.next(), it2.next()));
                        }
                        break;
                    }
                }
                break;
        }
        return new ActivityResult(intent, i);
    }
}
