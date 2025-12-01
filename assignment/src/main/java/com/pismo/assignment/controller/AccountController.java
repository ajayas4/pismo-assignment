package com.pismo.assignment.controller;

import com.pismo.assignment.constant.AppConstants;
import com.pismo.assignment.dto.AccountDTO;
import com.pismo.assignment.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {


    private final  AccountService accountService;



    @PostMapping(value = "/")
    public ResponseEntity<AccountDTO> save(@Valid @RequestBody AccountDTO accountDTO){
        AccountDTO response=accountService.save(accountDTO);
        return ResponseEntity.created(URI.create("account/"+response.accountId())).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDTO>> findAll(@RequestParam int page,@RequestParam int size,
                                                    @RequestParam(defaultValue = "accountId") String sortBy,@RequestParam(defaultValue = "asc")
                                                    @Pattern(regexp = "asc|desc", message="Invalid sort direction") String direction)  {
            if(!AppConstants.allowedSortByInAccount.contains(sortBy)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid sorting parameters");
            }
            Sort sort=direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return ResponseEntity.ok(accountService.findAll(page,size,sort));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Long accountId){
        return ResponseEntity.ok(accountService.findById(accountId));
    }
}
