package com.custacc.controller;

import com.custacc.service.ActionFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UiController {

    final ActionFacade actionFacade;

    public UiController(ActionFacade facadeservice) {
        this.actionFacade = facadeservice;
    }

    @GetMapping("/")
    public String listCustomers(Model model) {
        actionFacade.listCustomers(model);
        return "customers";
    }

    @RequestMapping(path = {"/customer/{customerID}/accounts"})
    public String listAccount(Model model, @PathVariable("customerID") Long customerID) {
        actionFacade.listAccounts(model, customerID);
        return "accounts";
    }

    @RequestMapping(path = {"/accounts/{accountID}/transaction"})
    public String listTransactions(Model model, @PathVariable("accountID") Long accountID) {
        actionFacade.getTransaction(model, accountID);
        return "transactions";
    }
}
