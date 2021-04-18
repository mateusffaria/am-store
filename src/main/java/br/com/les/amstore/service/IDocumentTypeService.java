package br.com.les.amstore.service;

import br.com.les.amstore.domain.DocumentType;

import java.util.List;

public interface IDocumentTypeService {
    public List<DocumentType> findAll();
    public DocumentType findById(Long id);
    public DocumentType saveAndFlush(DocumentType documentType);
}
