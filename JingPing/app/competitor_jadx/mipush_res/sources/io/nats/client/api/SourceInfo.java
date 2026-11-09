package io.nats.client.api;

import defpackage.zt;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.time.Duration;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SourceInfo extends SourceInfoBase {
    public SourceInfo(JsonValue jsonValue) {
        super(jsonValue);
    }

    public static List<SourceInfo> optionalListOf(JsonValue jsonValue) {
        return JsonValueUtils.optionalListOf(jsonValue, new zt(10));
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
        return "SourceInfo " + this.jv;
    }
}
