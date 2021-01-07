package com.example.company_lab3.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "public", name = "worker")
public class Worker {
    private Integer personnelNumber;
    private String fullName;
    private Integer category;
    private Machine machine;
    public Worker() {
    }

    @Id
    @Column(name = "personnel_number", nullable = false)
    public Integer getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    @Column(name = "full_name", nullable = false, length = 40)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    @Column(name = "category", nullable = false)
    public Integer getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return personnelNumber == worker.personnelNumber &&
                Objects.equals(fullName, worker.fullName) &&
                category.equals(worker.category);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mashine_id")
    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }


}
