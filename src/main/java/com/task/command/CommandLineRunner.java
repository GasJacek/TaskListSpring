package com.task.command;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import picocli.CommandLine;
import picocli.spring.PicocliSpringFactory;


@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
	
	private BufferedReader br;
	
	@Autowired
	private TaskCommand command;
	
	@Autowired
    private ApplicationContext applicationContext;
	
    public CommandLineRunner() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run(String... args) throws Exception {
    	CommandLine commandLine = new CommandLine(command, new PicocliSpringFactory(applicationContext));

    	while(true) {
            System.out.print("> ");
            String line = br.readLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.toLowerCase().startsWith("exit")) {
                break;
            }
            String[] res = line.split(" (?=([^\"]|\"[^\"]*\")*$)");
            commandLine.execute(res);
        }
    }
}