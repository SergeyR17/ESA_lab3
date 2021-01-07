package com.example.company_lab3.controllers;

import com.example.company_lab3.entity.Machine;
import com.example.company_lab3.entity.MachineList;
import com.example.company_lab3.entity.Worker;
import com.example.company_lab3.repository.MachineRepository;
import com.example.company_lab3.repository.WorkerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.company_lab3.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Реализуем JSON и XML APIs

@Controller
@RequestMapping("/RESTapi/machines/") // общий путь для всех членов класса
public class ApiForMachine extends XmlController {
    // Подключим (свяжем) репозитории (которые являются оболочкой баз Postgress)
    @Autowired
    MachineRepository machineRepository;
    @Autowired
    WorkerRepository workerRepository;

    // Реализуем сначала JSON
    // Получить все записи в формате json
    //@GetMapping
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private List<Machine> getMachines() {

        return this.machineRepository.findAll();
    }

    // Получить запись автомобиля по id
    //@GetMapping("{id}")
    @RequestMapping(value="{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Machine> getMachineById(@PathVariable(value = "id") Integer machineId)
            throws NotFoundException {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new NotFoundException("Отсутствует Машина по этому id :: " + machineId));
        return ResponseEntity.ok().body(machine);
    }

    // Создать запись
    //@PostMapping
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Machine addMachine(@RequestBody Machine machine) {
        return machineRepository.save(machine);
    }

    // Обновить запись
    //@PutMapping("{id}")
    @RequestMapping(value="{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Machine> updateMachine(@PathVariable Integer id, @Validated @RequestBody Machine machineDetails)
            throws NotFoundException {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Автомобиль не находится по этому id :: " + id));
        machine.setType(machineDetails.getType());

        return ResponseEntity.ok(machineRepository.save(machine));
    }

    //удалить запись
    //@DeleteMapping("{id}")
    @RequestMapping(value="{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Boolean> deleteMachine(@PathVariable Integer id)
            throws NotFoundException {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Автомобиль не находится по этому idd :: " + id));

        List<Worker> workers = workerRepository.findAll();
        workers.forEach(worker -> {
            if (worker.getMachine().getId().equals(machine.getId())) {
                worker.setMachine(null);
                workerRepository.save(worker);
            }
        });
        machineRepository.delete(machine);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
    // Реализуем xml
    // Получить все записи В формате xml
    @GetMapping("xml")
    private ResponseEntity<String> getMachinesXml() throws JsonProcessingException {
        List<Machine> machines = new ArrayList<Machine>();
        machines = this.machineRepository.findAll();
        return getXmlResponse(new MachineList(machines));
    }

    // Получить запись по id
    @GetMapping("xml/{id}")
    public ResponseEntity<String> getMachineByIdXml(@PathVariable(value = "id") Integer machineId)
            throws NotFoundException, JsonProcessingException {
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new NotFoundException("Отсутствует Машина по этому id :: " + machineId));
        return getXmlResponse(machine);
    }

    // Создать запись
    @PostMapping("xml")
    @ResponseBody
    public Machine addMachineXml(@RequestBody Machine machine) {

        return machineRepository.save(machine);
    }

    // Обновить запись
    @PutMapping("xml/{id}")
    @ResponseBody
    public ResponseEntity<Machine> updateMachineXml(@PathVariable Integer id, @Validated @RequestBody Machine machineDetails)
            throws NotFoundException {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Отсутствует Машина по этому id :: " + id));
        machine.setType(machineDetails.getType());

        return ResponseEntity.ok(machineRepository.save(machine));
    }

    //удалить запись
    @DeleteMapping("xml/{id}")
    @ResponseBody
    public Map<String, Boolean> deleteMachineXml(@PathVariable Integer id)
            throws NotFoundException {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Отсутствует Машина по этому id :: " + id));

        List<Worker> workers = workerRepository.findAll();
        workers.forEach(worker -> {
            if (worker.getMachine().getId().equals(machine.getId())) {
                worker.setMachine(null);
                workerRepository.save(worker);
            }
        });
        machineRepository.delete(machine);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }



}
