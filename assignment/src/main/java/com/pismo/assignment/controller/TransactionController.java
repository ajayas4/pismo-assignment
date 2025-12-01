package com.pismo.assignment.controller;

import com.pismo.assignment.constant.AppConstants;
import com.pismo.assignment.dto.TransactionDto;
import com.pismo.assignment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<TransactionDto> save(@RequestBody TransactionDto transactionDto){
        TransactionDto response=transactionService.save(transactionDto);
        return  ResponseEntity.created(URI.create("/transactions/"+response.transactionId())).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>> findAll(@RequestParam int page,@RequestParam int size,
                                                        @RequestParam(defaultValue = "eventDate") String sortBy,
                                                        @RequestParam(defaultValue = "asc") String direction){

        if(!AppConstants.allowedSortByInTransaction.contains(sortBy) || !AppConstants.allowedSortDirections.contains(direction)){
            return ResponseEntity.badRequest().build();
        }
        Sort sort=direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return  ResponseEntity.ok(transactionService.findAll(page,size,sort));
    }


    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionDto>> findByAccount(@PathVariable Long accountId,@RequestParam int page,
                                                              @RequestParam int size, @RequestParam(defaultValue = "eventDate") String sortBy,
                                                              @RequestParam(defaultValue = "asc") String direction){


        if(!AppConstants.allowedSortByInTransaction.contains(sortBy) || !AppConstants.allowedSortDirections.contains(direction)){
            return ResponseEntity.badRequest().build();
        }
        Sort sort=direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return  ResponseEntity.ok(transactionService.findByAccount(accountId,page,size,sort));
    }

}
