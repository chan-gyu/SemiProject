package com.yoriessence.helper.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.yoriessence.helper.model.vo.Helper;

import static com.yoriessence.common.JDBCTemplate.close;

public class HelperDao {
	private Properties props=new Properties();
	
	public HelperDao() {
		String path=HelperDao.class.getResource("/helper/helper.properties").getPath();
		try {
			props.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Helper> helperList(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Helper> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(props.getProperty("selectHelperAll"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Helper h = new Helper();
				h.setNumber(rs.getInt("helper_no"));
				h.setTitle(rs.getString("helper_title"));
				h.setContent(rs.getString("helper_content"));
				list.add(h);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public int helperCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("helperCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public Helper selectHelper(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Helper h=null;
		try {
			pstmt=conn.prepareStatement(props.getProperty("selectHelper"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				h =new Helper();
				h.setNumber(rs.getInt("helper_no"));
				h.setTitle(rs.getString("helper_title"));
				h.setContent(rs.getString("helper_content"));
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return h;
	}
	public int insertHelper(Connection conn, Helper h) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("insertHelper"));
			pstmt.setString(1, h.getTitle());
			pstmt.setString(2, h.getContent());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int helperUpdate(Connection conn, Helper h) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("helperUpdate"));
			pstmt.setString(1, h.getTitle());
			pstmt.setString(2, h.getContent());
			pstmt.setInt(3, h.getNumber());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int helperDelete(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("helperDelete"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
}
