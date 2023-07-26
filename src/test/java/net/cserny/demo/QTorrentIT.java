package net.cserny.demo;

import org.apache.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static net.cserny.demo.QTorrentRestClient.SID_KEY;
import static net.cserny.demo.QTorrentTestContainer.QTORRENT_PORT;

@SpringBootTest
@Testcontainers
public class QTorrentIT {

    @Autowired
    private QTorrentRestClient restClient;

    @Container
    public static QTorrentTestContainer container = QTorrentTestContainer.getInstance();

    @DynamicPropertySource
    public static void qTorrentProperties(DynamicPropertyRegistry registry) {
        registry.add("torrent.webui.baseUrl", () -> "http://localhost:" + QTORRENT_PORT);
    }

    @Test
    @DisplayName("Qtorrent started")
    public void startedContainer() throws Exception {
        given()
                .port(QTORRENT_PORT)
                .cookie(SID_KEY, restClient.generateSid())
                .when()
                .post("/api/v2/torrents/info")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK);
    }
}
