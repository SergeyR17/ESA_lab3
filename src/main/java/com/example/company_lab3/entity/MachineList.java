package com.example.company_lab3.entity;

import java.util.List;


public class MachineList {
    private List<Machine> machines;

    public MachineList(List<Machine> machines) {
        this.machines = machines;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }
}
