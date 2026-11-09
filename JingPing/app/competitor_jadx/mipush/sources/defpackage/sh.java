package defpackage;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.widget.d;
import io.nats.client.support.ApiConstants;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class sh {
    public int a;
    public int b;
    public Object c;
    public final Object d;
    public final Object e;

    public sh(byte[] bArr, byte[] bArr2, po poVar) {
        this.c = bArr;
        this.d = bArr2;
        this.e = poVar;
    }

    public void a(int i, boolean z, byte[] bArr) {
        int length = bArr.length - i;
        po poVar = (po) this.e;
        if (length < poVar.c()) {
            throw new IllegalArgumentException("target length is less than digest size.");
        }
        byte[] bArr2 = (byte[]) this.c;
        poVar.e(bArr2, 0, bArr2.length);
        poVar.f((byte) (this.a >>> 24));
        poVar.f((byte) (this.a >>> 16));
        poVar.f((byte) (this.a >>> 8));
        poVar.f((byte) this.a);
        poVar.f((byte) (this.b >>> 8));
        poVar.f((byte) this.b);
        poVar.f((byte) -1);
        byte[] bArr3 = (byte[]) this.d;
        poVar.e(bArr3, 0, bArr3.length);
        poVar.d(i, bArr);
        if (z) {
            this.b++;
        }
    }

    public void b(s81 s81Var, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if ((((e81) it.next()).a.c() & 8) != 0) {
                ((View) this.d).setTranslationY(y4.c(this.b, r3.a.b(), 0));
                return;
            }
        }
    }

    public void c(Context context, XmlResourceParser xmlResourceParser) {
        d dVar = new d();
        int attributeCount = xmlResourceParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (ApiConstants.ID.equals(xmlResourceParser.getAttributeName(i))) {
                String attributeValue = xmlResourceParser.getAttributeValue(i);
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), ApiConstants.ID, context.getPackageName()) : -1;
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

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public sh(android.content.Context r8, androidx.constraintlayout.widget.ConstraintLayout r9, int r10) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sh.<init>(android.content.Context, androidx.constraintlayout.widget.ConstraintLayout, int):void");
    }

    public sh(View view) {
        this.e = new int[2];
        this.d = view;
    }
}
