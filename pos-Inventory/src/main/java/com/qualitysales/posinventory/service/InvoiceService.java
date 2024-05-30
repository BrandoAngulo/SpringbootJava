package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.InvoiceDTO;
import com.qualitysales.posinventory.model.Invoice;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDTO> getInvoices();
    InvoiceDTO getInvoice(Integer id);
    InvoiceDTO saveInvoice(Invoice invoice);
    Invoice updateInvoice(Integer id, InvoiceDTO invoiceDTO);
    InvoiceDTO anularInvoice(Integer id, Invoice invoice);
    List<InvoiceDTO> getInvoicesByCustomerId(Integer customerId);

}
