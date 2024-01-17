package at.qe.skeleton.tests;

import at.qe.skeleton.external.exceptions.WeatherApiException;
import at.qe.skeleton.external.model.currentandforecast.CurrentAndForecastAnswerDTO;
import at.qe.skeleton.external.services.WeatherApiRequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeatherApiRequestServiceTest {

    @InjectMocks
    private WeatherApiRequestService weatherApiRequestService;

    @Test
    void buildApiUrl_CorrectParameters_ReturnsApiUrl() {
        // Arrange
        double latitude = 40.7128;
        double longitude = -74.0060;
        String expectedApiUrl = UriComponentsBuilder.fromPath("/data/3.0/onecall")
                .queryParam("lat", String.valueOf(latitude))
                .queryParam("lon", String.valueOf(longitude))
                .build().toUriString();

        // Act
        String result = weatherApiRequestService.buildApiUrl(latitude, longitude);

        // Assert
        assertEquals(expectedApiUrl, result);
    }

    @Test
    void handleApiResponse_SuccessfulResponse_DoesNotThrowException() {
        // Arrange
        ResponseEntity<CurrentAndForecastAnswerDTO> successResponseEntity = new ResponseEntity<>(HttpStatus.OK);

        // Act & Assert
        assertDoesNotThrow(() -> weatherApiRequestService.handleApiResponse(successResponseEntity));
    }

    @Test
    void handleApiResponse_ErrorResponse_ThrowsWeatherApiException() {
        // Arrange
        ResponseEntity<CurrentAndForecastAnswerDTO> errorResponseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        // Act & Assert
        WeatherApiException exception = assertThrows(WeatherApiException.class,
                () -> weatherApiRequestService.handleApiResponse(errorResponseEntity));

        assertEquals("Error while retrieving current and forecast weather. Status code: " + HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
