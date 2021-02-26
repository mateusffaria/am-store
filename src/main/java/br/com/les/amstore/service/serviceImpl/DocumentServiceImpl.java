package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Document;
import br.com.les.amstore.repository.Documents;
import br.com.les.amstore.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements IDocumentService {
    @Autowired
    Documents documents;

    @Override
    public List<Document> findAll() {
        return documents.findAll();
    }

    @Override
    public Document findById(Long id) {
        return documents.findById(id).get();
    }

    @Override
    public Document saveAndFlush(Document document) {
        return documents.saveAndFlush(document);
    }
}
