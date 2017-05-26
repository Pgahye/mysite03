package com.jx372.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.jx372.mysite.exception.UserDaoException;
import com.jx372.mysite.vo.UserVo;


@Repository
public class UserDao {
	
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
	

	
	public UserVo get (Long no){ //수정폼
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		UserVo vo = null;
		ResultSet rs = null;
		
		try{
			
			conn = getConnection();
			
			String sql="select no, name,email, gender from user where no = ? ";
			
			
			conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
		
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //2줄이상이면 
				
				 no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String gender =rs.getString(4);
				
				vo=new UserVo();
				
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setGender(gender);
				
			}
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}finally{
			
			try{
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					
					pstmt.close();
				}
				
				if(conn!=null){
					
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}

		return vo;
	}
	//로그인 처리 
	public UserVo get(String email,String password ) throws UserDaoException {
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		UserVo vo = null;
		ResultSet rs = null;
		
		try{
			
			conn = getConnection();
			String sql="select no,name,email,gender from user where email=? and password=password(?)";
			
			
			conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //2줄이상이면 
				
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				//String email=rs.getString(3);
				String gender=rs.getString(4);
				vo=new UserVo();
				
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setGender(gender);
				
			}
			
			
		}catch(SQLException e){
			
			throw new UserDaoException("User Not Found");
			
		
			
		}finally{
			
			try{
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					
					pstmt.close();
				}
				
				if(conn!=null){
					
					conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			
		}

		
		return vo;
		
	}
	
	public boolean insert(UserVo vo) {
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		
		try{
			
			conn = getConnection();
			
			String sql="insert into user values ( null, ?, ?, password(?), ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2,vo.getEmail());
			pstmt.setString(3,vo.getPassword());
			pstmt.setString(4,vo.getGender());
			
			int count = pstmt.executeUpdate();
			
			return count==1;
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}finally{
			
			try{
				
				
				if(conn!=null){
					
					conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			
		}
		
		
		
		return false;
	}
	
public boolean update(UserVo vo) {
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		
		try{
			
			conn = getConnection();
			
			String sql="update user set name=?, gender =? , password=password(?) where no=?";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2,vo.getGender());
			pstmt.setString(3,vo.getPassword());
			pstmt.setLong(4, vo.getNo());
		
			
			int count = pstmt.executeUpdate();
			
			return count==1;
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}finally{
			
			try{
				
				
				if(conn!=null){
					
					conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			
		}
		
		
		
		return false;
	}
	
public boolean smallupdate(UserVo vo) {
		
		Connection conn = null;
		PreparedStatement pstmt=null;
		
		try{
			
			conn = getConnection();
			String sql="update user set name=?, gender = ? where no=?";
		
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2,vo.getGender());
			pstmt.setLong(3, vo.getNo());
		
			
			int count = pstmt.executeUpdate();
			
			return count==1;
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}finally{
			
			try{
				
				
				if(conn!=null){
					
					conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			
		}
		
		
		
		return false;
	}

}
