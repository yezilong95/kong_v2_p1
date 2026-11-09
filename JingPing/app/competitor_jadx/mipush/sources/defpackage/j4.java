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
public final class j4 implements u2 {
    public final int E;
    public final Object F;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public j4(android.content.Context r10, android.content.res.XmlResourceParser r11) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.j4.<init>(android.content.Context, android.content.res.XmlResourceParser):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1, types: [android.widget.ListAdapter] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    public k4 a() {
        f4 f4Var = (f4) this.F;
        k4 k4Var = new k4(f4Var.a, this.E);
        View view = f4Var.e;
        i4 i4Var = k4Var.J;
        if (view != null) {
            i4Var.n = view;
        } else {
            CharSequence charSequence = f4Var.d;
            if (charSequence != null) {
                i4Var.d = charSequence;
                TextView textView = i4Var.l;
                if (textView != null) {
                    textView.setText(charSequence);
                }
            }
            Drawable drawable = f4Var.c;
            if (drawable != null) {
                i4Var.j = drawable;
                ImageView imageView = i4Var.k;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    i4Var.k.setImageDrawable(drawable);
                }
            }
        }
        if (f4Var.g != null) {
            AlertController$RecycleListView alertController$RecycleListView = (AlertController$RecycleListView) f4Var.b.inflate(i4Var.r, (ViewGroup) null);
            int i = f4Var.i ? i4Var.s : i4Var.t;
            Object obj = f4Var.g;
            ?? h4Var = obj;
            if (obj == null) {
                h4Var = new h4(f4Var.a, i, R.id.text1, null);
            }
            i4Var.o = h4Var;
            i4Var.p = f4Var.j;
            if (f4Var.h != null) {
                alertController$RecycleListView.setOnItemClickListener(new e4(f4Var, i4Var));
            }
            if (f4Var.i) {
                alertController$RecycleListView.setChoiceMode(1);
            }
            i4Var.e = alertController$RecycleListView;
        }
        k4Var.setCancelable(true);
        k4Var.setCanceledOnTouchOutside(true);
        k4Var.setOnCancelListener(null);
        k4Var.setOnDismissListener(null);
        ae0 ae0Var = f4Var.f;
        if (ae0Var != null) {
            k4Var.setOnKeyListener(ae0Var);
        }
        return k4Var;
    }

    @Override // defpackage.u2
    public boolean b(View view) {
        ((BottomSheetBehavior) this.F).I(this.E);
        return true;
    }

    public int c(int i) {
        float f = -1;
        SparseArray sparseArray = (SparseArray) this.F;
        int i2 = 0;
        if (-1 == i) {
            ex0 ex0Var = i == -1 ? (ex0) sparseArray.valueAt(0) : (ex0) sparseArray.get(-1);
            if (ex0Var != null) {
                while (true) {
                    ArrayList arrayList = ex0Var.b;
                    if (i2 >= arrayList.size()) {
                        i2 = -1;
                        break;
                    }
                    if (((fx0) arrayList.get(i2)).a(f, f)) {
                        break;
                    }
                    i2++;
                }
                if (-1 != i2) {
                    return i2 == -1 ? ex0Var.c : ((fx0) ex0Var.b.get(i2)).e;
                }
            }
        } else {
            ex0 ex0Var2 = (ex0) sparseArray.get(i);
            if (ex0Var2 != null) {
                while (true) {
                    ArrayList arrayList2 = ex0Var2.b;
                    if (i2 >= arrayList2.size()) {
                        i2 = -1;
                        break;
                    }
                    if (((fx0) arrayList2.get(i2)).a(f, f)) {
                        break;
                    }
                    i2++;
                }
                return i2 == -1 ? ex0Var2.c : ((fx0) ex0Var2.b.get(i2)).e;
            }
        }
        return -1;
    }

    public j4(Context context) {
        int iG = k4.g(context, 0);
        this.F = new f4(new ContextThemeWrapper(context, k4.g(context, iG)));
        this.E = iG;
    }

    public j4(int i, oz[] ozVarArr) {
        this.E = i;
        this.F = ozVarArr;
    }

    public j4(BottomSheetBehavior bottomSheetBehavior, int i) {
        this.F = bottomSheetBehavior;
        this.E = i;
    }
}
