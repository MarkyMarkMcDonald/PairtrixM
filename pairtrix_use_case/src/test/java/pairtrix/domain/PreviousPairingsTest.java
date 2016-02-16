package pairtrix.domain;

import org.junit.Test;
import pairtrix.domain.helpers.FakeTeamMembersRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PreviousPairingsTest {

    @Test
    public void itCanRecordPreviousPairings() throws Exception {
        Application application = new Application(new FakeTeamMembersRepository());
        LocalDate today = LocalDate.of(2015, Month.NOVEMBER, 16);
        LocalDate yesterday = today.minusDays(1);

        application.recordPairings(today, asList("Mark", "George"), asList("Bob", "Zim"));
        application.recordPairings(yesterday, asList("Bob", "Mark"), asList("George", "Zim"));

        assertThat(application.getPreviousTeamSetups(), hasSize(2));

        assertThat(application.getPreviousTeamSetups(), hasItem(
                hasProperty("pairings", hasItems(
                        hasProperty("members", containsInAnyOrder("George", "Zim")),
                        hasProperty("members", containsInAnyOrder("Bob", "Mark"))
                )))
        );

        assertThat(application.getPreviousTeamSetups(), hasItem(
                hasProperty("pairings", hasItems(
                        hasProperty("members", containsInAnyOrder("Mark", "George")),
                        hasProperty("members", containsInAnyOrder("Bob", "Zim"))
                )
        )));
    }

    @Test
    public void pairingsAreOrderedByDateReported() throws Exception {
        Application application = new Application(new FakeTeamMembersRepository());
        LocalDate today = LocalDate.of(2015, Month.NOVEMBER, 16);
        LocalDate yesterday = today.minusDays(1);
        LocalDate tomorrow = today.plusDays(1);

        application.recordPairings(today, asList("Mark", "George"));
        application.recordPairings(tomorrow, asList("Bob", "Zim"));
        application.recordPairings(yesterday, asList("Fred", "Peter"));

        List<TeamSetup> previousTeamSetups = application.getPreviousTeamSetups();
        assertThat(previousTeamSetups.get(0).getPairings().get(0).getMembers(), hasItems("Bob", "Zim"));
        assertThat(previousTeamSetups.get(1).getPairings().get(0).getMembers(), hasItems("Mark", "George"));
        assertThat(previousTeamSetups.get(2).getPairings().get(0).getMembers(), hasItems("Fred", "Peter"));
    }
}
