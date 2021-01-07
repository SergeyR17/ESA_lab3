package com.example.company_lab3.controllers;

import com.example.company_lab3.entity.Worker;
import com.example.company_lab3.entity.WorkerList;
import com.example.company_lab3.repository.WorkerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.company_lab3.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/RESTapi/workers/")
public class ApiForWorkers extends XmlController{

    @Autowired
    WorkerRepository workerRepository;

    //Для json ответа
    // Получить все записи
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private List<Worker> getWorkers() {
        return this.workerRepository.findAll();
    }

    // Получить запись по id
    @RequestMapping(value="{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Worker> getWorkerById(@PathVariable(value = "id") int workerId)
            throws NotFoundException {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new NotFoundException("Рабочий не находится по этому id :: " + workerId));
        return ResponseEntity.ok().body(worker);

    }

    // Создать запись
    @PostMapping
    @ResponseBody
    public Worker addWorker(@RequestBody Worker worker) {
        return workerRepository.save(worker);
    }

    // Обновить запись
    @RequestMapping(value="{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Worker> updateWorker(@PathVariable(value = "id") int personnelNumber, @Validated @RequestBody Worker workerDetails)
            throws NotFoundException {
        Worker worker = workerRepository.findById(personnelNumber)
                .orElseThrow(() -> new NotFoundException("Рабочий не находится по этому id :: " + personnelNumber));
        worker.setFullName(workerDetails.getFullName());
        worker.setCategory(workerDetails.getCategory());
        worker.setMachine(workerDetails.getMachine());

        return ResponseEntity.ok(workerRepository.save(worker));
    }

    //удалить запись
    @RequestMapping(value="{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Boolean> deleteMachine(@PathVariable(value = "id") int personnelNumber)
            throws NotFoundException {
        Worker worker = workerRepository.findById(personnelNumber)
                .orElseThrow(() -> new NotFoundException("Рабочий не находится по этому idd :: " + personnelNumber));

        workerRepository.delete(worker);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }


    //Для xml ответа (по умолчанию)
    // Получить все записи
    @GetMapping("xml")
    private ResponseEntity<String> getWorkersXml() throws JsonProcessingException {
        List<Worker> output = new ArrayList<Worker>();
        output = this.workerRepository.findAll();
        return getXmlResponse(new WorkerList(output));
    }

    // Получить запись по id
    @GetMapping("xml/{id}")
    public ResponseEntity<String> getWorkerByIdXml(@PathVariable(value = "id") int workerId)
            throws NotFoundException, JsonProcessingException {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new NotFoundException("Водитель не находится по этому id :: " + workerId));
        return getXmlResponse(worker);

    }

    // Создать запись
    @PostMapping("xml")
    @ResponseBody
    public Worker addWorkerXml(@RequestBody Worker worker) {
        Worker created = workerRepository.save(worker);
        return created;
    }

    // Обновить запись
    @PutMapping("xml/{id}")
    @ResponseBody
    public ResponseEntity<Worker> updateWorkerXml(@PathVariable(value = "id") int personnelNumber, @Validated @RequestBody Worker workerDetails)
            throws NotFoundException {
        Worker worker = workerRepository.findById(personnelNumber)
                .orElseThrow(() -> new NotFoundException("Водитель не находится по этому id :: " + personnelNumber));
        worker.setFullName(workerDetails.getFullName());
        worker.setCategory(workerDetails.getCategory());
        worker.setMachine(workerDetails.getMachine());

        return ResponseEntity.ok(workerRepository.save(worker));
    }

    //удалить запись
    @DeleteMapping("xml/{id}")
    @ResponseBody
    public Map<String, Boolean> deleteMachineXml(@PathVariable(value = "id") int personnelNumber)
            throws NotFoundException {
        Worker worker = workerRepository.findById(personnelNumber)
                .orElseThrow(() -> new NotFoundException("Рабочий не находится по этому idd :: " + personnelNumber));

        workerRepository.delete(worker);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }


}
