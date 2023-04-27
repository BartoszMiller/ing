package pl.ing.tesla;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest()
    @MethodSource("getApiUrlAndRequestAndResponse")
    void apiShouldReturnExpectedResponse(String url, String request, String response) throws Exception {

        String requestJson = getFileAsString(request);
        String responseJson = getFileAsString(response);

        this.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    public static Stream<Arguments> getApiUrlAndRequestAndResponse() {
        return Stream.of(
                Arguments.of("/atms/calculateOrder", "/atm/example_1_request.json", "/atm/example_1_response.json"),
                Arguments.of("/atms/calculateOrder", "/atm/example_2_request.json", "/atm/example_2_response.json"),
                Arguments.of("/onlinegame/calculate", "/game/example_request.json", "/game/example_response.json"),
                Arguments.of("/transactions/report", "/transaction/example_request.json", "/transaction/example_response.json")
        );
    }

    private String getFileAsString(String filePath) throws IOException {
        InputStream is = Objects.requireNonNull(this.getClass().getResourceAsStream(filePath));
        return new String(is.readAllBytes());
    }
}
