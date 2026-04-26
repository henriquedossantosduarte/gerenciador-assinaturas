package br.com.academia.gerenciadorassinaturas.controller;


import br.com.academia.gerenciadorassinaturas.model.Aluno;
import br.com.academia.gerenciadorassinaturas.model.Pagamento;
import br.com.academia.gerenciadorassinaturas.repository.AlunoRepository;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;


@Controller
public class AlunoController {


    @Autowired
    private AlunoRepository repository;

    @GetMapping("/")
    public String index0(Model model ){
        List<Aluno> listaAlunos= repository.findAll();

        Long hoje = listaAlunos.stream()
                        .filter(Aluno::isVenceHoje)
                                .count();

        Long atrasados = listaAlunos.stream()
                .filter(Aluno::isAtrasado)
                .count();

        model.addAttribute("alunos", listaAlunos);
        model.addAttribute("totalVenceHoje", hoje);
        model.addAttribute("totalAtrasados", atrasados);
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
    @PostMapping("/pagar/{id}")
    public String registrarPagamento(@PathVariable Long id) {
        repository.findById(id).ifPresent(aluno -> {

            Pagamento p = new Pagamento();
            p.setDataConfirmacao(LocalDate.now());

            String ref = LocalDate.now().getMonth().toString() + "/" + LocalDate.now().getYear();
            p.setMesReferencia(ref);

            // ← ESSA LINHA ESTAVA FALTANDO
            p.setAluno(aluno);
            aluno.getHistoricoPagamentos().add(p);

            aluno.setDataUltimoPagamento(LocalDate.now());
            repository.save(aluno);
        });

        return "redirect:/";
    }

    @GetMapping("/aluno/{id}/historico")
    public String verHistorico(@PathVariable Long id, Model model) {

        Aluno aluno = repository.findById(id).orElse(null);

        if (aluno == null) {
            return "redirect:/";
        }

        model.addAttribute("aluno", aluno);
        model.addAttribute("historico", aluno.getHistoricoPagamentos());

        return "historico"; // vai procurar o arquivo historico.html
    }
}
