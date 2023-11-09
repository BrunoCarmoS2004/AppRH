package com.br.AppRH.AppRH.Repository;
//Repository == Padrão de projeto entre o controller e o model

import java.util.List;

import org.springframework.data.jpa.repository.Query;
//faz o crud automaticamente
import org.springframework.data.repository.CrudRepository;
import com.br.AppRH.AppRH.Model.Candidato;
import com.br.AppRH.AppRH.Model.Vaga;

public interface CandidatoRepository extends CrudRepository<Candidato, String>{
    Iterable<Candidato>findByVaga(Vaga vaga);
    Candidato findByRg(String rg);
    //Excluir por id
    Candidato findById(long id);
    //List<Candidato>findByNomeCandidato(String nomeCandidato);
    //PARA A BUSCA
    @Query(value = "select u from Candidato u where u.nomeCandidato like %?1%")
    List<Candidato>findByNome(String nomeCandidato);
    
}
