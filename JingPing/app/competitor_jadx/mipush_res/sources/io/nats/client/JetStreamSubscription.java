package io.nats.client;

import io.nats.client.api.ConsumerInfo;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface JetStreamSubscription extends Subscription {
    List<Message> fetch(int i, long j);

    List<Message> fetch(int i, Duration duration);

    ConsumerInfo getConsumerInfo() throws IOException, JetStreamApiException;

    String getConsumerName();

    Iterator<Message> iterate(int i, long j);

    Iterator<Message> iterate(int i, Duration duration);

    void pull(int i);

    void pull(PullRequestOptions pullRequestOptions);

    void pullExpiresIn(int i, long j);

    void pullExpiresIn(int i, Duration duration);

    void pullNoWait(int i);

    void pullNoWait(int i, long j);

    void pullNoWait(int i, Duration duration);

    JetStreamReader reader(int i, int i2);
}
