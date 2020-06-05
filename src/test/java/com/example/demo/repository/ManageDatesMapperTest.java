package com.example.demo.repository;

import static org.junit.Assert.assertEquals;


import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import com.example.demo.domain.ManageDates;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseOperation;

@SpringJUnitConfig //Junit5上でSpring TestContext Frameworkを利用することを示す
@SpringBootTest //毎回サーバ起動
@DatabaseSetup(value = "/test/data.xml", type = DatabaseOperation.INSERT)
@DisplayName("ManageDatesMapperの結合テスト")
public class ManageDatesMapperTest {
//	実際にデータベースを動かすのでスタブは使用しない
	@Autowired
	private ManageDatesMapper sut;
	
	
	@Test
	void 全件検索して結果をリストで取得できること() throws Exception {
		List<ManageDates> actual = sut.findAll();
		assertEquals(3, actual.size());
	}
	
	@Test
	void 検索_1件して結果がキーに紐付く１件取得できること() throws Exception{
			ManageDates actual = sut.findOne("Y01");
			assertEquals(actual.getId(),"Y01");
			assertEquals(actual.getName(),"今週");
			assertEquals(actual.getYear(), 1);
			assertEquals(actual.getMonth(), 1);
			assertEquals(actual.getDate(), 1);
	
	}
	
	@Test
	public void 存在しないデータを検索すると結果がNULLとなること() throws Exception {
		ManageDates actual = sut.findOne("EmptyData");
		// TODO:サービス側で修正し、Nullを返さないようにするべき。
		assertEquals(null, actual);
	}
		
	@Test
	public void nullで検索するとnullが帰ってくること() throws Exception {
		ManageDates actual = sut.findOne(null);
		// TODO:サービス側で修正し、Nullを返さないようにするべき。
		assertEquals(null, actual);
	}
	
	@Test
	public void 新規データの登録ができること() throws Exception {
//		テスト用Entityクラス
		ManageDates manageDates = new ManageDates();
		manageDates.setId("Test");
		manageDates.setName("テスト");
		manageDates.setYear(0);
		manageDates.setMonth(0);
		manageDates.setDate(0);
//		テスト用のEntityクラスでメソッドの実行
		sut.insert(manageDates);
		ManageDates actual = sut.findOne("Test");
		
		assertEquals("テスト", actual.getName());
		
	}
	
	@Test
	public void データの更新が行えること() throws Exception {
		ManageDates manageDates = new ManageDates();
		manageDates.setId("Y02");
		manageDates.setName("更新テスト");
		manageDates.setYear(0);
		manageDates.setMonth(0);
		manageDates.setDate(0);
//		テスト用のEntityクラスでメソッドの実行
		sut.update(manageDates);
		
		assertEquals("更新テスト", sut.findOne("UpdateTest").getName());
	}	
	
	@Test
	public void データの削除が行えること() throws Exception {
		sut.delete("Y01");
		List<ManageDates> actual = sut.findAll();
		assertEquals(3, actual.size());
	}
}