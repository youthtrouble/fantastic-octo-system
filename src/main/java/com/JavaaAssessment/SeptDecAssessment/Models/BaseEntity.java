package com.JavaaAssessment.SeptDecAssessment.Models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * Represents the base entity in the product application.
 * This class contains information about the base entity.
 * The properties of the class are inherited by other classes because they contain common information.
 *
 *     The {@code BaseEntity} class provides methods to access and modify the base entity.
 *     It also includes annotations for database mapping and table configuration.
 *
 *
 *         The base entity includes:
 *         <ul>
 *             <li>Created At</li>
 *             <li>Updated At</li>
 *             <li>Is Deleted</li>
 *             </ul>
 *
 *
 *                 The {@code BaseEntity} class is annotated with {@code @MappedSuperclass} to indicate that it is a JPA entity.
 *                 It is also annotated with {@code @SQLDelete} to specify the name of the database table for this entity.
 *
 *
 *                     The class includes getters and setters for all the properties, as well as constructors for creating instances of the class.
 *
 *
 *
 */
@MappedSuperclass
@SQLDelete(sql = "UPDATE BaseEntity SET is_deleted = true WHERE id = ?")
public class BaseEntity {
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted = Boolean.FALSE;

    public BaseEntity() {

    }

    public BaseEntity(Date createdAt, Date updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = false;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
