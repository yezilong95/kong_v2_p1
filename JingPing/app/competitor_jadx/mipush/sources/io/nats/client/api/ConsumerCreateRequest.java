package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;

/* JADX INFO: loaded from: classes.dex */
public class ConsumerCreateRequest implements JsonSerializable {
    private final Action action;
    private final ConsumerConfiguration config;
    private final String streamName;

    public enum Action {
        Create("create"),
        Update("update"),
        CreateOrUpdate(null);

        public final String actionText;

        Action(String str) {
            this.actionText = str;
        }
    }

    public ConsumerCreateRequest(String str, ConsumerConfiguration consumerConfiguration) {
        this.streamName = str;
        this.config = consumerConfiguration;
        this.action = Action.CreateOrUpdate;
    }

    public Action getAction() {
        return this.action;
    }

    public ConsumerConfiguration getConfig() {
        return this.config;
    }

    public String getStreamName() {
        return this.streamName;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.STREAM_NAME, this.streamName);
        JsonUtils.addField(sbBeginJson, ApiConstants.ACTION, this.action.actionText);
        JsonUtils.addField(sbBeginJson, ApiConstants.CONFIG, this.config);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        return "ConsumerCreateRequest{streamName='" + this.streamName + "', " + this.config + '}';
    }

    public ConsumerCreateRequest(String str, ConsumerConfiguration consumerConfiguration, Action action) {
        this.streamName = str;
        this.config = consumerConfiguration;
        this.action = action;
    }
}
