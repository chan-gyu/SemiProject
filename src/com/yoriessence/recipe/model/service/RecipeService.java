package com.yoriessence.recipe.model.service;

import static com.yoriessence.common.JDBCTemplate.close;
import static com.yoriessence.common.JDBCTemplate.commit;
import static com.yoriessence.common.JDBCTemplate.getConnection;
import static com.yoriessence.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yoriessence.chef.model.vo.Profile;
import com.yoriessence.recipe.model.dao.RecipeDao;
import com.yoriessence.recipe.model.vo.Recipe;
import com.yoriessence.recipe.model.vo.RecipeComment;
import com.yoriessence.recipe.model.vo.RecipeIngredient;
import com.yoriessence.recipe.model.vo.RecipeProcedure;
import com.yoriessence.recipe.model.vo.RecipeRecommend;
import com.yoriessence.shopping.vo.Product;

public class RecipeService {
	
	private RecipeDao dao=new RecipeDao();
	
//	public Profile userProfile(String memberId) {
//		Connection conn=getConnection();
//		Profile p=dao.userProfile(conn, memberId);
//		close(conn);
//		return p;
//	}
	
	public String memberNickname(String memberId) {
		Connection conn=getConnection();
		String nickname=dao.memberNickname(conn, memberId);
		close(conn);
		return nickname;
	}
	
	//모든 레시피 가져오는 메소드
	public List<Recipe> selectRecipeList(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Recipe> list=dao.selectRecipeList(conn, cPage, numPerpage);
		for(Recipe r:list) {
			r.setRecommendCount(dao.selectRecommendCount(conn, r.getRecipeEnrollNo()));
			r.setCommentCount(dao.selectComment(conn, r.getRecipeEnrollNo()).size());
		}
		close(conn);
		return list;
	}
	
	//모든 레시피 갯수 가져옴
	public int selectRecipeCount() {
		Connection conn=getConnection();
		int result=dao.selectRecipeCount(conn);
		close(conn);
		return result;
	}
	
	public int selectRecipeCount(String keyword, String category, String ingredient) {
		Connection conn=getConnection();
		int result=dao.selectRecipeCount(conn, keyword, category, ingredient);
		close(conn);
		return result;
	}

	//특정 레시피의 등록번호 조회
	public int selectRecipeEnrollNo(Recipe r) {
		Connection conn=getConnection();
		int result=dao.selectRecipeEnrollNo(conn, r);
		close(conn);
		return result;
	}
	
	//특정 레시피의 재료 분류 조회
	public List<String> selectIngredientCategory(int recipeEnrollNo){
		Connection conn=getConnection();
		List<String> list=dao.selectIngredientCategory(conn, recipeEnrollNo);
		close(conn);
		return list;
	}
	
	//댓글 작성자의 프로필 이미지 가져오기
	public String selectMemberProfile(String userId) {
		Connection conn=getConnection();
		String img=dao.selectMemberProfile(conn, userId);
		close(conn);
		return img;
	}
	
