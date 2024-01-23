package com.JavaaAssessment.SeptDecAssessment.Models;

import jakarta.persistence.*;

/**
 *
 * Represents the admin details in the product application.
 * This class contains information about the admin's username and password.
 * <p>
 *     The {@code AdminDetails} class provides methods to access and modify the admin's username and password.
 *     It also includes annotations for database mapping and table configuration.
 *     </p>
 *
 *         The admin's details includes:
 *         <ul>
 *             <li>Username</li>
 *             <li>Password</li>
 *             </ul>
 *
 *             <p>
 *                 The {@code AdminDetails} class is annotated with {@code @Entity} to indicate that it is a JPA entity.
 *                 It is also annotated with {@code @Table} to specify the name of the database table for this entity.
 *                 </p>
 *                 <p>
 *                     The class includes getters and setters for all the properties, as well as constructors for creating instances of the class.
 *                     </p>
 *
 *
 */
@Entity
@Table(name = "admin")
public class AdminDetails extends BaseEntity {
    @Id
    @Column(name="admin_id")
    @SequenceGenerator(name = "admin_sequence", sequenceName = "admin_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_sequence")
    private Long adminId;
    private String userName;
    private String password;

    /**
     * Creates an instance of the {@code AdminDetails} class.
     */
    public AdminDetails() {

    }

    /**
     * Creates an instance of the {@code AdminDetails} class.
     * @param userName The username of the admin.
     * @param password The password of the admin.
     */
    public AdminDetails(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     *Gets the username of the admin.
     * @return The username of the admin.
     */
    public String getUserName() {
        return userName;
    }

    /**
     *Gets the password of the admin.
     * @return The password of the admin.
     */
    public String getPassword() {
        return password;
    }

    /**
     *Sets the username of the admin.
     * @param userName The username of the admin.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *Sets the password of the admin.
     * @param password The password of the admin.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return userName;
    }
}
