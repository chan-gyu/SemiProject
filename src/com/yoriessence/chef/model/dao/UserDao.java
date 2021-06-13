package com.yoriessence.chef.model.dao;

import static com.yoriessence.common.JDBCTemplate.*;

import com.yoriessence.chef.model.vo.*;
import com.yoriessence.recipe.model.vo.Recipe;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDao {
    private Properties pp = new Properties();

    public UserDao() {
        try{
            String path =  UserDao.class.getResource("/chef/chef.properties").getPath();
            pp.load(new FileReader(path));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 아이디를 기준으로 유저 정보가져옴
    public User userInfo(Connection conn, String id){
        PreparedStatement psmt = null;
        ResultSet rs = null;
        User u = null;

        try{
            psmt = conn.prepareStatement(pp.getProperty("getMemberInfo"));
            psmt.setString(1,id);

            rs= psmt.executeQuery();

            if(rs.next()){
                u= new User();
                u.setMemberId(rs.getString("member_id"));
                u.setMemberName(rs.getString("member_name"));
                u.setMemberEmail(rs.getString("member_email"));
                u.setMemberNickName(rs.getString("member_nickname"));
                u.setMemberAddress(rs.getString("member_address"));
                u.setMemberGrade(rs.getString("member_grade"));
                u.setMemberPhone(rs.getString("member_phone"));
                u.setMemberPoint(rs.getInt("member_point"));

            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return u;
    }

    // 셰프 등급만 추천순으로 조회하기, 페이징처리
    public List<User> chefRankList(Connection conn, int cPage, int numPerPage){
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<User> result = new ArrayList<>();

        try{
            psmt = conn.prepareStatement(pp.getProperty("chefRankList"));
            psmt.setInt(1,(cPage-1)*numPerPage+1);
            psmt.setInt(2,cPage*numPerPage);

            rs= psmt.executeQuery();

            while(rs.next()){
                User u = new User();
                u.setMemberId(rs.getString("memberid"));
                u.setMemberName(rs.getString("member_name"));
                u.setMemberNickName(rs.getString("member_nickname"));
                u.setRecommendCount(rs.getInt("recommend_count"));

                result.add(u);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }

    public List<Profile> chefProfileAll(Connection conn){
        // 셰프등급의 유저의 프로필을 추천순으로 정렬해서 가져옴
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Profile> chefProfileAll = new ArrayList<>();

        try{
            psmt = conn.prepareStatement(pp.getProperty("chefProfileAll"));

            rs = psmt.executeQuery();

            while(rs.next()){
                Profile p = new Profile();

                p.setMemberId(rs.getString("memberid"));
                p.setProfileName(rs.getString("profile_name"));
                p.setSelfIntro(rs.getString("profile_selfintro"));
                p.setProfilePic(rs.getString("profile_pic"));
                p.setProfileSnsUrl1(rs.getString("profile_SNS_URL_1"));
                p.setProfileSnsUrl2(rs.getString("profile_SNS_URL_2"));

                chefProfileAll.add(p);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return chefProfileAll;
    }

    // 셰프가 몇명인지 세는 메소드
    public int countChefList(Connection conn){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
       try{
           pstmt = conn.prepareStatement(pp.getProperty("countChefList"));

           rs = pstmt.executeQuery();

           if(rs.next()){
            result = rs.getInt(1);
           }

       }catch (SQLException e){
           e.printStackTrace();
       }finally {
           close(rs);
           close(pstmt);
       }

       return result;
    }

    // 셰프 검색하면 프로필 가져오는 메소드
    public List<Profile> chefProfile(Connection conn, String chefName){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Profile> chefProfile = new ArrayList<>();
        Profile p = null;
        try{
            pstmt = conn.prepareStatement(pp.getProperty("chefProfile"));

            pstmt.setString(1,chefName);

            rs = pstmt.executeQuery();

            if(rs.next()){
                p = new Profile();
                p.setMemberNickName(rs.getString("member_nickname"));
                p.setMemberId(rs.getString("memberid"));
                p.setProfileName(rs.getString("profile_name"));
                p.setSelfIntro(rs.getString("profile_selfintro"));
                p.setProfilePic(rs.getString("profile_pic"));
                p.setProfileSnsUrl1(rs.getString("profile_SNS_URL_1"));
                p.setProfileSnsUrl2(rs.getString("profile_SNS_URL_2"));

                chefProfile.add(p);
            }else{
                chefProfile=null;
                // 조회 결과가 없다면
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }

        return chefProfile;
    }

    public List<Profile> reGetProfile(Connection conn, String chefName){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Profile> chefProfile = new ArrayList<>();
        Profile p = null;
        try{
            pstmt = conn.prepareStatement(pp.getProperty("reGetProfile"));

            pstmt.setString(1,chefName);

            rs = pstmt.executeQuery();

            if(rs.next()){
                p = new Profile();
                p.setMemberNickName(rs.getString("member_nickname"));
                p.setMemberId(rs.getString("memberid"));
                p.setProfileName(rs.getString("profile_name"));
                p.setSelfIntro(rs.getString("profile_selfintro"));
                p.setProfilePic(rs.getString("profile_pic"));
                p.setProfileSnsUrl1(rs.getString("profile_SNS_URL_1"));
                p.setProfileSnsUrl2(rs.getString("profile_SNS_URL_2"));

                chefProfile.add(p);
            }else{
                chefProfile=null;
                // 조회 결과가 없다면
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }

        return chefProfile;
    }

    public List<RecipeRecommend> chefProfile2(Connection conn, String chefName){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<RecipeRecommend> chefProfile = new ArrayList<>();
        RecipeRecommend p = null;
        try{
            pstmt = conn.prepareStatement(pp.getProperty("chefProfile"));

            pstmt.setString(1,chefName);

            rs = pstmt.executeQuery();

            if(rs.next()){
                p = new RecipeRecommend();

                p.setMemberId(rs.getString("memberid"));
                p.setProfileName(rs.getString("profile_name"));
                p.setProfileSelfIntro(rs.getString("profile_selfintro"));
                p.setProfilePic(rs.getString("profile_pic"));
                p.setProfileSNSUrl1(rs.getString("profile_SNS_URL_1"));
                p.setProfileSNSUrl2(rs.getString("profile_SNS_URL_2"));

                chefProfile.add(p);
            }else{
                chefProfile=null;
                // 조회 결과가 없다면
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }

        return chefProfile;
    }


    // 레시피 리스트 가져오는 메소드
    public List<Recipe> getRecipe(Connection conn, String chefName,int cPage,int numPerPage){
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Recipe> recipeList = new ArrayList<>();

        try{
            psmt=conn.prepareStatement(pp.getProperty("getRecipe"));

            psmt.setString(1,chefName);
            psmt.setInt(2,(cPage-1)*numPerPage+1);
            psmt.setInt(3,cPage*numPerPage);

            rs = psmt.executeQuery();

            while(rs.next()){
                Recipe r = new Recipe();
                r.setRecipeEnrollNo(rs.getInt("Recipe_enroll_no"));
                r.setMemberId(rs.getString("member_id"));
                r.setRecipeTitle(rs.getString("recipe_title"));
                r.setRepresentPicture(rs.getString("represent_picture"));
                r.setRecipeVideoAddress(rs.getString("recipe_video_address"));
                r.setRecipeCategory(rs.getString("recipe_category"));
                r.setRecipeInfoHowmany(rs.getInt("recipe_info_howmany"));
                r.setRecipeInfoTime(rs.getInt("recipe_info_time"));
                r.setRecipeDifficult(rs.getString("recipe_difficult"));
                r.setRecipeProcedure(rs.getString("recipe_tip"));
                r.setRecipeViewCount(rs.getInt("recipe_view_count"));
                r.setRecipeEnrollDate(rs.getDate("recipe_enroll_date"));

                recipeList.add(r);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return recipeList;
    }

    public List<RecipeRecommend> recipeRecommendNum(Connection conn, String chefId, String sortVal,int cPage,int numPerPage){
        // ajax로 요청시
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<RecipeRecommend> recipeRecommend = new ArrayList<>();

        try {


           if(sortVal.equals("조회순")){
                psmt=conn.prepareStatement(pp.getProperty("sortViewCount"));
                psmt.setString(1,chefId);
                psmt.setInt(2,(cPage-1)*numPerPage+1);
                psmt.setInt(3,cPage*numPerPage);

            }else {
               psmt=conn.prepareStatement(pp.getProperty("countRecipeRecommend"));
               psmt.setString(1,chefId);
               psmt.setInt(2,(cPage-1)*numPerPage+1);
               psmt.setInt(3,cPage*numPerPage);

           }



            rs = psmt.executeQuery();

            while(rs.next()){
                RecipeRecommend r = new RecipeRecommend();

                r.setRecipeEnrollNum(rs.getInt("recipe_enroll_no"));
                r.setRecipeTitle(rs.getString("recipe_title"));
                r.setRecipeViewCount(rs.getInt("recipe_view_count"));
                r.setRepresentPicture(rs.getString("represent_picture"));
                r.setMemberId(rs.getString("member_id"));
                recipeRecommend.add(r);
            }

        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            close(rs);
            close(psmt);
        }

        return recipeRecommend;
    }

    public int getRecommendNum(Connection conn, String chefId, int recipeEnrollNum){
        PreparedStatement psmt = null;
        ResultSet rs = null;
        int result = 0;

        try{
            psmt = conn.prepareStatement(pp.getProperty("getRecommendNum"));

            psmt.setInt(1,recipeEnrollNum);
            psmt.setString(2,chefId);

            rs= psmt.executeQuery();

            if(rs.next()){
                rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }

    public int getCommentNum(Connection conn, String chefId, int recipeEnrollNum){
        PreparedStatement psmt = null;
        ResultSet rs = null;
        int result = 0;

        try{
            psmt = conn.prepareStatement(pp.getProperty("getCommentNum"));

            psmt.setInt(1,recipeEnrollNum);
            psmt.setString(2,chefId);

            rs= psmt.executeQuery();

            if(rs.next()){
                rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }


    public List<RecipeComment> recipeCommentNum(Connection conn, String chefId){

        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<RecipeComment> recipeCommentNum = new ArrayList<>();

        try {
            psmt = conn.prepareStatement(pp.getProperty("countComment"));
            psmt.setString(1,chefId);

            rs = psmt.executeQuery();

            while(rs.next()){
                RecipeComment c = new RecipeComment();
                c.setRecipeEnrollNo(rs.getInt("recipe_enroll_no"));
                c.setMemberId(rs.getString("member_id"));
                c.setMemberName(rs.getString("member_name"));
                c.setMemberNickName(rs.getString("member_nickname"));
                c.setRecipeEnrollNo(rs.getInt("recipe_enroll_no"));
                c.setCountRecipeComment(rs.getInt("count_recipe_comment"));
                c.setRepresentPicture(rs.getString("represent_picture"));


                recipeCommentNum.add(c);
            }

        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            close(rs);
            close(psmt);
        }

        return recipeCommentNum;

    }


    public List<Integer> countComment(Connection conn, String chefName){
        // 레시피별 댓글 갯수 가져오는 메소드
        // 수정해야함 결과값 인트 1개 아님
        // vo 새로만듦 참고하자.

        PreparedStatement psmt= null;
        ResultSet rs = null;
        List<Integer> result = new ArrayList<>();

        try{
            psmt = conn.prepareStatement(pp.getProperty("countComment"));

            psmt.setString(1,chefName);
            rs = psmt.executeQuery();

            while(rs.next()){
                result.add(rs.getInt(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return  result;
    }

    public List<Integer> countRecipeRecommend(Connection conn, String chefName){
        // 레시피 별 추천수 가져오는 메소드
        // 수정해야함 결과값 인트 1개 아님
        // vo 새로만듦 참고하자.

        PreparedStatement psmt= null;
        ResultSet rs = null;
        List<Integer> result = new ArrayList<>();

        try{
            psmt = conn.prepareStatement(pp.getProperty("countRecipeRecommend"));

            psmt.setString(1,chefName);
            rs = psmt.executeQuery();


            /*


            쿼리문 , 로직 수정해야함


             */

            while(rs.next()){
                result.add(rs.getInt(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return  result;
    }

    public int recommendChef(Connection conn,String chefId,int recommendYN){
        // 셰프 추천수 증감시키는 메소드
        PreparedStatement psmt = null;
        int result=0;

        try{
            if(recommendYN == 1){
                psmt = conn.prepareStatement(pp.getProperty("chefRecommend"));
            }else{
                psmt = conn.prepareStatement(pp.getProperty("chefRecommendCancel"));
            }

            psmt.setString(1,chefId);

            result= psmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(psmt);
        }

        return result;
    }
    public int userRecommendUpdate(Connection conn,String chefId){
        // 추천수 최신화 시키는 쿼리
        PreparedStatement psmt = null;
        int result=0;

        try{
            psmt = conn.prepareStatement(pp.getProperty("userRecommendUpdate"));

            psmt.setString(1,chefId);
            psmt.setString(2,chefId);

            result= psmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(psmt);
        }

        return result;
    }

    // 최신순, 조회순, 추천순으로 레시피 가져오는 메소드
    public List<Recipe> sortRecipe(Connection conn, String chefName,String sortVal){
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Recipe> recipeList = new ArrayList<>();

        try{
            if(sortVal.equals("추천순")){
                psmt=conn.prepareStatement(pp.getProperty("sortRecipeRecommend"));
                psmt.setString(1,chefName);

            }else if(sortVal.equals("최신순")){
                psmt=conn.prepareStatement(pp.getProperty("sortRecipe"));
                psmt.setString(1,chefName);
                psmt.setString(2,"Recipe_enroll_no");

            }else if(sortVal.equals("조회순")){
                psmt=conn.prepareStatement(pp.getProperty("sortRecipe"));
                psmt.setString(1,chefName);
                psmt.setString(2,"Recipe_view_count");
            }

            rs = psmt.executeQuery();

            while(rs.next()){
                Recipe r = new Recipe();
                r.setRecipeEnrollNo(rs.getInt("Recipe_enroll_no"));
                r.setMemberId(rs.getString("member_id"));
                r.setRecipeTitle(rs.getString("recipe_intro"));
                r.setRepresentPicture(rs.getString("represent_picture"));
                r.setRecipeVideoAddress(rs.getString("recipe_video_address"));
                r.setRecipeCategory(rs.getString("recipe_category"));
                r.setRecipeInfoHowmany(rs.getInt("recipe_info_howmany"));
                r.setRecipeInfoTime(rs.getInt("recipe_info_time"));
                r.setRecipeDifficult(rs.getString("recipe_difficult"));
                r.setRecipeProcedure(rs.getString("recipe_tip"));
                r.setRecipeViewCount(rs.getInt("recipe_view_count"));
                r.setRecipeEnrollDate(rs.getDate("recipe_enroll_date"));

                recipeList.add(r);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return recipeList;
    }

    public List<SortRankChef> SortRankChefAJax(Connection conn, int cPage, int numPerPage,String sortRef){
        // 셰프랭킹 페이지 랭킹별정렬하는 메소드

        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<SortRankChef> result = new ArrayList<>();

        try{
            if(sortRef.equals("일간랭킹")){
                psmt = conn.prepareStatement(pp.getProperty("sortChefDaily"));


            }else if(sortRef.equals("주간랭킹")){
                psmt = conn.prepareStatement(pp.getProperty("sortChefWeekly"));

            }else{
                psmt = conn.prepareStatement(pp.getProperty("sortChefMonthly"));

            }
            psmt.setInt(1,(cPage-1)*numPerPage+1);
            psmt.setInt(2,cPage*numPerPage);


            rs= psmt.executeQuery();

            while(rs.next()){
                SortRankChef s = new SortRankChef();
                s.setRecommendCount(rs.getInt("recommend_count"));
                s.setMemberId(rs.getString("member_id"));
                s.setProfilePic(rs.getString("profile_pic"));
                s.setProfileName(rs.getString("profile_name"));
                s.setMemberName(rs.getString("member_name"));
                s.setMemberNickName(rs.getString("member_nickname"));
                result.add(s);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }

    public List<User> SortRankChef(Connection conn, int cPage, int numPerPage,String sortRef){
        // 셰프랭킹 페이지 랭킹별정렬하는 메소드

        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<User> result = new ArrayList<>();

        try{
            if(sortRef.equals("일간랭킹")){
                psmt = conn.prepareStatement(pp.getProperty("sortChefDaily"));


            }else if(sortRef.equals("주간랭킹")){
                psmt = conn.prepareStatement(pp.getProperty("sortChefWeekly"));

            }else{
                psmt = conn.prepareStatement(pp.getProperty("sortChefMonthly"));

            }
            psmt.setInt(1,(cPage-1)*numPerPage+1);
            psmt.setInt(2,cPage*numPerPage);


            rs= psmt.executeQuery();

            while(rs.next()){
                User u = new User();

                u.setMemberId(rs.getString("member_id"));
                u.setMemberName(rs.getString("member_name"));
                u.setMemberEmail(rs.getString("member_email"));
                u.setMemberNickName(rs.getString("member_nickname"));
                u.setMemberAddress(rs.getString("member_address"));
                u.setMemberGrade(rs.getString("member_grade"));
                u.setMemberPhone(rs.getString("member_phone"));
                u.setMemberPoint(rs.getInt("member_point"));
                u.setRecommendCount(rs.getInt("recommend_count"));


                result.add(u);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }

    public int updateChefGrade(Connection conn){
        // 회원 등급 셰프로 조정하는 메소드
        PreparedStatement psmt = null;
        int result = 0;

        try{
            psmt = conn.prepareStatement(pp.getProperty("updateChefGrade"));

            result = psmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(psmt);
        }
        return result;
    }

    public int reviseProfile(Connection conn, Profile p){
        // 프로필 수정하는 메소드

        PreparedStatement psmt = null;
        int result = 0;

        try{
            psmt = conn.prepareStatement(pp.getProperty("reviseProfile"));
            psmt.setString(1,p.getProfileName());
            psmt.setString(2,p.getSelfIntro());
            psmt.setString(3,p.getProfilePic());
            psmt.setString(4,p.getProfileSnsUrl1());
            psmt.setString(5,p.getProfileSnsUrl2());
            psmt.setString(6,p.getMemberId());

            result=psmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(psmt);
        }

        return result;
    }

    public int createProfile(Connection conn,String id){
        // 기본 프로필 만드는 메소드
        PreparedStatement psmt = null;
        int result = 0;

        try{
            psmt = conn.prepareStatement(pp.getProperty("createProfile"));
            psmt.setString(1,id);

            result= psmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(psmt);
        }

        return result;
    }

    /*

     추천 레시피

     */
    public List<Recipe> todayRecipe(Connection conn,int sortday1, int sortday2){
        // 베스트 레시피 3개 가져오는 메소드
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<Recipe> list=new ArrayList<>();
        try{
            pstmt=conn.prepareStatement(pp.getProperty("todayRecipe"));

            pstmt.setInt(1,sortday1);
            pstmt.setInt(2,sortday2);

            rs=pstmt.executeQuery();
            while(rs.next()) {
                Recipe r=new Recipe();
                r.setRecipeEnrollNo(rs.getInt("recipe_enroll_no"));
                r.setMemberId(rs.getString("memberid"));
                r.setRecipeTitle(rs.getString("recipe_title"));
                r.setRepresentPicture(rs.getString("represent_picture"));
                r.setRecipeViewCount(rs.getInt("recipe_view_count"));
                r.setRecommendCount(rs.getInt("recommend_count"));
                list.add(r);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }

        return list;
    }

    public List<Recipe> periodRecipe(Connection conn, int cPage, int numPerPage, String sortRef){
        // 추천식단 데이터 가져오는 메소드

        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<Recipe> list=new ArrayList<>();
        try{

            if(sortRef.equals("주간")){
                pstmt = conn.prepareStatement(pp.getProperty("weeklyRecipe"));

            }else if(sortRef.equals("월간")){
                pstmt = conn.prepareStatement(pp.getProperty("monthlyRecipe"));

            }else{
                pstmt=conn.prepareStatement(pp.getProperty("periodRecipe"));
            }


            pstmt.setInt(1,(cPage-1)*numPerPage+1);
            pstmt.setInt(2,cPage*numPerPage);

            rs=pstmt.executeQuery();

            while(rs.next()) {
                Recipe r=new Recipe();
                r.setRecipeEnrollNo(rs.getInt("recipe_enroll_no"));
                r.setMemberId(rs.getString("member_id"));
                r.setRecipeTitle(rs.getString("recipe_title"));
                r.setRecipeIntro(rs.getString("recipe_intro"));
                r.setRepresentPicture(rs.getString("represent_picture"));
                r.setRecipeVideoAddress(rs.getString("recipe_video_address"));
                r.setRecipeCategory(rs.getString("recipe_category"));
                r.setRecipeInfoHowmany(rs.getInt("recipe_info_howmany"));
                r.setRecipeInfoTime(rs.getInt("recipe_info_time"));
                r.setRecipeDifficult(rs.getString("recipe_difficult"));
                r.setRecipeTip(rs.getString("recipe_tip"));
                r.setRecipeViewCount(rs.getInt("recipe_view_count"));

                // 레시피 추천 수 추가
                // 쿼리문 수정해야함.
                r.setRecommendCount(rs.getInt("recipe_recommend"));
                list.add(r);
            }

            if(list.size() == 0){
                pstmt = conn.prepareStatement(pp.getProperty("noRecipeData"));
                pstmt.setInt(1,(cPage-1)*numPerPage+1);
                pstmt.setInt(2,cPage*numPerPage-1);

                rs = pstmt.executeQuery();

                while(rs.next()){
                    Recipe r=new Recipe();
                    r.setRecipeEnrollNo(rs.getInt("recipe_enroll_no"));
                    r.setMemberId(rs.getString("member_id"));
                    r.setRecipeTitle(rs.getString("recipe_title"));
                    r.setRecipeIntro(rs.getString("recipe_intro"));
                    r.setRepresentPicture(rs.getString("represent_picture"));
                    r.setRecipeVideoAddress(rs.getString("recipe_video_address"));
                    r.setRecipeCategory(rs.getString("recipe_category"));
                    r.setRecipeInfoHowmany(rs.getInt("recipe_info_howmany"));
                    r.setRecipeInfoTime(rs.getInt("recipe_info_time"));
                    r.setRecipeDifficult(rs.getString("recipe_difficult"));
                    r.setRecipeTip(rs.getString("recipe_tip"));
                    r.setRecipeViewCount(rs.getInt("recipe_view_count"));

                    list.add(r);
                }
            }


        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }

        return list;
    }

    public List<Integer> countRecipeLike(Connection conn){
        // 레시피 좋아요 수 가져오는 메소드
        PreparedStatement psmt= null;
        ResultSet rs = null;
        List<Integer> result = new ArrayList<>();

        try{
            psmt = conn.prepareStatement(pp.getProperty("countRecipeLike"));

            rs = psmt.executeQuery();

            while(rs.next()){
                result.add(rs.getInt(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return  result;
    }

    public List<Integer> countRecipeComment(Connection conn){
        // 레시피 댓글 수 가져오는 메소드
        PreparedStatement psmt= null;
        ResultSet rs = null;
        List<Integer> result = new ArrayList<>();

        try{
            psmt = conn.prepareStatement(pp.getProperty("countRecipeComment"));

            rs = psmt.executeQuery();

            while(rs.next()){
                result.add(rs.getInt(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return  result;
    }

    public int countRecipeList(Connection conn){
        // 레시피 수 세는 메소드
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
        try{
            pstmt = conn.prepareStatement(pp.getProperty("countRecipeList"));

            rs = pstmt.executeQuery();

            if(rs.next()){
                result = rs.getInt(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }

        return result;
    }

    public List<RecipeRecommend> bestFiveChef(Connection conn){
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<RecipeRecommend> result = new ArrayList<>();

        try{
            psmt=conn.prepareStatement(pp.getProperty("bestFiveChef"));

            rs=psmt.executeQuery();

            while (rs.next()){
                RecipeRecommend r = new RecipeRecommend();

                r.setProfilePic(rs.getString("profile_pic"));
                r.setMemberName(rs.getString("member_name"));
                r.setMemberNickName(rs.getString("member_nickname"));
                r.setMemberId(rs.getString("member_id"));

                result.add(r);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }

    public List<RecipeRecommend> bestThreeRecipe(Connection conn){
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<RecipeRecommend> result = new ArrayList<>();

        try{
            psmt=conn.prepareStatement(pp.getProperty("bestThreeRecipe"));

            rs=psmt.executeQuery();

            while (rs.next()){
                RecipeRecommend r = new RecipeRecommend();

                r.setRepresentPicture(rs.getString("represent_picture"));
                r.setRecipeTitle(rs.getString("recipe_title"));
                r.setRecipeIntro(rs.getString("recipe_intro"));
                r.setRecipeEnrollNum(rs.getInt("recipe_enroll_no"));

                result.add(r);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }

    public List<RecipeRecommend> threeRecipe(Connection conn){
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<RecipeRecommend> result = new ArrayList<>();

        try{
            psmt=conn.prepareStatement(pp.getProperty("threeRecipe"));

            rs=psmt.executeQuery();

            while (rs.next()){
                RecipeRecommend r = new RecipeRecommend();

                r.setRepresentPicture(rs.getString("represent_picture"));
                r.setRecipeTitle(rs.getString("recipe_title"));
                r.setRecipeIntro(rs.getString("recipe_intro"));
                r.setRecipeEnrollNum(rs.getInt("recipe_enroll_no"));


                result.add(r);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(psmt);
        }

        return result;
    }

}
