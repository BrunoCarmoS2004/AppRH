package com.br.AppRH.AppRH.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.br.AppRH.AppRH.Model.*;
import com.br.AppRH.AppRH.Repository.*;

@Controller
public class BuscaController {
    @Autowired
    private CandidatoRepository cr;
    @Autowired
    private VagaRepository vr;
    @Autowired
    private DependentesRepository dr;
    @Autowired
    private FuncionarioRepository fr;

    //ABRIR O INDEX
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView abrirIndex(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    //BUSCA
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome){
        ModelAndView mv = new ModelAndView("index");
        String mensagem = "Resultado da busca por " + buscar;

        if(nome.equals("nomefuncionario")){
            mv.addObject("funcionarios", fr.findByNome(buscar));

        }else if(nome.equals("nomedependente")){
            mv.addObject("dependentes", dr.findByNome(buscar));

        }else if(nome.equals("nomecandidato")){
            mv.addObject("candidatos", cr.findByNome(buscar));

        }else if(nome.equals("nomevaga")){
            mv.addObject("vagas", vr.findByNome(buscar));

        }else{
            mv.addObject("funcionarios", fr.findByNome(buscar));
            mv.addObject("dependentes", dr.findByNome(buscar));
            mv.addObject("candidatos", cr.findByNome(buscar));
            mv.addObject("vagas", vr.findByNome(buscar));
        }
        //ACHO QUE DA PARA CRIAR UM REPOSITORIO COM TODAS AS MENSAGENS DE AVISO
        mv.addObject("mensagem", mensagem);
        return mv;
    }
}