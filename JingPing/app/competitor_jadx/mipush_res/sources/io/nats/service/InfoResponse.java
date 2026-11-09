package io.nats.service;

import defpackage.no;
import defpackage.zt;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class InfoResponse extends ServiceResponse {
    public static final String TYPE = "io.nats.micro.v1.info_response";
    private final String description;
    private final List<Endpoint> endpoints;

    public InfoResponse(String str, String str2, String str3, Map<String, String> map, String str4) {
        super(TYPE, str, str2, str3, map);
        this.description = str4;
        this.endpoints = new ArrayList();
    }

    private void _addServiceEndpoint(ServiceEndpoint serviceEndpoint) {
        this.endpoints.add(new Endpoint(serviceEndpoint.getName(), serviceEndpoint.getSubject(), serviceEndpoint.getQueueGroup(), serviceEndpoint.getMetadata()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ List lambda$new$0(JsonValue jsonValue) {
        return JsonValueUtils.listOf(jsonValue, new zt(2));
    }

    public void addServiceEndpoint(ServiceEndpoint serviceEndpoint) {
        _addServiceEndpoint(serviceEndpoint);
        this.serialized.set(null);
    }

    public void addServiceEndpoints(Collection<ServiceEndpoint> collection) {
        Iterator<ServiceEndpoint> it = collection.iterator();
        while (it.hasNext()) {
            _addServiceEndpoint(it.next());
        }
        this.serialized.set(null);
    }

    @Override // io.nats.service.ServiceResponse
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        InfoResponse infoResponse = (InfoResponse) obj;
        if (Objects.equals(this.description, infoResponse.description)) {
            return JsonUtils.listEquals(this.endpoints, infoResponse.endpoints);
        }
        return false;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Endpoint> getEndpoints() {
        return this.endpoints;
    }

    @Override // io.nats.service.ServiceResponse
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        String str = this.description;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        List<Endpoint> list = this.endpoints;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    @Override // io.nats.service.ServiceResponse
    public void subToJson(StringBuilder sb) {
        JsonUtils.addField(sb, ApiConstants.DESCRIPTION, this.description);
        JsonUtils.addJsons(sb, ApiConstants.ENDPOINTS, this.endpoints, true);
    }

    public InfoResponse(byte[] bArr) {
        this(ServiceResponse.parseMessage(bArr));
    }

    private InfoResponse(JsonValue jsonValue) {
        super(TYPE, jsonValue);
        this.description = JsonValueUtils.readString(jsonValue, ApiConstants.DESCRIPTION);
        this.endpoints = (List) JsonValueUtils.read(jsonValue, ApiConstants.ENDPOINTS, new no(3));
    }
}
