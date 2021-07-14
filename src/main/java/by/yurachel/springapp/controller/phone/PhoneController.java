package by.yurachel.springapp.controller.phone;

import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/phones")
public class PhoneController {

    private IService<Phone> phoneService;
    private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);

    @Autowired
    public void setService(@Qualifier("phoneService") IService<Phone> service) {
        this.phoneService = service;
    }

    @GetMapping()
    public String phoneList(Model model) {
        model.addAttribute("phones", phoneService.findAll());
        return "phones/phoneCatalog";
    }

    @GetMapping("/{id}")
    public String phonePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("phone", phoneService.findById(id));
        return "phones/showPhone";
    }

    @GetMapping("/new")
    public String addNewPhone(Model model) {
        model.addAttribute("newPhone", new Phone());
        return "phones/newPhone";
    }

    @PostMapping
    public String create(@ModelAttribute("newPhone") @Valid Phone phone,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "phones/newPhone";
        }
        phoneService.save(phone);
        return "redirect:/phones";
    }


    @GetMapping("/{id}/updatePhone")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("phone", phoneService.findById(id));
        return "phones/updatePhone";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable int id,
                         @ModelAttribute("phone") @Valid Phone phone,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "phones/updatePhone";
        }
        phoneService.save(phone);
        return "redirect:/phones";
    }

    @DeleteMapping(value = "/{id}", name = "removePhone")
    public String delete(@PathVariable("id") int id) {
        phoneService.deleteById(id);
        return "redirect:/phones";
    }
}
