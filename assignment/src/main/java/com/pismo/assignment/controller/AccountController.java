package com.pismo.assignment.controller;

import com.pismo.assignment.constant.AppConstants;
import com.pismo.assignment.dto.AccountDTO;
import com.pismo.assignment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;



    @PostMapping(value = "/")
    public ResponseEntity<AccountDTO> save(@RequestBody AccountDTO accountDTO){
        AccountDTO response=accountService.save(accountDTO);
        return ResponseEntity.created(URI.create("account/"+response.accountId())).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDTO>> findAll(@RequestParam int page,@RequestParam int size,
                                                    @RequestParam(defaultValue = "accountId") String sortBy,@RequestParam(defaultValue = "asc") String direction){
            if(!AppConstants.allowedSortByInAccount.contains(sortBy) || !AppConstants.allowedSortDirections.contains(direction)){
                return ResponseEntity.badRequest().build();
            }
            Sort sort=direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return ResponseEntity.ok(accountService.findAll(page,size,sort));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Long accountId){
        return ResponseEntity.ok(accountService.findById(accountId));
    }
}
