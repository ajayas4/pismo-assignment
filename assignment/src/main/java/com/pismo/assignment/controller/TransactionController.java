package com.pismo.assignment.controller;

import com.pismo.assignment.constant.AppConstants;
import com.pismo.assignment.dto.TransactionDto;
import com.pismo.assignment.service.TransactionService;
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
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {



    private final TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<TransactionDto> save(@Valid @RequestBody TransactionDto transactionDto){
        TransactionDto response=transactionService.save(transactionDto);
        return  ResponseEntity.created(URI.create("/transactions/"+response.transactionId())).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll(@RequestParam int page,@RequestParam int size,
                                                        @RequestParam(defaultValue = "eventDate") String sortBy,
                                                        @Pattern(regexp = "asc|desc", message="Invalid sort direction") @RequestParam(defaultValue = "asc") String direction)  {

        if(!AppConstants.allowedSortByInTransaction.contains(sortBy) || !AppConstants.allowedSortDirections.contains(direction)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid sorting parameters");
        }
        Sort sort=direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return  ResponseEntity.ok(transactionService.findAll(page,size,sort));
    }


    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionDto>> findByAccount(@PathVariable Long accountId,@RequestParam int page,
                                                              @RequestParam int size, @RequestParam(defaultValue = "eventDate") String sortBy,
                                                              @Pattern(regexp = "asc|desc", message="Invalid sort direction") @RequestParam(defaultValue = "asc") String direction)  {


        if(!AppConstants.allowedSortByInTransaction.contains(sortBy) || !AppConstants.allowedSortDirections.contains(direction)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid sorting parameters");
        }
        Sort sort=direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return  ResponseEntity.ok(transactionService.findByAccount(accountId,page,size,sort));
    }

}
