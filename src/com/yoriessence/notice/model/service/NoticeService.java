package com.yoriessence.notice.model.service;

import static com.yoriessence.common.JDBCTemplate.getConnection;
import static com.yoriessence.common.JDBCTemplate.close;
import static com.yoriessence.common.JDBCTemplate.commit;
import static com.yoriessence.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.yoriessence.notice.model.dao.NoticeDao;
import com.yoriessence.notice.model.vo.Notice;

public class NoticeService {
	private NoticeDao dao = new NoticeDao();
	
	public List<Notice> noticeList(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Notice> list=dao.noticeList(conn, cPage, numPerpage);
		close(conn);
		return list;
	}
	public int noticeCount() {
		Connection conn=getConnection();
		int result=dao.noticeCount(conn);
		close(conn);
		return result;
	}
	public int insertNotice(Notice n) {
		Connection conn=getConnection();
		int result=dao.insertNotice(conn, n);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	public Notice selectNotice(int no) {
		Connection conn=getConnection();
		Notice n=dao.selectNotice(conn, no);
		close(conn);
		return n;
	}
	public int noticeUpdate(Notice n) {
		Connection conn=getConnection();
		int result=dao.noticeUpdate(conn,n);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int noticeDelete(int no) {
		Connection conn=getConnection();
		int result=dao.noticeDelete(conn, no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}
