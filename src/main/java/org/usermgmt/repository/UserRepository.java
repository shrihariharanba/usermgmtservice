package org.usermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usermgmt.entity.UserEntity;

/**
 * User Repository
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Method to fetch the user bu email address
     * @param emailAddress
     * @return the user
     */
    UserEntity findByEmailAddress(String emailAddress);
}
