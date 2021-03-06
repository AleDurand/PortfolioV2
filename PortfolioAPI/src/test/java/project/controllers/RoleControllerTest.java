package project.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import project.Application;
import project.exceptions.EntityNotFoundException;
import project.models.RoleModel;
import project.services.RoleService;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class RoleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GlobalExceptionController controllerAdvice;

	@Autowired
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@InjectMocks
	private RoleController roleController;

	@Mock
	private RoleService roleServiceMock;

	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(roleController)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				.setControllerAdvice(controllerAdvice)
				.apply(springSecurity(springSecurityFilterChain)).build();
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void create() throws Exception {
		// @formatter:off
		RoleModel role = new RoleModel();
		role.setId(1);
		role.setName("name");
		
		RoleModel expectedRole = new RoleModel();
		expectedRole.setId(1);
		expectedRole.setName("name");
		when(roleServiceMock.create(any())).thenReturn(expectedRole);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/roles")
			.contentType(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsBytes(role))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("name")))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void read() throws Exception {
		// @formatter:off
		RoleModel expectedRole = new RoleModel();
		expectedRole.setId(1);
		expectedRole.setName("name");
		when(roleServiceMock.read(1)).thenReturn(expectedRole);

		mockMvc.perform(MockMvcRequestBuilders.get("/roles/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("name")))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void readNotFound() throws Exception {
		// @formatter:off
		when(roleServiceMock.read(1)).thenThrow(new EntityNotFoundException("resource.not_found", null));

		mockMvc.perform(MockMvcRequestBuilders.get("/roles/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void delete() throws Exception {
		// @formatter:off
		mockMvc.perform(MockMvcRequestBuilders.delete("/roles/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void deleteNotFound() throws Exception {
		// @formatter:off
		doThrow(new EntityNotFoundException("resource.not_found", null)).when(roleServiceMock).delete(1);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/roles/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		// @formatter:on
	}

	@Test
	@WithMockUser(authorities = { "Administrator" })
	public void getAll() throws Exception {
		// @formatter:off
		Page<RoleModel> expectedRoles = new PageImpl<RoleModel>(Arrays.asList(new RoleModel()));
		when(roleServiceMock.getAll(any(), any(), any())).thenReturn(expectedRoles);

		mockMvc.perform(MockMvcRequestBuilders.get("/roles")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$.content", hasSize(1)))
			.andExpect(content().contentType("application/json;charset=UTF-8"));
		// @formatter:on
	}

}
