package pairtrix.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;

public class TeamMembersRepositoryTest {

    private TeamMembersRepository teamMembersRepository;

    protected TeamMembersRepository setupTeamMembersRepository() {
        return new FakeTeamMembersRepository();
    }

    @Before
    public void setUp() throws Exception {
        teamMembersRepository = setupTeamMembersRepository();
    }

    @Test
    public void itStartsEmpty() throws Exception {
        assertThat(teamMembersRepository.findAll(), is(empty()));
    }

    @Test
    public void itCanHoldTeamMembers() throws Exception {
        teamMembersRepository.addMember("Varus");
        teamMembersRepository.addMember("Sona");
        teamMembersRepository.addMember("Akali");
        teamMembersRepository.addMember("Malzahar");
        teamMembersRepository.addMember("Udyr");

        List<String> teamMembers = teamMembersRepository.findAll();

        assertThat(teamMembers, hasSize(5));
        assertThat(teamMembers, hasItems(
                "Varus",
                "Sona",
                "Akali",
                "Malzahar",
                "Udyr"
        ));
    }
}