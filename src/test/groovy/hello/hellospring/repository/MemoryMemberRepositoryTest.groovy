package hello.hellospring.repository

import hello.hellospring.domain.Member
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*

class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository()

    @AfterEach
    void cleanup() {
        repository.clearStore()
    }

    @Test
    void save() {
        Member member = new Member()
        member.with {
            name = "spring"
        }

        repository.save(member)

        Member result = repository.findById(member.id)?.get()
        result == member
    }

    @Test
    void findByName() {
        Member member1 = new Member()
        member1.setName('spring1')
        repository.save(member1)

        Member member2 = new Member()
        member2.setName('spring2')
        repository.save(member2)

        Member result = repository.findByName('spring1').get()
        result == member1
    }

    @Test
    void findAll() {
        Member member1 = new Member()
        member1.name = 'spring1'
        repository.save(member1)

        Member member2 = new Member()
        member2.name = 'spring2'
        repository.save(member2)
    }
}