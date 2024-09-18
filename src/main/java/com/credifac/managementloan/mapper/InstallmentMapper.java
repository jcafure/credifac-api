package com.credifac.managementloan.mapper;

import com.credifac.managementloan.domain.PaymentStatus;
import com.credifac.managementloan.dto.InstallmentDTO;
import com.credifac.managementloan.entity.Installment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class InstallmentMapper {

    public List<InstallmentDTO> toDto(List<Installment> installments){
        return installments.stream().map(this::convertToInstallmentDTO).toList();
    }

     private InstallmentDTO convertToInstallmentDTO(Installment installment) {
        var dto = new InstallmentDTO();
        dto.installmentNumber = installment.getInstallmentNumber();
        dto.installmentAmount = installment.getInstallmentAmount();
        dto.paymentStatus = PaymentStatus.OPEN.name();
        dto.dueDate = LocalDate.now();
        return dto;
    }
}
