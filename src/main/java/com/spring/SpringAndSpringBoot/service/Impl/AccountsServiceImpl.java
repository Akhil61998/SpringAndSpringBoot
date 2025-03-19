package com.spring.SpringAndSpringBoot.service.Impl;

import com.spring.SpringAndSpringBoot.constants.AccountsConstants;
import com.spring.SpringAndSpringBoot.dto.AccountsDto;
import com.spring.SpringAndSpringBoot.dto.CustomerDto;
import com.spring.SpringAndSpringBoot.entity.Accounts;
import com.spring.SpringAndSpringBoot.entity.Customer;
import com.spring.SpringAndSpringBoot.exception.CustomerAlreadyExistsException;
import com.spring.SpringAndSpringBoot.exception.ResourceNotFoundException;
import com.spring.SpringAndSpringBoot.mapper.AccountsMapper;
import com.spring.SpringAndSpringBoot.mapper.CustomerMapper;
import com.spring.SpringAndSpringBoot.repository.AccountsRepository;
import com.spring.SpringAndSpringBoot.repository.CustomerRepository;
import com.spring.SpringAndSpringBoot.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service

@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    @Autowired
    private AccountsRepository  accountsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> customerOptional=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(customerOptional.isPresent()){
            throw new CustomerAlreadyExistsException("Customer Already registered with given MobileNumber"
                    +customerDto.getMobileNumber());
        }
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("Anonymous");
        Customer savedCustomer=customerRepository.save(customer);

       accountsRepository.save(createNewAccount(savedCustomer)) ;

        }


    private Accounts createNewAccount(Customer customer){

        Accounts accounts =new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccountNumber=100000000L +new Random().nextInt(900000000);

        accounts.setAccountNumber(randomAccountNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
//            accounts.setCreatedAt(LocalDateTime.now());
//            accounts.setCreatedBy("Anonymous");
        return accounts;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","MobileNumber",mobileNumber)
        );
        Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString())
        );

        CustomerDto customerDto=CustomerMapper.mapToCustomerDto(customer,new CustomerDto());

        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated=false;
        AccountsDto accountsDto=customerDto.getAccountsDto();
        if(accountsDto!=null){
            Accounts accounts=accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()-> new ResourceNotFoundException("Accounts","AccountNumber",accountsDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountsDto,accounts);
            accountsRepository.save(accounts);

            Long customerId=accounts.getCustomerId();
            Customer  customer=customerRepository.findById(customerId).orElseThrow(
                    ()->new ResourceNotFoundException("Customer","CustomerId",customerId.toString())
            );

            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated=true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","MobileNumber",mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}

