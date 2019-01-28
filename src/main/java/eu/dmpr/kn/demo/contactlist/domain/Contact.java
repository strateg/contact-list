package eu.dmpr.kn.demo.contactlist.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="contact")
@Getter @Setter @NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    @Column(name="name")
    public String name;

    @Column(name="url")
    public String url;
}
