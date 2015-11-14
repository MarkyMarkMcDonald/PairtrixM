package pairtrix.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Pairing {
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
}
