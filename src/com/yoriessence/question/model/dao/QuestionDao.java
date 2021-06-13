package com.yoriessence.question.model.dao;

import static com.yoriessence.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.yoriessence.notice.model.dao.NoticeDao;
import com.yoriessence.question.model.vo.Question;
import com.yoriessence.question.model.vo.QuestionComment;

public class QuestionDao {
	private Properties props=new Properties();
	
	public QuestionDao() {
		String path=NoticeDao.class.getResource("/serviceCenter/question.properties").getPath();
		try {
			props.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Question> questionList(Connection conn, int cPage, int numPerpage ,String loginMemberId){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Question> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(props.getProperty("selectQuestionAll"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			pstmt.setString(3, loginMemberId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Question q = new Question();
				q.setQuestionNumber(rs.getInt("key"));
				q.setMemberId(rs.getString("memberid"));
				q.setQuestionContent(rs.getString("question_content"));
				q.setQuestionTitle(rs.getString("question_title"));
				q.setQuestionPic(rs.getString("Question_pic"));
				list.add(q);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<Question> questionAdminList(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Question> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(props.getProperty("selectQuestionAdmin"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Question q = new Question();
				q.setQuestionNumber(rs.getInt("key"));
				q.setMemberId(rs.getString("memberid"));
				q.setQuestionContent(rs.getString("question_content"));
				q.setQuestionTitle(rs.getString("question_title"));
				q.setQuestionPic(rs.getString("Question_pic"));
				list.add(q);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int questionCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("questionCount"));
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
	public int insertQuestion(Connection conn, Question q) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("insertQuestion"));
			pstmt.setString(1, q.getMemberId());
			pstmt.setString(2, q.getQuestionContent());
			pstmt.setString(3, q.getQuestionTitle());
			pstmt.setString(4, q.getQuestionPic());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public Question selectQuestion(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Question q=null;
		try {
			pstmt=conn.prepareStatement(props.getProperty("selectQuestion"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				q =new Question();
				q.setQuestionNumber(rs.getInt("key"));
				q.setMemberId(rs.getString("memberid"));
				q.setQuestionContent(rs.getString("question_content"));
				q.setQuestionTitle(rs.getString("question_title"));
				q.setQuestionPic(rs.getString("Question_pic"));
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return q;
	}
	
	public int questionUpdate(Connection conn, Question q) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("questionUpdate"));
			pstmt.setString(1, q.getMemberId());
			pstmt.setString(2, q.getQuestionTitle());
			pstmt.setString(3, q.getQuestionContent());
			pstmt.setString(4, q.getQuestionPic());
			pstmt.setInt(5, q.getQuestionNumber());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int insertQuestionComment(Connection conn, QuestionComment qc) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("insertQuestionComment"));
			pstmt.setString(1, qc.getCommentNumber());
			pstmt.setString(2, qc.getCommentMemberId());
			pstmt.setString(3, qc.getCommentPic());
			pstmt.setString(4, qc.getCommentContent());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public QuestionComment selectQuestionComment(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		QuestionComment qc=null;
		try {
			pstmt=conn.prepareStatement(props.getProperty("selectQuestionComment"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				qc =new QuestionComment();
				qc.setCommentNumber(rs.getString("key"));
				qc.setCommentMemberId(rs.getString("memberid"));
				qc.setCommentPic(rs.getString("image"));
				qc.setCommentContent(rs.getString("content"));
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return qc;
	}
	public int questionDelete(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("questionDelete"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	public int questionCommentDelete(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("questionCommentDelete"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
}
