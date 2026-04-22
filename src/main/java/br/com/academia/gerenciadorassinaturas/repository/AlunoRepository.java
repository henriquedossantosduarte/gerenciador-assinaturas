package br.com.academia.gerenciadorassinaturas.repository;

import br.com.academia.gerenciadorassinaturas.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {


}
