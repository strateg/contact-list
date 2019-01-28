package eu.dmpr.kn.demo.contactlist.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import eu.dmpr.kn.demo.contactlist.domain.Contact;

public interface ContactService {

    /**
     * Finds a "page" of persons
     *
     * @param pageable
     * @return {@link Page} instance
     */
    Page<Contact> findAllPageable(Pageable pageable);

    Page<Contact> findByName(String searchForName, Pageable pageable);
}
