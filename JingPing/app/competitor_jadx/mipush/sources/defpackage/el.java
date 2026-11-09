package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class el extends s8 {
    public gl F;

    public boolean equals(Object obj) {
        if (!(obj instanceof el)) {
            return false;
        }
        el elVar = (el) obj;
        gl glVar = this.F;
        return glVar == null ? elVar.F == null : glVar.equals(elVar.F);
    }

    public int hashCode() {
        return (!this.E ? 1 : 0) ^ this.F.hashCode();
    }
}
