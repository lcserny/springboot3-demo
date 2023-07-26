package net.cserny.demo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/remote")
public class RemoteController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RemoteData> getResponseData() {
        log.info("Giving remote response back");
        return ResponseEntity.ok(new RemoteData.RemoteDataBuilder()
                .status("SUCCESS")
                .build());
    }
}
