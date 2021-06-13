package com.yoriessence.chef.model.service;



import com.yoriessence.chef.model.dao.UserDao;
import com.yoriessence.chef.model.vo.*;
import com.yoriessence.recipe.model.vo.Recipe;

import static com.yoriessence.common.JDBCTemplate.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

public class UserService {
    private Properties pp = new Properties();
    private UserDao dao = new UserDao();

    public UserService() {
        try{
            String path = UserService.class.getResource("/driver/driver.properties").getPath();
            pp.load(new FileReader(path));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 아이디로 유저 정보 가져오는 메소드
    public User userInfo(String id){
        Connection conn = getConnection();
        User userInfo = dao.userInfo(conn,id);
        close(conn);

        return userInfo;
    }


    public List<User> chefRankList(int cPage, int numPerPage){
        // 셰프 랭킹화면 데이터 가져오는 서비스
        Connection conn = getConnection();
        List<User> result = dao.chefRankList(conn,cPage,numPerPage);
        close(conn);

        return result;
    }

    public List<Profile> chefProfileAll(){
        // 추천수대로 셰프 프로필 가져오는 메소드
        Connection conn = getConnection();
        List<Profile> chefProfileAll = dao.chefProfileAll(conn);
        close(conn);

        return chefProfileAll;
    }

    public int countChefList(){
     // 셰프가 몇명인지 가져오는 서비스
     Connection conn = getConnection();
     int result = dao.countChefList(conn);
     close(conn);

     return result;
    }

    public List<Profile> chefProfile(String chefName){
        // 셰프 프로필 가져오는 서비스
        Connection conn = getConnection();
        List<Profile> chefProfile = dao.chefProfile(conn,chefName);
        close(conn);

        return chefProfile;
    };

    public List<Profile> reGetProfile(String chefName){
        // 셰프 프로필 가져오는 서비스
        Connection conn = getConnection();
        List<Profile> chefProfile = dao.reGetProfile(conn,chefName);
        close(conn);

        return chefProfile;
    };

    public List<RecipeRecommend> chefProfile2(String chefName){
        // 셰프 프로필 가져오는 서비스
        Connection conn = getConnection();
        List<RecipeRecommend> chefProfile = dao.chefProfile2(conn,chefName);
        close(conn);

        return chefProfile;
    };

    public List<Recipe> getRecipe(String chefName,int cPage,int numPerPage){
        // 셰프가 올린 레시피 전부 가져오는 서비스
        Connection conn = getConnection();
        List<Recipe> getRecipe = dao.getRecipe(conn,chefName,cPage,numPerPage);
        close(conn);

        return getRecipe;
    }

    public List<Integer> countComment(String chefName){
        // 셰프가 올린 레시피들의 댓글 수 가져오는 서비스
        Connection conn = getConnection();
        List<Integer> result = dao.countComment(conn,chefName);

        close(conn);

        return result;
    }

    public List<Integer> countRecipeRecommend(String chefName){
        // 셰프가 올린 레시피들의 추천수 가져오는 서비스
        Connection conn = getConnection();
        List<Integer> result = dao.countRecipeRecommend(conn,chefName);

        close(conn);

        return result;
    }

    public int getRecommendNum(String chefId, int recipeEnrollNum){
        Connection conn =getConnection();
        int result = dao.getRecommendNum(conn,chefId,recipeEnrollNum);
        close(conn);
        return result;
    }

    public int getCommentNum(String chefId, int recipeEnrollNum){
        Connection conn =getConnection();
        int result = dao.getCommentNum(conn,chefId,recipeEnrollNum);
        close(conn);
        return result;
    }

    public void recommendChef(String chefId,int recommendYN){
        // 셰프 추천수 증감시키는 서비스
        Connection conn =getConnection();
        int result = dao.recommendChef(conn,chefId,recommendYN);

        if (result > 0) commit(conn);
        else rollback(conn);

        close(conn);
    }
    public void userRecommendUpdate(String chefId){
        // 추천수 아이디 기준으로 최신화하는 서비스
        Connection conn = getConnection();
        int result = dao.userRecommendUpdate(conn,chefId);

        if (result > 0) commit(conn);
        else rollback(conn);

        close(conn);
    }

    public List<Recipe> sortRecipe(String chefId,String sortVal){
        // 조회수, 최신순, 추천순으로 레시피 가져오는 서비스

        Connection conn = getConnection();
        List<Recipe> getRecipe = dao.sortRecipe(conn,chefId,sortVal);
        close(conn);

        return getRecipe;
    }

    public List<RecipeRecommend> recipeRecommendNum(String chefId, String sortVal,int cPage,int numPerPage){
        Connection conn = getConnection();
        List<RecipeRecommend> recipeRecommend =dao.recipeRecommendNum(conn,chefId,sortVal,cPage,numPerPage);
        close(conn);

        return recipeRecommend;
    }
    public List<RecipeComment> recipeCommentNum(String chefId){
        Connection conn = getConnection();
        List<RecipeComment> RecipeComment =dao.recipeCommentNum(conn,chefId);
        close(conn);

        return RecipeComment;
    }

    public List<SortRankChef> SortRankChefAJax(int cPage,int numPerPage,String sortRef){
        // 셰프랭킹 페이지 랭킹별정렬하는 서비스
        Connection conn = getConnection();
        List<SortRankChef> result = dao.SortRankChefAJax(conn,cPage,numPerPage,sortRef);
        close(conn);

        return result;
    }

    public List<User> SortRankChef(int cPage,int numPerPage,String sortRef){
        // 셰프랭킹 페이지 랭킹별정렬하는 서비스
        Connection conn = getConnection();
        List<User> result = dao.SortRankChef(conn,cPage,numPerPage,sortRef);
        close(conn);

        return result;
    }

    public void updateChefGrade(){
        //  추천수 30이상이면 등급 셰프로 바꾸는 서비스
        Connection conn = getConnection();
        int result = dao.updateChefGrade(conn);

        if (result > 0) commit(conn);
        else rollback(conn);

        close(conn);
    }

    public void reviseProfile(Profile p){
        // 프로필 수정하는 서비스
        Connection conn =getConnection();
        int result = dao.reviseProfile(conn,p);

        if (result > 0) commit(conn);
        else rollback(conn);

        close(conn);
    }

    public void createProfile(String id){
        // 프로필이 없으면 생성하는 서비스
        Connection conn = getConnection();
        int createProfile = dao.createProfile(conn,id);

        if (createProfile > 0) commit(conn);
        else rollback(conn);

        close(conn);
    };


    /*


        추천 레시피


     */
    public List<Recipe> todayRecipe(int sortday1, int sortday2){
        // 베스트 레시피 3개 가져오는 서비스
        Connection conn = getConnection();
        List<Recipe> todayRecipe = dao.todayRecipe(conn,sortday1,sortday2);
        close(conn);

        return todayRecipe;
    }


    public List<Recipe> periodRecipe(int cPage, int numPerPage,String sortRef){
        // 셰프 랭킹화면 데이터 가져오는 서비스
        Connection conn = getConnection();
        List<Recipe> result = dao.periodRecipe(conn,cPage,numPerPage,sortRef);
        close(conn);

        return result;
    }

    public List<Integer> countRecipeLike(){
        // 레시피별 좋아요 수 가져오는 서비스
        Connection conn = getConnection();
        List<Integer> countRecipeLike = dao.countRecipeLike(conn);
        close(conn);

        return countRecipeLike;
    }

    public List<Integer> countRecipeComment(){
        // 레시피별 댓글 수 가져오는 메소드
        Connection conn = getConnection();
        List<Integer> countRecipeComment = dao.countRecipeComment(conn);
        close(conn);

        return countRecipeComment;
    }


    public int countRecipeList(){
        // 레시피가 몇개인지 가져오는 서비스
        Connection conn = getConnection();
        int result = dao.countRecipeList(conn);
        close(conn);

        return result;
    }

    /*

    메인페이지

     */
    public List<RecipeRecommend> bestFiveChef(){
        Connection conn = getConnection();
        List<RecipeRecommend> bestFiveChef= dao.bestFiveChef(conn);
        close(conn);

        return bestFiveChef;
    }

    public List<RecipeRecommend> bestThreeRecipe(){
        Connection conn = getConnection();
        List<RecipeRecommend> bestThreeRecipe= dao.bestThreeRecipe(conn);
        close(conn);

        return bestThreeRecipe;
    }

    public List<RecipeRecommend> threeRecipe(){
        Connection conn = getConnection();
        List<RecipeRecommend> threeRecipe= dao.threeRecipe(conn);
        close(conn);

        return threeRecipe;
    }



}
