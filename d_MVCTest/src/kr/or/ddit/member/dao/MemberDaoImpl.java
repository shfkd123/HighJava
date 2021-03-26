package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insertMember(Connection conn, MemberVO mv) throws SQLException {

		String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) "
				    + " values (?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mv.getMemId());
		pstmt.setString(2, mv.getMemName());
		pstmt.setString(3, mv.getMemTel());
		pstmt.setString(4, mv.getMemAddr());
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public boolean checkMember(Connection conn, String memId) throws SQLException {
		
		boolean chk = false;
		
		String sql = "select count(*) as cnt from mymember where mem_id = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		rs = pstmt.executeQuery();
		
		int cnt = 0;
		while(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(cnt > 0) {
			chk = true;
		}
		
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberList(Connection conn) throws SQLException {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery("select * from mymember");
		
		while(rs.next()) {
			MemberVO mv = new MemberVO();
			
			String memId = rs.getString("mem_id");
			String memName = rs.getString("mem_name");
			String memTel = rs.getString("mem_tel");
			String memAddr = rs.getString("mem_addr");
			
			mv.setMemId(memId);
			mv.setMemName(memName);
			mv.setMemTel(memTel);
			mv.setMemAddr(memAddr);
			
			memList.add(mv);
		}
		
		return memList;
	}

	@Override
	public int updateMember(Connection conn, MemberVO mv) throws SQLException {
		
		String sql = "update mymember "
				   + " set mem_name = ? "
				   + ", mem_tel = ? "
				   + ", mem_addr = ? "
				   + " where mem_id = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mv.getMemName());
		pstmt.setString(2, mv.getMemTel());
		pstmt.setString(3, mv.getMemAddr());
		pstmt.setString(4, mv.getMemId());
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		
		String sql = "delete from mymember where mem_id = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

}
