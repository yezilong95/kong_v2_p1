package androidx.work;

import android.net.Uri;
import androidx.work.impl.utils.DurationApi26Impl;
import defpackage.t40;
import defpackage.tm;
import defpackage.vf;
import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.collections.EmptySet;
import kotlin.uuid.Uuid;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* JADX INFO: loaded from: classes.dex */
public final class Constraints {
    public static final Companion Companion = new Companion(null);
    public static final Constraints NONE = new Constraints(null, false, false, false, false, 0, 0, null, 255, null);
    private final long contentTriggerMaxDelayMillis;
    private final long contentTriggerUpdateDelayMillis;
    private final Set<ContentUriTrigger> contentUriTriggers;
    private final NetworkType requiredNetworkType;
    private final boolean requiresBatteryNotLow;
    private final boolean requiresCharging;
    private final boolean requiresDeviceIdle;
    private final boolean requiresStorageNotLow;

    public static final class Builder {
        private Set<ContentUriTrigger> contentUriTriggers;
        private NetworkType requiredNetworkType;
        private boolean requiresBatteryNotLow;
        private boolean requiresCharging;
        private boolean requiresDeviceIdle;
        private boolean requiresStorageNotLow;
        private long triggerContentMaxDelay;
        private long triggerContentUpdateDelay;

        public Builder() {
            this.requiredNetworkType = NetworkType.NOT_REQUIRED;
            this.triggerContentUpdateDelay = -1L;
            this.triggerContentMaxDelay = -1L;
            this.contentUriTriggers = new LinkedHashSet();
        }

        public final Builder addContentUriTrigger(Uri uri, boolean z) {
            t40.n(uri, "uri");
            this.contentUriTriggers.add(new ContentUriTrigger(uri, z));
            return this;
        }

        public final Constraints build() {
            Set setN1 = vf.n1(this.contentUriTriggers);
            long j = this.triggerContentUpdateDelay;
            long j2 = this.triggerContentMaxDelay;
            return new Constraints(this.requiredNetworkType, this.requiresCharging, this.requiresDeviceIdle, this.requiresBatteryNotLow, this.requiresStorageNotLow, j, j2, setN1);
        }

        public final Builder setRequiredNetworkType(NetworkType networkType) {
            t40.n(networkType, "networkType");
            this.requiredNetworkType = networkType;
            return this;
        }

        public final Builder setRequiresBatteryNotLow(boolean z) {
            this.requiresBatteryNotLow = z;
            return this;
        }

        public final Builder setRequiresCharging(boolean z) {
            this.requiresCharging = z;
            return this;
        }

        public final Builder setRequiresDeviceIdle(boolean z) {
            this.requiresDeviceIdle = z;
            return this;
        }

        public final Builder setRequiresStorageNotLow(boolean z) {
            this.requiresStorageNotLow = z;
            return this;
        }

        public final Builder setTriggerContentMaxDelay(long j, TimeUnit timeUnit) {
            t40.n(timeUnit, "timeUnit");
            this.triggerContentMaxDelay = timeUnit.toMillis(j);
            return this;
        }

        public final Builder setTriggerContentUpdateDelay(long j, TimeUnit timeUnit) {
            t40.n(timeUnit, "timeUnit");
            this.triggerContentUpdateDelay = timeUnit.toMillis(j);
            return this;
        }

        public final Builder setTriggerContentMaxDelay(Duration duration) {
            t40.n(duration, "duration");
            this.triggerContentMaxDelay = DurationApi26Impl.toMillisCompat(duration);
            return this;
        }

        public final Builder setTriggerContentUpdateDelay(Duration duration) {
            t40.n(duration, "duration");
            this.triggerContentUpdateDelay = DurationApi26Impl.toMillisCompat(duration);
            return this;
        }

        public Builder(Constraints constraints) {
            t40.n(constraints, CryptoServicesPermission.CONSTRAINTS);
            this.requiredNetworkType = NetworkType.NOT_REQUIRED;
            this.triggerContentUpdateDelay = -1L;
            this.triggerContentMaxDelay = -1L;
            this.contentUriTriggers = new LinkedHashSet();
            this.requiresCharging = constraints.requiresCharging();
            this.requiresDeviceIdle = constraints.requiresDeviceIdle();
            this.requiredNetworkType = constraints.getRequiredNetworkType();
            this.requiresBatteryNotLow = constraints.requiresBatteryNotLow();
            this.requiresStorageNotLow = constraints.requiresStorageNotLow();
            this.triggerContentUpdateDelay = constraints.getContentTriggerUpdateDelayMillis();
            this.triggerContentMaxDelay = constraints.getContentTriggerMaxDelayMillis();
            Set<ContentUriTrigger> contentUriTriggers = constraints.getContentUriTriggers();
            t40.n(contentUriTriggers, "<this>");
            this.contentUriTriggers = new LinkedHashSet(contentUriTriggers);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(tm tmVar) {
            this();
        }

        private Companion() {
        }
    }

