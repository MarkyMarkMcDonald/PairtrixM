package pairtrix.domain.helpers;

import pairtrix.domain.TeamMembersRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeTeamMembersRepository implements TeamMembersRepository {

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
