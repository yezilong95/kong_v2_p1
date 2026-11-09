package defpackage;

import androidx.work.impl.WorkDatabaseVersions;
import io.nats.client.api.Replica;
import io.nats.client.api.Source;
import io.nats.client.api.SourceInfo;
import io.nats.client.api.StreamAlternate;
import io.nats.client.api.SubjectTransform;
import io.nats.client.impl.Headers;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.service.Endpoint;
import io.nats.service.EndpointStats;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zt implements Function {
    public final /* synthetic */ int a;

    public /* synthetic */ zt(int i) {
        this.a = i;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return new EndpointStats((JsonValue) obj);
            case 1:
                return Headers.lambda$_add$0((String) obj);
            case 2:
                return new Endpoint((JsonValue) obj);
            case 3:
                return JsonValueUtils.lambda$null$19((JsonValue) obj);
            case 4:
                return ((JsonValue) obj).string;
            case 5:
                return JsonValueUtils.getLong((JsonValue) obj);
            case 6:
                return JsonValueUtils.lambda$null$14((JsonValue) obj);
            case 7:
                return ((JsonValue) obj).string;
            case 8:
                return new Replica((JsonValue) obj);
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return new Source((JsonValue) obj);
            case 10:
                return new SourceInfo((JsonValue) obj);
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                return new StreamAlternate((JsonValue) obj);
            default:
                return new SubjectTransform((JsonValue) obj);
        }
    }
}
