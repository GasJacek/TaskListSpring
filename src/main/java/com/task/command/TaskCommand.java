package com.task.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine.*;

@Component
@Command( name = "task", subcommands = {AddTask.class, FetchAllTask.class, UpdateTask.class, DeleteTask.class})
public class TaskCommand {


    @Option(names =  "help" , usageHelp = true, description = "display a help message")
    private boolean helpRequested;

}