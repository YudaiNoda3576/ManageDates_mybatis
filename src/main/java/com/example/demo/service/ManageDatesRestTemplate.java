package com.example.demo.service;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.ManageDates;
import com.example.demo.dto.ManageDatesDto;

public class ManageDatesRestTemplate {
//	RestTemplateの練習です
	@Autowired
	RestTemplate restTemplate;

	public static final String findAll = "http://localhost:8888/findAll";
	public static final String search = "http://localhost:8888/seach/{id}";
	public static final String findOne = "http://localhost:8888/findOne/{id}";
	public static final String insert = "http://localhost:8888/insert";
	public static final String update = "http://localhost:8888/update/{id}";
	public static final String delete = "http://localhost:8888/delete/{id}";
	@Value("http://localhost:8888/put/{id}")
	private URI putUrl;
	
	public List<ManageDatesDto> getAllResponse() {
		ResponseEntity<List<ManageDatesDto>> response = restTemplate.exchange(findAll, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ManageDatesDto>>() {
		});
//		練習用にResourceファイルを作成
		List<ManageDatesDto> resource = response.getBody();
		
		if(CollectionUtils.isEmpty(resource)) {
			throw new ManageDatesNotFoundException("レコードが存在しません");
		}
		return resource;
	}
	
	public List<LocalDate> search(String id) {
		ResponseEntity<List<LocalDate>> response = restTemplate.exchange(findAll, HttpMethod.GET, null, 
			new ParameterizedTypeReference<List<LocalDate>>() {
			}, id);
		List<LocalDate>result = response.getBody();
		if(CollectionUtils.isEmpty(result)) {
			throw new ManageDatesNotFoundException("レコードが存在しません");
		}
		return result;
	}
	
	public boolean insert(@Validated @RequestBody ManageDates manageDates) {
		boolean response = restTemplate.postForObject(insert, manageDates, boolean.class);
		if(!response) {
			throw new ManageDatesNotFoundException("保存するレコードが存在しません");
		} return response;
	}
	
	public ManageDates getId(String id) {
		ManageDates result = restTemplate.getForObject(findOne, ManageDates.class, id);
		if(result != null) {
			throw new ManageDatesNotFoundException("取得するレコードが存在しません");
		}
		return result;
	}
	
	public boolean update(@Validated @RequestBody ManageDates manageDates) {
		RequestEntity<ManageDates> requestEntity = RequestEntity.put(putUrl).body(manageDates);
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(requestEntity, boolean.class);
		boolean result = responseEntity.getBody();
		if(!result) {
			throw new ManageDatesNotFoundException("更新するレコードが存在しません");
		}
		return result;
	}
	
	public boolean delete(String id) {
		ResponseEntity<Boolean> response = restTemplate.exchange(delete, HttpMethod.DELETE, null, boolean.class, id);
		boolean result = response.getBody();
	 if(!result) {
		throw new ManageDatesNotFoundException("更新するレコードが存在しません");
	}
	return result;
	}
}
