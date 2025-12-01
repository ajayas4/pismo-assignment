package com.pismo.assignment;


import com.pismo.assignment.dto.AccountDTO;
import com.pismo.assignment.entity.Account;
import com.pismo.assignment.exception.InternalServerException;
import com.pismo.assignment.repository.AccountRepository;
import com.pismo.assignment.service.AccountService;
import com.pismo.assignment.utils.DTOConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class TestAccountService {

    @Mock
    private AccountRepository accountRepository;

    @Spy
    private DTOConverter dtoConverter;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void test_save_success(){
        AccountDTO dto=new AccountDTO(null,"1");
        Account accountAfter=new Account(1l,"1");
        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(accountAfter);

        AccountDTO savedAccount= accountService.save(dto);

        Assertions.assertNotNull(savedAccount.accountId());
        Assertions.assertSame(1l,savedAccount.accountId());
    }


    @Test
    public void test_save_fail(){
        AccountDTO dto=new AccountDTO(null,"1");
        Account accountAfter=new Account(1l,"1");
        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(null);

        InternalServerException ex=Assertions.assertThrows(InternalServerException.class,
                ()->accountService.save(dto));

    }


    @Test
    public void test_find_all_success(){
        Account account1=new Account(1l,"3");
        Account account2=new Account(2l,"2");
        Account account3=new Account(3l,"1");
        Pageable pageable= PageRequest.of(0,3, Sort.by("accountId").descending());
        Page<Account> page=new PageImpl<>(List.of(account1,account2,account3));
        Mockito.when(accountRepository.findAll(any(Pageable.class))).thenReturn(page);
        List<AccountDTO> response= accountService.findAll(0,3, Sort.by("accountId").descending());
        Assertions.assertEquals(3,response.size());



    }


}
