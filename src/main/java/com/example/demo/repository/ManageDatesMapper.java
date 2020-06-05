package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.ManageDates;


@Mapper
public interface ManageDatesMapper {
	
	List<ManageDates> findAll();
	
	ManageDates findOne(String id);
	
	void insert(ManageDates manageDates);
	
	boolean update(ManageDates manageDates);
	
	boolean delete(String id);
}
