package br.com.les.amstore.service;

import br.com.les.amstore.domain.Document;

import java.util.List;

public interface IDocumentService {
    public List<Document> findAll();
    public Document findById(Long id);
    public Document saveAndFlush(Document document);
    public List<Document> saveAll(Iterable<Document> docs);
}
