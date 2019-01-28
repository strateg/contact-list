package eu.dmpr.kn.demo.contactlist.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import eu.dmpr.kn.demo.contactlist.domain.Contact;
import eu.dmpr.kn.demo.contactlist.persistence.ContactSpecificationsBuilder;
import eu.dmpr.kn.demo.contactlist.persistence.SearchOperation;
import eu.dmpr.kn.demo.contactlist.persistence.SpecSearchCriteria;
import eu.dmpr.kn.demo.contactlist.repository.ContactListRepository;
import eu.dmpr.kn.demo.contactlist.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactListRepository contactRepository;

    @Override
    public Page<Contact> findAllPageable(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    @Override
    public Page<Contact> findByName(String searchForName, Pageable pageable) {
        if (Objects.equals(searchForName, "")) {
            return findAllPageable(pageable);
        }

        ContactSpecificationsBuilder builder = new ContactSpecificationsBuilder();
        SpecSearchCriteria spec = new SpecSearchCriteria("name", SearchOperation.LIKE, searchForName);

        return contactRepository.findAll(builder.with(spec).build(), pageable);
    }
}
