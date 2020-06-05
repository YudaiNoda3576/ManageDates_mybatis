package com.example.demo.app;
//package com.example.demo.app;
//
//import java.time.LocalDate;
//
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.example.demo.domain.ManageDates;
//import com.example.demo.domain.SearchForm;
//import com.example.demo.service.ManageDatesService;
//
//
//@Controller
//@RequestMapping("/")
//@Transactional
////RestTemplateServivceと紐づくクラスです
//public class ManageDatesApiController {
//	
////	@Autowired
////	ManageDatesRestService manageDatesRestService;
//	
//	@Autowired
//	ManageDatesService manageDatesRestService;
//
//	
//	@GetMapping("/index")
//	public String index(Model model, @ModelAttribute("input") String input, SearchForm searchForm) {
//		model.addAttribute("searchForm", searchForm);
//		return "index";
//	}
//	
//	@PostMapping
//	public String search(@RequestParam("input") String input,
//			@Validated SearchForm searchForm,
//			BindingResult result,
//			Model model) {
////		計算結果
//		if(!result.hasErrors()) {
//			List<LocalDate>manageDatesCalResult = manageDatesRestService.search(input);
//			model.addAttribute("manageDatesCal", manageDatesRestService.findAll());
//			model.addAttribute("manageDatesCalResult", manageDatesCalResult);
//			model.addAttribute("val", input);
//		} else {
//			model.addAttribute("failed", "入力値に誤りがあります");
//			model.addAttribute("val", input);
//		}
//		return "index";
//	}
//	
////	新規登録
//	@GetMapping("/create")
//	public String create(ManageDates manageDates, Model model) {
//		model.addAttribute("manageDates", manageDates);
//		return"/create";
//	}
//	
//	@PostMapping("/create")
//	public String create(@ModelAttribute @Validated ManageDates manageDates, 
//			BindingResult result, 
//			Model model, 
//			RedirectAttributes redirectAttributes) {
//
//		if(!result.hasErrors()) {
//			manageDatesRestService.insert(manageDates);
//			redirectAttributes.addFlashAttribute("success", "新規登録が完了しました");
//			return "redirect:/index";
//		} else {
//			model.addAttribute("manageDatesCal", manageDatesRestService. findAll());
//			model.addAttribute("manageDatesForm", manageDates);
//			model.addAttribute("failed", "不正値に誤りがあります");
//			return "create";
//		}
//		
//	}
//	
//	@GetMapping("/edit/{id}")
//	public String getId(@PathVariable String id, 
//		    Model model,
//			RedirectAttributes redirectAttributes) {
//		ManageDates result = manageDatesRestService.findOne(id);
//		model.addAttribute("manageDates", result);
//		return "edit";
//	}
//		
//
//	@PostMapping("/edit/{id}")
//	public String update(@PathVariable String id, @ModelAttribute ManageDates manageDates,
//			Model model, RedirectAttributes redirectAttributes) {
//	 	manageDates.setId(id);
//	 	manageDatesRestService.update(manageDates);
//		redirectAttributes.addFlashAttribute("success", "更新が完了しました");
//		return "redirect:/index";
//	}
//	
//	
//	@GetMapping("/delete/{id}")
//	public String delete(@PathVariable String id, Model model) {
//		manageDatesRestService.delete(id);
//		model.addAttribute("success", "削除が成功しました");
//		return "redirect:/index";
//	}
//	
//}