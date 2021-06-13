package com.yoriessence.point.model.service;

import static com.yoriessence.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.yoriessence.point.model.dao.MemberPointDao;
import com.yoriessence.point.model.vo.MemberPoint;
import static com.yoriessence.common.JDBCTemplate.close;

public class MemberPointService {
	
	private MemberPointDao dao=new MemberPointDao();
	
	public List<MemberPoint> selectPointList(int cPage, int numPerpage, String memberId, String filter){
		Connection conn=getConnection();
		List<MemberPoint> list=dao.selectPointList(conn, cPage, numPerpage, memberId, filter);
		close(conn);
		return list;
	}
	
	public int selectPointCount(String memberId, String filter) {
		Connection conn=getConnection();
		int result=dao.selectPointCount(conn, memberId, filter);
		close(conn);
		return result;
	}
	
	public int selectPointSum(String memberId) {
		Connection conn=getConnection();
		int result=dao.selectPointSum(conn, memberId);
		close(conn);
		return result;
	}

}
