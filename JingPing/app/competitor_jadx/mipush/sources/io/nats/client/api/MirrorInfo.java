package io.nats.client.api;

import io.nats.client.support.JsonValue;
import java.time.Duration;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MirrorInfo extends SourceInfoBase {
    public MirrorInfo(JsonValue jsonValue) {
        super(jsonValue);
    }

    public static MirrorInfo optionalInstance(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return new MirrorInfo(jsonValue);
    }

    @Override // io.nats.client.api.SourceInfoBase
    public /* bridge */ /* synthetic */ Duration getActive() {
        return super.getActive();
    }

    @Override // io.nats.client.api.SourceInfoBase
    public /* bridge */ /* synthetic */ Error getError() {
        return super.getError();
    }

    @Override // io.nats.client.api.SourceInfoBase
    public /* bridge */ /* synthetic */ External getExternal() {
        return super.getExternal();
    }

    @Override // io.nats.client.api.SourceInfoBase
    public /* bridge */ /* synthetic */ long getLag() {
        return super.getLag();
    }

    @Override // io.nats.client.api.SourceInfoBase
    public /* bridge */ /* synthetic */ String getName() {
        return super.getName();
    }

    @Override // io.nats.client.api.SourceInfoBase
    public /* bridge */ /* synthetic */ List getSubjectTransforms() {
        return super.getSubjectTransforms();
    }

    public String toString() {
        return "MirrorInfo " + super.toString();
    }
}
