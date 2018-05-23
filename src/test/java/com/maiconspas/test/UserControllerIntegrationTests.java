package com.maiconspas.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiconspas.Application;
import com.maiconspas.entity.User;
import com.maiconspas.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@TestPropertySource(value = { "classpath:application.properties","classpath:application.test.properties" })
public class UserControllerIntegrationTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private List<User> userList = new ArrayList<>();

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

		this.userRepository.deleteAll();
		User admin = new User("admin", "admin");
		admin.setPassword("admin");
		userRepository.save(admin);
		User u = new User("login", "test");
		u.setPassword("test");
		userRepository.save(u);
		User u1 = new User("Test1", "test1");
		u.setPassword("test1");
		userRepository.save(u1);
		User u2 = new User("Test2", "test2");
		u.setPassword("test2");
		userRepository.save(u2);
		userList = userRepository.findAll();
	}

	public String obtainAccessToken() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("client_id", "sampleId");
		params.add("username", "admin");
		params.add("password", "admin");
		ResultActions result = mockMvc
				.perform(post("/oauth/token").params(params).with(httpBasic("sampleId", "sampleSecret"))
						.accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		String accesstoken = jsonParser.parseMap(resultString).get("access_token").toString();
		return accesstoken;
	}

	@Test
	public void shouldListUsersWithoutAuthenticationTest() throws Exception {
		mockMvc.perform(get("/users")).andExpect(status().isUnauthorized());
	}

	@Test
	public void shouldObtainAccessTokenTest() throws Exception {

		assertNotNull(obtainAccessToken());
	}

	@Test
	public void shouldListAllUsersWithAuthenticationTest() throws Exception {
		String accessToken = obtainAccessToken();
		mockMvc.perform(
				get("/users").header("Authorization", "Bearer " + accessToken).accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(userList.size()))).andExpect(jsonPath("$[0].id", is(this.userList.get(0).getId())))
				.andExpect(jsonPath("$[0].name", is(this.userList.get(0).getName())))
				.andExpect(jsonPath("$[1].id", is(this.userList.get(1).getId())))
				.andExpect(jsonPath("$[1].name", is(this.userList.get(1).getName())));
	}
	@Test
	public void shouldListLimitedAndPageOneUserWithAuthenticationTest() throws Exception {
		String accessToken = obtainAccessToken();
		mockMvc.perform(get("/users").param("limit", "1").param("page", "1")
				.header("Authorization", "Bearer " + accessToken).accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(this.userList.get(0).getId())))
				.andExpect(jsonPath("$[0].name", is(this.userList.get(0).getName())));
		mockMvc.perform(get("/users").param("limit", "1").param("page", "2")
				.header("Authorization", "Bearer " + accessToken).accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(this.userList.get(1).getId())))
				.andExpect(jsonPath("$[0].name", is(this.userList.get(1).getName())));
	}

	@Test
	public void shouldReturnErrorWithWrongAuthenticationTest() throws Exception {
		String accessToken = obtainAccessToken();
		mockMvc.perform(get("/adminUsers").header("Authorization", "Bearer " + accessToken)
				.accept("application/json;charset=UTF-8")).andExpect(status().isForbidden());
	}

	@Test
	public void shouldAddNewUserWithAuthenticationTest() throws Exception {
		String accessToken = obtainAccessToken();
		User user = new User("NewUser","newuser");
		user.setPassword("newuser");
		ObjectMapper mapper = new ObjectMapper();
		 mockMvc
			.perform(post("/users/new").contentType("application/json;charset=UTF-8")
					.content(mapper.writeValueAsString(user))
					.header("Authorization", "Bearer " + accessToken)
					.accept("application/json;charset=UTF-8"))
			.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void shouldGetSingleUserWithAuthenticationTest() throws Exception {
		String accessToken = obtainAccessToken();
		mockMvc.perform(
				get("/users/"+this.userList.get(1).getId()).header("Authorization", "Bearer " + accessToken).accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$[0].id", is(this.userList.get(1).getId())))
				.andExpect(jsonPath("$[0].name", is(this.userList.get(1).getName())));
	}

	@Test
	public void shouldUpdateUserWithAuthenticationTest() throws Exception {
		String accessToken = obtainAccessToken();
		User user = this.userList.get(1);
		user.setName("updatedName");
		ObjectMapper mapper = new ObjectMapper();
		 mockMvc
			.perform(post("/users/"+this.userList.get(1).getId()+"/update")
					.contentType("application/json;charset=UTF-8").content(mapper.writeValueAsString(user))
					.header("Authorization", "Bearer " + accessToken)
					.accept("application/json;charset=UTF-8"))
			.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void shouldDeleteUserWithAuthenticationTest() throws Exception {
		String accessToken = obtainAccessToken();
		User user = this.userList.stream().filter(x->!x.getUserName().equals("admin")).findFirst().get();
		 mockMvc
			.perform(delete("/users/"+user.getId())
					.header("Authorization", "Bearer " + accessToken)
					.accept("application/json;charset=UTF-8"))
			.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
		 
		 //Add again
		 userRepository.save(user);
	}

	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
