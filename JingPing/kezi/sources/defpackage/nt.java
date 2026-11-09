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
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class nt implements LayoutInflater.Factory2 {
    public final c b;

    public nt(c cVar) {
        this.b = cVar;
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
        c cVar = this.b;
        if (zEquals) {
            FragmentContainerView fragmentContainerView = new FragmentContainerView(context, attributeSet);
            fragmentContainerView.e = true;
            String classAttribute = attributeSet.getClassAttribute();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FragmentContainerView);
            if (classAttribute == null) {
                classAttribute = typedArrayObtainStyledAttributes.getString(R$styleable.FragmentContainerView_android_name);
            }
            String string = typedArrayObtainStyledAttributes.getString(R$styleable.FragmentContainerView_android_tag);
            typedArrayObtainStyledAttributes.recycle();
            int id = fragmentContainerView.getId();
            jt jtVarX = cVar.x(id);
            if (classAttribute != null && jtVarX == null) {
                if (id <= 0) {
                    throw new IllegalStateException(hk.j("FragmentContainerView must have an android:id to add Fragment ", classAttribute, string != null ? " with tag ".concat(string) : ExtensionRequestData.EMPTY_VALUE));
                }
                pt ptVarZ = cVar.z();
                context.getClassLoader();
                jt jtVarA = ptVarZ.a(classAttribute);
                jtVarA.D = true;
                kt ktVar = jtVarA.t;
                if ((ktVar != null ? ktVar.t : null) != null) {
                    jtVarA.D = true;
                }
                s7 s7Var = new s7(cVar);
                s7Var.o = true;
                jtVarA.E = fragmentContainerView;
                s7Var.e(fragmentContainerView.getId(), jtVarA, string, 1);
                if (s7Var.g) {
                    throw new IllegalStateException("This transaction is already being added to the back stack");
                }
                s7Var.p.v(s7Var, true);
            }
            for (d dVar : cVar.c.t()) {
                jt jtVar = dVar.c;
                if (jtVar.x == fragmentContainerView.getId() && (view2 = jtVar.F) != null && view2.getParent() == null) {
                    jtVar.E = fragmentContainerView;
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
                    zIsAssignableFrom = jt.class.isAssignableFrom(pt.b(context.getClassLoader(), attributeValue));
                } catch (ClassNotFoundException unused) {
                    zIsAssignableFrom = false;
                }
                if (zIsAssignableFrom) {
                    int id2 = view != null ? view.getId() : 0;
                    if (id2 == -1 && resourceId == -1 && string2 == null) {
                        throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
                    }
                    jt jtVarX2 = resourceId != -1 ? cVar.x(resourceId) : null;
                    if (jtVarX2 == null && string2 != null) {
                        po poVar = cVar.c;
                        ArrayList arrayList = (ArrayList) poVar.c;
                        int size = arrayList.size() - 1;
                        while (true) {
                            if (size >= 0) {
                                jt jtVar2 = (jt) arrayList.get(size);
                                if (jtVar2 != null && string2.equals(jtVar2.y)) {
                                    jtVarX2 = jtVar2;
                                    break;
                                }
                                size--;
                            } else {
                                Iterator it = ((HashMap) poVar.d).values().iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        jtVarX2 = null;
                                        break;
                                    }
                                    d dVar2 = (d) it.next();
                                    if (dVar2 != null) {
                                        jt jtVar3 = dVar2.c;
                                        if (string2.equals(jtVar3.y)) {
                                            jtVarX2 = jtVar3;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (jtVarX2 == null && id2 != -1) {
                        jtVarX2 = cVar.x(id2);
                    }
                    if (jtVarX2 == null) {
                        pt ptVarZ2 = cVar.z();
                        context.getClassLoader();
                        jtVarX2 = ptVarZ2.a(attributeValue);
                        jtVarX2.n = true;
                        jtVarX2.w = resourceId != 0 ? resourceId : id2;
                        jtVarX2.x = id2;
                        jtVarX2.y = string2;
                        jtVarX2.o = true;
                        jtVarX2.s = cVar;
                        kt ktVar2 = cVar.o;
                        jtVarX2.t = ktVar2;
                        FragmentActivity fragmentActivity = ktVar2.u;
                        jtVarX2.D = true;
                        if ((ktVar2 != null ? ktVar2.t : null) != null) {
                            jtVarX2.D = true;
                        }
                        dVarF = cVar.a(jtVarX2);
                        if (c.C(2)) {
                            Log.v("FragmentManager", "Fragment " + jtVarX2 + " has been inflated via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
                        }
                    } else {
                        if (jtVarX2.o) {
                            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string2 + ", or parent id 0x" + Integer.toHexString(id2) + " with another fragment for " + attributeValue);
                        }
                        jtVarX2.o = true;
                        jtVarX2.s = cVar;
                        kt ktVar3 = cVar.o;
                        jtVarX2.t = ktVar3;
                        FragmentActivity fragmentActivity2 = ktVar3.u;
                        jtVarX2.D = true;
                        if ((ktVar3 != null ? ktVar3.t : null) != null) {
                            jtVarX2.D = true;
                        }
                        dVarF = cVar.f(jtVarX2);
                        if (c.C(2)) {
                            Log.v("FragmentManager", "Retained Fragment " + jtVarX2 + " has been re-attached via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
                        }
                    }
                    jtVarX2.E = (ViewGroup) view;
                    dVarF.k();
                    dVarF.j();
                    View view3 = jtVarX2.F;
                    if (view3 == null) {
                        throw new IllegalStateException(hk.j("Fragment ", attributeValue, " did not create a view."));
                    }
                    if (resourceId != 0) {
                        view3.setId(resourceId);
                    }
                    if (jtVarX2.F.getTag() == null) {
                        jtVarX2.F.setTag(string2);
                    }
                    jtVarX2.F.addOnAttachStateChangeListener(new mt(this, dVarF));
                    return jtVarX2.F;
                }
            }
        }
        return null;
    }
}
