package br.com.academia.gerenciadorassinaturas.controller;


import br.com.academia.gerenciadorassinaturas.model.Aluno;
import br.com.academia.gerenciadorassinaturas.repository.AlunoRepository;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AlunoController {


    @Autowired
    private AlunoRepository repository;

    @GetMapping("/")
    public String index0(Model model ){
        var alunos = repository.findAll();
        model.addAttribute("alunos", alunos);
        return "index";
    }

    @GetMapping("/novo")
        public String formulario(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Aluno aluno) {
        repository.save(aluno);


        return  "redirect:/";
    }
}
