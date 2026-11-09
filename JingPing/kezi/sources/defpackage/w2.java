package defpackage;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class w2 implements y0 {
    public int b;
    public final Object c;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public w2(android.content.Context r10, android.content.res.XmlResourceParser r11) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w2.<init>(android.content.Context, android.content.res.XmlResourceParser):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1, types: [android.widget.ListAdapter] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    public x2 a() {
        s2 s2Var = (s2) this.c;
        x2 x2Var = new x2(s2Var.a, this.b);
        View view = s2Var.e;
        v2 v2Var = x2Var.g;
        if (view != null) {
            v2Var.n = view;
        } else {
            CharSequence charSequence = s2Var.d;
            if (charSequence != null) {
                v2Var.d = charSequence;
                TextView textView = v2Var.l;
                if (textView != null) {
                    textView.setText(charSequence);
                }
            }
            Drawable drawable = s2Var.c;
            if (drawable != null) {
                v2Var.j = drawable;
                ImageView imageView = v2Var.k;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    v2Var.k.setImageDrawable(drawable);
                }
            }
        }
        if (s2Var.g != null) {
            AlertController$RecycleListView alertController$RecycleListView = (AlertController$RecycleListView) s2Var.b.inflate(v2Var.r, (ViewGroup) null);
            int i = s2Var.i ? v2Var.s : v2Var.t;
            Object obj = s2Var.g;
            ?? u2Var = obj;
            if (obj == null) {
                u2Var = new u2(s2Var.a, i, R.id.text1, null);
            }
            v2Var.o = u2Var;
            v2Var.p = s2Var.j;
            if (s2Var.h != null) {
                alertController$RecycleListView.setOnItemClickListener(new r2(s2Var, v2Var));
            }
            if (s2Var.i) {
                alertController$RecycleListView.setChoiceMode(1);
            }
            v2Var.e = alertController$RecycleListView;
        }
        x2Var.setCancelable(true);
        x2Var.setCanceledOnTouchOutside(true);
        x2Var.setOnCancelListener(null);
        x2Var.setOnDismissListener(null);
        j60 j60Var = s2Var.f;
        if (j60Var != null) {
            x2Var.setOnKeyListener(j60Var);
        }
        return x2Var;
    }

    public boolean b() {
        return this.b < ((ArrayList) this.c).size();
    }

    @Override // defpackage.y0
    public boolean c(View view) {
        ((BottomSheetBehavior) this.c).I(this.b);
        return true;
    }

    public int d(int i) {
        float f = -1;
        SparseArray sparseArray = (SparseArray) this.c;
        int i2 = 0;
        if (-1 == i) {
            qk0 qk0Var = i == -1 ? (qk0) sparseArray.valueAt(0) : (qk0) sparseArray.get(-1);
            if (qk0Var != null) {
                while (true) {
                    ArrayList arrayList = qk0Var.b;
                    if (i2 >= arrayList.size()) {
                        i2 = -1;
                        break;
                    }
                    if (((rk0) arrayList.get(i2)).a(f, f)) {
                        break;
                    }
                    i2++;
                }
                if (-1 != i2) {
                    return i2 == -1 ? qk0Var.c : ((rk0) qk0Var.b.get(i2)).e;
                }
            }
        } else {
            qk0 qk0Var2 = (qk0) sparseArray.get(i);
            if (qk0Var2 != null) {
                while (true) {
                    ArrayList arrayList2 = qk0Var2.b;
                    if (i2 >= arrayList2.size()) {
                        i2 = -1;
                        break;
                    }
                    if (((rk0) arrayList2.get(i2)).a(f, f)) {
                        break;
                    }
                    i2++;
                }
                return i2 == -1 ? qk0Var2.c : ((rk0) qk0Var2.b.get(i2)).e;
            }
        }
        return -1;
    }

    public w2(ArrayList arrayList) {
        this.c = arrayList;
    }

    public w2(Context context) {
        int iG = x2.g(context, 0);
        this.c = new s2(new ContextThemeWrapper(context, x2.g(context, iG)));
        this.b = iG;
    }

    public w2(int i, at[] atVarArr) {
        this.b = i;
        this.c = atVarArr;
    }

    public w2(BottomSheetBehavior bottomSheetBehavior, int i) {
        this.c = bottomSheetBehavior;
        this.b = i;
    }
}
