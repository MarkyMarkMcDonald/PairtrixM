package pairtrix.domain;

import java.util.*;

public class TeamMembers {

    private TeamRepository teamRepository;

    public TeamMembers(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<String> withSeed(Random random) {
        List<String> randomizedList = new ArrayList<>(teamRepository.findAll());
        Collections.shuffle(randomizedList, random);
        return randomizedList;
    }

    public void add(String name) {
        teamRepository.addMember(name);
    }
}
