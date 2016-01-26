package pairtrix.domain;

import java.util.List;

public class Application {

    private TeamMembersRepository teamRepository;

    private PairingsGenerator pairingsGenerator;

    public Application(TeamMembersRepository teamRepository) {
        this.teamRepository = teamRepository;
        pairingsGenerator = new PairingsGenerator();
    }

    public List<Pairing> pairings() {
        return pairingsGenerator.pairings(teamRepository.findAll());
    }

    public void addTeamMember(String name) {
        teamRepository.addMember(name);
    }

}
