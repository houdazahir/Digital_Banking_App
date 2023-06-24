package org.sid.ebankingbackend.services;

import org.sid.ebankingbackend.entities.BankAccount;
import org.sid.ebankingbackend.entities.CurrentAccount;
import org.sid.ebankingbackend.entities.SavingAccount;
import org.sid.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService{
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount=
                bankAccountRepository.findById("0cf26781-7d31-4f02-9db5-586e6d0c42fd").orElse(null);
        if(bankAccount!=null){


            System.out.println("*******************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getCustomer().getClass().getSimpleName());
            if(bankAccount instanceof CurrentAccount){
                System.out.println ("Over Draft=>" +((CurrentAccount)bankAccount).getOverDraft());
            }
            else if(bankAccount instanceof SavingAccount){
                System.out.println ("Rate =>"+ ( (SavingAccount)bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(op->{
                System.out.println("=======================");
                System.out.println(op.getType()+"\t"+op.getOperationDate()+"\t"+op.getAmount());
            });
        }
    }
}
