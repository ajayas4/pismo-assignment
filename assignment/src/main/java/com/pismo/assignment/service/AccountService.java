package com.pismo.assignment.service;

import com.pismo.assignment.dto.AccountDTO;
import com.pismo.assignment.entity.Account;
import com.pismo.assignment.exception.InternalServerException;
import com.pismo.assignment.exception.ResourceNotFoundException;
import com.pismo.assignment.repository.AccountRepository;
import com.pismo.assignment.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final DTOConverter dtoConverter;

    @Autowired
    public AccountService(AccountRepository accountRepository,DTOConverter dtoConverter){
        this.accountRepository=accountRepository;
        this.dtoConverter=dtoConverter;
    }

    public AccountDTO save(AccountDTO accountDTO){
        Account account=dtoConverter.dtoToAccount(accountDTO).orElse(null);
        return dtoConverter.accountToDto(accountRepository.save(account)).
                orElseThrow(()-> new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));

    }

    public List<AccountDTO> findAll(int page, int size, Sort sort){
        Pageable pageable= PageRequest.of(page,size,sort);
        return dtoConverter.accountListToDtoList(accountRepository.findAll(pageable).toList()).orElse(null);
    }

    public AccountDTO findById(Long accountId){
        return dtoConverter.accountToDto(accountRepository.findById(accountId).orElse(null))
                .orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase()));

    }


}
