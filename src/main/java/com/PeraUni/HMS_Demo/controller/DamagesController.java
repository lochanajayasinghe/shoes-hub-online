package com.PeraUni.HMS_Demo.controller;

import com.PeraUni.HMS_Demo.model.Damages;
import com.PeraUni.HMS_Demo.service.DamagesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class DamagesController {

    private final DamagesService damagesService;

    public DamagesController(DamagesService damagesService) {
        this.damagesService = damagesService;
    }

    // Home page displaying all damage records
    @GetMapping("/damage")
    public String home(@RequestParam(value = "damage_Id", defaultValue = "0") Long damage_Id,
                       Model model) {
        List<Damages> damagesList = damagesService.getAllDamages();
        model.addAttribute("damagesList", damagesList);
        return "damage_home";
    }

    // Show form for creating a new damage record
    @GetMapping("/damage/create")
    public String createDamageForm(Model model) {
        model.addAttribute("damage", new Damages());
        return "damage_create";
    }

    // Handle form submission for creating or editing a damage record
    @PostMapping("/damage/save")
    public String saveDamage(@Valid @ModelAttribute("damage") Damages damage,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "damage_create";
        }

        damagesService.save(damage);
        redirectAttributes.addFlashAttribute("message", "Damage information saved successfully");
        return "redirect:/damage";
    }

    // Display a specific damage record
    @GetMapping("/damage/{damage_Id}")
    public String showDamage(@PathVariable Long damage_Id, Model model) {
        damagesService.findById(damage_Id)
                .ifPresent(damage -> model.addAttribute("damage", damage));
        return "damage_show";
    }

    // Show form to edit an existing damage record
    @GetMapping("/damage/{damage_Id}/edit")
    public String editDamage(@PathVariable("damage_Id") Long damage_Id, Model model) {
        Damages damage = damagesService.findById(damage_Id).orElse(null);
        model.addAttribute("damage", damage);
        return "damage_create";
    }

    // Handle deletion of a damage record
    @GetMapping("/damage/{damage_Id}/delete")
    public String deleteDamage(@PathVariable Long damage_Id, RedirectAttributes redirectAttributes) {
        damagesService.deleteById(damage_Id);
        redirectAttributes.addFlashAttribute("message", "Damage information deleted successfully");
        return "redirect:/damage";
    }

    @PostMapping("/damage/{damage_Id}/settle")
    public String settleDamage(@PathVariable Long damage_Id, @RequestParam(value = "settled", required = false, defaultValue = "false") boolean settled) {
        Optional<Damages> damageOptional = damagesService.findById(damage_Id);
        if (damageOptional.isPresent()) {
            Damages damage = damageOptional.get();
            damage.setSettled(settled);
            damagesService.save(damage);
        }
        return "redirect:/damage/view";
    }

    // View Page Mapping
    @GetMapping("/damage/view")
    public String viewDamages(Model model) {
        List<Damages> damagesList = damagesService.getAllDamages();
        model.addAttribute("damagesList", damagesList);
        return "damage_view";
    }
}