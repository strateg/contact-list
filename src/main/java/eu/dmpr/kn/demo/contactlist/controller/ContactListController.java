package eu.dmpr.kn.demo.contactlist.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eu.dmpr.kn.demo.contactlist.domain.Contact;
import eu.dmpr.kn.demo.contactlist.domain.Pager;
import eu.dmpr.kn.demo.contactlist.service.ContactService;

@Controller
@RequestMapping("/")
public class ContactListController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    ContactService contactService;

    /**
     * Handles all requests
     *
     * @param pageSize
     * @param page
     * @return model and view
     */
    @GetMapping("/contacts")
    public ModelAndView showPersonsPage(@RequestParam("searchFor") Optional<String> name, @RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page) {
        ModelAndView modelAndView = new ModelAndView("contacts");

        // Evaluate page size. If requested parameter is null, return initial page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to  prevent exception),
        // return initial size.
        // Otherwise, return value of param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Contact> contacts = contactService.findByName(name.orElse(""),PageRequest.of(evalPage, evalPageSize));
        Pager pager = new Pager(contacts.getTotalPages(), contacts.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("contacts", contacts);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }
}
