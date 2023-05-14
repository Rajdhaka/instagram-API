package com.geekster.instagram.repositories;

import com.geekster.instagram.models.User;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Long>{
    User findFirstByUserEmail(String userEmail);

    @Modifying
    @Query(value = "update user set user_phone_number =:userPhoneNo where user_id =:userId",nativeQuery = true)
    void updateUserByUserId(Long userId, String userPhoneNo);


    User findByUserName(String userName);
}
