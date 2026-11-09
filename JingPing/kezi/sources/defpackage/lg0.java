package defpackage;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class lg0 implements Iterable {
    public ig0 b;
    public ig0 c;
    public final WeakHashMap d = new WeakHashMap();
    public int e = 0;

    public ig0 a(Object obj) {
        ig0 ig0Var = this.b;
        while (ig0Var != null && !ig0Var.b.equals(obj)) {
            ig0Var = ig0Var.d;
        }
        return ig0Var;
    }

    public Object b(Object obj) {
        ig0 ig0VarA = a(obj);
        if (ig0VarA == null) {
            return null;
        }
        this.e--;
        WeakHashMap weakHashMap = this.d;
        if (!weakHashMap.isEmpty()) {
            Iterator it = weakHashMap.keySet().iterator();
            while (it.hasNext()) {
                ((kg0) it.next()).a(ig0VarA);
            }
        }
        ig0 ig0Var = ig0VarA.e;
        if (ig0Var != null) {
            ig0Var.d = ig0VarA.d;
        } else {
            this.b = ig0VarA.d;
        }
        ig0 ig0Var2 = ig0VarA.d;
        if (ig0Var2 != null) {
            ig0Var2.e = ig0Var;
        } else {
            this.c = ig0Var;
        }
        ig0VarA.d = null;
        ig0VarA.e = null;
        return ig0VarA.c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
    
        if (r3.hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0050, code lost:
    
        if (((defpackage.hg0) r7).hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0052, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0053, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != r6) goto L4
            return r0
        L4:
            boolean r1 = r7 instanceof defpackage.lg0
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            lg0 r7 = (defpackage.lg0) r7
            int r1 = r6.e
            int r3 = r7.e
            if (r1 == r3) goto L13
            return r2
        L13:
            java.util.Iterator r1 = r6.iterator()
            java.util.Iterator r7 = r7.iterator()
        L1b:
            r3 = r1
            hg0 r3 = (defpackage.hg0) r3
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L44
            r4 = r7
            hg0 r4 = (defpackage.hg0) r4
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L44
            java.lang.Object r3 = r3.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r4.next()
            if (r3 != 0) goto L3b
            if (r4 != 0) goto L43
        L3b:
            if (r3 == 0) goto L1b
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L1b
        L43:
            return r2
        L44:
            boolean r1 = r3.hasNext()
            if (r1 != 0) goto L53
            hg0 r7 = (defpackage.hg0) r7
            boolean r7 = r7.hasNext()
            if (r7 != 0) goto L53
            return r0
        L53:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lg0.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Iterator it = iterator();
        int iHashCode = 0;
        while (true) {
            hg0 hg0Var = (hg0) it;
            if (!hg0Var.hasNext()) {
                return iHashCode;
            }
            iHashCode += ((Map.Entry) hg0Var.next()).hashCode();
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        hg0 hg0Var = new hg0(this.b, this.c, 0);
        this.d.put(hg0Var, Boolean.FALSE);
        return hg0Var;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator it = iterator();
        while (true) {
            hg0 hg0Var = (hg0) it;
            if (!hg0Var.hasNext()) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(((Map.Entry) hg0Var.next()).toString());
            if (hg0Var.hasNext()) {
                sb.append(", ");
            }
        }
    }
}
