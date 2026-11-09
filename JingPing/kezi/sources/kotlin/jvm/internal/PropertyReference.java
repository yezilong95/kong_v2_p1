package kotlin.jvm.internal;

import defpackage.f00;
import defpackage.m00;
import defpackage.oy;
import defpackage.sz;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyReference extends CallableReference implements m00 {
    public final boolean h;

    public PropertyReference() {
        this.h = false;
    }

    @Override // kotlin.jvm.internal.CallableReference
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final m00 b() {
        if (this.h) {
            throw new UnsupportedOperationException("Kotlin reflection is not yet supported for synthetic Java properties. Please follow/upvote https://youtrack.jetbrains.com/issue/KT-55980");
        }
        return (m00) super.b();
    }

    @Override // kotlin.jvm.internal.CallableReference
    public sz compute() {
        return this.h ? this : super.compute();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PropertyReference) {
            PropertyReference propertyReference = (PropertyReference) obj;
            return getOwner().equals(propertyReference.getOwner()) && getName().equals(propertyReference.getName()) && getSignature().equals(propertyReference.getSignature()) && oy.c(getBoundReceiver(), propertyReference.getBoundReceiver());
        }
        if (obj instanceof m00) {
            return obj.equals(compute());
        }
        return false;
    }

    public abstract /* synthetic */ f00 getGetter();

    public int hashCode() {
        return getSignature().hashCode() + ((getName().hashCode() + (getOwner().hashCode() * 31)) * 31);
    }

    @Override // defpackage.m00
    public boolean isConst() {
        return b().isConst();
    }

    @Override // defpackage.m00
    public boolean isLateinit() {
        return b().isLateinit();
    }

    public String toString() {
        sz szVarCompute = compute();
        if (szVarCompute != this) {
            return szVarCompute.toString();
        }
        return "property " + getName() + " (Kotlin reflection is not available)";
    }

    public PropertyReference(Object obj) {
        super(obj);
        this.h = false;
    }

    public PropertyReference(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, (i & 1) == 1);
        this.h = (i & 2) == 2;
    }
}
