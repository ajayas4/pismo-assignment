package com.pismo.assignment;

import com.pismo.assignment.dto.TransactionDto;
import com.pismo.assignment.entity.Account;
import com.pismo.assignment.entity.OperationType;
import com.pismo.assignment.entity.Transaction;
import com.pismo.assignment.enums.OperationTypeEnum;
import com.pismo.assignment.exception.InternalServerException;
import com.pismo.assignment.repository.OperationTypeRepository;
import com.pismo.assignment.repository.TransactionRepository;
import com.pismo.assignment.service.TransactionService;
import com.pismo.assignment.utils.DTOConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class TestTransactionService {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private OperationTypeRepository operationTypeRepository;

    @Spy
    private DTOConverter dtoConverter;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public  void init(){

        OperationType op1=new OperationType(1l, OperationTypeEnum.CASH_PURCHASE,"CASH PURCHASE");
        OperationType op2=new OperationType(2l,OperationTypeEnum.INSTALLMENT_PURCHASE,"INSTALLMENT PURCHASE");
        OperationType op3=new OperationType(3l,OperationTypeEnum.WITHDRAWAL,"WITHDRAWAL");
        OperationType op4=new OperationType(4l,OperationTypeEnum.PAYMENT,"PAYMENT");
        Mockito.when(operationTypeRepository.findAll()).thenReturn(List.of(op1,op2,op3,op4));
        transactionService.init();
    }


    @Test
    public void test_save_transaction_success(){
        OperationType op1=new OperationType(1l,OperationTypeEnum.CASH_PURCHASE,"CASH PURCHASE");
        Transaction transaction=new Transaction(1l,1l,
                op1,new BigDecimal("-100.23"), LocalDateTime.now());
        Mockito.when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        TransactionDto dto= transactionService.save(new TransactionDto(null,
                1l,op1.getOperationTypeId(),new BigDecimal("-100.23"), LocalDateTime.now()));
        Assertions.assertEquals(1l,dto.transactionId());
        Assertions.assertEquals(new BigDecimal("-100.23"),dto.amount());

    }

    @Test
    public void test_save_transaction_fail(){
        OperationType op1=new OperationType(1l,OperationTypeEnum.CASH_PURCHASE,"CASH PURCHASE");
        Transaction transaction=new Transaction(1l,1l,
                op1,new BigDecimal(100.00), LocalDateTime.now());
        Mockito.when(transactionRepository.save(any(Transaction.class))).thenReturn(null);
        InternalServerException ex=Assertions.assertThrows(InternalServerException.class,
                ()->transactionService.save(new TransactionDto(null,1l,op1.getOperationTypeId(),new BigDecimal(100.00), LocalDateTime.now())));
    }


    @Test
    public void test_find_all_success(){
        OperationType op1=new OperationType(1l,OperationTypeEnum.CASH_PURCHASE,"CASH_PURCHASE");
        Transaction transaction1=new Transaction(1l,1l,
                op1,new BigDecimal(-100.00), LocalDateTime.now());

        Transaction transaction2=new Transaction(1l,1l,
                op1,new BigDecimal(-20.00), LocalDateTime.now());

        Transaction transaction3=new Transaction(1l,1l,
                op1,new BigDecimal(-30.00), LocalDateTime.now());

        Pageable pageable= PageRequest.of(0,3, Sort.by("transactionId").descending());
        Page<Transaction> page=new PageImpl<>(List.of(transaction1,transaction2,transaction3));


        Mockito.when(transactionRepository.findAll(pageable)).thenReturn(page);

        List<TransactionDto> list=transactionService.findAll(0,3,Sort.by("transactionId").descending());

        Assertions.assertSame(3,list.size());





    }


}
