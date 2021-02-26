package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.DocumentType;
import br.com.les.amstore.repository.DocumentsType;
import br.com.les.amstore.service.IDocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements IDocumentTypeService {
    @Autowired
    DocumentsType documentsType;


    @Override
    public List<DocumentType> findAll() {
        return documentsType.findAll();
    }

    @Override
    public DocumentType findById(Long id) {
        return documentsType.findById(id).get();
    }

    @Override
    public DocumentType saveAndFlush(DocumentType documentType) {
        return documentsType.saveAndFlush(documentType);
    }
}