    public static final class ContentUriTrigger {
        private final boolean isTriggeredForDescendants;
        private final Uri uri;

        public ContentUriTrigger(Uri uri, boolean z) {
            t40.n(uri, "uri");
            this.uri = uri;
            this.isTriggeredForDescendants = z;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!ContentUriTrigger.class.equals(obj != null ? obj.getClass() : null)) {
                return false;
            }
            t40.l(obj, "null cannot be cast to non-null type androidx.work.Constraints.ContentUriTrigger");
            ContentUriTrigger contentUriTrigger = (ContentUriTrigger) obj;
            return t40.f(this.uri, contentUriTrigger.uri) && this.isTriggeredForDescendants == contentUriTrigger.isTriggeredForDescendants;
        }

        public final Uri getUri() {
            return this.uri;
        }

        public int hashCode() {
            return Boolean.hashCode(this.isTriggeredForDescendants) + (this.uri.hashCode() * 31);
        }

        public final boolean isTriggeredForDescendants() {
            return this.isTriggeredForDescendants;
        }
    }

    public Constraints() {
        this(null, false, false, false, false, 0L, 0L, null, 255, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !Constraints.class.equals(obj.getClass())) {
            return false;
        }
        Constraints constraints = (Constraints) obj;
        if (this.requiresCharging == constraints.requiresCharging && this.requiresDeviceIdle == constraints.requiresDeviceIdle && this.requiresBatteryNotLow == constraints.requiresBatteryNotLow && this.requiresStorageNotLow == constraints.requiresStorageNotLow && this.contentTriggerUpdateDelayMillis == constraints.contentTriggerUpdateDelayMillis && this.contentTriggerMaxDelayMillis == constraints.contentTriggerMaxDelayMillis && this.requiredNetworkType == constraints.requiredNetworkType) {
            return t40.f(this.contentUriTriggers, constraints.contentUriTriggers);
        }
        return false;
    }

    public final long getContentTriggerMaxDelayMillis() {
        return this.contentTriggerMaxDelayMillis;
    }

    public final long getContentTriggerUpdateDelayMillis() {
        return this.contentTriggerUpdateDelayMillis;
    }

    public final Set<ContentUriTrigger> getContentUriTriggers() {
        return this.contentUriTriggers;
    }

    public final NetworkType getRequiredNetworkType() {
        return this.requiredNetworkType;
    }

    public final boolean hasContentUriTriggers() {
        return !this.contentUriTriggers.isEmpty();
    }

    public int hashCode() {
        int iHashCode = ((((((((this.requiredNetworkType.hashCode() * 31) + (this.requiresCharging ? 1 : 0)) * 31) + (this.requiresDeviceIdle ? 1 : 0)) * 31) + (this.requiresBatteryNotLow ? 1 : 0)) * 31) + (this.requiresStorageNotLow ? 1 : 0)) * 31;
        long j = this.contentTriggerUpdateDelayMillis;
        int i = (iHashCode + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.contentTriggerMaxDelayMillis;
        return this.contentUriTriggers.hashCode() + ((i + ((int) (j2 ^ (j2 >>> 32)))) * 31);
    }

    public final boolean requiresBatteryNotLow() {
        return this.requiresBatteryNotLow;
    }

    public final boolean requiresCharging() {
        return this.requiresCharging;
    }

    public final boolean requiresDeviceIdle() {
        return this.requiresDeviceIdle;
    }

    public final boolean requiresStorageNotLow() {
        return this.requiresStorageNotLow;
    }

    public Constraints(NetworkType networkType, boolean z, boolean z2, boolean z3, boolean z4, long j, long j2, Set<ContentUriTrigger> set) {
        t40.n(networkType, "requiredNetworkType");
        t40.n(set, "contentUriTriggers");
        this.requiredNetworkType = networkType;
        this.requiresCharging = z;
        this.requiresDeviceIdle = z2;
        this.requiresBatteryNotLow = z3;
        this.requiresStorageNotLow = z4;
        this.contentTriggerUpdateDelayMillis = j;
        this.contentTriggerMaxDelayMillis = j2;
        this.contentUriTriggers = set;
    }

    public Constraints(NetworkType networkType, boolean z, boolean z2, boolean z3, boolean z4, long j, long j2, Set set, int i, tm tmVar) {
        this((i & 1) != 0 ? NetworkType.NOT_REQUIRED : networkType, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? false : z3, (i & 16) != 0 ? false : z4, (i & 32) != 0 ? -1L : j, (i & 64) != 0 ? -1L : j2, (i & Uuid.SIZE_BITS) != 0 ? EmptySet.INSTANCE : set);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Constraints(Constraints constraints) {
        t40.n(constraints, "other");
        boolean z = constraints.requiresCharging;
        boolean z2 = constraints.requiresDeviceIdle;
        this(constraints.requiredNetworkType, z, z2, constraints.requiresBatteryNotLow, constraints.requiresStorageNotLow, constraints.contentTriggerUpdateDelayMillis, constraints.contentTriggerMaxDelayMillis, constraints.contentUriTriggers);
    }
}
