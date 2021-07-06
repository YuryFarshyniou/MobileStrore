package by.yurachel.springapp.controller.phone;

import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.service.IService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

        private IService<Phone> service;

        @Autowired
        public void setService(@Qualifier("phoneService") IService<Phone> service) {
            this.service = service;
        }

        @GetMapping()
        public String phoneList(Model model) {
            model.addAttribute("phones", service.findAll());
            return "phones/phoneCatalog";
        }

        @GetMapping("/{id}")
        public String phonePage(@PathVariable("id") int id, Model model) {
            model.addAttribute("phone", service.findById(id));
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
            service.save(phone);
            return "redirect:/phones";
        }

        @GetMapping("/{id}/updatePhone")
        public String edit(Model model, @PathVariable("id") int id) {
            model.addAttribute("phone", service.findById(id));
            return "phones/updatePhone";
        }

        @PatchMapping("/{id}")
        public String update(@PathVariable int id,
                             @ModelAttribute("phone") @Valid Phone phone,
                             BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "phones/updatePhone";
            }
            service.save(phone);
            return "redirect:/phones";
        }

        @DeleteMapping("/{id}")
        public String delete(@PathVariable("id") int id) {
            service.deleteById(id);
            return "redirect:/phones";
        }
}
