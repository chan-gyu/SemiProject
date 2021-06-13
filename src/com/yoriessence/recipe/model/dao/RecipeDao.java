package com.yoriessence.recipe.model.dao;

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

import com.yoriessence.chef.model.vo.Profile;
import com.yoriessence.chef.model.vo.User;
import com.yoriessence.recipe.model.vo.Recipe;
import com.yoriessence.recipe.model.vo.RecipeComment;
import com.yoriessence.recipe.model.vo.RecipeIngredient;
import com.yoriessence.recipe.model.vo.RecipeProcedure;
import com.yoriessence.recipe.model.vo.RecipeRecommend;
import com.yoriessence.shopping.vo.Product;

public class RecipeDao {
	
	private Properties prop=new Properties();
	
	public RecipeDao() {
		String path=RecipeDao.class.getResource("/sql/recipe_sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
//	
//	public Profile userProfile(Connection conn, String memberId) {
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		Profile p=null;
//		try {
//			pstmt=conn.prepareStatement(prop.getProperty("userProfile"));
//			pstmt.setString(1, memberId);
//			rs=pstmt.executeQuery();
//			if(rs.next()) {
//				p=new Profile();
//				p.setMemberId(memberId);
//				p.setProfileName(rs.getString("profile_name"));
//				p.setProfilePic(rs.getString("profile_pic"));
//				p.setProfileSnsUrl1(rs.getString("profile_sns_url1"));
//				p.setProfileSnsUrl2(rs.getString("profile_sns_url2"));
//				p.setSelfIntro(rs.getString("self_intro"));
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}
//		return p;
//	}
	
	public String memberNickname(Connection conn, String memberId) {
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String nickname=null;
	try {
		pstmt=conn.prepareStatement(prop.getProperty("memberNickname"));
		pstmt.setString(1, memberId);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			nickname=rs.getString("member_nickname");
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}
	return nickname;
}
	
	public List<Recipe> selectRecipeList(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Recipe> list=new ArrayList();
		try{
			pstmt=conn.prepareStatement(prop.getProperty("selectRecipeList"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
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
				r.setRecipeViewCount(rs.getInt("recipe_view_count"));
				r.setRecipeEnrollDate(rs.getDate("recipe_enroll_date"));
				r.setMainIngredient(rs.getString("main_ingredient"));
				r.setRecipeTip(rs.getString("recipe_tip"));
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
	
	public int selectRecipeCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectRecipeCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int selectRecipeCount(Connection conn, String keyword, String category, String ingredient) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectRecipeSearchCount").replaceFirst("#", category).replace("#", ingredient));
			pstmt.setString(1, "%"+keyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	
	public int selectRecipeEnrollNo(Connection conn, Recipe r) {
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectRecipeEnrollNo"));
			pstmt.setString(1, r.getMemberId());
			pstmt.setString(2, r.getRecipeTitle());
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Recipe selectRecipe(Connection conn, int recipeEnrollNo) {
		Recipe r=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectRecipe"));
			pstmt.setInt(1, recipeEnrollNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				r=new Recipe();
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
				r.setRecipeViewCount(rs.getInt("recipe_view_count"));
				r.setRecipeEnrollDate(rs.getDate("recipe_enroll_date"));
				r.setMainIngredient(rs.getString("main_ingredient"));
				r.setRecipeTip(rs.getString("recipe_tip"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return r;
	}
	
	public List<String> selectIngredientCategory(Connection conn, int recipeEnrollNo){
		List<String> list=new ArrayList();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectIngredientCategory"));
			pstmt.setInt(1, recipeEnrollNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<RecipeIngredient> selectIngredient(Connection conn, int recipeEnrollNo, String category){
		List<RecipeIngredient> list=new ArrayList();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectIngredient"));
			pstmt.setInt(1, recipeEnrollNo);
			pstmt.setString(2, category);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				RecipeIngredient ri=new RecipeIngredient();
				ri.setIngredientCategory(category);
				ri.setIngredientName(rs.getString("ingredient_name"));
				ri.setIngredientAmount(rs.getString("ingredient_amount"));
				list.add(ri);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//추천수 가져오는 메소드
	public int selectRecommendCount(Connection conn, int recipeEnrollNo) {
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectRecommendCount"));
			pstmt.setInt(1, recipeEnrollNo);
			pstmt.setInt(2, recipeEnrollNo);
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	//추천 리스트 메소드
	public List<RecipeRecommend> selectRecommendList(Connection conn, int recipeEnrollNo){
		List<RecipeRecommend> list=new ArrayList();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectRecommendList"));
			pstmt.setInt(1, recipeEnrollNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				RecipeRecommend rr=new RecipeRecommend();
				rr.setRecipeEnrollNo(rs.getInt("recipe_Enroll_No"));
				rr.setRecipeRecommendDate(rs.getDate("recipe_Recommend_Date"));
				rr.setMemberId(rs.getString("member_id"));
				list.add(rr);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//레시피의 모든 댓글 가져오는 메소드
	public List<RecipeComment> selectComment(Connection conn, int recipeEnrollNo){
		List<RecipeComment> list=new ArrayList();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectComment"));
			pstmt.setInt(1, recipeEnrollNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				RecipeComment rc=new RecipeComment();
				rc.setRecipeCommentNo(rs.getInt("recipe_comment_no"));
				rc.setCommentEnrollDate(rs.getDate("comment_enroll_date"));
				rc.setRecipeCommentWriter(rs.getString("recipe_comment_writer"));
				rc.setRecipeComment(rs.getString("recipe_comment"));
				list.add(rc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//특정 기준에 의해 레시피 검색하는 메소드
	public List<Recipe> searchRecipe(Connection conn, String keyword, String category, String ingredient, String order, int cPage, int numPerpage){
		List<Recipe> list=new ArrayList();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//정렬 기준에 따라 분기해 다른 쿼리문 가져옴
			String sqlKey=order.equals("recommend_count")?"recommendRecipeList":"dateRecipeList";
			pstmt=conn.prepareStatement(prop.getProperty(sqlKey).replaceFirst("#", category).replace("#", ingredient));
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
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
				r.setRecipeEnrollDate(rs.getDate("recipe_enroll_date"));
				r.setMainIngredient(rs.getString("main_ingredient"));
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
	
//	public List<RecipePicture> selectProcedurePicture(Connection conn, int recipeEnrollNo) {
	public List<RecipeProcedure> selectProcedure(Connection conn, int recipeEnrollNo) {
		List<RecipeProcedure> list=new ArrayList();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
//			pstmt=conn.prepareStatement(prop.getProperty("selectProcedurePicture"));
			pstmt=conn.prepareStatement(prop.getProperty("selectProcedure"));
			pstmt.setInt(1, recipeEnrollNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				RecipeProcedure rp=new RecipeProcedure();
				rp.setRecipeEnrollNo(recipeEnrollNo);
				rp.setProcedureNo(rs.getInt("procedure_no"));
				rp.setProcedureContent(rs.getString("Procedure_content"));
				rp.setProcedurePicture(rs.getString("procedure_picture"));
//				RecipePicture rp=new RecipePicture();
//				rp.setRecipeImageNo(rs.getInt("recipe_image_no"));
//				rp.setRecipeEnrollNo(rs.getInt("recipe_enroll_no"));
//				rp.setRecipeEnrollPicture(rs.getString("recipe_enroll_picture"));
				list.add(rp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	//댓글 작성자의 프로필 이미지 가져오는 메소드
	public String selectMemberProfile(Connection conn, String userId) {
		String result=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMemberProfile"));
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int insertRecipe(Connection conn, Recipe r) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertRecipe"));
			pstmt.setString(1, r.getMemberId());
			pstmt.setString(2, r.getRecipeTitle());
			pstmt.setString(3, r.getRecipeIntro());
			pstmt.setString(4, r.getRepresentPicture());
			pstmt.setString(5, r.getRecipeVideoAddress());
			pstmt.setString(6, r.getRecipeCategory());
			pstmt.setInt(7, r.getRecipeInfoHowmany());
			pstmt.setInt(8, r.getRecipeInfoTime());
			pstmt.setString(9, r.getRecipeDifficult());
			pstmt.setString(10, r.getMainIngredient());
			pstmt.setString(11, r.getRecipeTip());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertIngredient(Connection conn, RecipeIngredient ri) {
		int result=0;
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(prop.getProperty("insertIngredient"));
			System.out.println(prop.getProperty("insertIngredient"));
			pstmt.setInt(1, ri.getRecipeEnrollNo());
			pstmt.setString(2, ri.getIngredientName());
			pstmt.setString(3, ri.getIngredientAmount());
			pstmt.setString(4, ri.getIngredientCategory());
			System.out.println(ri);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
//	public int insertProcedurePicture(Connection conn, int recipeEnrollNo, int fileNo, String fileName) {
	public int insertProcedure(Connection conn, RecipeProcedure rp) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertProcedure"));
			System.out.println(rp);
			pstmt.setInt(1, rp.getRecipeEnrollNo());
			pstmt.setInt(2, rp.getProcedureNo());
			pstmt.setString(3, rp.getProcedureContent());
			pstmt.setString(4, rp.getProcedurePicture());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	

	public int insertComment(Connection conn, RecipeComment c) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertComment"));
			pstmt.setInt(1, c.getRecipeEnrollNo());
			pstmt.setString(2, c.getRecipeComment());
			pstmt.setString(3, c.getRecipeCommentWriter());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertRecommend(Connection conn, RecipeRecommend rr) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertRecommend"));
			pstmt.setInt(1, rr.getRecipeEnrollNo());
			pstmt.setString(2, rr.getMemberId());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateRecipe(Connection conn, Recipe r) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateRecipe"));
			pstmt.setString(1, r.getRecipeTitle());
			pstmt.setString(2, r.getRecipeIntro());
			pstmt.setString(3, r.getRecipeVideoAddress());
			pstmt.setString(4, r.getRecipeCategory());
			pstmt.setInt(5, r.getRecipeInfoHowmany());
			pstmt.setInt(6, r.getRecipeInfoTime());
			pstmt.setString(7, r.getRecipeDifficult());
			pstmt.setString(8, r.getRecipeTip());
			pstmt.setString(9, r.getMainIngredient());
			pstmt.setInt(10, r.getRecipeEnrollNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateRepresentPicture(Connection conn, String fileName, int recipeEnrollNo) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateRepresentPicture"));
			pstmt.setString(1, fileName);
			pstmt.setInt(2, recipeEnrollNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
//	public int updateProcedurePicture(Connection conn, int recipeEnrollNo, int fileNo, String fileName) {
//		int result=0;
//		PreparedStatement pstmt=null;
//		try {
//			pstmt=conn.prepareStatement(prop.getProperty("updateProcedurePicture"));
//			pstmt.setString(1, fileName);
//			pstmt.setInt(2, fileNo);
//			pstmt.setInt(3, recipeEnrollNo);
//			result=pstmt.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}
//		return result;
//	}
	
	
	
	public int deleteRecipe(Connection conn, int recipeNo) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteRecipe"));
			pstmt.setInt(1, recipeNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteIngredient(Connection conn, int recipeNo) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteIngredient"));
			pstmt.setInt(1, recipeNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteProcedure(Connection conn, int recipeEnrollNo) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteProcedure"));
			pstmt.setInt(1, recipeEnrollNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteAllComment(Connection conn, int recipeEnrollNo) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteAllComment"));
			pstmt.setInt(1, recipeEnrollNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteAllRecommend(Connection conn, int recipeEnrollNo) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteAllRecommend"));
			pstmt.setInt(1, recipeEnrollNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteRecommend(Connection conn, RecipeRecommend rr) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteRecommend"));
			pstmt.setInt(1, rr.getRecipeEnrollNo());
			pstmt.setString(2, rr.getMemberId());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateRecipeViewCount(Connection conn, Recipe r) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateRecipeViewCount"));
			pstmt.setInt(1, r.getRecipeViewCount()+1);
			pstmt.setInt(2, r.getRecipeEnrollNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Product> selectProduct(Connection conn, String keyword, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Product> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectProduct"));
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product pd=new Product();
				pd.setProductNo(rs.getInt("PRODUCTNO"));
				pd.setStock(rs.getInt("STOCK"));
				pd.setPrice(rs.getInt("PRICE"));
				pd.setExplanation(rs.getString("EXPLANATION"));
				pd.setProductName(rs.getString("PRODUCTNAME"));
				pd.setProductImage(rs.getString("PRODUCTIMAGE"));
				pd.setProductkategorie(rs.getString("PRODUCTKATEGORIE"));
				pd.setProductshopify(rs.getInt("PRODUCTSHOPIFY"));
				list.add(pd);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	

}
