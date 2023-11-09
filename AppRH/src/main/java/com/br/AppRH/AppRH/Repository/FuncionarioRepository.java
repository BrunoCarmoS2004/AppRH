package com.br.AppRH.AppRH.Repository;

import org.springframework.data.repository.CrudRepository;
import com.br.AppRH.AppRH.Model.Funcionario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface FuncionarioRepository extends CrudRepository<Funcionario,String>{

    Funcionario findById(long id);
    Funcionario findByEmail(String email);
    //PARA A BUSCA
    @Query(value = "select u from Funcionario u where u.nome like %?1%")
    List<Funcionario>findByNome(String nome);
}
