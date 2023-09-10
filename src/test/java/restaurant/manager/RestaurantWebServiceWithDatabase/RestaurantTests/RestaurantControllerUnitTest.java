package restaurant.manager.RestaurantWebServiceWithDatabase.RestaurantTests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import restaurant.manager.RestaurantWebServiceWithDatabase.Controllers.RestaurantController;
import restaurant.manager.RestaurantWebServiceWithDatabase.DTOs.RestaurantDTO;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.JwtUserDetailsService;
import restaurant.manager.RestaurantWebServiceWithDatabase.Services.RestaurantService;
import restaurant.manager.RestaurantWebServiceWithDatabase.Utilities.JwtTokenUtil;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
@WithMockUser(username = "user", password = "user", roles = "EATER")
public class RestaurantControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService service;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @Test
    public void fetchAllRestaurantsWithoutPagingTest() throws Exception {
        RestaurantDTO restaurantDTO = new RestaurantDTO(1, "TestRestaurant");
        List<RestaurantDTO> restaurantDTOList = Arrays.asList(restaurantDTO);
        given(service.fetchAllRestaurants(0, 5, "restaurantName")).willReturn(restaurantDTOList);
        mvc.perform(get("/restaurants/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].restaurantName", is(restaurantDTO.getRestaurantName())));
    }

    @Test
    public void Test() {
        Assertions.assertFalse(2 == 3);
    }


}
