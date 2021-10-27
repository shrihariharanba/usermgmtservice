package org.usermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.usermgmt.entity.UserEntity;

import java.util.List;

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

    @Query(nativeQuery = true, value="select * from  user where user_id in (?1)")
    List<UserEntity> findByUserIds(Long[] userIds);
}
