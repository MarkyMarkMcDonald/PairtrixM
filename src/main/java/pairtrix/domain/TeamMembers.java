package pairtrix.domain;

import java.util.*;

public class TeamMembers {

    List<String> teamMembers = new ArrayList<>();

    public List<String> withSeed(Random random) {
        List<String> randomizedList = new ArrayList<>(teamMembers);
        Collections.shuffle(randomizedList, random);
        return randomizedList;
    }

    public void add(String name) {
        teamMembers.add(name);
    }
}
