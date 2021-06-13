package com.yoriessence.manager.model.service;

import com.yoriessence.manager.model.dao.ManagerDao;
import com.yoriessence.manager.model.vo.ManagerPage;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import static com.yoriessence.common.JDBCTemplate.getConnection;
import static com.yoriessence.common.JDBCTemplate.close;
import static com.yoriessence.common.JDBCTemplate.commit;
import static com.yoriessence.common.JDBCTemplate.rollback;

public class ManagerService {
    private Properties pp = new Properties();
    private ManagerDao dao = new ManagerDao();

    public ManagerService() {
        try{
            String path = ManagerService.class.getResource("/driver/driver.properties").getPath();
            pp.load(new FileReader(path));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int countOrder(){
        Connection conn = getConnection();
        int result = dao.countOrder(conn);
        close(conn);
        return result;
    }

    public List<ManagerPage> getOrderList(int cPage,int numPerPage){
        Connection conn = getConnection();
        List<ManagerPage> result = dao.getOrderList(conn,cPage,numPerPage);
        close(conn);
        return result;
    }

    // 관리자 페이지 정렬
    public List<ManagerPage> getSortRef2(String searchDate, String endDate, String delivery,int cPage, int numPerPage){
        Connection conn = getConnection();
        List<ManagerPage> result = dao.getSortRef2(conn,searchDate,endDate,delivery,cPage,numPerPage);
        close(conn);
        return result;
    }

    public List<ManagerPage> getSortRef3(String searchDate, String endDate, String searchCondition,String searchVal,int cPage, int numPerPage){
        Connection conn = getConnection();
        List<ManagerPage> result = dao.getSortRef3(conn,searchDate,endDate,searchCondition,searchVal,cPage,numPerPage);
        close(conn);
        return result;
    }

    public int updateWaybill(String waybill, int orderNum){
        Connection conn = getConnection();
        int result = dao.updateWaybill(conn,waybill,orderNum);

        if(result>0)commit(conn);
        else rollback(conn);

        close(conn);
        return result;
    }

}
