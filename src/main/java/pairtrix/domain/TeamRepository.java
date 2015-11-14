package pairtrix.domain;

import java.util.List;

public interface TeamRepository {
    void addMember(String name);

    List<String> findAll();
}
