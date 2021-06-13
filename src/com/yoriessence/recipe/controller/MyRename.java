package com.yoriessence.recipe.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyRename implements FileRenamePolicy{
	@Override
	public File rename(File oldFile) {
		File newFile=null;
		do {
			//리네임 규칙 설정 : 절대 중복되지 않는 값으로 설정함
			long currentTime=System.currentTimeMillis();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");//ms까지 시간 가져옴
			int rndNum=(int)(Math.random()*1000+1);//1~1000
			String oriName=oldFile.getName();
			
			//객체 통해 들어온 파일의 확장자 가져오기
			String ext="";//확장자
			int dot=oriName.lastIndexOf(".");
			if(dot!=-1) {
				ext=oriName.substring(dot);
			}
			//점 기준으로 가져옴-맨 뒤에서부터 . 찾아서 뒤에 나오는 값이 바로 확장자이므로
			
			//새 이름으로 설정하기
			String newName="recipe_"+sdf.format(new Date(currentTime))+"_"+rndNum+ext;
			//SimpleDateFormat 객체의 format() 메소드 사용시 날짜를 형식에 맞춰 String으로 반환
			//_넣고 난수(rndNum)추가
			//확장자(ext) 추가
			newFile=new File(oldFile.getParent(), newName);
			//file객체 생성
		}while(!createNewFile(newFile));
		
		return newFile;
		
	}
	
	private boolean createNewFile(File newFile) {
		try {
			return newFile.createNewFile();
		}catch(IOException e) {
			return false;
		}
	}

}