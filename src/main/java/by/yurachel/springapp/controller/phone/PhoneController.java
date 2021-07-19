package by.yurachel.springapp.controller.phone;

import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

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
    public String phoneList(Model model,
                            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Phone> phones = phoneService.findAllPhones(pageable);
        int[] body = pagination(phones);
        model.addAttribute("phones", phones);
        model.addAttribute("body", body);

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

//    private List<Integer> pagination(Page<Phone> phones) {
//        List<Integer> body = new ArrayList<>();
//        if (phones.getTotalPages() > 7) {
//            int totalPages = phones.getTotalPages();
//            int pageNumber = phones.getNumber() + 1;
//            List<Integer> a = new ArrayList<>(Arrays.asList(1, -1));
//            List<Integer> b = new ArrayList<>(Arrays.asList(1, 2, 3));
//            List<Integer> c = new ArrayList<>(Arrays.asList(-1, totalPages));
//            List<Integer> d = new ArrayList<>(Arrays.asList(totalPages - 2, totalPages - 1, totalPages));
//            List<Integer> e = new ArrayList<>(Arrays.asList(pageNumber - 2, pageNumber - 1));
//            List<Integer> f = new ArrayList<>();
//            List<Integer> g = new ArrayList<>(Arrays.asList(pageNumber + 1, pageNumber + 2));
//            List<Integer> h = new ArrayList<>(Arrays.asList(pageNumber));
//
//            List<Integer> head = (pageNumber > 4) ? a : b;
//            List<Integer> tail = (pageNumber < totalPages - 3) ? c : d;
//            List<Integer> bodyBefore = (pageNumber > 4 && pageNumber > totalPages - 1) ? e : f;
//            List<Integer> bodyAfter = (pageNumber < 2 && pageNumber > totalPages - 3) ? g : f;
//            List<Integer> middle = (pageNumber > 3 && pageNumber > totalPages - 2) ? h : f;
//            body.addAll(head);
//            body.addAll(bodyBefore);
//            body.addAll(middle);
//            body.addAll(bodyAfter);
//            body.addAll(tail);
//
//        } else {
//            for (int i = 0; i < phones.getTotalPages(); i++) {
//                body.add(++i);
//            }
//        }
//
//        System.out.println(body);
//        return body;
//    }

    private int[] pagination(Page<Phone> phones) {
        int[] body;
        if (phones.getTotalPages() > 7) {
            int totalPages = phones.getTotalPages();
            int pageNumber = phones.getNumber() + 1;
            int[] head = (pageNumber > 4) ? new int[]{1, -1} : new int[]{1, 2, 3};
            int[] bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1) ? new int[]{pageNumber - 2, pageNumber - 1} : new int[]{};
            int[] bodyCenter = (pageNumber > 3 && pageNumber < totalPages - 2) ? new int[]{pageNumber} : new int[]{};
            int[] bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3) ? new int[]{pageNumber + 1, pageNumber + 2} : new int[]{};
            int[] tail = (pageNumber < totalPages - 3) ? new int[]{-1, totalPages} : new int[]{totalPages - 2, totalPages - 1, totalPages};
            body = merge(head, bodyBefore, bodyCenter, bodyAfter, tail);
        } else {
            body = new int[phones.getTotalPages()];
            for (int i = 0; i < phones.getTotalPages(); i++) {
                body[i] = i + 1;
            }
        }
        return body;
    }

    private int[] merge(int[]... intArrays) {
        return Arrays.stream(intArrays).flatMapToInt(Arrays::stream)
                .toArray();
    }
}
