package com.prm.bankservice.controller;

import com.prm.bankservice.domain.CurrentCustomer;
import com.prm.bankservice.domain.CustomerInfo;
import com.prm.bankservice.repository.CurrentCustomerRepository;
import com.prm.bankservice.repository.CustomerInfoRepository;
import com.prm.bankservice.domain.Transactions;
import com.prm.bankservice.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.Data;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/bank/service")
@Data
public class bank {
    private CustomerInfo customerInfo;
    private Transactions transactions;
    private CurrentCustomer currentCustomer;
    private CustomerInfoRepository customerInfoRepository;
    private CurrentCustomerRepository currentCustomerRepository;
    private TransactionsRepository transactionsRepository;


    @Autowired
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    @Autowired
    public void setCurrentUser(CurrentCustomer currentCustomer) { this.currentCustomer=currentCustomer;}

    @Autowired
    public void setTransaction(Transactions transactions) {
        this.transactions = transactions;
    }

    @Autowired
    public void setCustomerInfoRepository(CustomerInfoRepository customerInfoRepository) {
        this.customerInfoRepository = customerInfoRepository;
    }
    @Autowired
    public void  setCurrentCustomerRepository(CurrentCustomerRepository currentCustomerRepository){
        this.currentCustomerRepository=currentCustomerRepository;
    }

    @Autowired
    public void setTransactionsRepository(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @GetMapping("/check/{id}")
    public String checkCustomerCardID(@PathVariable("id") int id, HttpSession httpSession) {
        CustomerInfo cust=customerInfoRepository.findCustomerInfoByNative(id);
        if  (cust !=null) {
            CurrentCustomer currentCustomer=new CurrentCustomer();
            currentCustomer.setCurrUserCrdId(cust.getCustCrdId());
            currentCustomer.setCurrUserId(cust.getCustSeq());
            currentCustomer.setCurrUserSecCode(cust.getCustSecCode());
            currentCustomer.setCurrUserSecPinCode(cust.getCustSecPinCode());
            currentCustomerRepository.save(currentCustomer);
           return "OK"+ cust.getCustCrdId()+"   ";
        }
           return "NOK";
    }


   @GetMapping("/checkPin/{id}")
    public String checkPinPassword(@PathVariable("id") String id, HttpSession httpSession) {
        currentCustomer = currentCustomerRepository.getCurrentCustomer();
        if  (currentCustomer.getCurrUserSecPinCode() == id) {
          return "OK";
        }
          return "NOK";

    }

    @GetMapping("/checkSec/{id}")
    public String checkPassword(@PathVariable("id") String id, HttpSession httpSession) {
        currentCustomer = currentCustomerRepository.getCurrentCustomer();
        if  (currentCustomer.getCurrUserSecPinCode() == id) {
            return "OK";
        }
        return "NOK";
    }

    @GetMapping("/balance/{id}")
    public String getCustomerBalance(@PathVariable("id") int id, HttpSession httpSession) {
        CustomerInfo cust=customerInfoRepository.findCustomerInfoByNative(id);
           if  (customerInfoRepository.findCustomerInfoByNative(id) !=null) {
               return cust.getCustAmount().toString();
           }
               return "NOK";
           }

     @PostMapping("/deposit/{amount}")
    public String setDepositTran(HttpSession httpSession,@PathVariable("amount") long amount){
        CurrentCustomer  currentCust = currentCustomerRepository.getCurrentCustomer();
       customerInfo = customerInfoRepository.findCustomerInfoById(currentCust.getCurrUserId());

       if (currentCust !=null ){

          transactions= new Transactions();
          amount+= customerInfo.getCustAmount();
          transactions.setTranAmnt(amount);
          transactions.setTranCust(customerInfo.getCustSeq());
          transactions.setTranDate(new Date());
          transactions.setTranOp("D");
          transactionsRepository.save(transactions);
           customerInfo.setCustAmount(amount);
          customerInfoRepository.save(customerInfo);
          return "ok";

       }
          return  "NOK";
     }
    @PostMapping("/withdraw/{amount}")
    public String setWithdrawTran(HttpSession httpSession,@PathVariable("amount") long amount){
      //  CustomerInfo cust= (CustomerInfo) httpSession.getAttribute("ActiveCustomer");
        CurrentCustomer  currentCust = currentCustomerRepository.getCurrentCustomer();
        customerInfo = customerInfoRepository.findCustomerInfoById(currentCust.getCurrUserId());
        if (customerInfo !=null && customerInfo.getCustAmount()>=amount &&
                customerInfoRepository.findById(customerInfo.getCustSeq()).isPresent()){
            transactions= new Transactions();
            amount-= customerInfo.getCustAmount();
            transactions.setTranAmnt(amount);
            transactions.setTranCust(customerInfo.getCustSeq());
            transactions.setTranDate(new Date());
            transactions.setTranOp("W");
            transactionsRepository.save(transactions);
            customerInfo.setCustAmount(amount);
            customerInfoRepository.save(customerInfo);
            return "OK";

        }
            return "NOK";
    }

 @GetMapping("/exit")
 public String ExitService(){
        currentCustomerRepository.deleteAll();
        return "ok";

 }

        };







