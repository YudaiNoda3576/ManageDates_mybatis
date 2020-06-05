package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.ManageDates;
import com.example.demo.repository.ManageDatesMapper;

@Service
@Transactional

public class ManageDatesService {

	@Autowired ManageDatesMapper manageDatesMapper;
	
	
	public List<ManageDates> findAll() {
		return manageDatesMapper.findAll();
	}
	
	//全体検索結果に対して計算を行う	
	public List<LocalDate> search(String input) {
//		入力値をlocalDateに変換
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			.appendValue(ChronoField.YEAR, 4)
			.appendValue(ChronoField.MONTH_OF_YEAR, 2)
			.appendValue(ChronoField.DAY_OF_MONTH, 2)
			.toFormatter();
		LocalDate inputDate = LocalDate.parse(input, formatter); 
			
//		データモックの作成してテスト 日付入力パターン・しなかったパターンなど
		List<ManageDates> manageDates = manageDatesMapper.findAll();
			
		List<LocalDate> sumDate = new ArrayList<LocalDate>();
//		各レコードから計算用の年月日を取得。入力値と合算させる。
		for(ManageDates n : manageDates) {
			int dbYear = n.getYear();
			int dbMonth = n.getMonth();
			int dbDate = n.getDate();
			LocalDate sum = inputDate.plusYears(dbYear).plusMonths(dbMonth).plusDays(dbDate);
//			listに計算結果を格納
			sumDate.add(sum);
		}
			return sumDate;
	}

	
//	一件検索
	public ManageDates findOne(String id) {
		try {
			return manageDatesMapper.findOne(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ManageDatesNotFoundException("指定された値が存在しません");
		}
	}
	
// 新規登録
	public void insert(ManageDates manageDates) {
		manageDatesMapper.insert(manageDates);
	}
	
//	一件更新
	public boolean update(ManageDates manageDates) {
		boolean result = manageDatesMapper.update(manageDates);		
		
		if(result) {
			return true;
		} 
			return false;
		}

//	削除
	public boolean delete(String id) {
		boolean result = manageDatesMapper.delete(id);

		if(result) {
			return true;
		} else {
			return false;
		}
	}

	
}
		

