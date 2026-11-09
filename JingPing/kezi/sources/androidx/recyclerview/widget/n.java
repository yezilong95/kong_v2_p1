package androidx.recyclerview.widget;

import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.xr0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {
    public static final List s = Collections.EMPTY_LIST;
    public final View a;
    public WeakReference b;
    public int j;
    public RecyclerView r;
    public int c = -1;
    public int d = -1;
    public long e = -1;
    public int f = -1;
    public int g = -1;
    public n h = null;
    public n i = null;
    public final ArrayList k = null;
    public final List l = null;
    public int m = 0;
    public l n = null;
    public boolean o = false;
    public int p = 0;
    public int q = -1;

    public n(View view) {
        if (view == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.a = view;
    }

    public final void a(int i) {
        this.j = i | this.j;
    }

    public final int b() {
        int i = this.g;
        return i == -1 ? this.c : i;
    }

    public final List c() {
        ArrayList arrayList;
        return ((this.j & 1024) != 0 || (arrayList = this.k) == null || arrayList.size() == 0) ? s : this.l;
    }

    public final boolean d() {
        View view = this.a;
        return (view.getParent() == null || view.getParent() == this.r) ? false : true;
    }

    public final boolean e() {
        return (this.j & 1) != 0;
    }

    public final boolean f() {
        return (this.j & 4) != 0;
    }

    public final boolean g() {
        if ((this.j & 16) != 0) {
            return false;
        }
        WeakHashMap weakHashMap = xr0.a;
        return !this.a.hasTransientState();
    }

    public final boolean h() {
        return (this.j & 8) != 0;
    }

    public final boolean i() {
        return this.n != null;
    }

    public final boolean j() {
        return (this.j & 256) != 0;
    }

    public final boolean k() {
        return (this.j & 2) != 0;
    }

    public final void l(int i, boolean z) {
        if (this.d == -1) {
            this.d = this.c;
        }
        if (this.g == -1) {
            this.g = this.c;
        }
        if (z) {
            this.g += i;
        }
        this.c += i;
        View view = this.a;
        if (view.getLayoutParams() != null) {
            ((RecyclerView.LayoutParams) view.getLayoutParams()).c = true;
        }
    }

    public final void m() {
        this.j = 0;
        this.c = -1;
        this.d = -1;
        this.e = -1L;
        this.g = -1;
        this.m = 0;
        this.h = null;
        this.i = null;
        ArrayList arrayList = this.k;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.j &= -1025;
        this.p = 0;
        this.q = -1;
        RecyclerView.i(this);
    }

    public final void n(boolean z) {
        int i = this.m;
        int i2 = z ? i - 1 : i + 1;
        this.m = i2;
        if (i2 < 0) {
            this.m = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            return;
        }
        if (!z && i2 == 1) {
            this.j |= 16;
        } else if (z && i2 == 0) {
            this.j &= -17;
        }
    }

    public final boolean o() {
        return (this.j & Uuid.SIZE_BITS) != 0;
    }

    public final boolean p() {
        return (this.j & 32) != 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.c + " id=" + this.e + ", oldPos=" + this.d + ", pLpos:" + this.g);
        if (i()) {
            sb.append(" scrap ");
            sb.append(this.o ? "[changeScrap]" : "[attachedScrap]");
        }
        if (f()) {
            sb.append(" invalid");
        }
        if (!e()) {
            sb.append(" unbound");
        }
        if ((this.j & 2) != 0) {
            sb.append(" update");
        }
        if (h()) {
            sb.append(" removed");
        }
        if (o()) {
            sb.append(" ignored");
        }
        if (j()) {
            sb.append(" tmpDetached");
        }
        if (!g()) {
            sb.append(" not recyclable(" + this.m + ")");
        }
        if ((this.j & 512) != 0 || f()) {
            sb.append(" undefined adapter position");
        }
        if (this.a.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }
}
