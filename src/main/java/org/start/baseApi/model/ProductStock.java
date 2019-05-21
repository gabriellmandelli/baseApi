package org.start.baseApi.model;

import org.hibernate.annotations.Type;
import org.start.baseApi.model.enums.Movement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class ProductStock implements Serializable {

    @Id
    @Type(type="uuid-char")
    @Column(unique = true)
    private UUID id;

    @OneToOne
    private SaleItem saleItem;

    @Column
    private int amount;

    @Column
    private Movement movement;

}
