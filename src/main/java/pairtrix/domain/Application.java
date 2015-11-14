package pairtrix.domain;

import com.codepoetics.protonpack.StreamUtils;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Application {

    private List<String> teamMembers = new ArrayList<>();

    private Random random;

    public Application() {
        this.random = new Random();
    }

    public Application(Random random) {
        this.random = random;
    }

    public List<Pairing> pairings() {
        return StreamUtils.aggregate(getRandomTeamMembers().stream(), 2)
                .map(pairing -> {
                    if (pairing.size() <= 1) {
                        return new Pairing(pairing.get(0), Optional.empty());
                    } else {
                        return new Pairing(pairing.get(0), Optional.of(pairing.get(1)));
                    }
                })
                .collect(toList());
    }

    private List<String> getRandomTeamMembers() {
        Collections.shuffle(teamMembers, random);
        return teamMembers;
    }

    public void addTeamMember(String name) {
        teamMembers.add(name);
    }
}
