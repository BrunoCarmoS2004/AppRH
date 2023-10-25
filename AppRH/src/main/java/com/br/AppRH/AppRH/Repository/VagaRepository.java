package com.br.AppRH.AppRH.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
//faz o crud automaticamente
import org.springframework.data.repository.CrudRepository;
import com.br.AppRH.AppRH.Model.Vaga;


public interface VagaRepository extends CrudRepository<Vaga, String>{
    //Achar o código da vaga
    //variavel do codigo do model vaga
    Vaga findByCodigo(long codigo);
    List<Vaga> findByNome(String nome); 
}
