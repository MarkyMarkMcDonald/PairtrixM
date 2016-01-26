package pairtrix.domain

import java.time.LocalDate

class Application(private val teamMembersRepository: TeamMembersRepository) {

    private var teamSetups = listOf<TeamSetup>()

    private val pairalizer: Pairalizer = RandomPairalizer()

    fun pairings(): List<Pairing> {
        return pairalizer.pairings(teamMembersRepository.findAll())
    }

    fun addTeamMember(name: String) {
        teamMembersRepository.addMember(name)
    }

    @SafeVarargs
    fun recordPairings(dateReported: LocalDate, vararg stringPairings: List<String>) {
        val pairings = stringPairings.map { teamMembers -> Pairing(teamMembers[0], teamMembers[1]) }
        val teamSetup = TeamSetup(pairings, dateReported)
        teamSetups = teamSetups.plus(teamSetup)
    }

    val previousTeamSetups: List<TeamSetup>
        get() {
            return teamSetups.sortedByDescending { it.dateReported }
        }
}

