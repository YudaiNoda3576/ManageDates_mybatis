package com.example.demo.app;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ManageDates;
import com.example.demo.service.ManageDatesRestService;

@RestController
@RequestMapping("/api")
public class ManageDatesRestController {
	@Autowired
	ManageDatesRestService manageDatesRestService;
	
//	全件取得
	@GetMapping("findAll")
	public List<ManageDates>getManageDates() {
		List<ManageDates>manageDates = manageDatesRestService.findAll();
		return manageDates;
	}
	
//	計算実行
	@GetMapping("search/{id}")
	public List<LocalDate> search(@PathVariable("id") String id) {
		List<LocalDate>list = new ArrayList<LocalDate>();
		if(id != null) {
			return manageDatesRestService.search(id);
		} else {
			list.add(LocalDate.now());
			return list;
		}
	}
	
	
//	一件検索
	@GetMapping("findOne/{id}")
	public ManageDates getManageDateOne(@PathVariable String id) {
		ManageDates manageDates = manageDatesRestService.findOne(id);
		return manageDates;
	}
	
//	新規登録
	@PostMapping("/insert")
	@ResponseStatus(HttpStatus.CREATED)
//	RequestBodyでHTTPリクエストのボディ部分を引数にマッピングできる。これで、POSTメソッドでもユーザー情報の受け取りができる。
	public void postManageDateOne(@RequestBody ManageDates manageDates) {
		manageDatesRestService.insert(manageDates);
	}
	
//　一件更新
	@PutMapping("update/{id}")
	public boolean update(@RequestBody ManageDates manageDates) {
		return manageDatesRestService.update(manageDates);
	}
	
//	削除
	@DeleteMapping("delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean delete(@PathVariable("id") String id) {
		return manageDatesRestService.delete(id);
	}
	
	
}
