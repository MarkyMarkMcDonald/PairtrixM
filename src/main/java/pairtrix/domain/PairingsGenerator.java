package pairtrix.domain;

import com.codepoetics.protonpack.StreamUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class PairingsGenerator {

    private TeamSetupHistory teamSetupHistory = new TeamSetupHistory();

    public final void recordPairings(LocalDate dateReported, List<Pairing> pairings) {
        teamSetupHistory.recordPairings(dateReported, pairings);
    }

    public List<TeamSetup> getPreviousTeamSetups() {
        return teamSetupHistory.getPreviousTeamSetups();
    }

    public List<Pairing> pairings(List<String> teamMembers) {
        List<Pairing> pairings = StreamUtils.aggregate(randomize(teamMembers).stream(), 2)
                .map(pairing -> {
                    if (pairing.size() <= 1) {
                        return new Pairing(pairing.get(0), Optional.empty());
                    } else {
                        return new Pairing(pairing.get(0), Optional.of(pairing.get(1)));
                    }
                })
                .collect(toList());

        recordPairings(LocalDate.now(), pairings);

        return pairings;
    }

    private List<String> randomize(List<String> teamMembers) {
        List<String> randomizedList = new ArrayList<>(teamMembers);
        Collections.shuffle(randomizedList);
        return randomizedList;
    }
}
