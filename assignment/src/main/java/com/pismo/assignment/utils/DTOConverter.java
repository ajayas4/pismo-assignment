package com.pismo.assignment.utils;

import com.pismo.assignment.dto.AccountDTO;
import com.pismo.assignment.dto.TransactionDto;
import com.pismo.assignment.entity.Account;
import com.pismo.assignment.entity.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DTOConverter {


    public Optional<Account> dtoToAccount(AccountDTO accountDTO){
        if(accountDTO==null){
            return Optional.empty();
        }
        return Optional.of(Account.builder().accountId(accountDTO.accountId())
                .documentNumber(accountDTO.documentNumber()).build());
    }

    public Optional<AccountDTO> accountToDto(Account account){
        if(account==null){
            return Optional.empty();
        }
        return Optional.of(new AccountDTO(account.getAccountId(), account.getDocumentNumber()));
    }

    public Optional<List<Account>> dtoListToAccountList(List<AccountDTO> dtoList){
        if(CollectionUtils.isEmpty(dtoList)){
            return Optional.empty();
        }
        List<Account> accountList=new ArrayList<>();
        return Optional.of(dtoList.stream().map(dto->dtoToAccount(dto)).flatMap(Optional::stream).collect(Collectors.toList()));
    }

    public Optional<List<AccountDTO>> accountListToDtoList(List<Account> accountList){
        if(CollectionUtils.isEmpty(accountList)){
            return Optional.empty();
        }
        List<AccountDTO> dtoList=new ArrayList<>();
        return Optional.of(accountList.stream().map(account->accountToDto(account)).flatMap(Optional::stream).collect(Collectors.toList()));
    }


    public Optional<Transaction>  dtoToTransaction(TransactionDto dto){
        if(dto==null){
            return Optional.empty();
        }
        return Optional.of(Transaction.builder().transactionId(dto.transactionId()).accountId(dto.accountId())
                .amount(dto.amount()).eventDate(LocalDateTime.now()).build());
    }


    public Optional<TransactionDto>  transactionToDto(Transaction transaction){
        if(transaction==null){
            return Optional.empty();
        }
        return Optional.of(new TransactionDto(transaction.getTransactionId(),transaction.getAccountId(),
                transaction.getOperationType().getOperationTypeId(),
                transaction.getAmount(),transaction.getEventDate()
               ));
    }

    public Optional<List<Transaction>> dtoListToTransactionList(List<TransactionDto> dtoList){
        if(CollectionUtils.isEmpty(dtoList)){
            return Optional.empty();
        }
        List<Transaction> transactionList=new ArrayList<>();
        return Optional.of(dtoList.stream().map(dto->dtoToTransaction(dto)).flatMap(Optional::stream).collect(Collectors.toList()));
    }



    public Optional<List<TransactionDto>> transactionListToDtoList(List<Transaction> transactionList){
        if(CollectionUtils.isEmpty(transactionList)){
            return Optional.empty();
        }
        List<TransactionDto> dtoList=new ArrayList<>();
        return Optional.of(transactionList.stream().map(transaction->transactionToDto(transaction)).flatMap(Optional::stream).collect(Collectors.toList()));
    }

}
