package com.example.demo.app;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.ManageDatesController;
import com.example.demo.domain.ManageDates;
import com.example.demo.repository.ManageDatesMapper;
import com.example.demo.service.ManageDatesService;



//MockMvcを使用するとnullpointerexceptionが発生するのでこのアノテーションで解消
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("ManageDatesContrllorerの単体テスト")
public class ManageDatesControllerTest {

//	依存しているサービスクラスをモック化
@MockBean
ManageDatesService service;

@InjectMocks
private ManageDatesController target;

//@Autowired 
//private ManageDatesController target;

@Autowired
private MockMvc sut;

//＠AutoConfigureMockMvcを使用したら不要
//@Before
//public void setUp() throws Exception {
////	tymeleafを使用している場合、MockMVCはHTMLファイルテンプレートの場所やページファイルの拡張子が.htmlであることを理解してくれないようです。
////	そのため、循環ビューが発生しているなどのエラーが吐かれる。以下に明示して対処。
//	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//	viewResolver.setPrefix("classpath:templates/");
//	viewResolver.setSuffix(".html");
//
//	sut = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
//}


@Test
public void 計算実行画面のリクエストが正常となりViewとしてindexが返ること() throws Exception {
	sut.perform(get("/index"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
}

@Test
public void 新規登録画面のリクエストが正常となりViewとしてcreateが返ること() throws Exception {
	sut.perform(get("/create"))
		.andExpect(status().isOk())
		.andExpect(view().name("/create"));
}

//@Test
//public void 新規登録画面で登録処理を行うとサービスで処理されて計算実行画面に遷移すること() throws Exception {
////	テスト用のFormをインスタンス化
//	ManageDates manageDates = insertForm("テスト", "テスト", 0, 0, 0);
////	テスト用
//	ManageDatesMapper manageDatesMapper = insertMapper()
//	
//	sut.perform(post("/crete").param("id", "Test").param("name", "テスト"))
//	   .andExpect(status().isOk())
//	   .andExpect(view().name("/index"));
//	
//	verify(service, times(1));
//}
	
//テスト用Formクラスの作成
public ManageDates insertForm(String id, String name, int year, int month, int date) {
	ManageDates manageDates = new ManageDates();
	manageDates.setId(id);
	manageDates.setName(name);
	manageDates.setYear(year);
	manageDates.setMonth(month);
	manageDates.setDate(date);
	
	return manageDates;
}


}
