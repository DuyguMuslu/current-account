package com.custacc.service;

import com.custacc.dto.AccountDto;
import com.custacc.dto.TransactionDto;
import com.custacc.model.Transaction;
import com.custacc.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TransactionService {

    TransactionRepository repository;
    ModelMapper modelmapper;

    @Autowired
    public TransactionService(TransactionRepository repository,
                              ModelMapper modelmapper) {
        this.repository = repository;
        this.modelmapper = modelmapper;
    }


    public void saveTransaction(TransactionDto transactionDto) {
        repository.save(modelmapper.map(transactionDto, Transaction.class));
    }

    public TransactionDto getAccountTransaction(AccountDto accountDto) {
        TransactionDto transactionDto= new TransactionDto();
        Optional<TransactionDto> transactionDtoOptional = Optional.ofNullable(repository.findByAccountID(accountDto.getAccountID())).map(i -> modelmapper.map(i, TransactionDto.class));
        if(transactionDtoOptional.isPresent()) transactionDto=transactionDtoOptional.get();
        return transactionDto;
    }

}
