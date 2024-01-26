package org.adaschool.Weather;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;

@SpringBootTest
public class WeatherReportServiceTests {

    @MockBean
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Test
    public void testGetWeatherReport() {
        // Mocking the external API response
        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main mockMain = new WeatherApiResponse.Main();
        mockMain.setTemperature(25.0);
        mockMain.setHumidity(50.0);
        mockApiResponse.setMain(mockMain);
        Mockito.when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(mockApiResponse);

        // Perform the service method and assert the result
        WeatherReport result = weatherReportService.getWeatherReport(37.8267, -122.4233);
        assertEquals(25.0, result.getTemperature());
        assertEquals(50.0, result.getHumidity());
    }

}
