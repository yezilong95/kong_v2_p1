package io.nats.client.api;

import io.nats.client.Message;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonParser;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.time.ZonedDateTime;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StreamInfo extends ApiResponse<StreamInfo> {
    private final List<StreamAlternate> alternates;
    private final ClusterInfo clusterInfo;
    private final StreamConfiguration config;
    private final ZonedDateTime createTime;
    private final MirrorInfo mirrorInfo;
    private final List<SourceInfo> sourceInfos;
    private final StreamState streamState;
    private final ZonedDateTime timestamp;

    public StreamInfo(Message message) {
        this(JsonParser.parseUnchecked(message.getData()));
    }

    public List<StreamAlternate> getAlternates() {
        return this.alternates;
    }

    public ClusterInfo getClusterInfo() {
        return this.clusterInfo;
    }

    public StreamConfiguration getConfig() {
        return this.config;
    }

    public StreamConfiguration getConfiguration() {
        return this.config;
    }

    public ZonedDateTime getCreateTime() {
        return this.createTime;
    }

    public MirrorInfo getMirrorInfo() {
        return this.mirrorInfo;
    }

    public List<SourceInfo> getSourceInfos() {
        return this.sourceInfos;
    }

    public StreamState getStreamState() {
        return this.streamState;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    @Override // io.nats.client.api.ApiResponse
    public String toString() {
        return "StreamInfo " + this.jv;
    }

    public StreamInfo(JsonValue jsonValue) {
        super(jsonValue);
        this.createTime = JsonValueUtils.readDate(this.jv, ApiConstants.CREATED);
        this.config = StreamConfiguration.instance(JsonValueUtils.readValue(this.jv, ApiConstants.CONFIG));
        this.streamState = new StreamState(JsonValueUtils.readValue(this.jv, ApiConstants.STATE));
        this.clusterInfo = ClusterInfo.optionalInstance(JsonValueUtils.readValue(this.jv, ApiConstants.CLUSTER));
        this.mirrorInfo = MirrorInfo.optionalInstance(JsonValueUtils.readValue(this.jv, ApiConstants.MIRROR));
        this.sourceInfos = SourceInfo.optionalListOf(JsonValueUtils.readValue(this.jv, ApiConstants.SOURCES));
        this.alternates = StreamAlternate.optionalListOf(JsonValueUtils.readValue(this.jv, ApiConstants.ALTERNATES));
        this.timestamp = JsonValueUtils.readDate(this.jv, ApiConstants.TIMESTAMP);
    }
}
