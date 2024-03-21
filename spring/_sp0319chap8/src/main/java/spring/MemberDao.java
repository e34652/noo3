package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {
	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL = ?", 
				new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member(
						rs.getString("EMAIL"), 
						rs.getString("PASSWORD"), 
						rs.getString("NAME"),
						rs.getTimestamp("REGDATE").toLocalDateTime());
				member.setId(rs.getLong("ID"));
				return member;
			}
		}, email);

		return results.isEmpty() ? null : results.get(0);
	}

	public Member selectByEmail2(String email) {
		Member results = jdbcTemplate.queryForObject("select * from MEMBER where EMAIL = ?", 
				new RowMapper<Member>() {
			//email도 unique인데 멤버 하나 뽑는데 List를 쓴 이유 
			//QueryForObject는 값이 0개면 EmptyResult, 값이 두개 이상이면 IncorrectResult 예외가 발생함 
			//null 처리를 해도 예외가 발생하게 됨 따라서 count 처럼 값이 무조건 하나만 나올때 사용하는 게 좋음
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member(
						rs.getString("EMAIL"), 
						rs.getString("PASSWORD"), 
						rs.getString("NAME"),
						rs.getTimestamp("REGDATE").toLocalDateTime());
				member.setId(rs.getLong("ID"));
				return member;
			}
		}, email);

		return results;
	}
	
	public Member selectByEmai3(String email) {
		List<Member> results = jdbcTemplate.query
				("select * from MEMBER where EMAIL = ?",
				(rs, rowNum) ->{
					Member member = new Member(
						rs.getString("EMAIL"), 
						rs.getString("PASSWORD"), 
						rs.getString("NAME"),
						rs.getTimestamp("REGDATE").toLocalDateTime());
				member.setId(rs.getLong("ID"));
				return member;	
		}, email);

		return results.isEmpty() ? null : results.get(0);
	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query
				("select * from MEMBER",
				(rs, rowNum) ->{
					Member member = new Member(
						rs.getString("EMAIL"), 
						rs.getString("PASSWORD"), 
						rs.getString("NAME"),
						rs.getTimestamp("REGDATE").toLocalDateTime());
				member.setId(rs.getLong("ID"));
				return member;	
		});
		return results.isEmpty() ? null : results;
	}
	

	public void insert(Member member) {
		jdbcTemplate.update("insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) "
				+ "values(?,?,?,now())", member.getEmail(), member.getPassword(), member.getName());
	}
	
	public void insert2(Member member) {
		// key holder를 쓰기 위해 preparedStatement 사용
		//insert 하자마자 자동생성된 id값을 알기 위한 코드
	
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) "
						+ "values(?,?,?,now())", new String[]{"ID"});
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}

	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?", member.getName(), member.getPassword(), member.getEmail());
	}
	
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from member" , Integer.class);
	}
}
