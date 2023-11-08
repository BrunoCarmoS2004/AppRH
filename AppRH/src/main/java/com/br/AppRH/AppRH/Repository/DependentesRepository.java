package com.br.AppRH.AppRH.Repository;

import org.springframework.data.repository.CrudRepository;
import com.br.AppRH.AppRH.Model.Dependentes;
import com.br.AppRH.AppRH.Model.Funcionario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface DependentesRepository extends CrudRepository<Dependentes, String>{
    
    Iterable<Dependentes>findByFuncionario(Funcionario funcionario);

    //Para metodo delete
    Dependentes findByCpf(String cpf);
    Dependentes findById(long id);

    //busca
    List<Dependentes> findByNome(String nome);

}
