package androidx.fragment.app;

import android.util.Log;
import androidx.activity.result.ActivityResult;
import defpackage.ou;
import defpackage.vz;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final /* synthetic */ int a;
    public final /* synthetic */ c b;

    public /* synthetic */ b(c cVar, int i) {
        this.a = i;
        this.b = cVar;
    }

    public final void a(Object obj) {
        switch (this.a) {
            case 0:
                ActivityResult activityResult = (ActivityResult) obj;
                c cVar = this.b;
                FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo = (FragmentManager$LaunchedFragmentInfo) cVar.x.pollFirst();
                if (fragmentManager$LaunchedFragmentInfo == null) {
                    Log.w("FragmentManager", "No IntentSenders were started for " + this);
                } else {
                    ou ouVar = cVar.c;
                    String str = fragmentManager$LaunchedFragmentInfo.E;
                    vz vzVarT = ouVar.t(str);
                    if (vzVarT == null) {
                        Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str);
                    } else {
                        vzVarT.j(fragmentManager$LaunchedFragmentInfo.F, activityResult.E, activityResult.F);
                    }
                }
                break;
            case 1:
                Map map = (Map) obj;
                ArrayList arrayList = new ArrayList(map.values());
                int[] iArr = new int[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++) {
                    iArr[i] = ((Boolean) arrayList.get(i)).booleanValue() ? 0 : -1;
                }
                c cVar2 = this.b;
                FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo2 = (FragmentManager$LaunchedFragmentInfo) cVar2.x.pollFirst();
                if (fragmentManager$LaunchedFragmentInfo2 == null) {
                    Log.w("FragmentManager", "No permissions were requested for " + this);
                } else {
                    ou ouVar2 = cVar2.c;
                    String str2 = fragmentManager$LaunchedFragmentInfo2.E;
                    if (ouVar2.t(str2) == null) {
                        Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str2);
                    }
                }
                break;
            default:
                ActivityResult activityResult2 = (ActivityResult) obj;
                c cVar3 = this.b;
                FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo3 = (FragmentManager$LaunchedFragmentInfo) cVar3.x.pollFirst();
                if (fragmentManager$LaunchedFragmentInfo3 == null) {
                    Log.w("FragmentManager", "No Activities were started for result for " + this);
                } else {
                    ou ouVar3 = cVar3.c;
                    String str3 = fragmentManager$LaunchedFragmentInfo3.E;
                    vz vzVarT2 = ouVar3.t(str3);
                    if (vzVarT2 == null) {
                        Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str3);
                    } else {
                        vzVarT2.j(fragmentManager$LaunchedFragmentInfo3.F, activityResult2.E, activityResult2.F);
                    }
                }
                break;
        }
    }
}
