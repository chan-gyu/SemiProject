package com.yoriessence.question.model.service;

import static com.yoriessence.common.JDBCTemplate.close;
import static com.yoriessence.common.JDBCTemplate.commit;
import static com.yoriessence.common.JDBCTemplate.getConnection;
import static com.yoriessence.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.yoriessence.question.model.dao.QuestionDao;
import com.yoriessence.question.model.vo.Question;
import com.yoriessence.question.model.vo.QuestionComment;

public class QuestionService {
	private QuestionDao dao = new QuestionDao();
	
	public List<Question> questionList(int cPage, int numPerpage, String loginMemberId){
		Connection conn=getConnection();
		List<Question> list=dao.questionList(conn, cPage, numPerpage, loginMemberId);
		close(conn);
		return list;
	}
	
	public List<Question> questionAdminList(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Question> list=dao.questionAdminList(conn, cPage, numPerpage);
		close(conn);
		return list;
	}
	
	public int questionCount(){
		Connection conn=getConnection();
		int result=dao.questionCount(conn);
		close(conn);
		return result;
	}
	public int insertQuestion(Question q) {
		Connection conn=getConnection();
		int result=dao.insertQuestion(conn, q);
		if(result>0)commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	
	public Question selectQuestion(int no) {
		Connection conn=getConnection();
		Question q=dao.selectQuestion(conn, no);
		close(conn);
		return q;
	}
	public int questionUpdate(Question q) {
		Connection conn=getConnection();
		int result=dao.questionUpdate(conn, q);
		if(result>0)commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int insertQuestionComment(QuestionComment qc) {
		Connection conn=getConnection();
		int result=dao.insertQuestionComment(conn, qc);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public QuestionComment selectQuestionComment(int no) {
		Connection conn=getConnection();
		QuestionComment qc=dao.selectQuestionComment(conn, no);
		close(conn);
		return qc;
	}
	public int questionDelete(int no) {
		Connection conn=getConnection();
		int result=dao.questionDelete(conn, no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int questionCommentDelete(int no) {
		Connection conn=getConnection();
		int result=dao.questionCommentDelete(conn, no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
}
