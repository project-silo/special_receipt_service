package com.special.receipt.controller;

import com.special.receipt.dto.ReceiptDto;
import com.special.receipt.model.ReceiptForm;
import com.special.receipt.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String order(@RequestParam("fromName") String fromName,
                        @RequestParam("toName") String toName,
                        @RequestParam("menu[]") List<String> menu,
                        @RequestParam("money[]") List<String> money) {
        if (menu.size() == 0 || money.size() == 0) {
            return "error";
        }

        ReceiptForm receiptForm = ReceiptForm.builder()
                .fromName(fromName)
                .toName(toName)
                .menu(menu)
                .money(money)
                .build();
        receiptService.order(receiptForm);

        return "result";
    }
    @GetMapping("/result/{fromName}&{toName}")
    public String result(Model model,
                         @PathVariable String fromName,
                         @PathVariable String toName) {
        List<ReceiptDto> receipt = receiptService.getReceipt(fromName, toName);

        if (receipt == null) {
            return "error";
        }
        model.addAttribute("fromName", fromName);
        model.addAttribute("toName", toName);
        model.addAttribute("receiptList", receipt);
        return "result";
    }
}
