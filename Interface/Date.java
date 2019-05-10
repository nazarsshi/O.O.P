package com.company;

public class Date implements Command {
    @Override
    public void execute() {
        System.out.println(System.currentTimeMillis());
    }
}

