package by.yurachel.springapp.controller.phone;

import by.yurachel.springapp.config.security.SecurityUser;
import by.yurachel.springapp.model.phone.OperatingSystem;
import by.yurachel.springapp.model.phone.Phone;
import by.yurachel.springapp.model.phone.ScreenTechnology;
import by.yurachel.springapp.model.user.User;
import by.yurachel.springapp.service.IService;
import by.yurachel.springapp.service.userService.IUserService;
import by.yurachel.springapp.util.phoneUtils.PhoneUtils;
import by.yurachel.springapp.util.userUtils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/phones")
public class PhoneController {

    private final IService<Phone> phoneService;
    private final IUserService<User> userService;
    private final UserUtils userUtils;
    private final PhoneUtils phoneUtils;

    private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);

    public PhoneController(IService<Phone> phoneService, IUserService<User> userService, UserUtils userUtils, PhoneUtils phoneUtils) {
        this.phoneService = phoneService;
        this.userService = userService;
        this.userUtils = userUtils;
        this.phoneUtils = phoneUtils;
    }

    @GetMapping()
    public String phoneList(Model model,
                            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Phone> phones = phoneService.findAllWithPagination(pageable);
        int[] body = pagination(phones);
        model.addAttribute("phones", phones);
        model.addAttribute("body", body);
        model.addAttribute("amountOfElements", new int[]{5, 10, 20, 50});
        model.addAttribute("userUtils", userUtils);


        return "phones/phoneCatalog";
    }

    @GetMapping("/{id}")
    public String phonePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("phone", phoneService.findById(id));
        model.addAttribute("imageLink", "");
        model.addAttribute("userUtils", userUtils);

        return "phones/showPhone";
    }

    @GetMapping("/new")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String addNewPhone(Model model) {
        model.addAttribute("newPhone", new Phone());
        model.addAttribute("os", OperatingSystem.values());
        model.addAttribute("screenTech", ScreenTechnology.values());
        return "phones/newPhone";
    }

    @PostMapping
    public String create(@ModelAttribute("newPhone") @Valid Phone phone,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("os", OperatingSystem.values());
            model.addAttribute("screenTech", ScreenTechnology.values());
            return "phones/newPhone";
        }
        phoneService.save(phone);
        return "redirect:/phones";
    }

    @PostMapping("/{id}")
    public String addImage(@PathVariable long id, @ModelAttribute(value = "imageLink") String imageLink) {
        Phone phone = phoneService.findById(id);
        phoneUtils.addImage(phone, imageLink);
        phoneService.save(phone);
        return "redirect:/phones/" + phone.getId();
    }


    @GetMapping("/{id}/updatePhone")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("phone", phoneService.findById(id));
        model.addAttribute("os", OperatingSystem.values());
        model.addAttribute("screenTech", ScreenTechnology.values());
        return "phones/updatePhone";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id,
                         @ModelAttribute("phone") @Valid Phone phone,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("os", OperatingSystem.values());
            model.addAttribute("screenTech", ScreenTechnology.values());
            return "phones/updatePhone";
        }
        phoneService.save(phone);
        return "redirect:/phones";
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<Phone> delete(@PathVariable long id) {
        phoneUtils.findUsersAndOrders(id);
        phoneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/bookmark/{id}")
    public String addToBookmark(@PathVariable long id, Authentication authentication) {

        Phone phone = phoneService.findById(id);
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        List<Phone> bookmarks = user.getBookmarks();
        userUtils.addToBookMarks(bookmarks, phone);
        userService.save(user);
        if (user.isBookmarksEmpty()) {
            User userFromDb = userService.findById(user.getId());
            SecurityUser securityUser1 = new SecurityUser(userFromDb);
            Collection<? extends GrantedAuthority> authorities = securityUser1.getAuthorities();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(securityUser1, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            userFromDb.setBookmarksEmpty(false);
        }
        return "redirect:/phones/" + id;
    }

    @DeleteMapping(value = "/bookmark/{id}")
    public String deleteFromBookmark(@PathVariable long id,
                                     @RequestParam(value = "from", required = false) String from,
                                     Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        userUtils.deleteFromBookmarks(user.getBookmarks(), id);
        userService.save(user);
        if (from.equals("bookmarks")) {
            return "redirect:/profile/" + id + "/bookmarks";
        }
        return "redirect:/phones/" + id;
    }


    private int[] pagination(Page<Phone> phones) {
        int[] body;
        int maxPhonePages = 7;
        int headMaxPage = 4;
        int bodyBeforeMaxPage = 4;
        int bodyAfterMaxPages = 2;
        int bodyCenterMaxPage = 3;


        if (phones.getTotalPages() > maxPhonePages) {
            int totalPages = phones.getTotalPages();
            int pageNumber = phones.getNumber() + 1; //Отображаемый индекс страницы на единицу больше,чем тот,что мы имеем в коде.

            /*If current page greater than headMaxPage ,than we display page one and minus one,else we display pages one,two three.*/
            int[] head = (pageNumber > headMaxPage) ? new int[]{1, -1} : new int[]{1, 2, 3};
            /*If current page greater than bodyBeforeMaxPage and pageNumber less than totalPAges minus one,than we display pageNumber minus two,
             and pageNumber minus one,else we display nothing.*/
            int[] bodyBefore = (pageNumber > bodyBeforeMaxPage && pageNumber < totalPages - 1) ? new int[]{pageNumber - 2, pageNumber - 1} : new int[]{};
             /*If current page greater than bodyAfterMaxPages and pageNumber less than totalPAges minus three,than we display pageNumber plus one,
             and pageNumber plus two,else we display nothing.*/
            int[] bodyAfter = (pageNumber > bodyAfterMaxPages && pageNumber < totalPages - 3) ? new int[]{pageNumber + 1, pageNumber + 2} : new int[]{};
            /*If current page greater than bodyCenterMaxPage and pageNumber less than totalPAges minus two,than we display pageNumber,else we display nothing.*/
            int[] bodyCenter = (pageNumber > bodyCenterMaxPage && pageNumber < totalPages - 2) ? new int[]{pageNumber} : new int[]{};
            /*If current page less than totalPages minus three,than we display minus one and totalPages,else we display totalPages-2,totalPages-1 and totalPages.*/
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
