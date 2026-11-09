package io.nats.client.impl;

import defpackage.zn;
import io.nats.client.JetStreamReader;
import io.nats.client.JetStreamStatusException;
import io.nats.client.Message;
import io.nats.client.NatsSystemClock;
import io.nats.client.PullRequestOptions;
import io.nats.client.impl.MessageManager;
import io.nats.client.support.NatsConstants;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public class NatsJetStreamPullSubscription extends NatsJetStreamSubscription {
    private final AtomicLong pullSubjectIdHolder;

    /* JADX INFO: renamed from: io.nats.client.impl.NatsJetStreamPullSubscription$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$io$nats$client$impl$MessageManager$ManageResult;

        static {
            int[] iArr = new int[MessageManager.ManageResult.values().length];
            $SwitchMap$io$nats$client$impl$MessageManager$ManageResult = iArr;
            try {
                iArr[MessageManager.ManageResult.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$nats$client$impl$MessageManager$ManageResult[MessageManager.ManageResult.STATUS_TERMINUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$nats$client$impl$MessageManager$ManageResult[MessageManager.ManageResult.STATUS_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static class JetStreamReaderImpl implements JetStreamReader {
        private final int batchSize;
        private final int repullAt;
        private final NatsJetStreamPullSubscription sub;
        private boolean keepGoing = true;
        private int currentBatchRed = 0;

        public JetStreamReaderImpl(NatsJetStreamPullSubscription natsJetStreamPullSubscription, int i, int i2) {
            this.sub = natsJetStreamPullSubscription;
            this.batchSize = i;
            this.repullAt = Math.max(1, Math.min(i, i2));
            natsJetStreamPullSubscription.pull(i);
        }

        private Message track(Message message) {
            if (message != null) {
                int i = this.currentBatchRed + 1;
                this.currentBatchRed = i;
                if (i == this.repullAt && this.keepGoing) {
                    this.sub.pull(this.batchSize);
                }
                if (this.currentBatchRed == this.batchSize) {
                    this.currentBatchRed = 0;
                }
            }
            return message;
        }

        @Override // io.nats.client.JetStreamReader
        public Message nextMessage(Duration duration) throws IllegalStateException, InterruptedException {
            return track(this.sub.nextMessage(duration));
        }

        @Override // io.nats.client.JetStreamReader
        public void stop() {
            this.keepGoing = false;
        }

        @Override // io.nats.client.JetStreamReader
        public Message nextMessage(long j) throws IllegalStateException, InterruptedException {
            return track(this.sub.nextMessage(j));
        }
    }

    public NatsJetStreamPullSubscription(String str, String str2, NatsConnection natsConnection, NatsDispatcher natsDispatcher, NatsJetStream natsJetStream, String str3, String str4, MessageManager messageManager) {
        super(str, str2, null, natsConnection, natsDispatcher, natsJetStream, str3, str4, messageManager);
        this.pullSubjectIdHolder = new AtomicLong();
    }

    private List<Message> _fetch(int i, long j) {
        List<Message> listDrainAlreadyBuffered = drainAlreadyBuffered(i);
        int size = i - listDrainAlreadyBuffered.size();
        if (size != 0) {
            try {
                long jNanoTime = NatsSystemClock.nanoTime();
                String str_pull = _pull(PullRequestOptions.builder(size).expiresIn(Duration.ofMillis(j > 20 ? j - 10 : j)).build(), false, null);
                long j2 = j * NatsConstants.NANOS_PER_MILLI;
                for (long jNanoTime2 = j2; size > 0 && jNanoTime2 > 0; jNanoTime2 = j2 - (NatsSystemClock.nanoTime() - jNanoTime)) {
                    NatsMessage natsMessageNextMessageInternal = nextMessageInternal(Duration.ofNanos(jNanoTime2));
                    if (natsMessageNextMessageInternal == null) {
                        break;
                    }
                    int i2 = AnonymousClass3.$SwitchMap$io$nats$client$impl$MessageManager$ManageResult[this.manager.manage(natsMessageNextMessageInternal).ordinal()];
                    if (i2 == 1) {
                        listDrainAlreadyBuffered.add(natsMessageNextMessageInternal);
                        size--;
                    } else if (i2 != 2) {
                        if (i2 == 3 && str_pull.equals(natsMessageNextMessageInternal.getSubject())) {
                            throw new JetStreamStatusException(natsMessageNextMessageInternal.getStatus(), this);
                        }
                    } else if (str_pull.equals(natsMessageNextMessageInternal.getSubject())) {
                        break;
                    }
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        return listDrainAlreadyBuffered;
    }

    private Iterator<Message> _iterate(final int i, final long j) {
        final List<Message> listDrainAlreadyBuffered = drainAlreadyBuffered(i);
        int size = i - listDrainAlreadyBuffered.size();
        if (size == 0) {
            return new Iterator<Message>() { // from class: io.nats.client.impl.NatsJetStreamPullSubscription.1
                @Override // java.util.Iterator
                public boolean hasNext() {
                    return listDrainAlreadyBuffered.size() > 0;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public Message next() {
                    return (Message) listDrainAlreadyBuffered.remove(0);
                }
            };
        }
        final String str_pull = _pull(PullRequestOptions.builder(size).expiresIn(Duration.ofNanos(j)).build(), false, null);
        return new Iterator<Message>() { // from class: io.nats.client.impl.NatsJetStreamPullSubscription.2
            int received = 0;
            boolean done = false;
            Message msg = null;

            @Override // java.util.Iterator
            public boolean hasNext() {
                try {
                    if (this.msg != null) {
                        return true;
                    }
                    if (this.done) {
                        return false;
                    }
                    if (listDrainAlreadyBuffered.isEmpty()) {
                        Message message_nextUnmanaged = NatsJetStreamPullSubscription.this._nextUnmanaged(j, str_pull);
                        this.msg = message_nextUnmanaged;
                        if (message_nextUnmanaged == null) {
                            this.done = true;
                            return false;
                        }
                    } else {
                        this.msg = (Message) listDrainAlreadyBuffered.remove(0);
                    }
                    int i2 = this.received + 1;
                    this.received = i2;
                    this.done = i2 == i;
                    return true;
                } catch (InterruptedException unused) {
                    this.msg = null;
                    this.done = true;
                    Thread.currentThread().interrupt();
                    return false;
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Message next() {
                Message message = this.msg;
                this.msg = null;
                return message;
            }
        };
    }

    private List<Message> drainAlreadyBuffered(int i) {
        ArrayList arrayList = new ArrayList(i);
        while (true) {
            try {
                NatsMessage natsMessageNextMessageInternal = nextMessageInternal(null);
                if (natsMessageNextMessageInternal == null) {
                    break;
                }
                if (this.manager.manage(natsMessageNextMessageInternal) == MessageManager.ManageResult.MESSAGE) {
                    arrayList.add(natsMessageNextMessageInternal);
                    if (arrayList.size() == i) {
                        break;
                    }
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return arrayList;
            }
        }
        return arrayList;
    }

    private void durationGtZeroRequired(Duration duration, String str) {
        if (duration == null || duration.toMillis() <= 0) {
            throw new IllegalArgumentException(zn.m(str, " wait duration must be supplied and greater than 0."));
        }
    }

    public String _pull(PullRequestOptions pullRequestOptions, boolean z, PullManagerObserver pullManagerObserver) {
        String strPrependPrefix = this.js.prependPrefix("CONSUMER.MSG.NEXT." + this.stream + NatsConstants.DOT + this.consumerName);
        String strReplace = getSubject().replace("*", Long.toString(this.pullSubjectIdHolder.incrementAndGet()));
        this.manager.startPullRequest(strReplace, pullRequestOptions, z, pullManagerObserver);
        this.connection.publishInternal(strPrependPrefix, strReplace, null, pullRequestOptions.serialize(), true, this.connection.forceFlushOnRequest);
        return strReplace;
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public List<Message> fetch(int i, long j) {
        durationGtZeroRequired(j, "Fetch");
        return _fetch(i, j);
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription
    public boolean isPullMode() {
        return true;
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public Iterator<Message> iterate(int i, Duration duration) {
        durationGtZeroRequired(duration, "Iterate");
        return _iterate(i, duration.toNanos());
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public void pull(int i) {
        _pull(PullRequestOptions.builder(i).build(), true, null);
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public void pullExpiresIn(int i, Duration duration) {
        durationGtZeroRequired(duration, "Expires In");
        _pull(PullRequestOptions.builder(i).expiresIn(duration).build(), true, null);
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public void pullNoWait(int i) {
        _pull(PullRequestOptions.noWait(i).build(), true, null);
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public JetStreamReader reader(int i, int i2) {
        return new JetStreamReaderImpl(this, i, i2);
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public void pull(PullRequestOptions pullRequestOptions) {
        _pull(pullRequestOptions, true, null);
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public void pullNoWait(int i, Duration duration) {
        durationGtZeroRequired(duration, "NoWait Expires In");
        _pull(PullRequestOptions.noWait(i).expiresIn(duration).build(), true, null);
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public List<Message> fetch(int i, Duration duration) {
        durationGtZeroRequired(duration, "Fetch");
        return _fetch(i, duration.toMillis());
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public Iterator<Message> iterate(int i, long j) {
        durationGtZeroRequired(j, "Iterate");
        return _iterate(i, j * NatsConstants.NANOS_PER_MILLI);
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public void pullExpiresIn(int i, long j) {
        durationGtZeroRequired(j, "Expires In");
        _pull(PullRequestOptions.builder(i).expiresIn(j).build(), true, null);
    }

    @Override // io.nats.client.impl.NatsJetStreamSubscription, io.nats.client.JetStreamSubscription
    public void pullNoWait(int i, long j) {
        durationGtZeroRequired(j, "NoWait Expires In");
        _pull(PullRequestOptions.noWait(i).expiresIn(j).build(), true, null);
    }

    private void durationGtZeroRequired(long j, String str) {
        if (j <= 0) {
            throw new IllegalArgumentException(zn.m(str, " wait duration must be supplied and greater than 0."));
        }
    }
}
