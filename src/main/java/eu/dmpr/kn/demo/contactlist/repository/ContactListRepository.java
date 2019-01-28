package eu.dmpr.kn.demo.contactlist.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import eu.dmpr.kn.demo.contactlist.domain.Contact;

@Repository
public interface ContactListRepository extends PagingAndSortingRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {
}
