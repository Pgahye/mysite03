package com.jx372.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.UserVo;
import com.jx372.mysite.vo.guestBookVo;




@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	
public boolean replyupdate(BoardVo vo) {

	vo.setOrder_no(vo.getOrder_no()+1);
	vo.setGroup_no(vo.getGroup_no());
		
	int count = sqlSession.update("board.replyupdate", vo);
	

		return count == 1;
		

}
public boolean insert(BoardVo vo){
	
			
		int count= sqlSession.insert("board.insert", vo);
		
		return count ==1 ;
		
		
	}

public int getsize(String keyword){
	
	
	int sum = sqlSession.selectOne("board.getsize",keyword);

	return sum;
}

public List<BoardVo> getList(int num, String keyword){
	
	
	
	Map<String, Object> map = new HashMap<String, Object>(); 
	map.put( "keyword", keyword ); 
	map.put( "num", (num-1)*5 ); 
	 
	 
	return sqlSession.selectList( "board.getList", map ); 

	
}

public BoardVo getNo (Long no){ //답글
	
	BoardVo vo = sqlSession.selectOne("board.getNo", no);

	return vo;
}
	
public BoardVo get (Long no){ //수정폼
	
	
	BoardVo vo = sqlSession.selectOne("board.get", no);
	
	
	return vo;
}

public boolean hitupdate(BoardVo vo) {

	int count = sqlSession.update("board.hitupdate", vo);

	return count ==1 ;
}

	public boolean update(BoardVo vo) {

		int count = sqlSession.update("board.update", vo);

		return count ==1 ;
	}
	
public boolean delete(BoardVo vo){
		
	int count = sqlSession.delete("board.delete", vo);

	return count ==1 ;
		
		
	}

public int getTotalCount( String keyword ) { 
		int totalCount = 0; 

 
 		Connection conn = null; 
 		PreparedStatement pstmt = null; 
 		ResultSet rs = null; 
 		 
 		try { 
 		//	conn = getConnection(); 
 			if( "".equals( keyword ) ) { 
 				String sql = "select count(*) from board"; 
 				pstmt = conn.prepareStatement(sql); 
 			} else {  
 				String sql = 
 					"select count(*)" + 
 					"  from board" + 
 					" where title like ? or content like ?"; 
 				pstmt = conn.prepareStatement(sql); 
 				 
				pstmt.setString(1, "%" + keyword + "%"); 
 				pstmt.setString(2, "%" + keyword + "%"); 
 			} 
			rs = pstmt.executeQuery(); 
		if( rs.next() ) { 
 				totalCount = rs.getInt( 1 ); 
 			} 
 		} catch (SQLException e) { 
 			System.out.println( "error:" + e ); 
 		} finally { 
			try { 
 				if( rs != null ) { 
 					rs.close(); 
 				} 
 				if( pstmt != null ) { 
 					pstmt.close(); 
 				} 
 				if( conn != null ) { 
 					conn.close(); 
 				} 
 			} catch ( SQLException e ) { 
				System.out.println( "error:" + e ); 
			}   
 		} 
 		 
 		return totalCount; 
 	} 



}
