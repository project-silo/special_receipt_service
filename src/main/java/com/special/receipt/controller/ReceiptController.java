package com.special.receipt.controller;

import com.special.receipt.model.ReceiptForm;
import com.special.receipt.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/receipt")
@RequiredArgsConstructor
@Controller
public class ReceiptController {

    private final ReceiptService receiptService;

    @GetMapping("/order")
    public String order() {
        return "index";
    }

    @PostMapping("/order")
    public String order(ReceiptForm receiptForm) {
        receiptService.order(receiptForm);
        return "result";
    }
    @GetMapping("/result")
    public String result() {
        return "result";
    }
}
