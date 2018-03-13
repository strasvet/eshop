package com.example.eshop.repository;

import com.example.eshop.model.User;
import com.example.eshop.model.dtoByRole.UserForNoAdmin;
import com.example.eshop.model.web.LoginRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User getByUserNameAndPassword(String username, String password);


    @Query("select new com.example.eshop.model.dtoByRole.UserForNoAdmin(u.firstName, u.lastName, u.email) from User u")
    List<UserForNoAdmin> getUserForNoAdmin();



/*
    @Query("select new com.example.demo.core_2.EmployeeFirstnameLastnameEmailDTO(e.firstName, e.lastName, e.email) from Employee e where e.id = :employeeId")
    EmployeeFirstnameLastnameEmailDTO getBasicEmployee(@Param("employeeId") Integer employeeId);


    @Query(value = "select max(u.age) from User u")
    Integer findMaxAge();

    User findByUsername(String username);

    List<User> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<User> findAllByLastNameIn(String ...lastNames);

    List<User> findAllByIsActivatedTrue();

    Page<User> findAllByCreatedOnNotNull(Pageable pageable);


*/


}
