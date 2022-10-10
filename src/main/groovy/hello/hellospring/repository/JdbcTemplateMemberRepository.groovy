package hello.hellospring.repository

import hello.hellospring.domain.Member
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert

import javax.sql.DataSource
import java.sql.ResultSet
import java.sql.SQLException

class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate

    //@Autowired //생성자가 1개이면 Autowired를 생략할 수 있고, Spring이 자동으로 Injection해준다
    JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource)
    }

    @Override
    Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id")
        Map<String, Object> parameters = new HashMap<>()
        parameters.put("name", member.getName())
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters))
        member.setId(key.longValue())
        return member
    }

    @Override
    Optional<Member> findById(Long id) {
        // Groovy style
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id)

        return result.stream().findAny()
    }

    @Override
    Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name)
        return result.stream().findAny()
    }

    @Override
    List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper())
    }

    @Override
    void clearStore() {
        // TODO
    }

    private RowMapper<Member> memberRowMapper() {
        return {rs, num ->   // cf. java lambda (rs, num) -> {}
            Member member = new Member()
            member.id = rs.getLong("id")
            member.name = rs.getString("name")
            return member
        }
    }
}
