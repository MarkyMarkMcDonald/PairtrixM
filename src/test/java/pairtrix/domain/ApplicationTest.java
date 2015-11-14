package pairtrix.domain;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ApplicationTest {

    @Test
    public void application_givesPairings() throws Exception {
        Application application = new Application();

        List<Pairing> pairings = application.pairings();

        assertThat(pairings.size(), is(0));

        application.addTeamMember("Mark");
        application.addTeamMember("Adam");
        application.addTeamMember("Desmond");
        application.addTeamMember("Chad");
        application.addTeamMember("Craig");
        application.addTeamMember("George");

        pairings = application.pairings();

        assertThat(pairings.size(), is(3));

        Pairing pairingWithMark = pairings.stream()
                .filter(pairing -> pairing.getMembers().contains("Mark"))
                .findFirst().get();

        assertThat(pairingWithMark.getMembers().size(), is(2));
    }

    @Test
    public void handlingOddNumbersOfTeamMembers() throws Exception {
        Application application = new Application();
        application.addTeamMember("Mark");
        List<Pairing> pairings = application.pairings();
        List<String> pairedNames = pairings.get(0).getMembers();
        assertThat(pairedNames.size(), equalTo(1));
        assertThat(pairedNames.get(0), equalTo("Mark"));
    }
}
