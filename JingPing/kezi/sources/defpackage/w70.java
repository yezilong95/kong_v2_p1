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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class w70 {
    public final MotionLayout a;
    public final w2 b;
    public v70 c;
    public final ArrayList d;
    public final v70 e;
    public final ArrayList f;
    public final SparseArray g;
    public final HashMap h;
    public final SparseIntArray i;
    public int j;
    public int k;
    public MotionEvent l;
    public boolean m;
    public o70 n;
    public boolean o;
    public float p;
    public float q;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public w70(Context context, MotionLayout motionLayout, int i) {
        int eventType;
        v70 v70Var = null;
        this.b = null;
        this.c = null;
        ArrayList arrayList = new ArrayList();
        this.d = arrayList;
        this.e = null;
        this.f = new ArrayList();
        this.g = new SparseArray();
        this.h = new HashMap();
        this.i = new SparseIntArray();
        this.j = 400;
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
                            v70Var = new v70(this, context, xml);
                            arrayList.add(v70Var);
                            if (this.c == null && !v70Var.b) {
                                this.c = v70Var;
                                zo0 zo0Var = v70Var.l;
                                if (zo0Var != null) {
                                    zo0Var.b(this.o);
                                }
                            }
                            if (v70Var.b) {
                                if (v70Var.c == -1) {
                                    this.e = v70Var;
                                } else {
                                    this.f.add(v70Var);
                                }
                                arrayList.remove(v70Var);
                            }
                            break;
                        case 2:
                            if (v70Var == null) {
                                Log.v("MotionScene", " OnSwipe (" + context.getResources().getResourceEntryName(i) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            v70Var.l = new zo0(context, this.a, xml);
                            break;
                        case 3:
                            v70Var.getClass();
                            v70Var.m.add(new u70(context, v70Var, xml));
                            break;
                        case 4:
                            this.b = new w2(context, xml);
                            break;
                        case 5:
                            h(context, xml);
                            break;
                        case 6:
                            v70Var.k.add(new y00(xml));
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
        for (v70 v70Var : this.d) {
            int i2 = v70Var.n;
            if (i2 != 0) {
                int i3 = v70Var.d;
                r70 r70Var = r70.e;
                r70 r70Var2 = r70.d;
                r70 r70Var3 = r70.c;
                if (i == i3 && (i2 == 4 || i2 == 2)) {
                    motionLayout.setState(r70Var);
                    motionLayout.setTransition(v70Var);
                    if (v70Var.n == 4) {
                        motionLayout.o(1.0f);
                        motionLayout.setState(r70Var3);
                        motionLayout.setState(r70Var2);
                        return true;
                    }
                    motionLayout.setProgress(1.0f);
                    motionLayout.p(true);
                    motionLayout.setState(r70Var3);
                    motionLayout.setState(r70Var2);
                    motionLayout.setState(r70Var);
                    return true;
                }
                if (i == v70Var.c && (i2 == 3 || i2 == 1)) {
                    motionLayout.setState(r70Var);
                    motionLayout.setTransition(v70Var);
                    if (v70Var.n == 3) {
                        motionLayout.o(0.0f);
                        motionLayout.setState(r70Var3);
                        motionLayout.setState(r70Var2);
                        return true;
                    }
                    motionLayout.setProgress(0.0f);
                    motionLayout.p(true);
                    motionLayout.setState(r70Var3);
                    motionLayout.setState(r70Var2);
                    motionLayout.setState(r70Var);
                    return true;
                }
            }
        }
        return false;
    }

    public final d b(int i) {
        int iD;
        w2 w2Var = this.b;
        if (w2Var != null && (iD = w2Var.d(i)) != -1) {
            i = iD;
        }
        SparseArray sparseArray = this.g;
        if (sparseArray.get(i) != null) {
            return (d) sparseArray.get(i);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + oy.w(this.a.getContext(), i) + " In MotionScene");
        return (d) sparseArray.get(sparseArray.keyAt(0));
    }

    public final int c(Context context, String str) {
        int identifier;
        if (str.contains("/")) {
            identifier = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
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
        v70 v70Var = this.c;
        int i = v70Var.e;
        if (i == -2) {
            return AnimationUtils.loadInterpolator(this.a.getContext(), this.c.g);
        }
        if (i == -1) {
            return new t70(nm.c(v70Var.f));
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

    public final void e(j70 j70Var) {
        v70 v70Var = this.c;
        if (v70Var != null) {
            Iterator it = v70Var.k.iterator();
            while (it.hasNext()) {
                ((y00) it.next()).a(j70Var);
            }
        } else {
            v70 v70Var2 = this.e;
            if (v70Var2 != null) {
                Iterator it2 = v70Var2.k.iterator();
                while (it2.hasNext()) {
                    ((y00) it2.next()).a(j70Var);
                }
            }
        }
    }

    public final float f() {
        zo0 zo0Var;
        v70 v70Var = this.c;
        if (v70Var == null || (zo0Var = v70Var.l) == null) {
            return 0.0f;
        }
        return zo0Var.q;
    }

    public final int g() {
        v70 v70Var = this.c;
        if (v70Var == null) {
            return -1;
        }
        return v70Var.d;
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
            } else if (attributeName.equals("id")) {
                iC = c(context, attributeValue);
                int iIndexOf = attributeValue.indexOf(47);
                if (iIndexOf >= 0) {
                    attributeValue = attributeValue.substring(iIndexOf + 1);
                }
                this.h.put(attributeValue, Integer.valueOf(iC));
            }
        }
        if (iC != -1) {
            int i2 = this.a.K;
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
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + oy.w(this.a.getContext(), i2));
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
                hg hgVar = cVar2.d;
                if (!hgVar.b) {
                    hgVar.a(cVar.d);
                }
                jg jgVar = cVar2.b;
                if (!jgVar.a) {
                    jg jgVar2 = cVar.b;
                    jgVar.a = jgVar2.a;
                    jgVar.b = jgVar2.b;
                    jgVar.d = jgVar2.d;
                    jgVar.e = jgVar2.e;
                    jgVar.c = jgVar2.c;
                }
                kg kgVar = cVar2.e;
                if (!kgVar.a) {
                    kgVar.a(cVar.e);
                }
                ig igVar = cVar2.c;
                if (!igVar.a) {
                    igVar.a(cVar.c);
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k(int r9, int r10) {
        /*
            r8 = this;
            w2 r0 = r8.b
            r1 = -1
            if (r0 == 0) goto L18
            int r0 = r0.d(r9)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r9
        Ld:
            w2 r2 = r8.b
            int r2 = r2.d(r10)
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
            v70 r5 = (defpackage.v70) r5
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
            zo0 r9 = r5.l
            if (r9 == 0) goto L45
            boolean r10 = r8.o
            r9.b(r10)
        L45:
            return
        L46:
            v70 r9 = r8.e
            java.util.ArrayList r4 = r8.f
            java.util.Iterator r4 = r4.iterator()
        L4e:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L60
            java.lang.Object r5 = r4.next()
            v70 r5 = (defpackage.v70) r5
            int r6 = r5.c
            if (r6 != r10) goto L4e
            r9 = r5
            goto L4e
        L60:
            v70 r10 = new v70
            r10.<init>(r8, r9)
            r10.d = r0
            r10.c = r2
            if (r0 == r1) goto L6e
            r3.add(r10)
        L6e:
            r8.c = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w70.k(int, int):void");
    }

    public final boolean l() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            if (((v70) it.next()).l != null) {
                return true;
            }
        }
        v70 v70Var = this.c;
        return (v70Var == null || v70Var.l == null) ? false : true;
    }
}
