package pl.ing.tesla.atm;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtmController.class)
class AtmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest()
    @CsvSource({
            "/atm/example_1_request.json, /atm/example_1_response.json",
            "/atm/example_2_request.json, /atm/example_2_response.json"
    })
    void calculateAtmsOrder_shouldGenerateExpectedOrder(String request, String response) throws Exception {

        String requestJson = getFileAsString(request);
        String responseJson = getFileAsString(response);

        this.mockMvc.perform(post("/atms/calculateOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    private String getFileAsString(String file) throws IOException {
        return new String(Objects.requireNonNull(this.getClass().getResourceAsStream(file)).readAllBytes());
    }
}
