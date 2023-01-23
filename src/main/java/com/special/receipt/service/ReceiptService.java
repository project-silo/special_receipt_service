package com.special.receipt.service;

import com.special.receipt.domain.Receipt;
import com.special.receipt.model.ReceiptForm;
import com.special.receipt.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Transactional
    public void order(ReceiptForm receiptForm) {
        Receipt receipt = Receipt.of(receiptForm);
        receiptRepository.save(receipt);
    }
}
