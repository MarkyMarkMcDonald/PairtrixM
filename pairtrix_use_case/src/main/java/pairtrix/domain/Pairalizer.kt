package pairtrix.domain

import pairtrix.random.shuffle

interface Pairalizer {
    fun pairings(teamMembers: List<String>): List<Pairing>
}

class RandomPairalizer : Pairalizer {
    override fun pairings(teamMembers: List<String>): List<Pairing> {
        if (teamMembers.size == 0) return listOf()
        val randomizedTeam = teamMembers.shuffle()
        return aggregateByTwo(randomizedTeam)
    }

    /*
     input:
     A B C

     ->
     create sublist:
     A B C
     B C Null

     ->
     remove even elements:
     A C
     B Null
     */
    private fun aggregateByTwo(team: List<String>): List<Pairing> {
        return team.zip(team.subList(1, team.size).plus(null)).filterIndexed { i, pair -> i % 2 == 0 }
                .map { Pairing(it.first, it.second) }
    }
}
