package com.yoriessence.manager.model.dao;

import com.yoriessence.manager.model.vo.ManagerPage;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.yoriessence.common.JDBCTemplate.close;

public class ManagerDao {
    private Properties pp = new Properties();

    public ManagerDao() {
        try{
            String path =  ManagerDao.class.getResource("/manager/manager.properties").getPath();
            pp.load(new FileReader(path));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int countOrder(Connection conn) {
        PreparedStatement psmt = null;
        ResultSet rs =null;
        int result= 0;
        try{
            psmt = conn.prepareStatement(pp.getProperty("countOrder"));

            rs= psmt.executeQuery();

            if(rs.next()){
              result = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }
        return result;
    }

    public List<ManagerPage> getOrderList(Connection conn,int cPage, int numPerPage){
        PreparedStatement psmt = null;
        ResultSet rs =null;
        List<ManagerPage> result = new ArrayList<>();
        try{
            psmt = conn.prepareStatement(pp.getProperty("getOrderList"));
            psmt.setInt(1,(cPage-1)*numPerPage+1);
            psmt.setInt(2,numPerPage*cPage);

            rs= psmt.executeQuery();

            while (rs.next()){
                ManagerPage m = new ManagerPage();
                m.setRowNum(rs.getInt("rnum"));
                m.setOrderNumber(rs.getInt("order_number"));
                m.setMemberName(rs.getString("member_name"));
                m.setAmountPrice(rs.getInt("amountprice"));
                m.setPaymentMethod(rs.getString("payment_method"));
                m.setShippingStatus(rs.getString("shipping_status"));
                m.setPaymentDate(rs.getDate("payment_date"));
                m.setWaybill(rs.getString("waybill"));

                result.add(m);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }

    public List<ManagerPage> getSortRef2(Connection conn,String searchDate, String endDate, String delivery,int cPage, int numPerPage){
        PreparedStatement psmt = null;
        ResultSet rs =null;
        List<ManagerPage> result = new ArrayList<>();
        try{
            psmt = conn.prepareStatement(pp.getProperty("getSortRef2"));


            if(delivery.equals("all")){
                psmt = conn.prepareStatement(pp.getProperty("getSortRef2All"));
                psmt.setInt(1,(cPage-1)*numPerPage+1);
                psmt.setInt(2,numPerPage*cPage);

            }else if(delivery.equals("ready")){
                delivery="배송준비";
                psmt.setString(1,searchDate);
                psmt.setString(2,endDate);
                psmt.setString(3,delivery);
                psmt.setInt(4,(cPage-1)*numPerPage+1);
                psmt.setInt(5,numPerPage*cPage);

            }else if(delivery.equals("ing")){
                delivery="배송중";
                psmt.setString(1,searchDate);
                psmt.setString(2,endDate);
                psmt.setString(3,delivery);
                psmt.setInt(4,(cPage-1)*numPerPage+1);
                psmt.setInt(5,numPerPage*cPage);

            }else if(delivery.equals("finish")){
                delivery="배송완료";
                psmt.setString(1,searchDate);
                psmt.setString(2,endDate);
                psmt.setString(3,delivery);
                psmt.setInt(4,(cPage-1)*numPerPage+1);
                psmt.setInt(5,numPerPage*cPage);
            }


            rs= psmt.executeQuery();

            while (rs.next()){
                ManagerPage m = new ManagerPage();
                m.setRowNum(rs.getInt("rnum"));
                m.setOrderNumber(rs.getInt("order_number"));
                m.setMemberName(rs.getString("member_name"));
                m.setAmountPrice(rs.getInt("amountprice"));
                m.setPaymentMethod(rs.getString("payment_method"));
                m.setShippingStatus(rs.getString("shipping_status"));
                m.setPaymentDate(rs.getDate("payment_date"));
                m.setWaybill(rs.getString("waybill"));

                result.add(m);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }

    public List<ManagerPage> getSortRef3(Connection conn,String searchDate, String endDate, String searchCondition,String searchVal,int cPage, int numPerPage){
        PreparedStatement psmt = null;
        ResultSet rs =null;
        List<ManagerPage> result = new ArrayList<>();
        try{
//            psmt = conn.prepareStatement(pp.getProperty("getSortRef3"));

            if(searchCondition.equals("orderNum")){
                psmt = conn.prepareStatement(pp.getProperty("getSortRef3OrderNum"));
                psmt.setString(1,searchDate);
                psmt.setString(2,endDate);
                psmt.setString(3,searchVal);

            }else if(searchCondition.equals("memberName")){
                psmt = conn.prepareStatement(pp.getProperty("getSortRef3MemberName"));
                psmt.setString(1,searchDate);
                psmt.setString(2,endDate);
                psmt.setString(3,searchVal);

            }else if(searchCondition.equals("memberId")){
                psmt = conn.prepareStatement(pp.getProperty("getSortRef3MemberId"));
                psmt.setString(1,searchDate);
                psmt.setString(2,endDate);
                psmt.setString(3,searchVal);

            }else if(searchCondition.equals("productName")){
                psmt = conn.prepareStatement(pp.getProperty("getSortRef3ProductName"));
                psmt.setString(1,searchDate);
                psmt.setString(2,endDate);
                psmt.setString(3,searchVal);
            }

            psmt.setInt(4,(cPage-1)*numPerPage+1);
            psmt.setInt(5,numPerPage*cPage);

            rs= psmt.executeQuery();

            while (rs.next()){
                ManagerPage m = new ManagerPage();
                m.setRowNum(rs.getInt("rnum"));
                m.setOrderNumber(rs.getInt("order_number"));
                m.setMemberName(rs.getString("member_name"));
                m.setAmountPrice(rs.getInt("amountprice"));
                m.setPaymentMethod(rs.getString("payment_method"));
                m.setShippingStatus(rs.getString("shipping_status"));
                m.setPaymentDate(rs.getDate("payment_date"));
                m.setWaybill(rs.getString("waybill"));

                result.add(m);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }

    public int updateWaybill(Connection conn,String waybill,int orderNum){
        PreparedStatement psmt = null;
        int result = 0;

        try{
            psmt = conn.prepareStatement(pp.getProperty("updateWaybill"));

            psmt.setString(1,waybill);
            psmt.setInt(2,orderNum);

            result = psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            close(psmt);
        }

        return result;
    }

}
