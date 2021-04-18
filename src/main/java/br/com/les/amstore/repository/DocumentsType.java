package br.com.les.amstore.repository;

import br.com.les.amstore.domain.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsType extends JpaRepository<DocumentType, Long> {
}
