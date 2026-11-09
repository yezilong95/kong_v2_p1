package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R$styleable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.c;
import androidx.fragment.app.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class zz implements LayoutInflater.Factory2 {
    public final c E;

    public zz(c cVar) {
        this.E = cVar;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zIsAssignableFrom;
        d dVarF;
        View view2;
        boolean zEquals = FragmentContainerView.class.getName().equals(str);
        c cVar = this.E;
        if (zEquals) {
            FragmentContainerView fragmentContainerView = new FragmentContainerView(context, attributeSet);
            fragmentContainerView.H = true;
            String classAttribute = attributeSet.getClassAttribute();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FragmentContainerView);
            if (classAttribute == null) {
                classAttribute = typedArrayObtainStyledAttributes.getString(R$styleable.FragmentContainerView_android_name);
            }
            String string = typedArrayObtainStyledAttributes.getString(R$styleable.FragmentContainerView_android_tag);
            typedArrayObtainStyledAttributes.recycle();
            int id = fragmentContainerView.getId();
            vz vzVarW = cVar.w(id);
            if (classAttribute != null && vzVarW == null) {
                if (id <= 0) {
                    throw new IllegalStateException(zn.n("FragmentContainerView must have an android:id to add Fragment ", classAttribute, string != null ? " with tag ".concat(string) : ""));
                }
                b00 b00VarY = cVar.y();
                context.getClassLoader();
                vz vzVarA = b00VarY.a(classAttribute);
                vzVarA.g0 = true;
                wz wzVar = vzVarA.W;
                if ((wzVar != null ? wzVar.b0 : null) != null) {
                    vzVarA.g0 = true;
                }
                m9 m9Var = new m9(cVar);
                m9Var.o = true;
                vzVarA.h0 = fragmentContainerView;
                m9Var.e(fragmentContainerView.getId(), vzVarA, string, 1);
                if (m9Var.g) {
                    throw new IllegalStateException("This transaction is already being added to the back stack");
                }
                m9Var.p.u(m9Var, true);
            }
            for (d dVar : cVar.c.v()) {
                vz vzVar = dVar.c;
                if (vzVar.a0 == fragmentContainerView.getId() && (view2 = vzVar.i0) != null && view2.getParent() == null) {
                    vzVar.h0 = fragmentContainerView;
                    dVar.b();
                }
            }
            return fragmentContainerView;
        }
        if ("fragment".equals(str)) {
            String attributeValue = attributeSet.getAttributeValue(null, "class");
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.Fragment);
            if (attributeValue == null) {
                attributeValue = typedArrayObtainStyledAttributes2.getString(R$styleable.Fragment_android_name);
            }
            int resourceId = typedArrayObtainStyledAttributes2.getResourceId(R$styleable.Fragment_android_id, -1);
            String string2 = typedArrayObtainStyledAttributes2.getString(R$styleable.Fragment_android_tag);
            typedArrayObtainStyledAttributes2.recycle();
            if (attributeValue != null) {
                try {
                    zIsAssignableFrom = vz.class.isAssignableFrom(b00.b(context.getClassLoader(), attributeValue));
                } catch (ClassNotFoundException unused) {
                    zIsAssignableFrom = false;
                }
                if (zIsAssignableFrom) {
                    int id2 = view != null ? view.getId() : 0;
                    if (id2 == -1 && resourceId == -1 && string2 == null) {
                        throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
                    }
                    vz vzVarW2 = resourceId != -1 ? cVar.w(resourceId) : null;
                    if (vzVarW2 == null && string2 != null) {
                        ou ouVar = cVar.c;
                        ArrayList arrayList = (ArrayList) ouVar.F;
                        int size = arrayList.size() - 1;
                        while (true) {
                            if (size >= 0) {
                                vz vzVar2 = (vz) arrayList.get(size);
                                if (vzVar2 != null && string2.equals(vzVar2.b0)) {
                                    vzVarW2 = vzVar2;
                                    break;
                                }
                                size--;
                            } else {
                                Iterator it = ((HashMap) ouVar.G).values().iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        vzVarW2 = null;
                                        break;
                                    }
                                    d dVar2 = (d) it.next();
                                    if (dVar2 != null) {
                                        vz vzVar3 = dVar2.c;
                                        if (string2.equals(vzVar3.b0)) {
                                            vzVarW2 = vzVar3;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (vzVarW2 == null && id2 != -1) {
                        vzVarW2 = cVar.w(id2);
                    }
                    if (vzVarW2 == null) {
                        b00 b00VarY2 = cVar.y();
                        context.getClassLoader();
                        vzVarW2 = b00VarY2.a(attributeValue);
                        vzVarW2.Q = true;
                        vzVarW2.Z = resourceId != 0 ? resourceId : id2;
                        vzVarW2.a0 = id2;
                        vzVarW2.b0 = string2;
                        vzVarW2.R = true;
                        vzVarW2.V = cVar;
                        wz wzVar2 = cVar.o;
                        vzVarW2.W = wzVar2;
                        FragmentActivity fragmentActivity = wzVar2.c0;
                        vzVarW2.g0 = true;
                        if ((wzVar2 != null ? wzVar2.b0 : null) != null) {
                            vzVarW2.g0 = true;
                        }
                        dVarF = cVar.a(vzVarW2);
                        if (c.B(2)) {
                            Log.v("FragmentManager", "Fragment " + vzVarW2 + " has been inflated via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
                        }
                    } else {
                        if (vzVarW2.R) {
                            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string2 + ", or parent id 0x" + Integer.toHexString(id2) + " with another fragment for " + attributeValue);
                        }
                        vzVarW2.R = true;
                        vzVarW2.V = cVar;
                        wz wzVar3 = cVar.o;
                        vzVarW2.W = wzVar3;
                        FragmentActivity fragmentActivity2 = wzVar3.c0;
                        vzVarW2.g0 = true;
                        if ((wzVar3 != null ? wzVar3.b0 : null) != null) {
                            vzVarW2.g0 = true;
                        }
                        dVarF = cVar.f(vzVarW2);
                        if (c.B(2)) {
                            Log.v("FragmentManager", "Retained Fragment " + vzVarW2 + " has been re-attached via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
                        }
                    }
                    vzVarW2.h0 = (ViewGroup) view;
                    dVarF.k();
                    dVarF.j();
                    View view3 = vzVarW2.i0;
                    if (view3 == null) {
                        throw new IllegalStateException(zn.n("Fragment ", attributeValue, " did not create a view."));
                    }
                    if (resourceId != 0) {
                        view3.setId(resourceId);
                    }
                    if (vzVarW2.i0.getTag() == null) {
                        vzVarW2.i0.setTag(string2);
                    }
                    vzVarW2.i0.addOnAttachStateChangeListener(new yz(this, dVarF));
                    return vzVarW2.i0;
                }
            }
        }
        return null;
    }
}
