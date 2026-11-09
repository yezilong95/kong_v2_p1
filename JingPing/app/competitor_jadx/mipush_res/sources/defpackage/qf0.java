package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$id;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.c;
import androidx.constraintlayout.widget.d;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.Status;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class qf0 {
    public final MotionLayout a;
    public final j4 b;
    public pf0 c;
    public final ArrayList d;
    public final pf0 e;
    public final ArrayList f;
    public final SparseArray g;
    public final HashMap h;
    public final SparseIntArray i;
    public int j;
    public int k;
    public MotionEvent l;
    public boolean m;
    public p50 n;
    public boolean o;
    public float p;
    public float q;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public qf0(Context context, MotionLayout motionLayout, int i) {
        int eventType;
        pf0 pf0Var = null;
        this.b = null;
        this.c = null;
        ArrayList arrayList = new ArrayList();
        this.d = arrayList;
        this.e = null;
        this.f = new ArrayList();
        this.g = new SparseArray();
        this.h = new HashMap();
        this.i = new SparseIntArray();
        this.j = Status.BAD_REQUEST_CODE;
        this.k = 0;
        this.m = false;
        this.a = motionLayout;
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            eventType = xml.getEventType();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
        while (true) {
            byte b = 1;
            if (eventType != 1) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    switch (name.hashCode()) {
                        case -1349929691:
                            b = !name.equals("ConstraintSet") ? (byte) -1 : (byte) 5;
                            break;
                        case -1239391468:
                            b = !name.equals("KeyFrameSet") ? (byte) -1 : (byte) 6;
                            break;
                        case 269306229:
                            if (!name.equals("Transition")) {
                                b = -1;
                            }
                            break;
                        case 312750793:
                            b = !name.equals("OnClick") ? (byte) -1 : (byte) 3;
                            break;
                        case 327855227:
                            b = !name.equals("OnSwipe") ? (byte) -1 : (byte) 2;
                            break;
                        case 793277014:
                            b = !name.equals("MotionScene") ? (byte) -1 : (byte) 0;
                            break;
                        case 1382829617:
                            b = !name.equals("StateSet") ? (byte) -1 : (byte) 4;
                            break;
                        default:
                            b = -1;
                            break;
                    }
                    switch (b) {
                        case 0:
                            i(context, xml);
                            break;
                        case 1:
                            pf0Var = new pf0(this, context, xml);
                            arrayList.add(pf0Var);
                            if (this.c == null && !pf0Var.b) {
                                this.c = pf0Var;
                                l11 l11Var = pf0Var.l;
                                if (l11Var != null) {
                                    l11Var.b(this.o);
                                }
                            }
                            if (pf0Var.b) {
                                if (pf0Var.c == -1) {
                                    this.e = pf0Var;
                                } else {
                                    this.f.add(pf0Var);
                                }
                                arrayList.remove(pf0Var);
                            }
                            break;
                        case 2:
                            if (pf0Var == null) {
                                Log.v("MotionScene", " OnSwipe (" + context.getResources().getResourceEntryName(i) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            pf0Var.l = new l11(context, this.a, xml);
                            break;
                        case 3:
                            pf0Var.getClass();
                            pf0Var.m.add(new of0(context, pf0Var, xml));
                            break;
                        case 4:
                            this.b = new j4(context, xml);
                            break;
                        case 5:
                            h(context, xml);
                            break;
                        case 6:
                            pf0Var.k.add(new p70(xml));
                            break;
                        default:
                            Log.v("MotionScene", "WARNING UNKNOWN ATTRIBUTE " + name);
                            break;
                    }
                }
                eventType = xml.next();
            }
            this.g.put(R$id.motion_base, new d());
            this.h.put("motion_base", Integer.valueOf(R$id.motion_base));
            return;
        }
    }

    public final boolean a(MotionLayout motionLayout, int i) {
        if (this.n != null) {
            return false;
        }
        for (pf0 pf0Var : this.d) {
            int i2 = pf0Var.n;
            if (i2 != 0) {
                int i3 = pf0Var.d;
                lf0 lf0Var = lf0.H;
                lf0 lf0Var2 = lf0.G;
                lf0 lf0Var3 = lf0.F;
                if (i == i3 && (i2 == 4 || i2 == 2)) {
                    motionLayout.setState(lf0Var);
                    motionLayout.setTransition(pf0Var);
                    if (pf0Var.n == 4) {
                        motionLayout.o(1.0f);
                        motionLayout.setState(lf0Var3);
                        motionLayout.setState(lf0Var2);
                        return true;
                    }
                    motionLayout.setProgress(1.0f);
                    motionLayout.p(true);
                    motionLayout.setState(lf0Var3);
                    motionLayout.setState(lf0Var2);
                    motionLayout.setState(lf0Var);
                    return true;
                }
                if (i == pf0Var.c && (i2 == 3 || i2 == 1)) {
                    motionLayout.setState(lf0Var);
                    motionLayout.setTransition(pf0Var);
                    if (pf0Var.n == 3) {
                        motionLayout.o(0.0f);
                        motionLayout.setState(lf0Var3);
                        motionLayout.setState(lf0Var2);
                        return true;
                    }
                    motionLayout.setProgress(0.0f);
                    motionLayout.p(true);
                    motionLayout.setState(lf0Var3);
                    motionLayout.setState(lf0Var2);
                    motionLayout.setState(lf0Var);
                    return true;
                }
            }
        }
        return false;
    }

    public final d b(int i) {
        int iC;
        j4 j4Var = this.b;
        if (j4Var != null && (iC = j4Var.c(i)) != -1) {
            i = iC;
        }
        SparseArray sparseArray = this.g;
        if (sparseArray.get(i) != null) {
            return (d) sparseArray.get(i);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + t40.f0(this.a.getContext(), i) + " In MotionScene");
        return (d) sparseArray.get(sparseArray.keyAt(0));
    }

    public final int c(Context context, String str) {
        int identifier;
        if (str.contains("/")) {
            identifier = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), ApiConstants.ID, context.getPackageName());
        } else {
            identifier = -1;
        }
        if (identifier == -1) {
            if (str.length() > 1) {
                return Integer.parseInt(str.substring(1));
            }
            Log.e("MotionScene", "error in parsing id");
        }
        return identifier;
    }

    public final Interpolator d() {
        pf0 pf0Var = this.c;
        int i = pf0Var.e;
        if (i == -2) {
            return AnimationUtils.loadInterpolator(this.a.getContext(), this.c.g);
        }
        if (i == -1) {
            return new nf0(vr.d(pf0Var.f));
        }
        if (i == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i == 1) {
            return new AccelerateInterpolator();
        }
        if (i == 2) {
            return new DecelerateInterpolator();
        }
        if (i == 4) {
            return new AnticipateInterpolator();
        }
        if (i != 5) {
            return null;
        }
        return new BounceInterpolator();
    }

    public final void e(ef0 ef0Var) {
        pf0 pf0Var = this.c;
        if (pf0Var != null) {
            Iterator it = pf0Var.k.iterator();
            while (it.hasNext()) {
                ((p70) it.next()).a(ef0Var);
            }
        } else {
            pf0 pf0Var2 = this.e;
            if (pf0Var2 != null) {
                Iterator it2 = pf0Var2.k.iterator();
                while (it2.hasNext()) {
                    ((p70) it2.next()).a(ef0Var);
                }
            }
        }
    }

    public final float f() {
        l11 l11Var;
        pf0 pf0Var = this.c;
        if (pf0Var == null || (l11Var = pf0Var.l) == null) {
            return 0.0f;
        }
        return l11Var.q;
    }

    public final int g() {
        pf0 pf0Var = this.c;
        if (pf0Var == null) {
            return -1;
        }
        return pf0Var.d;
    }

    public final void h(Context context, XmlResourceParser xmlResourceParser) {
        d dVar = new d();
        dVar.b = false;
        int attributeCount = xmlResourceParser.getAttributeCount();
        int iC = -1;
        int iC2 = -1;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlResourceParser.getAttributeName(i);
            String attributeValue = xmlResourceParser.getAttributeValue(i);
            attributeName.getClass();
            if (attributeName.equals("deriveConstraintsFrom")) {
                iC2 = c(context, attributeValue);
            } else if (attributeName.equals(ApiConstants.ID)) {
                iC = c(context, attributeValue);
                int iIndexOf = attributeValue.indexOf(47);
                if (iIndexOf >= 0) {
                    attributeValue = attributeValue.substring(iIndexOf + 1);
                }
                this.h.put(attributeValue, Integer.valueOf(iC));
            }
        }
        if (iC != -1) {
            int i2 = this.a.q0;
            dVar.i(context, xmlResourceParser);
            if (iC2 != -1) {
                this.i.put(iC, iC2);
            }
            this.g.put(iC, dVar);
        }
    }

    public final void i(Context context, XmlResourceParser xmlResourceParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.MotionScene);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R$styleable.MotionScene_defaultDuration) {
                this.j = typedArrayObtainStyledAttributes.getInt(index, this.j);
            } else if (index == R$styleable.MotionScene_layoutDuringTransition) {
                this.k = typedArrayObtainStyledAttributes.getInteger(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void j(int i) {
        SparseIntArray sparseIntArray = this.i;
        int i2 = sparseIntArray.get(i);
        if (i2 > 0) {
            j(sparseIntArray.get(i));
            SparseArray sparseArray = this.g;
            d dVar = (d) sparseArray.get(i);
            d dVar2 = (d) sparseArray.get(i2);
            if (dVar2 == null) {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + t40.f0(this.a.getContext(), i2));
                return;
            }
            dVar.getClass();
            HashMap map = dVar2.c;
            for (Integer num : map.keySet()) {
                num.getClass();
                c cVar = (c) map.get(num);
                HashMap map2 = dVar.c;
                if (!map2.containsKey(num)) {
                    map2.put(num, new c());
                }
                c cVar2 = (c) map2.get(num);
                th thVar = cVar2.d;
                if (!thVar.b) {
                    thVar.a(cVar.d);
                }
                vh vhVar = cVar2.b;
                if (!vhVar.a) {
                    vh vhVar2 = cVar.b;
                    vhVar.a = vhVar2.a;
                    vhVar.b = vhVar2.b;
                    vhVar.d = vhVar2.d;
                    vhVar.e = vhVar2.e;
                    vhVar.c = vhVar2.c;
                }
                wh whVar = cVar2.e;
                if (!whVar.a) {
                    whVar.a(cVar.e);
                }
                uh uhVar = cVar2.c;
                if (!uhVar.a) {
                    uhVar.a(cVar.c);
                }
                for (String str : cVar.f.keySet()) {
                    if (!cVar2.f.containsKey(str)) {
                        cVar2.f.put(str, cVar.f.get(str));
                    }
                }
            }
            sparseIntArray.put(i, -1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(int r9, int r10) {
        /*
            r8 = this;
            j4 r0 = r8.b
            r1 = -1
            if (r0 == 0) goto L18
            int r0 = r0.c(r9)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r9
        Ld:
            j4 r2 = r8.b
            int r2 = r2.c(r10)
            if (r2 == r1) goto L16
            goto L1a
        L16:
            r2 = r10
            goto L1a
        L18:
            r0 = r9
            goto L16
        L1a:
            java.util.ArrayList r3 = r8.d
            java.util.Iterator r4 = r3.iterator()
        L20:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L46
            java.lang.Object r5 = r4.next()
            pf0 r5 = (defpackage.pf0) r5
            int r6 = r5.c
            if (r6 != r2) goto L34
            int r7 = r5.d
            if (r7 == r0) goto L3a
        L34:
            if (r6 != r10) goto L20
            int r6 = r5.d
            if (r6 != r9) goto L20
        L3a:
            r8.c = r5
            l11 r9 = r5.l
            if (r9 == 0) goto L45
            boolean r10 = r8.o
            r9.b(r10)
        L45:
            return
        L46:
            pf0 r9 = r8.e
            java.util.ArrayList r4 = r8.f
            java.util.Iterator r4 = r4.iterator()
        L4e:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L60
            java.lang.Object r5 = r4.next()
            pf0 r5 = (defpackage.pf0) r5
            int r6 = r5.c
            if (r6 != r10) goto L4e
            r9 = r5
            goto L4e
        L60:
            pf0 r10 = new pf0
            r10.<init>(r8, r9)
            r10.d = r0
            r10.c = r2
            if (r0 == r1) goto L6e
            r3.add(r10)
        L6e:
            r8.c = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qf0.k(int, int):void");
    }

    public final boolean l() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            if (((pf0) it.next()).l != null) {
                return true;
            }
        }
        pf0 pf0Var = this.c;
        return (pf0Var == null || pf0Var.l == null) ? false : true;
    }
}
