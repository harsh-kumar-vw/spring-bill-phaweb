package com.shawebbill.shawebbill.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
        private int projectId;
        private String projectDescription;
        private String referenceProjectNumber;

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public String getProjectDescription() {
            return projectDescription;
        }

        public void setProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription;
        }

        public String getReferenceProjectNumber() {
            return referenceProjectNumber;
        }

        public void setReferenceProjectNumber(String referenceProjectNumber) {
            this.referenceProjectNumber = referenceProjectNumber;
        }
    }

