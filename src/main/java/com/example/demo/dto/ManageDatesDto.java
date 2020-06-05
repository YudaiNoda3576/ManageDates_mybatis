package com.example.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
//RestTemplateServiceで使用するクラスです
@Data
public class ManageDatesDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<ManageDatesDto> resource;
	
	public ManageDatesDto() {
		resource = new ArrayList<>();
	}
	
	private String id;
	private String name;
	private int year;
	private int month;
	private int date;
}
