package com.shawebbill.shawebbill.controller;

public class Program {
    private int programId;
    private String programDescription;
    private String programType;

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId=" + programId +
                ", programDescription='" + programDescription + '\'' +
                ", programType='" + programType + '\'' +
                '}';
    }
}