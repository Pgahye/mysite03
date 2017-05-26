package com.jx372.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.guestBookVo;




@Repository
public class guestBookDao {
	
	private Connection getConnection() throws SQLException {

		Connection conn = null;

		PreparedStatement pstmt = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			// 2. connection 하기

			String url = "jdbc:mysql://localhost:3306/webdb?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC Driver을 찾을수 없습니다. ");

		} // 드라이버 로드
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error: " + e);
		}

		return conn;

		}
	
public List<guestBookVo> getList(){
		
		Connection conn = null;
		Statement stmt=null;
		
		ResultSet rs=null;
		
		List<guestBookVo> list=new ArrayList<guestBookVo>();
		
		
		
		
		try{
			
			conn=getConnection();
			
			stmt = conn.createStatement();
			
			String sql="select no, name, message, date_format(date,'%Y-%m-%d') from guestbook order by date desc";
		
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				Long no=rs.getLong(1);
				String name=rs.getString(2);
				String message=rs.getString(3);
				String date=rs.getString(4);
				
				guestBookVo vo=new guestBookVo();
				
				vo.setNo(no);
				vo.setName(name);
				vo.setMessage(message);
				vo.setDate(date);
			
				
				list.add(vo);
				
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally{
			
			try{
				
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					
					stmt.close();
				}
				if(conn!=null){
					
					conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			
			
		}
		
		return list;
		
	}
	
	public boolean insert(guestBookVo vo){
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		
		
		
		
		try {
			conn= getConnection();
			
			String sql="insert into guestbook values(null, ? , ? , ?, now())";
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getMessage());
			
			int count = pstmt.executeUpdate();
			
			return count ==1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				
			if(pstmt != null){
				
				pstmt.close();
			}
			if(conn != null){
				conn.close();
				
			}
			}catch(SQLException e){
				
				e.printStackTrace();
			}
			
		}
		
		return false;
		
		
	}	public boolean delete(guestBookVo vo){
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		
		
		
		
		try {
			conn= getConnection();
			
			String sql="delete from guestbook where no=? and pwd=?";
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPwd());

			
			int count = pstmt.executeUpdate();
			
			return count ==1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				
			if(pstmt != null){
				
				pstmt.close();
			}
			if(conn != null){
				conn.close();
				
			}
			}catch(SQLException e){
				
				e.printStackTrace();
			}
			
		}
		
		return false;
		
		
	}

}
