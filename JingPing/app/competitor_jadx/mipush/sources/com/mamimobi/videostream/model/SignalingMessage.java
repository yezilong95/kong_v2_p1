package com.mamimobi.videostream.model;

import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SignalingMessage {
    public WebCandidate candidate;
    public ArrayList<String> data;
    public ArrayList<SignalingMessage> dataObj;
    public String deviceType = DeviceType.PHONE.getType();
    public String id;
    public boolean isLoop;
    public String key;
    public String mode;
    public boolean muteFlag;
    public String name;
    public Map<String, Object> payload;
    public String sdpAnswer;
    public String sdpOffer;
    public String sender;
    public boolean state;
    public String text;
    public String type;
}
