package pairtrix.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pairing {
    private List<String> members = new ArrayList<>();

    public Pairing(String firstMember, Optional<String> secondMember) {
        members.add(firstMember);
        secondMember.ifPresent(member -> members.add(member));
    }

    public List<String> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "Pairing{" +
                "members=" + members +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pairing pairing = (Pairing) o;

        return !(members != null ? !members.equals(pairing.members) : pairing.members != null);

    }

    @Override
    public int hashCode() {
        return members != null ? members.hashCode() : 0;
    }
}