	//레시피 등록 메소드
	public int insertRecipe(Recipe r) {
		Connection conn=getConnection();
		int result=dao.insertRecipe(conn, r);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int insertIngredientMap(Map<String, List<RecipeIngredient>> map, int recipeEnrollNo) {
		Connection conn=getConnection();
		int result=0;
		Set set=map.entrySet();
		Iterator i=set.iterator();
		stop:
		while(i.hasNext()){
			Map.Entry e=(Map.Entry)i.next();
			String key=(String)e.getKey();
			List<RecipeIngredient> list=(List<RecipeIngredient>)e.getValue();
			for(RecipeIngredient ri:list) {
				ri.setIngredientCategory(key);
				ri.setRecipeEnrollNo(recipeEnrollNo);
				result=dao.insertIngredient(conn, ri);
				System.out.println(result);
//				if(!(result>0)) break stop;
			}
		}
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//레시피 과정 가져오는 메소드
//	public List<RecipePicture> selectProcedurePicture(int recipeEnrollNo){
	public List<RecipeProcedure> selectProcedure(int recipeEnrollNo){
		Connection conn=getConnection();
		List<RecipeProcedure> list=dao.selectProcedure(conn, recipeEnrollNo);
		close(conn);
		return list;
	}

	//요리과정의 사진 저장하는 메소드
//	public int insertProcedurePicture(int recipeEnrollNo, int fileNo, String fileName) {
	public int insertProcedure(RecipeProcedure rp) {
		Connection conn=getConnection();
//		int result=dao.insertProcedurePicture(conn, recipeEnrollNo, fileNo, fileName);
		int result=dao.insertProcedure(conn, rp);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
//	public int insertIngredient(RecipeIngredient ri, int recipeEnrollNo) {
//		Connection conn=getConnection();
//		int result=dao.insertIngredient(conn, ri, recipeEnrollNo);
//		if(result>0) commit(conn);
//		else rollback(conn);
//		close(conn);
//		return result;
//	}
	
	//특정 레시피만 조회하는 메소드
	public Recipe selectRecipe(int recipeEnrollNo) {
		Connection conn=getConnection();
		Recipe r=dao.selectRecipe(conn, recipeEnrollNo);
		r.setRecommendCount(dao.selectRecommendCount(conn, recipeEnrollNo));
		r.setCommentCount(dao.selectComment(conn, r.getRecipeEnrollNo()).size());
		close(conn);
		return r;
	}
	
	//특정 레시피의 재료 이름과 양 조회
	public List<RecipeIngredient> selectIngredient(String category, int recipeEnrollNo){
		Connection conn=getConnection();
		List<RecipeIngredient> list=dao.selectIngredient(conn, recipeEnrollNo, category);
		close(conn);
		return list;
	}
	
	public List<RecipeRecommend> selectRecommendList(int recipeEnrollNo){
		Connection conn=getConnection();
		List<RecipeRecommend> list=dao.selectRecommendList(conn, recipeEnrollNo);
		close(conn);
		return list;
	}
	
	//특정 레시피 삭제
	public int deleteRecipe(int recipeEnrollNo) {
		Connection conn=getConnection();
		int result=dao.deleteRecipe(conn, recipeEnrollNo);
		dao.deleteAllComment(conn, recipeEnrollNo);
		dao.deleteIngredient(conn, recipeEnrollNo);
		dao.deleteProcedure(conn, recipeEnrollNo);
		dao.deleteAllRecommend(conn, recipeEnrollNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//특정 레시피의 재료 삭제
	public int deleteIngredient(int recipeEnrollNo) {
		Connection conn=getConnection();
		int result=dao.deleteIngredient(conn, recipeEnrollNo);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	//레시피 추천 취소하기
	public int deleteRecommend(RecipeRecommend rr) {
		Connection conn=getConnection();
		int result=dao.deleteRecommend(conn, rr);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//레시피 검색하기
	public List<Recipe> searchRecipe(String keyword, String category, String ingredient, String order, int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Recipe> list=dao.searchRecipe(conn, keyword, category, ingredient, order, cPage, numPerpage);
		for(Recipe r:list) {
			r.setRecommendCount(dao.selectRecommendCount(conn, r.getRecipeEnrollNo()));
			r.setCommentCount(dao.selectComment(conn, r.getRecipeEnrollNo()).size());
		}
		close(conn);
		return list;
	}
	
	//레시피의 모든 댓글 가져오는 메소드
	public List<RecipeComment> selectComment(int recipeEnrollNo){
		Connection conn=getConnection();
		List<RecipeComment> list=dao.selectComment(conn, recipeEnrollNo);
		close(conn);
		return list;
	}
	
	//레시피 번호를 기준으로 댓글 등록하는 메소드
	public int insertComment(RecipeComment comment) {
		Connection conn=getConnection();
		int result=dao.insertComment(conn, comment);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//레시피 번호 기준으로 추천하는 메소드
	public int insertRecommend(RecipeRecommend rr) {
		Connection conn=getConnection();
		int result=dao.insertRecommend(conn, rr);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//레시피 수정 메소드
	public int updateRecipe(Recipe r, Map<String, List<RecipeIngredient>> ingMap, List<RecipeProcedure> procedure) {
		Connection conn=getConnection();
		int result=dao.updateRecipe(conn, r);
		if(result>0) {
			if(r.getRepresentPicture()!=null) {
				result=dao.updateRepresentPicture(conn, r.getRepresentPicture(), r.getRecipeEnrollNo());
			}
		}
		if(result>0) {
			Map<String, List<RecipeIngredient>> oriIng=new HashMap();
			List<String> category=dao.selectIngredientCategory(conn, r.getRecipeEnrollNo());
			for(String c:category) {
				oriIng.put(c, dao.selectIngredient(conn, r.getRecipeEnrollNo(), c));
			}
			result=dao.deleteIngredient(conn, r.getRecipeEnrollNo());
		}
		if(result>0) {
			commit(conn);
			Set set=ingMap.entrySet();
			Iterator it=set.iterator();
			stop:
			while(it.hasNext()){
				Map.Entry e=(Map.Entry)it.next();
				String key=(String)e.getKey();
				List<RecipeIngredient> list=(List<RecipeIngredient>)e.getValue();
				for(RecipeIngredient ri:list) {
					ri.setIngredientCategory(key);
					result=dao.insertIngredient(conn, ri);
					if(result==0) break stop;
				}
			}
		}
		if(result>0) {
			dao.deleteProcedure(conn, r.getRecipeEnrollNo());
			for(RecipeProcedure rp:procedure) {
//				result=dao.updateProcedurePicture(conn, r.getRecipeEnrollNo(), i+1, pictures.get(i));
				result=dao.insertProcedure(conn, rp);
				if(result==0) break;
			}
			if(result>0) {
				commit(conn);
				close(conn);
				return result;
			}
		}
		rollback(conn);
		close(conn);
		return result;
		
	}
	
	//레시피 조회수 올리는 메소드
	public int updateRecipeViewCount(Recipe r) {
		Connection conn=getConnection();
		int result=dao.updateRecipeViewCount(conn, r);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public List<Product> selectProduct(String keyword, int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Product> list=dao.selectProduct(conn, keyword, cPage, numPerpage);
		close(conn);
		return list;
	}
	
}
