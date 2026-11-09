package io.nats.client;

import io.nats.client.api.PublishAck;
import io.nats.client.impl.Headers;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes.dex */
public interface JetStream {
    ConsumerContext getConsumerContext(String str, String str2) throws IOException, JetStreamApiException;

    StreamContext getStreamContext(String str) throws IOException, JetStreamApiException;

    PublishAck publish(Message message) throws IOException, JetStreamApiException;

    PublishAck publish(Message message, PublishOptions publishOptions) throws IOException, JetStreamApiException;

    PublishAck publish(String str, Headers headers, byte[] bArr) throws IOException, JetStreamApiException;

    PublishAck publish(String str, Headers headers, byte[] bArr, PublishOptions publishOptions) throws IOException, JetStreamApiException;

    PublishAck publish(String str, byte[] bArr) throws IOException, JetStreamApiException;

    PublishAck publish(String str, byte[] bArr, PublishOptions publishOptions) throws IOException, JetStreamApiException;

    CompletableFuture<PublishAck> publishAsync(Message message);

    CompletableFuture<PublishAck> publishAsync(Message message, PublishOptions publishOptions);

    CompletableFuture<PublishAck> publishAsync(String str, Headers headers, byte[] bArr);

    CompletableFuture<PublishAck> publishAsync(String str, Headers headers, byte[] bArr, PublishOptions publishOptions);

    CompletableFuture<PublishAck> publishAsync(String str, byte[] bArr);

    CompletableFuture<PublishAck> publishAsync(String str, byte[] bArr, PublishOptions publishOptions);

    JetStreamSubscription subscribe(String str) throws IOException, JetStreamApiException;

    JetStreamSubscription subscribe(String str, Dispatcher dispatcher, MessageHandler messageHandler, PullSubscribeOptions pullSubscribeOptions) throws IOException, JetStreamApiException;

    JetStreamSubscription subscribe(String str, Dispatcher dispatcher, MessageHandler messageHandler, boolean z) throws IOException, JetStreamApiException;

    JetStreamSubscription subscribe(String str, Dispatcher dispatcher, MessageHandler messageHandler, boolean z, PushSubscribeOptions pushSubscribeOptions) throws IOException, JetStreamApiException;

    JetStreamSubscription subscribe(String str, PullSubscribeOptions pullSubscribeOptions) throws IOException, JetStreamApiException;

    JetStreamSubscription subscribe(String str, PushSubscribeOptions pushSubscribeOptions) throws IOException, JetStreamApiException;

    JetStreamSubscription subscribe(String str, String str2, Dispatcher dispatcher, MessageHandler messageHandler, boolean z, PushSubscribeOptions pushSubscribeOptions) throws IOException, JetStreamApiException;

    JetStreamSubscription subscribe(String str, String str2, PushSubscribeOptions pushSubscribeOptions) throws IOException, JetStreamApiException;
}
