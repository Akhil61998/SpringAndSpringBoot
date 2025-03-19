package com.spring.SpringAndSpringBoot.repository;

import com.spring.SpringAndSpringBoot.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {


    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional
    @Modifying
    /*These two Annotations are required to delete otherwise will get below error
    * (jakarta.persistence.TransactionRequiredException: No EntityManager with actual transaction available
    * for current thread - cannot reliably process 'remove' call)*/
    void deleteByCustomerId(Long customerId);
}
