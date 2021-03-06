package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	// 필드

	// 생성자

	// 메소드 - gs

	// 메소드 - 일반
	//업데이트 폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model,@RequestParam("no") int no ) {
		System.out.println("PhoneController > updateForm()");
		PhoneDao phoneDao = new PhoneDao();
		PersonVo personVo = phoneDao.getPerson(no);

		// ds 데이터보내기 ==> request attribute에 넣는다.
		model.addAttribute("personVo", personVo);
		return "/WEB-INF/views/updateForm.jsp";
	}
	//업데이트
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController > update()");
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(personVo);
		
		return "redirect:/list";
	}
	// 전화번호 삭제
		@RequestMapping(value = "/delete/{no}", method = { RequestMethod.GET, RequestMethod.POST })
		public String delete1(@PathVariable("no")int num) {
			System.out.println("PhoneController > delete()");
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personDelete(num);
			System.out.println(num);
			return "redirect:/list";
		}
	// 전화번호 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("PhoneController > delete()");
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personDelete(no);
		
		return "redirect:/list";
	}

	// 전화번호 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("PhoneController > list()");

		// Dao만들기
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList);
		// ds 데이터보내기 ==> request attribute에 넣는다.
		model.addAttribute("personList", personList);
		
		return "/WEB-INF/views/list.jsp";
	}

	// 전화번호 등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController > write()");

		// 파라미터 꺼내기 + Vo로 묶어서 Ds로 메소드의 파라미터로 보내준다.

		// Dao로 저장하기
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personInsert(personVo);
		System.out.println(count);

		// 리다이렉트
		// 리스트로 리다이텍트 시킬 예정

		return "redirect:/list";
	}

	// 전화번호 등록
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String write2(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		System.out.println("PhoneController > write()");
		/*
		 * System.out.println(name); System.out.println(hp);
		 * System.out.println(company);
		 */
		// Vo만들기
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);

		// Dao로 저장하기
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personInsert(personVo);
		System.out.println(count);

		// 리다이렉트
		// 리스트로 리다이텍트 시킬 예정

		return "redirect:/list";
	}

	// 전화번호 등록 폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneController > writeForm()");

		return "/WEB-INF/views/writeForm.jsp";
	}

	// 테스트
	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public String test() {
		System.out.println("PhoneController > test()");
		// 다오
		return "/WEB-INF/views/test.jsp";
	}

	// 등록메소드

	// 수정폼메소드

	// 삭제메소드

	// 리스트메소드

}