package com.meirapentermann.controllers;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)  //run tests using this special Spring class
@ContextConfiguration(locations = { "StateControllerTests-context.xml" }) // with this Spring config file
@WebAppConfiguration  //and treat it like a WebApp(something TomCat could run
public class TaskControllerTests {
	MockMvc mockMvc;  //Need one of these to "perform()" requests

	@Autowired
	WebApplicationContext wc; //This got created when I told Spring to treat like a WebApp
							 //It's where all Spring's beans live (Controllers, DAO's, anything Spring created
//	@Autowired
//	StateController controller; //Spring created a controller because I annotated my class with @Contoller
//							    //Here's how I get ahold of that object
//
//	MockStateDAO mockDAO;     //This is set as a field

	@Before
	public void setUp() {
//		mockDAO = wc.getBean(MockStateDAO.class);  //the MockStateDAO was created in StateControllerTests-contex.xml
//		
//		// TODO - uncomment below to add a Mock object, which we control
//		controller.setStateDao(mockDAO);
		
		// TODO - build the MockMvc object with MockMvcBuilders
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build(); //final step, build the thing to make request
																 //using the Application Context that Spring
																 //created for me
	}

	@After
	public void tearDown(){
//		mockDAO.loadStates(); //cleanup
	}

	@Test
	public void test_GET_GetStateData_do_with_valid_name_param_returns_State() {
		try {
			MvcResult result = mockMvc.perform(get("/GetStateData.do").param("name", "Colorado"))
					.andExpect(status().isOk()).andReturn();
			ModelAndView mv = result.getModelAndView();
			assertEquals("result.jsp", mv.getViewName());
			ModelMap map = mv.getModelMap(); //what is in the model and available to JSP?
			assertNotNull(map.get("state"));

////			State st = (State) map.get("state");
//			assertEquals("Colorado", st.getName());
//			assertEquals("CO", st.getAbbreviation());
//			assertEquals("3", st.getLatitude());
//			assertEquals("4", st.getLongitude()); // - test other fields

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
}
