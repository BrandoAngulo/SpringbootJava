package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.InvoiceDTO;
import com.qualitysales.posinventory.model.Invoice;
import com.qualitysales.posinventory.service.InvoiceService;
import com.qualitysales.posinventory.service.impl.InvoiceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posinventory/invoice")
public class InvoiceController {
    private final InvoiceServiceImpl invoiceService;

    public InvoiceController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/get-invoices")
    public List<InvoiceDTO> getInvoices() {

        return ResponseEntity.ok(invoiceService.getInvoices()).getBody();
    }

    @GetMapping("/get-invoice")
    public ResponseEntity<InvoiceDTO> getInvoice(@RequestParam("invoiceId") Integer invoiceId) {
        return ResponseEntity.ok(invoiceService.getInvoice(invoiceId));
    }

    @PostMapping("/save-invoice")
    public ResponseEntity<InvoiceDTO> saveInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.saveInvoice(invoice));
    }

    @PutMapping("/update-invoice")
    public ResponseEntity<InvoiceDTO> updateInvoice(@RequestParam Integer id, @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, invoice));
    }

    @PutMapping("/inactivar-invoice")
    public ResponseEntity<InvoiceDTO> anularInvoice(@RequestParam Integer id, @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.anularInvoice(id, invoice));
    }

    @GetMapping("/invoices-customer-id")
    public ResponseEntity<List<InvoiceDTO>> getCustomerInvoices(@RequestParam Integer customerId) {
        return ResponseEntity.ok(invoiceService.getInvoicesByCustomerId(customerId));
    }
}
