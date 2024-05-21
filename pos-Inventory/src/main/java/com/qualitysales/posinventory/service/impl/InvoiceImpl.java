package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.Controllers.DTO.InvoiceDTO;
import com.qualitysales.posinventory.mapper.InvoiceMapper;
import com.qualitysales.posinventory.model.Invoice;
import com.qualitysales.posinventory.repository.InvoiceRepository;
import com.qualitysales.posinventory.service.InvoiceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InvoiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public List<InvoiceDTO> getInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceDTO> invoiceDTOList = invoiceMapper.toInvoiceList(invoices);
        try {
            log.info("getInvoices ok: {}", invoiceDTOList.toString());
            return invoiceDTOList;
        } catch (RuntimeException e) {
            log.error("getInvoices error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public InvoiceDTO getInvoice(Integer id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invoice not found"));
        InvoiceDTO invoiceDTO = invoiceMapper.toInvoice(invoice);
        try {
            log.info("getInvoice ok: {}", invoiceDTO.toString());
            return invoiceDTO;
        } catch (Exception e) {
            log.error("getInvoice error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public InvoiceDTO saveInvoice(Invoice invoice) {


        try {
            InvoiceDTO invoiceDTO = invoiceMapper.toInvoice(invoice);
            invoiceRepository.save(invoice);
            log.info("saveInvoice ok: {}", invoiceDTO.toString());
            return invoiceDTO;
        } catch (Exception e) {
            log.error("saveInvoice error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public InvoiceDTO updateInvoice(Integer id, Invoice invoice) {
        Invoice invoiceId = invoiceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invoice not found"));
        InvoiceDTO invoiceDTO = invoiceMapper.toInvoice(invoiceId);
        try {
            if (invoiceId.getId().equals(id)){
                log.info("updateInvoice ok: {}", invoiceDTO.toString());
                invoiceDTO.setClient(invoice.getClient());
                invoiceDTO.setDate(invoice.getDate());
                invoiceDTO.setTotal(invoice.getTotal());
                invoiceDTO.setItemInvoice(invoice.getItemInvoice());
                invoiceRepository.save(invoice);
            }else {
                log.info("updateInvoice error: {}", invoiceDTO.toString());
                throw new IllegalArgumentException("Invoice id not match");
            }
            return invoiceDTO;
        } catch (Exception e) {
            log.error("updateInvoice error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void deleteInvoice(Integer id) {
            invoiceRepository.deleteById(id);

        try {
            log.info("deleteInvoice ok: {}", id);
            invoiceRepository.deleteById(id);
        } catch (Exception e) {
            log.error("deleteInvoice error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public List<InvoiceDTO> getInvoicesByCustomerId(Integer customerId) {
        return List.of();
    }
}
