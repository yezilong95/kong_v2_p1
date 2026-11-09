package kotlin.jvm.internal;

import defpackage.h60;
import defpackage.s60;
import defpackage.t40;
import defpackage.z60;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyReference extends CallableReference implements z60 {
    public final boolean K;

    public PropertyReference() {
        this.K = false;
    }

    @Override // kotlin.jvm.internal.CallableReference
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final z60 b() {
        if (this.K) {
            throw new UnsupportedOperationException("Kotlin reflection is not yet supported for synthetic Java properties. Please follow/upvote https://youtrack.jetbrains.com/issue/KT-55980");
        }
        return (z60) super.b();
    }

    @Override // kotlin.jvm.internal.CallableReference
    public h60 compute() {
        return this.K ? this : super.compute();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PropertyReference) {
            PropertyReference propertyReference = (PropertyReference) obj;
            return getOwner().equals(propertyReference.getOwner()) && getName().equals(propertyReference.getName()) && getSignature().equals(propertyReference.getSignature()) && t40.f(getBoundReceiver(), propertyReference.getBoundReceiver());
        }
        if (obj instanceof z60) {
            return obj.equals(compute());
        }
        return false;
    }

    public abstract /* synthetic */ s60 getGetter();

    public int hashCode() {
        return getSignature().hashCode() + ((getName().hashCode() + (getOwner().hashCode() * 31)) * 31);
    }

    @Override // defpackage.z60
    public boolean isConst() {
        return b().isConst();
    }

    @Override // defpackage.z60
    public boolean isLateinit() {
        return b().isLateinit();
    }

    public String toString() {
        h60 h60VarCompute = compute();
        if (h60VarCompute != this) {
            return h60VarCompute.toString();
        }
        return "property " + getName() + " (Kotlin reflection is not available)";
    }

    public PropertyReference(Object obj) {
        super(obj);
        this.K = false;
    }

    public PropertyReference(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, (i & 1) == 1);
        this.K = (i & 2) == 2;
    }
}
