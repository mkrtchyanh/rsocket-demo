package io.hayk.demo.note;

import io.hayk.demo.AbstractDomainEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "note")
public class Note extends AbstractDomainEntity {

    /**
     * Accessible by reflection.
     * <p>
     * Will be used by frameworks depending on default constructor
     */
    Note() {
        super();
    }
}
