package org.usermgmt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.usermgmt.model.User;
import org.usermgmt.service.impl.LoginServiceImpl;
import org.usermgmt.service.impl.UserServiceImpl;
import org.usermgmt.util.SecurityUtil;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginServiceImpl loginService;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private SecurityUtil securityUtil;

    private static final ObjectMapper om = new ObjectMapper();

    private User loginUser;

    @Before
    public void init() {
        loginUser = new User(3L, "haran", "haran@hidglobal.com", "test123", new Date(), new Date(), new Date());
    }

    @Test
    public void testValidate() throws Exception {
        doNothing().when(loginService).validate(anyString(), anyString());
        when(securityUtil.check(anyString(),anyString())).thenReturn(true);
        when(userService.findUserByEmailAddress(anyString())).thenReturn(loginUser);
        when(userService.updateUser(any(User.class))).thenReturn(loginUser);
        mockMvc.perform(post("/login?emailAddress=shri@hidglobal.com&password=test123")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(loginService, times(1)).validate("shri@hidglobal.com","test123");
    }
}
