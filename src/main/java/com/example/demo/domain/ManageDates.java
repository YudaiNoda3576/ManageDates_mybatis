package com.example.demo.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
//このクラスがManageDatesFormの役割を担っている

@Data
public class ManageDates {

	public ManageDates() {
		
	}
	@NotNull(message = "必須項目です")
	@Size(min = 1, max = 10, message = "1文字以上10文字以内で入力してください")
	private String id;
	
	@NotNull(message = "必須項目です")
	@Size(min = 1, max = 10, message = "1文字以上10文字以内で入力してください")
	private String name;
	
	@Digits(integer = 4, fraction = 4, message = "4桁以内で入力してください")
	@NotNull(message = "必須項目です")
	private int year = 0;
	
	@Digits(integer = 2, fraction = 2, message = "2桁以内で入力してください")
	@NotNull(message = "必須項目です")
	private int month = 0;
	
	@Digits(integer = 2, fraction = 2, message = "2桁以内で入力してください")
	@NotNull(message = "必須項目です")
	private int date = 0;
}
