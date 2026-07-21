package dev.codeswithrobi.advjava;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoComponent {

  @GetMapping
  String Test() {
    return "Hello world";
  }

}
