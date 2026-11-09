package io.nats.client.support;

import io.nats.client.impl.Headers;

/* JADX INFO: loaded from: classes.dex */
public class IncomingHeadersProcessor {
    private Headers headers;
    private Status inlineStatus;
    private final int serializedLength;

    public IncomingHeadersProcessor(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException(NatsConstants.SERIALIZED_HEADER_CANNOT_BE_NULL_OR_EMPTY);
        }
        boolean z = false;
        int i = 0;
        while (true) {
            int i2 = NatsConstants.HEADER_VERSION_BYTES_LEN;
            if (i >= i2) {
                int length = bArr.length;
                this.serializedLength = length;
                TokenType tokenType = TokenType.CRLF;
                Token token = new Token(bArr, length, length - 2, tokenType);
                Token token2 = new Token(bArr, length, i2, (TokenType) null);
                if (token2.isType(TokenType.SPACE)) {
                    token2 = initStatus(bArr, length, token2);
                    if (token2.samePoint(token)) {
                        return;
                    } else {
                        z = true;
                    }
                }
                if (!token2.isType(tokenType)) {
                    throw new IllegalArgumentException(NatsConstants.INVALID_HEADER_COMPOSITION);
                }
                initHeader(bArr, length, token2, z);
                return;
            }
            if (bArr[i] != NatsConstants.HEADER_VERSION_BYTES[i]) {
                throw new IllegalArgumentException(NatsConstants.INVALID_HEADER_VERSION);
            }
            i++;
        }
    }

    private void initHeader(byte[] bArr, int i, Token token, boolean z) {
        Token token2;
        Token token3 = new Token(bArr, i, token, (TokenType) null);
        while (true) {
            TokenType tokenType = TokenType.TEXT;
            if (!token3.isType(tokenType)) {
                token3.mustBe(TokenType.CRLF);
                return;
            }
            Token token4 = new Token(bArr, i, token, TokenType.KEY);
            Token token5 = new Token(bArr, i, token4, (TokenType) null);
            if (token5.isType(TokenType.SPACE)) {
                token5 = new Token(bArr, i, token5, (TokenType) null);
            }
            if (token5.isType(tokenType)) {
                token2 = new Token(bArr, i, token5, TokenType.CRLF);
            } else {
                token5.mustBe(TokenType.CRLF);
                token2 = token5;
            }
            if (this.headers == null) {
                this.headers = new Headers();
            }
            this.headers.add(token4.getValue(), token5.getValue());
            token3 = new Token(bArr, i, token2, (TokenType) null);
            token = token2;
        }
    }

    private Token initStatus(byte[] bArr, int i, Token token) {
        Token token2;
        Token token3 = new Token(bArr, i, token, TokenType.WORD);
        Token token4 = new Token(bArr, i, token3, (TokenType) null);
        if (token4.isType(TokenType.SPACE)) {
            Token token5 = new Token(bArr, i, token4, TokenType.TEXT);
            token2 = new Token(bArr, i, token5, TokenType.CRLF);
            token4 = token5;
        } else {
            token4.mustBe(TokenType.CRLF);
            token2 = token4;
        }
        this.inlineStatus = new Status(token3, token4);
        return token2;
    }

    public Headers getHeaders() {
        return this.headers;
    }

    public int getSerializedLength() {
        return this.serializedLength;
    }

    public Status getStatus() {
        return this.inlineStatus;
    }
}
