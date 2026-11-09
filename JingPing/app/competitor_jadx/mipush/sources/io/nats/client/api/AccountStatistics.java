package io.nats.client.api;

import io.nats.client.Message;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AccountStatistics extends ApiResponse<AccountStatistics> {
    private final ApiStats api;
    private final String domain;
    private final AccountTier rollupTier;
    private final Map<String, AccountTier> tiers;

    public AccountStatistics(Message message) {
        super(message);
        this.rollupTier = new AccountTier(this.jv);
        this.domain = JsonValueUtils.readString(this.jv, ApiConstants.DOMAIN);
        this.api = new ApiStats(JsonValueUtils.readObject(this.jv, ApiConstants.API));
        JsonValue object = JsonValueUtils.readObject(this.jv, ApiConstants.TIERS);
        this.tiers = new HashMap();
        Map<String, JsonValue> map = object.map;
        if (map != null) {
            for (String str : map.keySet()) {
                this.tiers.put(str, new AccountTier(object.map.get(str)));
            }
        }
    }

    public ApiStats getApi() {
        return this.api;
    }

    public long getConsumers() {
        return this.rollupTier.getConsumers();
    }

    public String getDomain() {
        return this.domain;
    }

    public AccountLimits getLimits() {
        return this.rollupTier.getLimits();
    }

    public long getMemory() {
        return this.rollupTier.getMemoryBytes();
    }

    public long getReservedMemory() {
        return this.rollupTier.getReservedMemoryBytes();
    }

    public long getReservedStorage() {
        return this.rollupTier.getReservedStorageBytes();
    }

    public long getStorage() {
        return this.rollupTier.getStorageBytes();
    }

    public long getStreams() {
        return this.rollupTier.getStreams();
    }

    public Map<String, AccountTier> getTiers() {
        return this.tiers;
    }
}
