package com.br.AppRH.AppRH.Controllers;
//Não explicou, copia tudo
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.br.AppRH.AppRH.Model.Candidato;
import com.br.AppRH.AppRH.Model.Vaga;
import com.br.AppRH.AppRH.Repository.CandidatoRepository;
import com.br.AppRH.AppRH.Repository.VagaRepository;
//ModelAndView SERVE PARA ADICIONAR COISAS NA VIEW
//Mostrar para o código que essa classe é um controller
@Controller
public class VagaController {
    //Ele le automaticamente o repositório
    @Autowired
    private VagaRepository vr;
    @Autowired
    private CandidatoRepository cr;
    //criar as vagas
    //Endereço no site para entrar nesse METODO de cadastrar vaga
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
    public String form(){ 
        //RETORNAR UMA VIEW 
        return "vaga/formVaga";
    }
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            //Para mostrar uma mensagem
            attributes.addAttribute("mensagem_erro", "Verifique os campos...");
            //caso coloque algo errado, voltar para a mesma pagina
            return "redirect:/cadastrarVaga";
        }
        vr.save(vaga);
        attributes.addAttribute("mensagem", "Vaga cadastrada com sucesso");
        return "redirect:/cadastrarVaga";
    }
    //LISTAR VAGAS
    @RequestMapping(value = "/vagas")
    public ModelAndView listaVagas(){
        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga>vaga = vr.findAll();
        //mandando essa vaga para a view
        mv.addObject("vagas", vaga);
        return mv;
    }
    //Buscando a vaga por código e seus integrantes
    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalherVaga(@PathVariable("codigo") long codigo){
        Vaga vaga = vr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("vaga/detalhesVaga");
        mv.addObject("vaga", vaga);
        Iterable<Candidato>candidatos = cr.findByVaga(vaga);
        mv.addObject(candidatos);
        return mv;
    }
    //Deletar Vaga
    @RequestMapping("/deletarVaga")
    public String deletarVaga(long codigo){
        Vaga vaga = vr.findByCodigo(codigo);
        vr.delete(vaga);
        return "redirect:/vagas";
    }
    //detalhar e adicionar candidato
    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
    public String detalhesVagaPost(@PathVariable("codigo") long codigo, @Valid Candidato candidato, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/{codigo}";
        }
        if(cr.findByRg(candidato.getRg()) != null){
            attributes.addFlashAttribute("mensagem_erro", "RG duplicado!");
            return "redirect:/{codigo}";
        }
        Vaga vaga = vr.findByCodigo(codigo);
        candidato.setVaga(vaga);
        cr.save(candidato);
        attributes.addFlashAttribute("mensagem", "Candidato adicionado com sucesso");
        return "redirect:/{codigo}";
    }
    //DELETAR CANDIDATO da vaga pelo RG
    @RequestMapping("/deletarCandidato")
    public String deletarCandidato(String rg){
        Candidato candidato = cr.findByRg(rg);
        Vaga vaga = candidato.getVaga();
         //fazendo o int virar String
        String codigo = "" + vaga.getCodigo();
        cr.delete(candidato);
        return "redirect:/"+codigo;
    }
    // Metodo para entrar na pagina de atualizar as vagas
    //Pagina de formulario de edição de vaga
    @RequestMapping(value = "/editar-vaga", method = RequestMethod.GET)
	public ModelAndView editarVaga(long codigo) {
		Vaga vaga = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("vaga/update-vaga");
		mv.addObject("vaga", vaga);
		return mv;
	}

	// UPDATE vaga
	@RequestMapping(value = "/editar-vaga", method = RequestMethod.POST)
	public String updateVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {
		vr.save(vaga);
		attributes.addFlashAttribute("success", "Vaga alterada com sucesso!");

		long codigoLong = vaga.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
}
