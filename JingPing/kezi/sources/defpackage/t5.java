package defpackage;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class t5 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ int c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ t5(ze zeVar, int i, Object obj, int i2) {
        this.b = i2;
        this.e = zeVar;
        this.c = i;
        this.d = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Object obj = this.e;
        Object obj2 = this.d;
        switch (this.b) {
            case 0:
                ((TextView) obj2).setTypeface((Typeface) obj, i);
                break;
            case 1:
                int i2 = BottomSheetBehavior.h0;
                ((BottomSheetBehavior) obj).L((View) obj2, i, false);
                break;
            case 2:
                Object obj3 = ((g2) obj2).a;
                ze zeVar = (ze) obj;
                String str = (String) zeVar.a.get(Integer.valueOf(i));
                if (str != null) {
                    l2 l2Var = (l2) zeVar.e.get(str);
                    if (l2Var == null) {
                        zeVar.g.remove(str);
                        zeVar.f.put(str, obj3);
                    } else {
                        f2 f2Var = l2Var.a;
                        if (zeVar.d.remove(str)) {
                            f2Var.b(obj3);
                        }
                    }
                    break;
                }
                break;
            default:
                ((ze) obj).a(i, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", (IntentSender.SendIntentException) obj2));
                break;
        }
    }

    public t5(TextView textView, Typeface typeface, int i) {
        this.b = 0;
        this.d = textView;
        this.e = typeface;
        this.c = i;
    }

    public t5(BottomSheetBehavior bottomSheetBehavior, View view, int i) {
        this.b = 1;
        this.e = bottomSheetBehavior;
        this.d = view;
        this.c = i;
    }
}
