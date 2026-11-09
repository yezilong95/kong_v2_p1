package defpackage;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class yq0 implements Iterable {
    public vq0 E;
    public vq0 F;
    public final WeakHashMap G = new WeakHashMap();
    public int H = 0;

    public vq0 a(Object obj) {
        vq0 vq0Var = this.E;
        while (vq0Var != null && !vq0Var.E.equals(obj)) {
            vq0Var = vq0Var.G;
        }
        return vq0Var;
    }

    public Object b(Object obj) {
        vq0 vq0VarA = a(obj);
        if (vq0VarA == null) {
            return null;
        }
        this.H--;
        WeakHashMap weakHashMap = this.G;
        if (!weakHashMap.isEmpty()) {
            Iterator it = weakHashMap.keySet().iterator();
            while (it.hasNext()) {
                ((xq0) it.next()).a(vq0VarA);
            }
        }
        vq0 vq0Var = vq0VarA.H;
        if (vq0Var != null) {
            vq0Var.G = vq0VarA.G;
        } else {
            this.E = vq0VarA.G;
        }
        vq0 vq0Var2 = vq0VarA.G;
        if (vq0Var2 != null) {
            vq0Var2.H = vq0Var;
        } else {
            this.F = vq0Var;
        }
        vq0VarA.G = null;
        vq0VarA.H = null;
        return vq0VarA.F;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
    
        if (r3.hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0050, code lost:
    
        if (((defpackage.uq0) r7).hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0052, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0053, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != r6) goto L4
            return r0
        L4:
            boolean r1 = r7 instanceof defpackage.yq0
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            yq0 r7 = (defpackage.yq0) r7
            int r1 = r6.H
            int r3 = r7.H
            if (r1 == r3) goto L13
            return r2
        L13:
            java.util.Iterator r1 = r6.iterator()
            java.util.Iterator r7 = r7.iterator()
        L1b:
            r3 = r1
            uq0 r3 = (defpackage.uq0) r3
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L44
            r4 = r7
            uq0 r4 = (defpackage.uq0) r4
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
            uq0 r7 = (defpackage.uq0) r7
            boolean r7 = r7.hasNext()
            if (r7 != 0) goto L53
            return r0
        L53:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yq0.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Iterator it = iterator();
        int iHashCode = 0;
        while (true) {
            uq0 uq0Var = (uq0) it;
            if (!uq0Var.hasNext()) {
                return iHashCode;
            }
            iHashCode += ((Map.Entry) uq0Var.next()).hashCode();
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        uq0 uq0Var = new uq0(this.E, this.F, 0);
        this.G.put(uq0Var, Boolean.FALSE);
        return uq0Var;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator it = iterator();
        while (true) {
            uq0 uq0Var = (uq0) it;
            if (!uq0Var.hasNext()) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(((Map.Entry) uq0Var.next()).toString());
            if (uq0Var.hasNext()) {
                sb.append(", ");
            }
        }
    }
}
