package com.edu.crud.aluno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.edu.crud.aluno.entity.Aluno;
import com.edu.crud.aluno.service.AlunoService;

@Controller
public class AlunoController {
	private AlunoService alunoService;

	public AlunoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlunoController(AlunoService alunoService) {
		super();
		this.alunoService = alunoService;
	}
	
	@GetMapping("/alunos")
	public String listarAlunos(Model model) {
	 model.addAttribute("alunos", alunoService.getAllAlunos());
	 return "aluno.html";
	}
	
	@GetMapping("/alunos/cadastro")
	public String cadastrarAlunoViaForm(Model model) {
		Aluno aluno = new Aluno();
		model.addAttribute("aluno",aluno);
		return "cadastrarAluno.html";
	}
	
	@PostMapping("/alunos")
	
	public String salvarAluno(@ModelAttribute("aluno") Aluno aluno ) {
		alunoService.salvarAluno(aluno);
		return "redirect:/alunos.html";
	}
	
	@GetMapping("/alunos/editar/{id}")
	public String editarAlunoViaForm(@PathVariable Long id, Model model) {
		model.addAttribute("aluno", alunoService.getAlunoById(id));
		return "atualizaAluno.html";
	}
	
	@PostMapping("alunos/{id}")
	public String atuliazarAluno(@PathVariable long id, @ModelAttribute("aluno") Aluno aluno, Model model) {
		Aluno existenciaDeAluo = alunoService.getAlunoById(id);
		existenciaDeAluo.setId(id);
		existenciaDeAluo.setNome(aluno.getNome());
		existenciaDeAluo.setSobreNome(aluno.getSobreNome());
		existenciaDeAluo.setIdade(aluno.getIdade());
		existenciaDeAluo.setEmail(aluno.getEmail());
		
		alunoService.atualizarAluno(existenciaDeAluo);
		return "redirect:/alunos";
	}
	
	@GetMapping("/alunos/excluir/{id}")
	public String excluirAlunoViaForm(@PathVariable Long id) {
		alunoService.excluirAlunoById(id);
		return "redirect:/alunos.html";
	}
	
}
