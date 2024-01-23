package com.JavaaAssessment.SeptDecAssessment.Repository.Admin;

import com.JavaaAssessment.SeptDecAssessment.Models.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository class for AdminDetails
 * This class is used to perform CRUD operations on the AdminDetails table
 * <p>
 * This interface extends the JpaRepository interface, which provides generic
 * CRUD operations and advanced querying capabilities for the Customer entity.
 * It is annotated with the {@link org.springframework.data.jpa.repository.JpaRepository}
 * annotation to indicate that it is a repository interface.
 * </p>
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.JavaaAssessment.SeptDecAssessment.Models.Customer
 */
@Repository
public interface AdminRepository extends JpaRepository<AdminDetails, Long>  {

    /**
     * This method is used to validate the admin
     *
     * @param userName
     * @param password
     * @return boolean
     */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AdminDetails a WHERE a.userName = ?1 AND a.password = ?2")
    public boolean validateAdmin(String userName, String password);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AdminDetails a WHERE a.userName = ?1")
    public boolean checkAdmin(String userName);

    /**
     * This method is used to find a admin by username
     *
     * @param username
     * @return AdminDetails
     */
    @Query("SELECT a FROM AdminDetails a WHERE a.userName = ?1")
    Optional<AdminDetails> getAdminByUsername(String username);
}
