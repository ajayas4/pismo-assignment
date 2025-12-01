package com.pismo.assignment.service;

import com.pismo.assignment.constant.AppConstants;
import com.pismo.assignment.dto.TransactionDto;
import com.pismo.assignment.entity.OperationType;
import com.pismo.assignment.entity.Transaction;
import com.pismo.assignment.exception.InternalServerException;
import com.pismo.assignment.exception.ResourceConflictException;
import com.pismo.assignment.exception.ResourceNotFoundException;
import com.pismo.assignment.repository.OperationTypeRepository;
import com.pismo.assignment.repository.TransactionRepository;
import com.pismo.assignment.utils.DTOConverter;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    Map<Long, OperationType> operationTypeMap;


    private final TransactionRepository transactionRepository;
    private final DTOConverter dtoConverter;

    private final OperationTypeRepository operationTypeRepository;
    @PostConstruct
    public void init(){
        operationTypeMap=operationTypeRepository.findAll().stream().
                collect(Collectors.toMap(op->op.getOperationTypeId(),Function.identity()));
    }

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,DTOConverter dtoConverter
    ,OperationTypeRepository operationTypeRepository){
        this.transactionRepository=transactionRepository;
        this.dtoConverter=dtoConverter;
        this.operationTypeRepository=operationTypeRepository;
    }

    public TransactionDto save(TransactionDto dto){
        validateTransactionType(dto.transactionType());
        dto=applyDebitorCredit(dto);
        Transaction transaction=dtoConverter.dtoToTransaction(dto).orElseThrow(()-> new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        transaction.setOperationType(operationTypeMap.get(dto.transactionType()));
        return dtoConverter.transactionToDto(transactionRepository.save(transaction)).orElseThrow(()-> new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }

    public List<TransactionDto> findAll(int page,int size,Sort sort){
        Pageable pageable= PageRequest.of(page, size,sort);
        return dtoConverter.transactionListToDtoList(transactionRepository.findAll(pageable).toList()).orElse(null);
    }

    public List<TransactionDto> findByAccount(Long accountId, int page, int size, Sort sort){
        Pageable pageable=PageRequest.of(page,size,sort);
        return dtoConverter.transactionListToDtoList(transactionRepository.findByAccountId(accountId,pageable).toList()).
                orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase()));
    }


    private void validateTransactionType(Long id){
        if(operationTypeMap==null || operationTypeMap.isEmpty()){
            throw new ResourceConflictException(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase());
        }
        if(operationTypeMap.get(id)==null){
            throw new ResourceConflictException(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase());
        }
    }
    private TransactionDto applyDebitorCredit(TransactionDto dto){
        if(AppConstants.debitTransactionTypes.contains(dto.transactionType())){
            return new TransactionDto(dto.transactionId(), dto.accountId(),
                    dto.transactionType(),dto.amount().multiply(new BigDecimal(-1)),dto.eventDate());
        }
        return dto;
    }


}
