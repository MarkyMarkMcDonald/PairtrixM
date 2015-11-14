package pairtrix.domain;

import java.util.List;

public interface TeamMembersRepository {
    void addMember(String name);

    List<String> findAll();
}
