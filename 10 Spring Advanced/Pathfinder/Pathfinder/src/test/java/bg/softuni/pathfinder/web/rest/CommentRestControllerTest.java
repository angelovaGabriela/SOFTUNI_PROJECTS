package bg.softuni.pathfinder.web.rest;

import bg.softuni.pathfinder.model.dto.CommentCreationDTO;
import bg.softuni.pathfinder.model.dto.CommentMessageDTO;
import bg.softuni.pathfinder.model.views.CommentDisplayView;
import bg.softuni.pathfinder.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {
    private static final Long ROUTE_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    public void getComments_twoCommentsExist_commentsReturnedAsJsonAndStatusIsOk() throws Exception {

        when(commentService.getAllCommentsForRoute(ROUTE_ID)).thenReturn(List.of(
                new CommentDisplayView(1L, "John Doe", "This is comment #1!"),
                new CommentDisplayView(2L, "Foo Bar", "This is comment #2!")
        ));

        mockMvc.perform(get("/api/" + ROUTE_ID + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].authorName", is("John Doe")))
                .andExpect(jsonPath("$.[0].message", is("This is comment #1!")))
                .andExpect(jsonPath("$.[1].authorName", is("Foo Bar")))
                .andExpect(jsonPath("$.[1].message", is("This is comment #2!")));


    }

    @Test
    @WithMockUser("testUsername")
    public void createComment_sampleData_commentIsReturnedAsExpected() throws Exception {
        when(commentService.createComment(any())).thenAnswer(interaction -> {
            CommentCreationDTO commentCreationDTO = interaction.getArgument(0);
            return new CommentDisplayView(1L, commentCreationDTO.getUsername(), commentCreationDTO.getMessage());
        });

        CommentMessageDTO commentMessageDTO = new CommentMessageDTO("This is comment #1");
        ObjectMapper objectMapper = new ObjectMapper();


        mockMvc.perform(post("/api/" + ROUTE_ID + "/comments")
                        .content(objectMapper.writeValueAsString(commentMessageDTO))
                        .with(csrf())
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.message", is("This is comment #1")))
                .andExpect(jsonPath("$.authorName", is("testUsername")));
    }
}
