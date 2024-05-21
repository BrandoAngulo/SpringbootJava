package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.InvoiceDTO;
import com.qualitysales.posinventory.model.Invoice;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDTO> getInvoices();
    InvoiceDTO getInvoice(Integer id);
    InvoiceDTO saveInvoice(Invoice invoice);
    InvoiceDTO updateInvoice(Integer id, Invoice invoice);
    void deleteInvoice(Integer id);
    List<InvoiceDTO> getInvoicesByCustomerId(Integer customerId);

}
