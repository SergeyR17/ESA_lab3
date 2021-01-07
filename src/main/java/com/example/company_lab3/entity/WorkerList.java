package com.example.company_lab3.entity;

import java.util.List;

public class WorkerList {
    private List<Worker> workers;

    public WorkerList(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
