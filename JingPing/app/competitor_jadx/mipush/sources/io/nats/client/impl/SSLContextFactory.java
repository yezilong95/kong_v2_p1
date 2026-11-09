package io.nats.client.impl;

import javax.net.ssl.SSLContext;

/* JADX INFO: loaded from: classes.dex */
public interface SSLContextFactory {
    SSLContext createSSLContext(SSLContextFactoryProperties sSLContextFactoryProperties);
}
