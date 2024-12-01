package com.example.beadando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller // kell
public class BeadandoApplication {
	public static void main(String[] args) {
		SpringApplication.run(BeadandoApplication.class, args);
	}
	@GetMapping("/feladat1a")
	@ResponseBody // kell
	public String kiir1a() {
		return "<h1>Hello world!</h1>";
	}
}
