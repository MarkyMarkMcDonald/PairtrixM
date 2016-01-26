package pairtrix.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeamSetupHistory {

    private List<TeamSetup> teamSetups = new ArrayList<>();

    public void recordPairings(LocalDate dateReported, List<Pairing> pairings) {
        TeamSetup teamSetup = new TeamSetup(pairings, dateReported);
        teamSetups.add(teamSetup);
    }

    public List<TeamSetup> getPreviousTeamSetups() {
        teamSetups.sort(TeamSetup::compareTo);
        return teamSetups;
    }
}
