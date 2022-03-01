package org.example.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lacombe.kata.Coordinate;
import lacombe.kata.GameState;
import lacombe.kata.Player;
import lacombe.kata.TicTacToe;
import org.apache.logging.log4j.util.Strings;
import org.example.dto.CoordinateDTO;
import org.example.dto.GameStateDTO;
import org.example.dto.GridDTO;
import org.example.dto.PlayersShotDTO;
import org.example.service.TicTacToeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TicTacToeController.class)
public class TicTacToeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicTacToeService ticTacToeService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void play_X_from_1_1() throws Exception {
        PlayersShotDTO playersShotDTO = new PlayersShotDTO("PLAYER_X", new CoordinateDTO(1, 1));
        String inputJson = mapToJson(playersShotDTO);

        ticTacToeService.play(Player.PLAYER_X, new Coordinate(1, 1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/TicTacToe/play")
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    public void state_is_over() throws Exception {
        Mockito.when(ticTacToeService.state()).thenReturn(GameState.IS_OVER);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/TicTacToe/state")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String expectedOutputJson = mapToJson(GameStateDTO.IS_OVER);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedOutputJson);
    }
    @Test
    public void grid_value() throws Exception {
        var ticTacToe = new TicTacToe();
        ticTacToe.play(Player.PLAYER_X, 2, 2);
        ticTacToe.play(Player.PLAYER_O, 1, 3);

        Mockito.when(ticTacToeService.getGrid()).thenReturn(ticTacToe.getCells());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/TicTacToe/grid")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String[][] expectedCells = {
                {Strings.EMPTY, Strings.EMPTY, Strings.EMPTY},
                {Strings.EMPTY, "PLAYER_X", Strings.EMPTY},
                {"PLAYER_O", Strings.EMPTY, Strings.EMPTY}
        };
        String expectedOutputJson = mapToJson(new GridDTO(expectedCells));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedOutputJson);
    }
}
