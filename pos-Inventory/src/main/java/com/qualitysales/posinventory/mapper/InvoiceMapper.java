package com.qualitysales.posinventory.mapper;

import com.qualitysales.posinventory.Controllers.DTO.InvoiceDTO;
import com.qualitysales.posinventory.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InvoiceMapper {

    InvoiceMapper MAPPER = Mappers.getMapper(InvoiceMapper.class);

    InvoiceDTO toInvoice(Invoice invoice);
    Invoice toInvoiceDTO(InvoiceDTO invoiceDTO);

    List<InvoiceDTO> toInvoiceList(List<Invoice> invoices);
    List<Invoice> toInvoiceDTOList(List<Invoice> invoices);
}