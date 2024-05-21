package com.qualitysales.posinventory.Controllers.DTO;

import com.qualitysales.posinventory.model.Client;
import com.qualitysales.posinventory.model.ItemInvoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InvoiceDTO {
    private Integer id;
    private String invoiceCode;
    private Client client;
    private String date;
    private BigDecimal total;
    private ItemInvoice itemInvoice;
}
