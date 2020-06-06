package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.User;
import com.example.demo.form.SignupForm;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	
	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("contents", "login/home :: home_contents");
		return "login/homeLayout";
	}
	
	@GetMapping("/userList")
	public String getUserList(Model model) {
		model.addAttribute("contents", "login/userList :: userList_contents");
		
		List<User>userList = userService.selectMany();
		
		model.addAttribute("userList", userList);
		
		int count = userService.count();
		
		model.addAttribute("userListCount", count);
		
		return "login/homeLayout";
	}
	
	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, 
			@PathVariable("id") String userId) {
//		確認用
		System.out.println("userId = " + userId);
//		コンテンツ部分にユーザー詳細を表示
		model.addAttribute("contents", "login/userDetail :: userDetail_contents");
		
		User user = userService.selectOne(userId);
		
		form.setUserId(user.getUser_id());
		form.setUserName(user.getUser_name());
		form.setPassword(user.getPassword());
		form.setEmail(user.getEmail());
		
		model.addAttribute("signupForm", form);
		
		return "login/homeLayout";
	}
	
	@PostMapping(value = "/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		User user = new User();
		
		user.setUser_id(form.getUserId());
		user.setUser_name(form.getUserName());
		user.setPassword(form.getPassword());
		user.setEmail(form.getEmail());
		
			 boolean result = userService.updateOne(user);
			 if(result == true) {
				 model.addAttribute("result", "更新完了");
			 } else {
				 model.addAttribute("result", "更新に失敗しました");
			 }
			 
			 return getUserList(model);
	}
	
	@PostMapping(value = "/userDetail", params = "delete")
	public String postUserDetailDelete(@ModelAttribute SignupForm form, Model model) {
		boolean result = userService.deleteOne(form.getUserId());
		
	if(result == true) {
		model.addAttribute("result", "一件削除しました");
	} else {
		model.addAttribute("result", "削除に失敗しました");
	}
	return getUserList(model);
	}
	
	@GetMapping("/logout")
	public String getLogout() {
		return "redirect:/login";
	}
	
	@GetMapping("/admin")
	public String getAdmin(Model model) {
		model.addAttribute("contents", "login/admin :: admin_contents");
		
		return "login/homeLayout";
	}
}
