package com.spring.SpringAndSpringBoot.controller;

/*ResponseEntity<> - allows developer to send response body, statusand headers on the http response */

/*@RestController// @Controller +@ResponseBody where @ResponseBody says response should be in json format not in
spring mvc format i.e html or any other ui format*/

/*@ControllerAdvice is used along with @ExceptionHandler Using this annotation we are telling spring boot that whenever exception happens in any of
my controller please invoke method i am writing here
we can even use @RestControllerAdvice which is @ControllerAdvice+@ResponseBody*/

/*@RequestHeader & @requestBody used to receive header and body individually*/
import com.spring.SpringAndSpringBoot.constants.AccountsConstants;
import com.spring.SpringAndSpringBoot.dto.CustomerDto;
import com.spring.SpringAndSpringBoot.dto.ResponseDto;
import com.spring.SpringAndSpringBoot.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD Operations for accounts",
        description = "CREATE,RETRIEVE,UPDATE,DELETE")
@RestController// @Controller +@ResponseBody where @ResponseBody says response should be in json format not in spring mvc format i.e html or any other ui format
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated //This will tell to spring boot that to perform validation all the rest apis that are defined in this class and @Valid next to input
public class AccountsController {


//    @Autowired /*we can go with this but recommended approach is to create the constructor so instead of this mention
//    AllArgsConstructor*/
    private IAccountsService iAccountsService;

    @Operation(
            summary = "Create Account Rest api",
            description = "REST Api to create new customer and account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CRATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

        iAccountsService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto>  fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits") String mobileNumber){
    CustomerDto customerDto=iAccountsService.fetchAccount(mobileNumber);

    return new ResponseEntity<>(customerDto,HttpStatus.OK);

//   Orwe can do this way return ResponseEntity.status(HttpStatus.OK).body(customerDto);


    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto){
        boolean isUpdated= iAccountsService.updateAccount(customerDto);
        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200,
                    AccountsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam String mobileNumber){

        boolean isDeleted=iAccountsService.deleteAccount(mobileNumber);

        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200,
                    AccountsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500)
            );
        }

    }

}
