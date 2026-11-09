package io.nats.service;

import io.nats.client.support.NatsConstants;
import io.nats.client.support.Validator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class Group {
    private final String name;
    private Group next;

    public Group(String str) {
        String strEmptyAsNull = Validator.emptyAsNull(str);
        if (strEmptyAsNull == null) {
            throw new IllegalArgumentException("Group name cannot be null or empty.");
        }
        if (strEmptyAsNull.contains(NatsConstants.GREATER_THAN)) {
            throw new IllegalArgumentException("Group name cannot contain '>'.");
        }
        this.name = Validator.validateSubjectTerm(strEmptyAsNull, "Group name", false);
    }

    public Group appendGroup(Group group) {
        Group group2 = this;
        while (true) {
            Group group3 = group2.next;
            if (group3 == null) {
                group2.next = group;
                return this;
            }
            group2 = group3;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Group group = (Group) obj;
        if (Objects.equals(this.name, group.name)) {
            return Objects.equals(this.next, group.next);
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public Group getNext() {
        return this.next;
    }

    public String getSubject() {
        if (this.next == null) {
            return this.name;
        }
        return this.name + NatsConstants.DOT + this.next.getSubject();
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Group group = this.next;
        return iHashCode + (group != null ? group.hashCode() : 0);
    }

    public String toString() {
        return "Group [" + getSubject().replace('.', '/') + "]";
    }
}
