package pairtrix.domain

interface TeamMembersRepository {
    fun addMember(name: String)

    fun findAll(): List<String>
}
