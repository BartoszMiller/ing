package pl.ing.tesla;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class AtmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest()
    @CsvSource({
            "/atms/calculateOrder , /atm/example_1_request.json, /atm/example_1_response.json",
            "/atms/calculateOrder , /atm/example_2_request.json, /atm/example_2_response.json",
            "/onlinegame/calculate , /game/example_request.json, /game/example_response.json",
            "/transactions/report , /transaction/example_request.json, /transaction/example_response.json"
    })
    void calculateAtmsOrder_shouldGenerateExpectedOrder(String url, String request, String response) throws Exception {

        String requestJson = getFileAsString(request);
        String responseJson = getFileAsString(response);

        this.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    private String getFileAsString(String filePath) throws IOException {
        InputStream is = Objects.requireNonNull(this.getClass().getResourceAsStream(filePath));
        return new String(is.readAllBytes());
    }
}
