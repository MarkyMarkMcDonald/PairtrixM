package pairtrix.domain

import org.junit.Test
import java.time.LocalDate

class TeamSetupTest {
    @Test
    fun testIntersect() {
        val firstSetup = TeamSetup(setOf(Pairing("a", "b"), Pairing("c", "d")), LocalDate.MAX)
        assert(firstSetup.intersect(setOf(Pairing("a", "b"))).equals(setOf(Pairing("a", "b"))))
    }

    @Test
    fun testCompairing() {
        val firstSetup = TeamSetup(setOf(Pairing("a", "b"), Pairing("c", "d")), LocalDate.MAX)
        val setupSharingAPairing = TeamSetup(setOf(Pairing("c", "d")), LocalDate.MAX)
        val setupNotSharingAPairings = TeamSetup(setOf(Pairing("a", "c"), Pairing("b", "d")), LocalDate.MAX)

        assert(firstSetup.compairing(setupNotSharingAPairings))
        assert(!firstSetup.compairing(setupSharingAPairing))
    }
}

