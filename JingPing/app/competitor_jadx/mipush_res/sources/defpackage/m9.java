package defpackage;

import android.util.Log;
import androidx.fragment.app.c;
import androidx.work.impl.WorkDatabaseVersions;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.NatsConstants;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class m9 implements e00 {
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

    public m9(c cVar) {
        cVar.y();
        wz wzVar = cVar.o;
        if (wzVar != null) {
            wzVar.c0.getClassLoader();
        }
        this.a = new ArrayList();
        this.o = false;
        this.r = -1;
        this.p = cVar;
    }

    @Override // defpackage.e00
    public final boolean a(ArrayList arrayList, ArrayList arrayList2) {
        if (c.B(2)) {
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

    public final void b(m00 m00Var) {
        this.a.add(m00Var);
        m00Var.c = this.b;
        m00Var.d = this.c;
        m00Var.e = this.d;
        m00Var.f = this.e;
    }

    public final void c(int i) {
        if (this.g) {
            if (c.B(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            ArrayList arrayList = this.a;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                m00 m00Var = (m00) arrayList.get(i2);
                vz vzVar = m00Var.b;
                if (vzVar != null) {
                    vzVar.U += i;
                    if (c.B(2)) {
                        Log.v("FragmentManager", "Bump nesting of " + m00Var.b + " to " + m00Var.b.U);
                    }
                }
            }
        }
    }

    public final int d(boolean z) {
        if (this.q) {
            throw new IllegalStateException("commit already called");
        }
        if (c.B(2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new ta0());
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
        cVar.r(this, z);
        return this.r;
    }

    public final void e(int i, vz vzVar, String str, int i2) {
        Class<?> cls = vzVar.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = vzVar.b0;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + vzVar + ": was " + vzVar.b0 + " now " + str);
            }
            vzVar.b0 = str;
        }
        if (i != 0) {
            if (i == -1) {
                throw new IllegalArgumentException("Can't add fragment " + vzVar + " with tag " + str + " to container view with no id");
            }
            int i3 = vzVar.Z;
            if (i3 != 0 && i3 != i) {
                throw new IllegalStateException("Can't change container ID of fragment " + vzVar + ": was " + vzVar.Z + " now " + i);
            }
            vzVar.Z = i;
            vzVar.a0 = i;
        }
        b(new m00(i2, vzVar));
        vzVar.V = this.p;
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
            m00 m00Var = (m00) arrayList.get(i);
            switch (m00Var.a) {
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
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + m00Var.a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(NatsConstants.SPACE);
            printWriter.println(m00Var.b);
            if (z) {
                if (m00Var.c != 0 || m00Var.d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(m00Var.c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(m00Var.d));
                }
                if (m00Var.e != 0 || m00Var.f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(m00Var.e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(m00Var.f));
                }
            }
        }
    }

    public void setOnStartPostponedListener(uz uzVar) {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i >= arrayList.size()) {
                return;
            }
            vz vzVar = ((m00) arrayList.get(i)).b;
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
            sb.append(NatsConstants.SPACE);
            sb.append(this.h);
        }
        sb.append(JsonUtils.CLOSE);
        return sb.toString();
    }
}
