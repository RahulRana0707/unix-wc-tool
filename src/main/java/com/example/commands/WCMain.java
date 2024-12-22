package com.example.commands;

import com.beust.jcommander.Parameter;

public class WCMain {

    @Parameter(names = "-v", description = "Verbose mode")
    public Boolean verbose = false;
}
