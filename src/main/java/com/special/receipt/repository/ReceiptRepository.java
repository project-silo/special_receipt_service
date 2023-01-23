package com.special.receipt.repository;

import com.special.receipt.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    Optional<List<Receipt>> findByFromNameAndToNameOrderByCreatedAtDesc(String fromName, String toName);
}
