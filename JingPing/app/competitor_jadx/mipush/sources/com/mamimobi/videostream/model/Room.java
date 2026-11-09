package com.mamimobi.videostream.model;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Room {
    private String createTime;
    private String createUserId;
    private int maxSize;
    private List<String> membersId = new ArrayList();
    private String roomId;
    private RoomType roomType;

    /* JADX INFO: renamed from: com.mamimobi.videostream.model.Room$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mamimobi$videostream$model$RoomType;

        static {
            int[] iArr = new int[RoomType.values().length];
            $SwitchMap$com$mamimobi$videostream$model$RoomType = iArr;
            try {
                iArr[RoomType.SINGLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$mamimobi$videostream$model$RoomType[RoomType.SINGLE_AUDIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private Room(RoomType roomType) {
        this.roomType = roomType;
        int i = AnonymousClass1.$SwitchMap$com$mamimobi$videostream$model$RoomType[roomType.ordinal()];
        if (i == 1 || i == 2) {
            this.maxSize = 2;
        } else {
            this.maxSize = 100;
        }
    }

    public static Room createRoom(RoomType roomType) {
        return new Room(roomType);
    }

    public void addMemberId(String str) {
        this.membersId.add(str);
    }

    public void addMembersId(List<String> list) {
        this.membersId.addAll(list);
    }

    public int currentSize() {
        return this.membersId.size();
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public String getCreateUserId() {
        return this.createUserId;
    }

    public List<String> getMembers() {
        return this.membersId;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public int leftSize() {
        return this.maxSize - this.membersId.size();
    }

    public int maxSize() {
        return this.maxSize;
    }

    public void remove(String str) {
        this.membersId.remove(str);
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public void setCreateUserId(String str) {
        this.createUserId = str;
    }

    public void setRoomId(String str) {
        this.roomId = str;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
