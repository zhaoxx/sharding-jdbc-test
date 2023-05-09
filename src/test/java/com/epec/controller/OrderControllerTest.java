package com.epec.controller;

import com.epec.Application;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class OrderControllerTest  {

	MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationConnect;


	@Before
	public void setUp() throws JsonProcessingException {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();
	}
	
	@Test
	public void getOrderListByUserId() {
		try {
			String uri = "/order/1";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
					.andReturn();
			String content = mvcResult.getResponse().getContentAsString();
			System.out.println(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void createOrder() {
		try {
			String uri = "/order/createOrder";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("userId", "3").param("orderId", "3"))
					.andReturn();
			String content = mvcResult.getResponse().getContentAsString();
			System.out.println(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
