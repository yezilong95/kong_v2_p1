package org.slf4j.helpers;

import java.util.Iterator;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public class BasicMarker implements Marker {
    private static final long serialVersionUID = -2849567615646933777L;

    @Override // org.slf4j.Marker
    public void add(Marker marker) {
        if (marker == null) {
            throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
        }
        if (!contains(marker) && !marker.contains(this)) {
            throw null;
        }
    }

    @Override // org.slf4j.Marker
    public boolean contains(Marker marker) {
        if (marker == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        if (equals(marker)) {
            return true;
        }
        if (hasReferences()) {
            throw null;
        }
        return false;
    }

    @Override // org.slf4j.Marker
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Marker)) {
            return false;
        }
        ((Marker) obj).getName();
        throw null;
    }

    @Override // org.slf4j.Marker
    public String getName() {
        return null;
    }

    @Override // org.slf4j.Marker
    @Deprecated
    public boolean hasChildren() {
        return hasReferences();
    }

    @Override // org.slf4j.Marker
    public boolean hasReferences() {
        throw null;
    }

    @Override // org.slf4j.Marker
    public int hashCode() {
        throw null;
    }

    @Override // org.slf4j.Marker
    public Iterator<Marker> iterator() {
        throw null;
    }

    @Override // org.slf4j.Marker
    public boolean remove(Marker marker) {
        throw null;
    }

    public String toString() {
        if (!hasReferences()) {
            return getName();
        }
        Iterator<Marker> it = iterator();
        StringBuilder sb = new StringBuilder(getName());
        sb.append(" [ ");
        while (it.hasNext()) {
            sb.append(it.next().getName());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    @Override // org.slf4j.Marker
    public boolean contains(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        throw null;
    }
}
