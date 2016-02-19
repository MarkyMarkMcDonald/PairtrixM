package pairtrix.domain

import java.time.LocalDate

public class Application(private val teamMembersRepository: TeamMembersRepository) {

    private var teamSetups = listOf<TeamSetup>()

    private val pairalizer: Pairalizer = RandomPairalizer()

    fun pairings(): List<Pairing> {
        return pairalizer.pairings(teamMembersRepository.findAll(), teamSetups)
    }

    fun addTeamMember(name: String) {
        teamMembersRepository.addMember(name)
    }

    @SafeVarargs
    fun recordPairings(dateReported: LocalDate, vararg stringPairings: List<String>) {
        val pairings = stringPairings.map { teamMembers -> Pairing(teamMembers[0], teamMembers[1]) }
        recordPairings(dateReported, pairings)
    }

    fun recordPairings(dateReported: LocalDate, pairings: List<Pairing>) {
        val teamSetup: TeamSetup = TeamSetup(pairings.toSet(), dateReported)
        teamSetups = teamSetups.plus(element = teamSetup)
    }

    val previousTeamSetups: List<TeamSetup>
        get() {
            return teamSetups.sortedByDescending { it.dateReported }
        }
}

