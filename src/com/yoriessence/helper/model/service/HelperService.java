package com.yoriessence.helper.model.service;

import static com.yoriessence.common.JDBCTemplate.close;
import static com.yoriessence.common.JDBCTemplate.commit;
import static com.yoriessence.common.JDBCTemplate.getConnection;
import static com.yoriessence.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.yoriessence.helper.model.dao.HelperDao;
import com.yoriessence.helper.model.vo.Helper;

public class HelperService {
	private HelperDao dao = new HelperDao();
	
	public List<Helper> helperList(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Helper> list=dao.helperList(conn, cPage, numPerpage);
		close(conn);
		return list;
	}
	public int helperCount() {
		Connection conn=getConnection();
		int result=dao.helperCount(conn);
		close(conn);
		return result;
	}
	public int insertHelper(Helper h) {
		Connection conn=getConnection();
		int result=dao.insertHelper(conn, h);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	public Helper selectHelper(int no) {
		Connection conn=getConnection();
		Helper h=dao.selectHelper(conn, no);
		close(conn);
		return h;
	}
	public int HelperUpdate(Helper h) {
		Connection conn=getConnection();
		int result=dao.helperUpdate(conn,h);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int helperDelete(int no) {
		Connection conn=getConnection();
		int result=dao.helperDelete(conn, no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}
