package ca.mcgill.ecse428.moviejunkie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	@GetMapping("/version")
	public String getVersion() {
		return "1.0";
	}

	@GetMapping("/ping")
	public String getPing() { return "pong"; }

}
