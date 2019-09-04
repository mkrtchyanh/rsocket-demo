package io.hayk.demo;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractDomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created")
    private LocalDateTime created;

    protected AbstractDomainEntity() {
        super();
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
