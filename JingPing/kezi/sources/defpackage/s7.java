package defpackage;

import android.util.Log;
import androidx.fragment.app.c;
import androidx.work.impl.WorkDatabaseVersions;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class s7 implements st {
    public final ArrayList a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public String h;
    public int i;
    public CharSequence j;
    public int k;
    public CharSequence l;
    public ArrayList m;
    public ArrayList n;
    public boolean o;
    public final c p;
    public boolean q;
    public int r;

    public s7(c cVar) {
        cVar.z();
        kt ktVar = cVar.o;
        if (ktVar != null) {
            ktVar.u.getClassLoader();
        }
        this.a = new ArrayList();
        this.o = false;
        this.r = -1;
        this.p = cVar;
    }

    @Override // defpackage.st
    public final boolean a(ArrayList arrayList, ArrayList arrayList2) {
        if (c.C(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.g) {
            return true;
        }
        c cVar = this.p;
        if (cVar.d == null) {
            cVar.d = new ArrayList();
        }
        cVar.d.add(this);
        return true;
    }

    public final void b(au auVar) {
        this.a.add(auVar);
        auVar.c = this.b;
        auVar.d = this.c;
        auVar.e = this.d;
        auVar.f = this.e;
    }

    public final void c(int i) {
        if (this.g) {
            if (c.C(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            ArrayList arrayList = this.a;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                au auVar = (au) arrayList.get(i2);
                jt jtVar = auVar.b;
                if (jtVar != null) {
                    jtVar.r += i;
                    if (c.C(2)) {
                        Log.v("FragmentManager", "Bump nesting of " + auVar.b + " to " + auVar.b.r);
                    }
                }
            }
        }
    }

    public final int d(boolean z) {
        if (this.q) {
            throw new IllegalStateException("commit already called");
        }
        if (c.C(2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new m30());
            f("  ", printWriter, true);
            printWriter.close();
        }
        this.q = true;
        boolean z2 = this.g;
        c cVar = this.p;
        if (z2) {
            this.r = cVar.i.getAndIncrement();
        } else {
            this.r = -1;
        }
        cVar.s(this, z);
        return this.r;
    }

    public final void e(int i, jt jtVar, String str, int i2) {
        Class<?> cls = jtVar.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = jtVar.y;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + jtVar + ": was " + jtVar.y + " now " + str);
            }
            jtVar.y = str;
        }
        if (i != 0) {
            if (i == -1) {
                throw new IllegalArgumentException("Can't add fragment " + jtVar + " with tag " + str + " to container view with no id");
            }
            int i3 = jtVar.w;
            if (i3 != 0 && i3 != i) {
                throw new IllegalStateException("Can't change container ID of fragment " + jtVar + ": was " + jtVar.w + " now " + i);
            }
            jtVar.w = i;
            jtVar.x = i;
        }
        b(new au(i2, jtVar));
        jtVar.s = this.p;
    }

    public final void f(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.h);
            printWriter.print(" mIndex=");
            printWriter.print(this.r);
            printWriter.print(" mCommitted=");
            printWriter.println(this.q);
            if (this.f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f));
            }
            if (this.b != 0 || this.c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.c));
            }
            if (this.d != 0 || this.e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.e));
            }
            if (this.i != 0 || this.j != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.i));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.j);
            }
            if (this.k != 0 || this.l != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.k));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.l);
            }
        }
        ArrayList arrayList = this.a;
        if (arrayList.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            au auVar = (au) arrayList.get(i);
            switch (auVar.a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case WorkDatabaseVersions.VERSION_9 /* 9 */:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case WorkDatabaseVersions.VERSION_10 /* 10 */:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + auVar.a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(auVar.b);
            if (z) {
                if (auVar.c != 0 || auVar.d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(auVar.c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(auVar.d));
                }
                if (auVar.e != 0 || auVar.f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(auVar.e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(auVar.f));
                }
            }
        }
    }

    public void setOnStartPostponedListener(ht htVar) {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i >= arrayList.size()) {
                return;
            }
            jt jtVar = ((au) arrayList.get(i)).b;
            i++;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(Uuid.SIZE_BITS);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.r >= 0) {
            sb.append(" #");
            sb.append(this.r);
        }
        if (this.h != null) {
            sb.append(" ");
            sb.append(this.h);
        }
        sb.append("}");
        return sb.toString();
    }
}
