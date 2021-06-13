package com.yoriessence.message.model.service;

import com.yoriessence.message.model.dao.MessageDao;
import com.yoriessence.message.model.vo.Message;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.yoriessence.common.JDBCTemplate.getConnection;
import static com.yoriessence.common.JDBCTemplate.close;
import static com.yoriessence.common.JDBCTemplate.commit;
import static com.yoriessence.common.JDBCTemplate.rollback;


public class MessageService {
    private Properties pp = new Properties();
    private MessageDao dao = new MessageDao();

    public MessageService() {
        try{
            String path = MessageService.class.getResource("/driver/driver.properties").getPath();
            pp.load(new FileReader(path));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void inputMessage(Message m){
        // 메세지 입력하는 서비스

        Connection conn = getConnection();
        int result = dao.inputMessage(conn,m);

        if(result>0) commit(conn);
        else rollback(conn);

        close(conn);
    }

    public Map<String, List<Message>> getMessage(Message m){
        // 보내고 받은 메세지 전체 가져오는 서비스
        Connection conn = getConnection();
        Map<String, List<Message>> result = dao.getMessage(conn,m);

        close(conn);

        return result;
    }

    public List<String> sendMList(String memberId){
        // 누구한테 보냈는지 목록 가져오는 서비스
        Connection conn = getConnection();
        List<String> res = dao.sendMList(conn,memberId);

        close(conn);

        return res;
    }

    public String mesList(String memberId,String targetId){
        // 가장 최근에 보낸 메세지 내용 가져옴
        Connection conn = getConnection();
        String res = dao.mesList(conn,memberId,targetId);

        close(conn);

        return res;
    }

}
