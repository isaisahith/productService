package org.sahith.products.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {
    private long id;
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private State state;
}
