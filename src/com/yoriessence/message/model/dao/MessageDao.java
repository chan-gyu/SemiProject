package com.yoriessence.message.model.dao;

import com.yoriessence.message.model.vo.Message;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.yoriessence.common.JDBCTemplate.close;

public class MessageDao {
    private Properties pp = new Properties();

    public MessageDao() {
        try{
            String path =  MessageDao.class.getResource("/sql/message_sql.properties").getPath();
            pp.load(new FileReader(path));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int inputMessage(Connection conn,Message m){
        // 메세지 입력하는 메소드
        PreparedStatement psmt = null;
        int result =0;

        try{
            psmt = conn.prepareStatement(pp.getProperty("inputMessage"));

            psmt.setString(1,m.getMemberId());
            psmt.setString(2,m.getTargetId());
            psmt.setString(3,m.getSendMessage());

            result = psmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(psmt);
        }

        return result;
    }

    public Map<String, List<Message>> getMessage(Connection conn,Message m){
        // 메세지를 가져오는 메소드
        PreparedStatement psmt = null;
        ResultSet rs= null;
        Map<String, List<Message>> result = new HashMap<>();

        try{
            psmt = conn.prepareStatement(pp.getProperty("getMessage"));
            psmt.setString(1,m.getMemberId());
            psmt.setString(2,m.getTargetId());
            psmt.setString(3,m.getTargetId());
            psmt.setString(4,m.getMemberId());

            rs=psmt.executeQuery();

            while(rs.next()){
                List<Message> mList = new ArrayList<>();

                while(rs.next()){
                    Message mes = new Message();
                    mes.setMemberId(rs.getString("member_id"));
                    mes.setTargetId(rs.getString("targetid"));
                    mes.setSendMessage(rs.getString("sendmessage"));
                    mes.setSendDate(rs.getString("senddate"));

                    mList.add(mes);
                }
                result.put("message",mList);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(psmt);
        }

        return result;
    }

    public List<String> sendMList(Connection conn,String memberId){
        // 메세지를 누구에게 보냈는지 가져오는 메소드
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();

        try{
            psmt = conn.prepareStatement(pp.getProperty("sendMList"));
            psmt.setString(1,memberId);

            rs= psmt.executeQuery();

            while(rs.next()){
                String a = rs.getString("targetId");

                result.add(a);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }

    public String mesList(Connection conn,String memberId,String targetId){
        // 회원에게 보낸 가장 최근 메세지 내용을 가져오는 메소드
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String mes = "";

        try{
            psmt = conn.prepareStatement(pp.getProperty("mesList"));
            psmt.setString(1,memberId);
            psmt.setString(2,targetId);


            rs= psmt.executeQuery();

            if(rs.next()){
                mes = rs.getString("sendmessage");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return mes;
    }

}
