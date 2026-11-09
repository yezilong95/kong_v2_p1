package androidx.work;

import android.annotation.SuppressLint;
import androidx.work.WorkInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class WorkQuery {
    private final List<UUID> mIds;
    private final List<WorkInfo.State> mStates;
    private final List<String> mTags;
    private final List<String> mUniqueWorkNames;

    public static final class Builder {
        List<UUID> mIds = new ArrayList();
        List<String> mUniqueWorkNames = new ArrayList();
        List<String> mTags = new ArrayList();
        List<WorkInfo.State> mStates = new ArrayList();

        private Builder() {
        }

        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromIds(List<UUID> list) {
            Builder builder = new Builder();
            builder.addIds(list);
            return builder;
        }

        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromStates(List<WorkInfo.State> list) {
            Builder builder = new Builder();
            builder.addStates(list);
            return builder;
        }

        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromTags(List<String> list) {
            Builder builder = new Builder();
            builder.addTags(list);
            return builder;
        }

        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromUniqueWorkNames(List<String> list) {
            Builder builder = new Builder();
            builder.addUniqueWorkNames(list);
            return builder;
        }

        public Builder addIds(List<UUID> list) {
            this.mIds.addAll(list);
            return this;
        }

        public Builder addStates(List<WorkInfo.State> list) {
            this.mStates.addAll(list);
            return this;
        }

        public Builder addTags(List<String> list) {
            this.mTags.addAll(list);
            return this;
        }

        public Builder addUniqueWorkNames(List<String> list) {
            this.mUniqueWorkNames.addAll(list);
            return this;
        }

        public WorkQuery build() {
            if (this.mIds.isEmpty() && this.mUniqueWorkNames.isEmpty() && this.mTags.isEmpty() && this.mStates.isEmpty()) {
                throw new IllegalArgumentException("Must specify ids, uniqueNames, tags or states when building a WorkQuery");
            }
            return new WorkQuery(this);
        }
    }

    public WorkQuery(Builder builder) {
        this.mIds = builder.mIds;
        this.mUniqueWorkNames = builder.mUniqueWorkNames;
        this.mTags = builder.mTags;
        this.mStates = builder.mStates;
    }

    public static WorkQuery fromIds(List<UUID> list) {
        return Builder.fromIds(list).build();
    }

    public static WorkQuery fromStates(List<WorkInfo.State> list) {
        return Builder.fromStates(list).build();
    }

    public static WorkQuery fromTags(List<String> list) {
        return Builder.fromTags(list).build();
    }

    public static WorkQuery fromUniqueWorkNames(String... strArr) {
        return Builder.fromUniqueWorkNames(Arrays.asList(strArr)).build();
    }

    public List<UUID> getIds() {
        return this.mIds;
    }

    public List<WorkInfo.State> getStates() {
        return this.mStates;
    }

    public List<String> getTags() {
        return this.mTags;
    }

    public List<String> getUniqueWorkNames() {
        return this.mUniqueWorkNames;
    }

    public static WorkQuery fromIds(UUID... uuidArr) {
        return fromIds((List<UUID>) Arrays.asList(uuidArr));
    }

    public static WorkQuery fromStates(WorkInfo.State... stateArr) {
        return Builder.fromStates(Arrays.asList(stateArr)).build();
    }

    public static WorkQuery fromTags(String... strArr) {
        return fromTags((List<String>) Arrays.asList(strArr));
    }

    public static WorkQuery fromUniqueWorkNames(List<String> list) {
        return Builder.fromUniqueWorkNames(list).build();
    }
}
