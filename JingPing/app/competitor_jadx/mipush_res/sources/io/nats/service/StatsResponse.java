package io.nats.service;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class StatsResponse extends ServiceResponse {
    public static final String TYPE = "io.nats.micro.v1.stats_response";
    private final List<EndpointStats> endpointStatsList;
    private final ZonedDateTime started;

    public StatsResponse(ServiceResponse serviceResponse, ZonedDateTime zonedDateTime, List<EndpointStats> list) {
        super(TYPE, serviceResponse);
        this.started = zonedDateTime;
        this.endpointStatsList = list;
    }

    @Override // io.nats.service.ServiceResponse
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        StatsResponse statsResponse = (StatsResponse) obj;
        if (Objects.equals(this.started, statsResponse.started)) {
            return Objects.equals(this.endpointStatsList, statsResponse.endpointStatsList);
        }
        return false;
    }

    public List<EndpointStats> getEndpointStatsList() {
        return this.endpointStatsList;
    }

    public ZonedDateTime getStarted() {
        return this.started;
    }

    @Override // io.nats.service.ServiceResponse
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        ZonedDateTime zonedDateTime = this.started;
        int iHashCode2 = (iHashCode + (zonedDateTime != null ? zonedDateTime.hashCode() : 0)) * 31;
        List<EndpointStats> list = this.endpointStatsList;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    @Override // io.nats.service.ServiceResponse
    public void subToJson(StringBuilder sb) {
        JsonUtils.addJsons(sb, ApiConstants.ENDPOINTS, this.endpointStatsList, true);
        JsonUtils.addField(sb, ApiConstants.STARTED, this.started);
    }

    public StatsResponse(byte[] bArr) {
        this(ServiceResponse.parseMessage(bArr));
    }

    private StatsResponse(JsonValue jsonValue) {
        super(TYPE, jsonValue);
        this.endpointStatsList = EndpointStats.listOf(JsonValueUtils.readValue(jsonValue, ApiConstants.ENDPOINTS));
        this.started = JsonValueUtils.readDate(jsonValue, ApiConstants.STARTED);
    }
}
