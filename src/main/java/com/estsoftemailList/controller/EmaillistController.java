package com.estsoftemailList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoftemailList.dao.EmailListDao;

@Controller
public class EmaillistController {

	// @Autowired를 쓸 수 있도록 기본생성자로 생성가능하도록 바꿔놨음
	@Autowired
	private EmailListDao dao; 
	//dao를 생성하기 위해선 jdbc driver 필요 => form에 등록해놓을것
	
	/* DI -> 이거없어도됨 ///////없으면 ROOT에서 찾는데 ROOT에도 없으면 내가 만들어줘야함 -> 실패
	setDao( )
	EmaillistController() {
		dao = new Email
	}*/
	@RequestMapping("/index")
	@ResponseBody
	public String index(){
		
		System.out.println("dao"+ dao);
	return "emailListIndex";
}
}
