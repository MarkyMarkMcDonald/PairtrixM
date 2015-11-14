package pairtrix.cli;

import pairtrix.domain.TeamRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTeamRepository implements TeamRepository {

    private List<String> teamMembers = new ArrayList<>();

    @Override
    public void addMember(String name) {
        teamMembers.add(name);
    }

    @Override
    public List<String> findAll() {
        return teamMembers;
    }
}
