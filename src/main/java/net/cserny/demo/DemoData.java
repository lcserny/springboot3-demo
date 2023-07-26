package net.cserny.demo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DemoData {

    @NotEmpty
    private String name;
    private int age;
}
