package com.example.three;

import java.io.Console;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/urls")
public class ArticleController {
	@Autowired private ArticleRepository repository;
	private Integer DEFAULT_PAGE_COUNT = 4;
	



	@GetMapping("")
	public String getUrls(Model m,
			@RequestParam(value="page", defaultValue="1") Integer page, 
			@RequestParam(value="order", defaultValue="desc") String order ) { 
		
//		No property id found for type MyEntity!
		
		Page<MyEntity> list =  repository.findAll(PageRequest.of(page-1, DEFAULT_PAGE_COUNT,      
				order.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "urlNum"));
		
		System.out.println("잘 찍히는지 확인 !! " +  list);
		
		m.addAttribute("count", (int) Math.ceil(repository.count() / DEFAULT_PAGE_COUNT.doubleValue()));
		m.addAttribute("order", order);
		m.addAttribute("urls", list ); 

		return "urls";  
	}
		
	
	@PostMapping("")
	public String AddEd(MyEntity myentity) {
			repository.save(myentity); // insert
		return "redirect:urls";
	}
	

	@RequestMapping("/cUrl")
	public String cUrl() {
		return "cUrl";
	}

	
	@RequestMapping("/{id}")
	public String urlsId(@PathVariable("id") int urlNum) {
		System.out.println("hi");
		MyEntity myentity = repository.findById(urlNum).get();
		myentity.setCount(myentity.getCount() +1);
		repository.save(myentity);
		
		return "redirect:" + myentity.getOUrl();
	}

	
	@RequestMapping("/delete/{id}")
	public String Delete(@PathVariable("id") int urlNum) {
		
		System.out.println("삭제버튼 눌림!");
		
		repository.deleteById(urlNum);
		return "redirect:/urls";	
	}
	
	@RequestMapping("/mod/{id}")
	public String mod(@PathVariable("id") int urlNum, Model m) {
		
		MyEntity myentity = repository.findById(urlNum).get();
		m.addAttribute("my",myentity);
		
		return "urlsMod";	
	}
	
	
}