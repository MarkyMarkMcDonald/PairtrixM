package pairtrix.domain

import pairtrix.random.shuffle

interface Pairalizer {
    fun pairings(teamMembers: List<String>, previousTeamSetups: List<TeamSetup>): List<Pairing>
}

class RandomPairalizer : Pairalizer {
    override fun pairings(teamMembers: List<String>, previousTeamSetups: List<TeamSetup>): List<Pairing> {
        if (teamMembers.size == 0) return listOf()

        var pairs: List<Pairing> = randomPairs(teamMembers)

        while (previousTeamSetups.any { teamSetup -> teamSetup.compairing(pairs.toSet()) }) {
            pairs = randomPairs(teamMembers)
        }

        return pairs
    }

    private fun randomPairs(teamMembers: List<String>): List<Pairing> {
        val randomizedTeam = teamMembers.shuffle()
        return aggregateByTwo(randomizedTeam)
    }

    private fun aggregateByTwo(team: List<String>): List<Pairing> {
        val withoutFirstMember: List<String> = team.drop(1)

        val subListWithNull = listOf<String?>(null).plus(withoutFirstMember).reversed()

        return team.zip(subListWithNull)
                .filterIndexed { i, pair -> i % 2 == 0 } // drop overlapping pairs
                .map { Pairing(it.first, it.second) }
    }
}
