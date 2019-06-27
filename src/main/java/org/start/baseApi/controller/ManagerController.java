package org.start.baseApi.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.start.baseApi.model.Manager;
import org.start.baseApi.respository.ManagerRepository;
import org.start.baseApi.util.authentication.ProjectToken;

import javax.ws.rs.core.Response;
import java.util.*;

@RestController
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Manager> findAll() {
        return managerRepository.findAll();
}

    @GetMapping(value = "{idManager}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Manager find(@PathVariable("idManager") UUID idManager) {
        return managerRepository.findById(idManager).orElse(null) ;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Manager save(@RequestBody Manager Manager) {
        Manager.setId(UUID.randomUUID());
        return managerRepository.save(Manager);
    }

    @PutMapping(value = "{idManager}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Manager edit(@PathVariable("idManager") UUID idManager,
                        @RequestBody Manager manager) {

        if (manager.getId() == null){
            manager.setId(idManager);
        }
        return managerRepository.save(manager);
    }

    @DeleteMapping(value = "{idManager}")
    public void delete(@PathVariable("idManager") UUID idManager){
        managerRepository.deleteById(idManager);
    }

    @DeleteMapping(value = "")
    public void deleteAll(){
        managerRepository.deleteAll();
    }

    @PostMapping(value = "login", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public String authenticateUser(@RequestBody Manager manager) {

        ProjectToken projectToken = new ProjectToken();
        JSONObject jsonReturn = new JSONObject();

        Manager lManager = managerRepository.findByLoginPassword( manager.getLogin(), manager.getPassword());

        if (lManager == null) {
            jsonReturn.put("path", "/manager/login");
            jsonReturn.put("message", "Invalid Token");
            jsonReturn.put("error", Response.Status.UNAUTHORIZED);
            jsonReturn.put("status", Response.Status.UNAUTHORIZED.getStatusCode());
        }else{
            jsonReturn.put("status", Response.Status.ACCEPTED.toString());
            jsonReturn.put("userId", lManager.getId().toString());
            jsonReturn.put("token", projectToken.genereteToken(manager.getLogin()));
        }

        return jsonReturn.toString();
    }
}
