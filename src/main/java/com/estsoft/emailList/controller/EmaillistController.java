package com.estsoft.emailList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.emailList.dao.EmailListDao;
import com.estsoft.emailList.vo.EmailListVo;

@Controller
public class EmaillistController {

	// @Autowired를 쓸 수 있도록 기본생성자로 생성가능하도록 바꿔놨음
	@Autowired // new DAO() 필요없음
	private EmailListDao dao;
	// dao를 생성하기 위해선 jdbc driver 필요 => form에 등록해놓을것

	/*
	 * DI -> 이거없어도됨 ///////없으면 ROOT에서 찾는데 ROOT에도 없으면 내가 만들어줘야함 -> 실패 setDao( )
	 * EmaillistController() { dao = new Email }
	 */
/*
  	Ver1. new vo
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	// required= true : 비어져서 넘어오면 안됨
	// defaultValue : 비어서 오는경우
	public String insert(@RequestParam(value = "firstName", required = true, defaultValue = "") String firstName,
			@RequestParam(value = "lastName", required = true, defaultValue = "") String lastName,
			@RequestParam(value = "email", required = true, defaultValue = "") String email) {
		EmailListVo vo = new EmailListVo();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);
		
		dao.insert(vo);
		
		return "redirect:/index";
	}
	*/
	
	//Ver2. emailList안의 field 이름이 일치해야함
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	// required= true : 비어져서 넘어오면 안됨
	// defaultValue : 비어서 오는경우
	public String insert(@ModelAttribute EmailListVo vo) {
		dao.insert(vo);	
		return "redirect:/index";
	}


	/*
	 * GET방식인 경우도 제어하고 싶을땐 따로 또 METHOD를 만들어준다 -> 제어할게 아니고 그냥 다받을꺼면 method를 안쓰면된다
	 * 
	 * @RequestMapping(value="/insert", method=RequestMethod.GET) public String
	 * insert(String name){ return "redirect:/index"; }
	 */

	@RequestMapping("/form")
	public String form() {
		return "/WEB-INF/views/form_emaillist.jsp";
	}

	@RequestMapping("/index")
	public String index(Model model) {
		List<EmailListVo> list = dao.getList();
		for (EmailListVo vo : list) {
			System.out.println(vo);
		}

		model.addAttribute("list", list);

		return "/WEB-INF/views/index.jsp";
	}

	/*
	 * ver.2 ModelAndView
	 * 
	 * @RequestMapping("/index2") public ModelAndView index(Model model){
	 * List<EmailListVo> list = dao.getList(); ModelAndView mav = new
	 * ModelAndView(); mav.addObject("list", list)
	 * mav.setViewName("/WEB-INF/views/index.jsp"); return mav; }
	 */
}
