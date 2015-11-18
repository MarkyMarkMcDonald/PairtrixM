package pairtrix.domain;

import java.time.LocalDate;
import java.util.List;

public class TeamSetup implements Comparable<TeamSetup>{

    private List<Pairing> pairings;
    private LocalDate dateReported;

    public TeamSetup(List<Pairing> pairings, LocalDate dateReported) {
        this.pairings = pairings;
        this.dateReported = dateReported;
    }

    public List<Pairing> getPairings() {
        return pairings;
    }

    @Override
    public int compareTo(TeamSetup teamSetup) {
        return teamSetup.getDateReported().compareTo(dateReported);
    }

    @Override
    public String toString() {
        return "TeamSetup{" +
                "pairings=" + pairings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamSetup teamSetup = (TeamSetup) o;

        return !(pairings != null ? !pairings.equals(teamSetup.pairings) : teamSetup.pairings != null);

    }

    @Override
    public int hashCode() {
        return pairings != null ? pairings.hashCode() : 0;
    }

    public LocalDate getDateReported() {
        return dateReported;
    }
}
