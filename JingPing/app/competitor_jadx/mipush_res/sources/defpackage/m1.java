package defpackage;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import com.mamimobi.AccessService;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m1 implements Handler.Callback {
    public final /* synthetic */ int a;

    public /* synthetic */ m1(int i) {
        this.a = i;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        LinkedList linkedList;
        switch (this.a) {
            case 0:
                AccessService accessService = AccessService.e0;
                LinkedList linkedList2 = fa0.F;
                if (linkedList2 == null) {
                    fa0.F = new LinkedList();
                } else {
                    linkedList2.clear();
                }
                LinkedList linkedList3 = fa0.F;
                String strQ0 = fa0.q0(accessService, "MotionEvents", "");
                if (strQ0.isEmpty()) {
                    linkedList = new LinkedList();
                } else {
                    LinkedList linkedList4 = new LinkedList();
                    try {
                        JSONArray jSONArray = new JSONArray(strQ0);
                        for (int i = 0; i < jSONArray.length(); i++) {
                            LinkedList linkedList5 = new LinkedList();
                            JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                try {
                                    linkedList5.offer(o80.Q(new JSONObject(jSONArray2.getString(i2))));
                                } catch (Exception unused) {
                                }
                            }
                            linkedList4.add(linkedList5);
                            break;
                        }
                    } catch (Exception unused2) {
                    }
                    linkedList = linkedList4;
                }
                linkedList3.addAll(linkedList);
                if (fa0.F.isEmpty()) {
                    AccessService.j0.set(false);
                } else {
                    LinkedList<LinkedList> linkedList6 = fa0.F;
                    uk0.d = new di(23);
                    uk0.c = new LinkedList();
                    for (LinkedList linkedList7 : linkedList6) {
                        LinkedList linkedList8 = new LinkedList();
                        Iterator it = linkedList7.iterator();
                        while (it.hasNext()) {
                            linkedList8.add(MotionEvent.obtain((MotionEvent) it.next()));
                        }
                        uk0.c.add(linkedList8);
                    }
                    uk0.a();
                }
                break;
            default:
                uk0.a();
                break;
        }
        return false;
    }
}
