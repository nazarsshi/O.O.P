package com.company;

public class Echo implements Command {
    private String string;
    public Echo(String string){
        this.string = string;
    }
    @Override
    public void execute() {
        System.out.println(string);
    }
}

