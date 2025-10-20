package br.com.juridico.totvs.fullstack.Backend.repository;

import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Optei pela pasta repository ppois ela permite interação diretamente com o bd no spring data JPA fazendo com que a camada service consiga utilizar o CRUD

//Indica que é uma interface componente de acesso a dados
@Repository
public interface PontoTuristicoRepository extends JpaRepository<PontoTuristico, Long> {
}
