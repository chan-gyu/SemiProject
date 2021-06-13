package com.yoriessence.point.model.dao;

import static com.yoriessence.common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.yoriessence.point.model.vo.MemberPoint;

public class MemberPointDao {
	
	private Properties prop=new Properties();
	
	public MemberPointDao() {
		String path=MemberPointDao.class.getResource("/sql/point_sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberPoint> selectPointList(Connection conn, int cPage, int numPerpage, String memberId, String filter){
		List<MemberPoint> list=new ArrayList();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			if(filter==null||filter=="") {
				sql=prop.getProperty("selectPointList");
			}else if(filter.equals("used")) {
				sql=prop.getProperty("selectUsedPoint");
			}else {
				sql=prop.getProperty("selectEarnedPoint");
			}
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			System.out.println((cPage-1)*numPerpage+1+" "+cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberPoint p=new MemberPoint();
				p.setPointNo(rs.getInt("point_no"));
				p.setMemberId(memberId);
				p.setPointInfo(rs.getString("point_info"));
				p.setPoint(rs.getInt("point"));
				p.setPointDate(rs.getDate("point_date"));
				list.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int selectPointCount(Connection conn, String memberId, String filter) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql="";
		try {
			if(filter==null||filter=="") {
				sql=prop.getProperty("selectPointCount");
			}else if(filter.equals("used")) {
				sql=prop.getProperty("selectUsedCount");
			}else {
				sql=prop.getProperty("selectEarnedCount");
			}
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
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
	
	public int selectPointSum(Connection conn, String memberId) {
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectPointSum"));
			pstmt.setString(1, memberId);
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

}
