package defpackage;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.widget.d;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class gg {
    public int a;
    public int b;
    public Object c;
    public final Object d;
    public final Cloneable e;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public gg(android.content.Context r8, androidx.constraintlayout.widget.ConstraintLayout r9, int r10) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gg.<init>(android.content.Context, androidx.constraintlayout.widget.ConstraintLayout, int):void");
    }

    public void a(xu0 xu0Var, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if ((((ju0) it.next()).a.c() & 8) != 0) {
                ((View) this.d).setTranslationY(t3.c(this.b, r3.a.b(), 0));
                return;
            }
        }
    }

    public void b(Context context, XmlResourceParser xmlResourceParser) {
        d dVar = new d();
        int attributeCount = xmlResourceParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if ("id".equals(xmlResourceParser.getAttributeName(i))) {
                String attributeValue = xmlResourceParser.getAttributeValue(i);
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue.length() > 1) {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                dVar.i(context, xmlResourceParser);
                ((SparseArray) this.e).put(identifier, dVar);
                return;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [int[], java.lang.Cloneable] */
    public gg(View view) {
        this.e = new int[2];
        this.d = view;
    }
}
