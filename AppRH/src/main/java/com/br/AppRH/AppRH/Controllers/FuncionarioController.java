package com.br.AppRH.AppRH.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.br.AppRH.AppRH.Model.Funcionario;
import com.br.AppRH.AppRH.Repository.DependentesRepository;
import com.br.AppRH.AppRH.Repository.FuncionarioRepository;
import jakarta.validation.Valid;
import com.br.AppRH.AppRH.Model.Dependentes;

@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository fr;
    @Autowired
    private DependentesRepository dr;

    //CHAMAR PAGINA DE CADASTRAR FUNCIONARIOS
    @RequestMapping(value ="/cadastrarFuncionario", method = RequestMethod.GET)
    public String form(){
        return "funcionario/formFuncionario";
    }
    //CADASTRAR FUNCIONARIOS
    @RequestMapping(value ="/cadastrarFuncionario", method = RequestMethod.POST)
    public String form(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem_erro", "Verifique os campos");
            return "redirect:/cadastrarFuncionario";
        }
        if(fr.findByEmail(funcionario.getEmail()) != null){
            attributes.addFlashAttribute("mensagem_erro", "Email ja usado!");
           return "redirect:/cadastrarFuncionario";
        }
        fr.save(funcionario);
        attributes.addFlashAttribute("mensagem", "Funcionario Cadastrado com sucesso!");
        return "redirect:/cadastrarFuncionario";
    }

    //Listar Funcionario
    @RequestMapping("/funcionarios")
    public ModelAndView listarFuncionarios(){
        ModelAndView mv = new ModelAndView("funcionario/listaFuncionario");
        //iterable = buscar todos
        Iterable<Funcionario> funcionario = fr.findAll();
        mv.addObject("funcionarios", funcionario);
        return mv;
    }
    
    //Lista dependentes baseado no funcionario
    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.GET)
    public ModelAndView listarDependentes(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("funcionario/dependentes");
        Funcionario funcionario = fr.findById(id);
        mv.addObject("funcionario", funcionario);
        //criando uma lista de dependentes baseado no funcionario
        Iterable<Dependentes> dependentes = dr.findByFuncionario(funcionario);
        mv.addObject("dependentes", dependentes);
        return mv;
    }

    //O ID É DO FUNCIONARIO OK?
    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.POST)
    public String dependentesPost(@PathVariable("id") long id, Dependentes dependentes, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
           attributes.addFlashAttribute("mensagem_erro", "Verifique os campos!");
           return "redirect:/dependentes/{id}";
        }
        if(dr.findByCpf(dependentes.getCpf()) != null){
            attributes.addFlashAttribute("mensagem_erro", "CPF ja usado!");
           return "redirect:/dependentes/{id}";
        }
        //buscando o id do funcionario clicado
        Funcionario funcionario = fr.findById(id);
        //setando o funcionario como dono do dependente
        dependentes.setFuncionario(funcionario);
        dr.save(dependentes);
        attributes.addFlashAttribute("mensagem", "Dependente Cadastrado com sucesso!");
        return "redirect:/dependentes/{id}";
    }

    @RequestMapping("/deletarFuncionario")
    public String deletarFuncionario(long id){
        Funcionario funcionario = fr.findById(id);
        fr.delete(funcionario);
        return "redirect:/funcionarios";
    }

    @RequestMapping(value = "/editarFuncionario", method = RequestMethod.GET)
    public ModelAndView paginaEditarFuncionario(long id){
        ModelAndView mv = new ModelAndView("funcionario/updateFuncionario");
        Funcionario funcionario = fr.findById(id);
        mv.addObject("funcionario", funcionario);
        return mv;
    }

    @RequestMapping(value = "/editarFuncionario", method = RequestMethod.POST)
    public String editarFuncionario(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes){
       fr.save(funcionario);
       attributes.addFlashAttribute("successs", "Funcionário alterado!");
       //PARA RECUPERAR O ID DO FUNCIONARIO ALTERADO
       long idlong = funcionario.getId();
       //para transfomar em string
       String id =""+idlong;
       return "redirect:/dependentes/"+id;
    }

    @RequestMapping("/deletarDependete")
    public String deletarDependente(String cpf){
        Dependentes dependentes = dr.findByCpf(cpf);
        //para saber qual funcionario esse dependente pertente
        Funcionario funcionario = dependentes.getFuncionario();
        String codigo = "" + funcionario.getId();
        dr.delete(dependentes);
        return "redirect:/dependentes/"+codigo;
    }
}
