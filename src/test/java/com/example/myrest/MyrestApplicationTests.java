package com.example.myrest;

import com.example.myrest.burger.Burger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MyrestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	private String toJsonString(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}

//	todo split up
	@Test
	void testAllTheThings() throws Exception {
		Burger testBurger = new Burger();
		testBurger.setName("Big Mac");
//        we're using an empty memory db
//        test saving a burger
		mockMvc.perform(post("/v1/burgers")
				.content(toJsonString(testBurger))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Big Mac")));

//        add a second burger
		testBurger.setName("Whopper");
		mockMvc
				.perform(post("/v1/burgers")
						.content(toJsonString(testBurger))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isCreated());

//        test getting the second burger
		mockMvc
				.perform(get("/v1/burgers/2").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Whopper")));

//        test getting the Big Mac
		mockMvc
				.perform(get("/v1/burgers?name=mac").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)));

//        test getting a random burger
		MvcResult result = mockMvc
				.perform(get("/v1/burgers/random").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		Integer id1 = JsonPath.parse(result.getResponse().getContentAsString()).read("$.id");

//        test deleting the random burger
		mockMvc
				.perform(delete("/v1/burgers/" + id1).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

//        test getting another random burger
		result = mockMvc
				.perform(get("/v1/burgers/random").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		Integer id2 = JsonPath.parse(result.getResponse().getContentAsString()).read("$.id");
		assertThat(id1, not(equalTo(id2)));

	}


}
