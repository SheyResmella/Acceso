package com.salesianos.cache.controller;

import com.salesianos.cache.model.Pc;
import com.salesianos.cache.service.PcService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pcs")
public class PcController {

    private final PcService pcService;

    public PcController(PcService pcService) {
        this.pcService = pcService;
    }

    @GetMapping("/")
    public String listarPcs(Model model) {
        List<Pc> pcs = pcService.getAllPcs();
        model.addAttribute("pcs", pcs);
        return "index";
    }

    @GetMapping("/{id}/edit")
    public String showEditPcForm(@PathVariable Long id, Model model) {
        Pc pc = pcService.getPcById(id);
        if (pc == null) {
            return "redirect:/pcs/";
        }
        model.addAttribute("pc", pc);
        return "editar";
    }

    @PostMapping("/{id}")
    public String updatePc(@PathVariable Long id, @ModelAttribute Pc pc) {
        pc.setId(id);
        pcService.updatePc(pc);
        return "redirect:/pcs/";
    }

    @GetMapping("/add")
    public String showAddPcForm(Model model) {
        model.addAttribute("pc", new Pc());
        return "nuevo";
    }

    @PostMapping("/add")
    public String addPc(@ModelAttribute("pc") Pc pc) {
        pcService.updatePc(pc);
        return "redirect:/pcs/";
    }
    
    @GetMapping("/{id}/delete")
    public String deletePc(@PathVariable Long id) {
        pcService.deletePc(id);
        return "redirect:/pcs/";
    }
}
