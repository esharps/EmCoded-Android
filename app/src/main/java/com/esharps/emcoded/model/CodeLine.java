package com.esharps.emcoded.model;

public class CodeLine {
    // Programming language for code editor
    private String programLanguage;
    // Code written by user to be sent as a command
    private String command;

    public CodeLine() {

    }

    public CodeLine(String programLanguage, String command) {
        this.programLanguage = programLanguage;
        this.command = command;
    }

    public String getProgramLanguage() {
        return programLanguage;
    }

    public void setProgramLanguage(String programLanguage) {
        this.programLanguage = programLanguage;
    }

    public String getCommand() {
        return command;
    }

    public void setCodeCommand(String command) {
        this.command = command;
    }



}
