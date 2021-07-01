package br.com.les.amstore.repository;

import br.com.les.amstore.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Documents extends JpaRepository<Document, Long> {

}
