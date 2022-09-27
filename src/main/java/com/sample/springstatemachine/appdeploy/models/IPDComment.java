package com.sample.springstatemachine.appdeploy.models;

public enum IPDComment {
    MODEL_START("com.sap.newton.sysml.service.model.start"), 
    MODEL_DONE("com.sap.newton.sysml.service.model.done"), 
    APP_ZIP("com.sap.newton.sysml.service.app.zip"), 
    APP_PUSH("com.sap.newton.sysml.service.app.push"), 
    APP_READY("com.sap.newton.sysml.service.app.ready"), 
    APP_START("com.sap.newton.sysml.service.app.start"), 
    APP_LINK("com.sap.newton.sysml.service.app.link"), 
    APP_COMPLETE("com.sap.newton.sysml.service.sim.complete"), 
    APP_RESULT("com.sap.newton.sysml.service.sim.result"), 
    APP_DOWNLOAD("com.sap.newton.sysml.service.app.download"), 
    APP_COPY("com.sap.newton.sysml.service.app.copy"), 
    UNKNOWN("");

    private String message;

    IPDComment(String message) {
        this.message = message;
    }

    public String getComment() {
        return message;
    }
}