package androidx.fragment.app;

import android.util.Log;
import androidx.activity.result.ActivityResult;
import defpackage.f2;
import defpackage.jt;
import defpackage.po;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b implements f2 {
    public final /* synthetic */ int b;
    public final /* synthetic */ c c;

    public /* synthetic */ b(c cVar, int i) {
        this.b = i;
        this.c = cVar;
    }

    @Override // defpackage.f2
    public final void b(Object obj) {
        switch (this.b) {
            case 0:
                ActivityResult activityResult = (ActivityResult) obj;
                c cVar = this.c;
                FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo = (FragmentManager$LaunchedFragmentInfo) cVar.x.pollFirst();
                if (fragmentManager$LaunchedFragmentInfo == null) {
                    Log.w("FragmentManager", "No IntentSenders were started for " + this);
                } else {
                    po poVar = cVar.c;
                    String str = fragmentManager$LaunchedFragmentInfo.b;
                    jt jtVarS = poVar.s(str);
                    if (jtVarS == null) {
                        Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str);
                    } else {
                        jtVarS.j(fragmentManager$LaunchedFragmentInfo.c, activityResult.b, activityResult.c);
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
                c cVar2 = this.c;
                FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo2 = (FragmentManager$LaunchedFragmentInfo) cVar2.x.pollFirst();
                if (fragmentManager$LaunchedFragmentInfo2 == null) {
                    Log.w("FragmentManager", "No permissions were requested for " + this);
                } else {
                    po poVar2 = cVar2.c;
                    String str2 = fragmentManager$LaunchedFragmentInfo2.b;
                    if (poVar2.s(str2) == null) {
                        Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str2);
                    }
                }
                break;
            default:
                ActivityResult activityResult2 = (ActivityResult) obj;
                c cVar3 = this.c;
                FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo3 = (FragmentManager$LaunchedFragmentInfo) cVar3.x.pollFirst();
                if (fragmentManager$LaunchedFragmentInfo3 == null) {
                    Log.w("FragmentManager", "No Activities were started for result for " + this);
                } else {
                    po poVar3 = cVar3.c;
                    String str3 = fragmentManager$LaunchedFragmentInfo3.b;
                    jt jtVarS2 = poVar3.s(str3);
                    if (jtVarS2 == null) {
                        Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str3);
                    } else {
                        jtVarS2.j(fragmentManager$LaunchedFragmentInfo3.c, activityResult2.b, activityResult2.c);
                    }
                }
                break;
        }
    }
}
