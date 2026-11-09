package io.nats.client.impl;

import io.nats.client.JetStreamApiException;
import io.nats.client.Message;
import io.nats.client.api.StreamInfo;
import io.nats.client.api.StreamInfoOptions;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonUtils;

/* JADX INFO: loaded from: classes.dex */
class StreamInfoReader {
    private ListRequestEngine engine = new ListRequestEngine();
    private StreamInfo streamInfo;

    public StreamInfo getStreamInfo() {
        return this.streamInfo;
    }

    public boolean hasMore() {
        return this.engine.hasMore();
    }

    public byte[] nextJson(StreamInfoOptions streamInfoOptions) {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.OFFSET, Integer.valueOf(this.engine.nextOffset()));
        if (streamInfoOptions != null) {
            JsonUtils.addField(sbBeginJson, ApiConstants.SUBJECTS_FILTER, streamInfoOptions.getSubjectsFilter());
            JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.DELETED_DETAILS, Boolean.valueOf(streamInfoOptions.isDeletedDetails()));
        }
        return JsonUtils.endJson(sbBeginJson).toString().getBytes();
    }

    public void process(Message message) throws JetStreamApiException {
        this.engine = new ListRequestEngine(message);
        StreamInfo streamInfo = new StreamInfo(message);
        StreamInfo streamInfo2 = this.streamInfo;
        if (streamInfo2 == null) {
            this.streamInfo = streamInfo;
        } else {
            streamInfo2.getStreamState().getSubjects().addAll(streamInfo.getStreamState().getSubjects());
        }
    }
}
