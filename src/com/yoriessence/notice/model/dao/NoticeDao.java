package com.yoriessence.notice.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.yoriessence.notice.model.vo.Notice;

import static com.yoriessence.common.JDBCTemplate.close;

public class NoticeDao {
	private Properties props=new Properties();
	
	public NoticeDao() {
		String path=NoticeDao.class.getResource("/notice/notice.properties").getPath();
		try {
			props.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Notice> noticeList(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Notice> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(props.getProperty("selectNoticeAll"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Notice n = new Notice();
				n.setNumber(rs.getInt("notice_no"));
				n.setTitle(rs.getString("notice_title"));
				n.setContent(rs.getString("notice_content"));
				n.setFilePath(rs.getString("filepath"));
				list.add(n);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public int noticeCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("noticeCount"));
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
	public Notice selectNotice(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Notice n=null;
		try {
			pstmt=conn.prepareStatement(props.getProperty("selectNotice"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				n =new Notice();
				n.setNumber(rs.getInt("notice_no"));
				n.setTitle(rs.getString("notice_title"));
				n.setContent(rs.getString("notice_content"));
				n.setFilePath(rs.getString("filepath"));
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return n;
	}
	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("insertNotice"));
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getContent());
			pstmt.setString(3, n.getFilePath());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int noticeUpdate(Connection conn, Notice n) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("noticeUpdate"));
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getContent());
			pstmt.setString(3, n.getFilePath());
			pstmt.setInt(4, n.getNumber());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int noticeDelete(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("noticeDelete"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}

}
