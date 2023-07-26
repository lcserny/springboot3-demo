package net.cserny.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "torrent.webui")
@Getter
@Setter
public class TorrentWebUiProperties {

    private String baseUrl;
    private String username;
    private String password;
}
