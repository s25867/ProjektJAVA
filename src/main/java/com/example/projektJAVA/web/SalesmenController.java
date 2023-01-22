package com.example.projektJAVA.web;

import com.example.projektJAVA.exception.ResourceNotFoundException;
import com.example.projektJAVA.model.Salesman;
import com.example.projektJAVA.repository.SalesmenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SalesmenController {
    @Autowired
    private SalesmenRepository salesmansRepository;

    @GetMapping("/salesmans")
    public String getAllSalesmen(Model model){
        if(!this.salesmansRepository.findAll().isEmpty()){
            model.addAttribute("listSalesmen", this.salesmansRepository.findAll());
            return "showSalesmen";
        }
        else{
            model.addAttribute("message", "List of salesmans is empty!");
            return "error";
        }
    }

    @GetMapping("/salesmans/{id}")
    public String getSalesmanById(@PathVariable(value = "id") Long salesmanId, Model model){
        if(this.salesmansRepository.findById(salesmanId).isPresent()){
            Salesman salesman = this.salesmansRepository.findById(salesmanId).orElseThrow();

            model.addAttribute("salesman", salesman);
            model.addAttribute("header", "Found salesman:");
            return "showSalesman";
        }
        else{
            model.addAttribute("message", "Salesman with id: " + salesmanId + " not found.");
            return "error";
        }
    }

    @GetMapping("/salesmans/add")
    public String createSalesman(Model model){
        model.addAttribute("salesman", new Salesman());
        return "createSalesman";
    }
    @PostMapping("/salesmans/add")
    public String addSalesman(@ModelAttribute Salesman salesman, Model model){
        this.salesmansRepository.save(salesman);

        model.addAttribute("salesman", salesman);
        model.addAttribute("header", "Added salesman:");
        return "showSalesman";
    }

    @GetMapping("/salesmans/update/{id}")
    public String recreateSalesman(@PathVariable ("id") Long salesmanId, Model model){
        if(this.salesmansRepository.findById(salesmanId).isPresent()){
            Salesman salesman = this.salesmansRepository.findById(salesmanId).orElseThrow();

            model.addAttribute("existingSalesman", salesman);
            model.addAttribute("updatedSalesman", new Salesman());
            return "updateSalesman";
        }
        else{
            model.addAttribute("message", "Salesman with id: " + salesmanId + " not found");
            return "error";
        }


    }

    @PostMapping("/salesmans/update/{id}")
    public String updateSalesman(@PathVariable ("id") Long salesmanId, @ModelAttribute Salesman salesman, Model model){
        if(this.salesmansRepository.findById(salesmanId).isPresent()){
            Salesman existingSalesman = this.salesmansRepository.findById(salesmanId).orElseThrow();

            existingSalesman.setName(salesman.getName());
            existingSalesman.setSurname(salesman.getSurname());
            existingSalesman.setPesel(salesman.getPesel());
            existingSalesman.setSalary(salesman.getSalary());
            this.salesmansRepository.save(existingSalesman);

            model.addAttribute("salesman", existingSalesman);
            model.addAttribute("header", "Updated salesman:");
            return "showSalesman";
        }
        else{
            model.addAttribute("Salesman with id: " + salesmanId + " not found");
            return "error";
        }
    }

    @DeleteMapping("/salesmans/delete/{id}")
    public String deletesalesman(@PathVariable ("id") Long salesmanId, Model model){
        if(this.salesmansRepository.findById(salesmanId).isPresent()){
            Salesman existingSalesman = this.salesmansRepository.findById(salesmanId).orElseThrow();
            this.salesmansRepository.delete(existingSalesman);

            model.addAttribute("message", "Salesman with id: " + salesmanId + " has been deleted.");
        }
        else{
            model.addAttribute("Salesman with id: " + salesmanId + " not found");
        }
        return "error";
    }
}
