package br.gotasdetecnologia.crud.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.gotasdetecnologia.crud.entity.Aluno;
import br.gotasdetecnologia.crud.service.AlunoService;

@Controller
public class AlunoController {

	private AlunoService alunoService;

	public AlunoController(AlunoService alunoService) {
		super();
		this.alunoService = alunoService;
	}

	@GetMapping("/alunos")
	public String listarAlunos(Model model) {
		model.addAttribute("alunos", alunoService.getAllAlunos());
		return "alunos";
	}

	@GetMapping("/alunos/cadastro")
	public String cadastraAlunoViaForm(Model model) {
		Aluno aluno = new Aluno();
		model.addAttribute("aluno", aluno);
		return "cadastrarAluno";
	}

	@PostMapping("/alunos")
	public String salvarAluno(@ModelAttribute("aluno") Aluno aluno) {
		alunoService.salvarAluno(aluno);
		return "redirect:/alunos";
	}

	@GetMapping("/alunos/editar/{id}")
	public String editarAlunoViaForm(@PathVariable Long id, Model model) {
		model.addAttribute("aluno", alunoService.getAlunoById(id));
		return "atualizarAluno";
	}

	@PostMapping("alunos/{id}")
	public String atualizarAluno(@PathVariable long id, @ModelAttribute("aluno") Aluno aluno, Model model) {
		Aluno existenciaDeAluno = alunoService.getAlunoById(id);
		existenciaDeAluno.setId(id);
		existenciaDeAluno.setNome(aluno.getNome());
		existenciaDeAluno.setSobrenome(aluno.getSobrenome());
		existenciaDeAluno.setIdade(aluno.getIdade());
		existenciaDeAluno.setEmail(aluno.getEmail());
		alunoService.atualizarAluno(existenciaDeAluno);
		return "redirect:/alunos";
	}

	@GetMapping("/alunos/excluir/{id}")
	public String excluirAluno(@PathVariable Long id) {
		alunoService.excluirAlunoById(id);
		return "redirect:/alunos";
	}
}
