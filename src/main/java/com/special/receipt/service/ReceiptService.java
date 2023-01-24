package com.special.receipt.service;

import com.special.receipt.domain.LifeQuotes;
import com.special.receipt.domain.Receipt;
import com.special.receipt.domain.ReceiptMenu;
import com.special.receipt.dto.LifeQuotesDto;
import com.special.receipt.dto.ReceiptDto;
import com.special.receipt.model.ReceiptForm;
import com.special.receipt.repository.LifeQuotesRepository;
import com.special.receipt.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final LifeQuotesRepository lifeQuotesRepository;

    @Transactional
    public void order(ReceiptForm receiptForm) {
        Receipt receipt = Receipt.of(receiptForm);
        receiptRepository.save(receipt);
    }

    public List<ReceiptDto> getReceipt(String fromName, String toName) {
        Optional<List<Receipt>> optionalReceipt = receiptRepository.findByFromNameAndToNameOrderByCreatedAtDesc(fromName, toName);
        if (optionalReceipt.isEmpty()) {
            return null;
        }

        Receipt receipt = optionalReceipt.get().get(0);
        List<ReceiptDto> result = new ArrayList<>();
        DecimalFormat format = new DecimalFormat("###,###");

        Long totalMoney = 0L;
        for (ReceiptMenu receiptMenu : receipt.getReceiptMenu()) {
            result.add(ReceiptDto.from(receiptMenu.getMenu(), format.format(receiptMenu.getMoney()) + "원"));
            totalMoney += receiptMenu.getMoney();
        }

        for (ReceiptDto receiptDto : result) {
            receiptDto.setTotalMoney(format.format(totalMoney) + "원");
        }

        return result;
    }

    public LifeQuotesDto getLifeQuote() {
        long num = (long)(Math.random() * 50 + 1);
        Optional<LifeQuotes> lifeQuotes = lifeQuotesRepository.findById(num);
        if (lifeQuotes.isEmpty()) {
            return null;
        }
        LifeQuotes lifeQuote = lifeQuotes.get();

        return LifeQuotesDto.builder()
                .lifeQuote(lifeQuote.getLifeQuote())
                .name("-" + lifeQuote.getName())
                .build();
    }
}
