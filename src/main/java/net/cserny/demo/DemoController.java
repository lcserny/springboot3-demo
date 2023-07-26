package net.cserny.demo;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/")
public class DemoController {

    private final RestTemplate restClient;
    private final DemoRepository demoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DemoData> retrieveDemo() {
        log.info("Retrieving demos");
        return demoRepository.findAll().stream()
                .map(e -> {
                    return new DemoData.DemoDataBuilder()
                            .name(e.getName())
                            .age(e.getAge())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDemo(@RequestBody @Valid DemoData data) {
        log.info("Saving demo " + data);
        DemoEntity ent = new DemoEntity.DemoEntityBuilder()
                .name(data.getName())
                .age(data.getAge())
                .build();
        demoRepository.save(ent);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RemoteData callRemote() {
        log.info("Proxying remote service");
        ResponseEntity<RemoteData> response = restClient.getForEntity("http://localhost:8081/remote", RemoteData.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new RuntimeException(response.getStatusCode().toString());
    }
}
