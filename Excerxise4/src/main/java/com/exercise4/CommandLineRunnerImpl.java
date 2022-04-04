package com.exercise4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

	@Autowired
	private Environment environment;

	@Autowired
	private ServerProperties serverProperties;

	@Override
	public void run(String... args) throws Exception {
		String[] activeProfiles = environment.getActiveProfiles();
		for (String profile : activeProfiles) {
			System.out.printf("Application running on port: [%s], profile: [%s]\n", serverProperties.getPort(),
					profile);
		}
	}
}