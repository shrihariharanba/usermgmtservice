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
import org.usermgmt.service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    private static final ObjectMapper om = new ObjectMapper();

    private User newUser;
    private User updatedUser;
    private User loginUser;
    private List<User> list;

    @Before
    public void init() {
        newUser = new User(1L, "Shri", "shri@hidglobal.com", "test123", new Date(), null, null);
        updatedUser = new User(2L, "hari", "hari@hidglobal.com", "test123", new Date(), new Date(), null);
        loginUser = new User(3L, "haran", "haran@hidglobal.com", "test123", new Date(), new Date(), new Date());
        list = Arrays.asList(newUser, updatedUser, loginUser);
    }

    @Test
    public void testGet() throws Exception {
        when(userService.getUser(anyLong())).thenReturn(newUser);
        mockMvc.perform(get("/users/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.name", is("Shri")));

        verify(userService, times(1)).getUser(1L);

    }

    @Test
    public void testGetAll() throws Exception {
        when(userService.getAll()).thenReturn(list);
        mockMvc.perform(get("/users"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Shri")))
                .andExpect(jsonPath("$[0].emailAddress", is("shri@hidglobal.com")))
                .andExpect(jsonPath("$[1].userId", is(2)))
                .andExpect(jsonPath("$[1].name", is("hari")))
                .andExpect(jsonPath("$[1].emailAddress", is("hari@hidglobal.com")));
        verify(userService, times(1)).getAll();

    }

    @Test
    public void testCreate() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(newUser);
        mockMvc.perform(post("/users")
                        .content(om.writeValueAsString(newUser))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.name", is("Shri")))
                .andExpect(jsonPath("$.emailAddress", is("shri@hidglobal.com")))
                .andExpect(jsonPath("$.createdDate").isNotEmpty())
                .andExpect(jsonPath("$.updatedDate").isEmpty())
                .andExpect(jsonPath("$.lastLogin").isEmpty());

        verify(userService, times(1)).createUser(any(User.class));

    }

    @Test
    public void testUpdate() throws Exception {
        when(userService.updateUser(any(User.class))).thenReturn(updatedUser);
        mockMvc.perform(put("/users")
                        .content(om.writeValueAsString(updatedUser))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(2)))
                .andExpect(jsonPath("$.name", is("hari")))
                .andExpect(jsonPath("$.emailAddress", is("hari@hidglobal.com")))
                .andExpect(jsonPath("$.createdDate").isNotEmpty())
                .andExpect(jsonPath("$.updatedDate").isNotEmpty())
                .andExpect(jsonPath("$.lastLogin").isEmpty());
        verify(userService, times(1)).updateUser(any(User.class));
    }


    @Test
    public void testDelete() throws Exception {
        doNothing().when(userService).deleteUser(updatedUser);

        mockMvc.perform(delete("/users")
                        .content(om.writeValueAsString(updatedUser))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(any(User.class));

    }


}
