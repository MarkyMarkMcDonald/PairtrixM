package pairtrix.domain;

import com.codepoetics.protonpack.StreamUtils;

import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class Application {

    private TeamMembers teamMembers;

    private Random random;
    private List<TeamSetup> teamSetups = new ArrayList<>();

    public Application(TeamMembersRepository teamRepository) {
        teamMembers = new TeamMembers(teamRepository);
        this.random = new Random();
    }

    public Application(TeamMembersRepository teamRepository, Random random) {
        teamMembers = new TeamMembers(teamRepository);
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
        return teamMembers.withSeed(random);
    }

    public void addTeamMember(String name) {
        teamMembers.add(name);
    }

    @SafeVarargs
    public final void recordPairings(LocalDate dateReported, List<String>... stringPairings) {
        List<Pairing> pairings = Arrays.stream(stringPairings)
                .map(teamMembers -> new Pairing(teamMembers.get(0), Optional.ofNullable(teamMembers.get(1))))
                .collect(toList());
        TeamSetup teamSetup = new TeamSetup(pairings, dateReported);
        teamSetups.add(teamSetup);
    }

    public List<TeamSetup> getPreviousTeamSetups() {
        teamSetups.sort(TeamSetup::compareTo);
        return teamSetups;
    }
}
