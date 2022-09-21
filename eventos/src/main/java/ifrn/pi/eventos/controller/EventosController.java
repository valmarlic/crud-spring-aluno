package ifrn.pi.eventos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.models.Convidado;
import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.repositories.ConvidadoRepository;
import ifrn.pi.eventos.repositories.EventoRepository;

@Controller
//@RequestMapping("/eventos")
public class EventosController {
	@Autowired // Auto vinculado
	private EventoRepository er;
	@Autowired
	private ConvidadoRepository cr;

//	@GetMapping("/")
//	public String home() {
//		return "home";
//	}

	@GetMapping("/cadastro")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView("view/Cadastro");

		return mv;

	}

	@GetMapping("/contato")
	public ModelAndView cantato() {
		ModelAndView mv = new ModelAndView("view/Contato");

		return mv;

	}

	@GetMapping("/")
	// @PostMapping("/eventos")
	public ModelAndView home() {
		List<Evento> eventos = er.findAll();
		ModelAndView mv = new ModelAndView("eventos/lista");
		mv.addObject("eventos", eventos);
		System.out.println(mv);
		return mv;

	}
//	@GetMapping("/eventos")
//	// @PostMapping("/eventos")
//	public ModelAndView evento() {
//		List<Evento> eventos = er.findAll();
//		ModelAndView mv = new ModelAndView("eventos/lista");
//		mv.addObject("eventos", eventos);
//		System.out.println(mv);
//		return mv;
//
//	}

	@GetMapping("/listar")
	// @PostMapping("/eventos")
	public ModelAndView listar() {
		List<Evento> eventos = er.findAll();
		ModelAndView mv = new ModelAndView("eventos/lista");
		mv.addObject("eventos", eventos);
		System.out.println(mv);
		return mv;

	}

	@GetMapping("eventos/form")
	public String form(Evento evento) {
		return "eventos/formEvento";
	}

	@GetMapping("eventos/detalhes")
	public String eventoDetalhes(Convidado convidado) {
		return "eventos/detalhes";
	}

	// @RequestMapping(path = "/eventos", method = RequestMethod.POST)
	@PostMapping("/adicionar")
	public String salvar(Evento evento) {
		er.save(evento);
		return "redirect:/";
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		java.util.Optional<Evento> opt = er.findById(id);
		ModelAndView md = new ModelAndView();
		if (opt.isEmpty()) {
			md.setViewName("redirect:/{id}");
			return md;
		}
		md.setViewName("eventos/detalhes");
		Evento evento = opt.get();
		md.addObject("eventos", evento);
		List<Convidado> convidados = cr.findByEvento(evento);
		md.addObject("convidados", convidados);

		return md;
	}

	//Slvar um convidado
	@PostMapping("/{idEvento}") 
	public String salvarConvidado(@PathVariable Long idEvento, Convidado convidado) {
		System.out.println("Id do evento" + idEvento);
		System.out.println(convidado);

		java.util.Optional<Evento> opt = er.findById(idEvento);
		
		if (opt.isEmpty()) {
			
			return "redirect:/listar";
		}

		Evento evento = opt.get();
		convidado.setEvento(evento);

		cr.save(convidado);

		return "redirect:/{idEvento}";
		//return "/eventos/detalhes";
	}

	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarEvento(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Evento> opt = er.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redrect:/");
			return md;
		}

		Evento evento = opt.get();
		md.setViewName("eventos/formEvento");
		md.addObject("evento", evento);
		return md;
	}

	@PostMapping("/{id}/convidado")
	public ModelAndView selecionarConvidado(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Convidado> opt = cr.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redrect:/");
			return md;
		}

		Convidado convidado = opt.get();
		md.setViewName("redirect:/{id}");
		md.addObject("convidado", convidado);
		return md;
	}


	@GetMapping("/{idEvento}/convidados/{idConvidado}/selecionar")
	public ModelAndView selecionarConvidado(@PathVariable Long idEvento, @PathVariable Long idConvidado) {
		ModelAndView md = new ModelAndView();
		Optional<Evento> optEvento = er.findById(idEvento);
		Optional<Convidado> optConvidado = cr.findById(idConvidado);
		Evento evento = optEvento.get();
		Convidado convidado = optConvidado.get();
		if (optEvento.isEmpty() || optConvidado.isEmpty()) {
			md.setViewName("redirect:/{idEvento}");
			return md;
		}

		if (evento.getId() == convidado.getEvento().getId()) {
			md.addObject("convidado", convidado.getId());
			md.addObject("evento", evento);
			// cr.save(convidado);
			List<Convidado> convidados = cr.findByEvento(evento);
			// List<Evento> eventos = er.findByConvidado(convidado);

			md.addObject("convidados", convidados);
			// md.addObject("eventos", eventos);
			System.out.println("ID evento:==============" + evento.getId());
			System.out.println("Nome evento:==============" + evento.getNome());
			System.out.println("ID convidado:==============" + convidado.getId());
			System.out.println("Nome convidado:==============" + convidado.getNome());
			System.out.println("Id de evento em convidado:==============" + convidado.getEvento().getId());
			md.setViewName("redirect:/{idEvento}");
			//md.setViewName("redirect:/{idEvento}/convidados");
			return md;
		}

//		md.addObject("convidado", convidado);
//		md.addObject("evento", evento);
//		List<Convidado> convidados = cr.findByEvento(evento);
//		md.addObject("convidados", convidados);
		md.setViewName("redirect:/");
		return md;
	}

	@GetMapping("/{id}/remover")
	public String apagarEvento(@PathVariable Long id) {
		Optional<Evento> opt = er.findById(id);
		if (!opt.isEmpty()) {
			Evento evento = opt.get();
			List<Convidado> convidados = cr.findByEvento(evento);
			cr.deleteAll(convidados);
			er.delete(evento);
		}
		return "redirect:/";
	}

	@GetMapping("/{id}/convidados/{idConvidado}/removerconvidado")
	public ModelAndView apagarConvidado(@PathVariable Long id, @PathVariable Long idConvidado) {
		ModelAndView md = new ModelAndView();
		Optional<Evento> opt = er.findById(id);
		Optional<Convidado> optConvidado = cr.findById(idConvidado);

		if (!opt.isEmpty() || !optConvidado.isEmpty()) {
			Convidado convidado = optConvidado.get();
			Evento evento = opt.get();
			cr.delete(convidado);
			md.addObject("convidado", convidado);
			md.addObject("evento", evento);
			md.setViewName("redirect:/{id}");
			return md;
		}
		Convidado convidado = optConvidado.get();
		Evento evento = opt.get();
		// Convidado convidado = optConvidado.get();
		// Evento evento = optev.get();
		md.setViewName("redirect:/");
		md.addObject("convidado", convidado);
		md.addObject("evento", evento);
		// md.addObject("eventos", evento);
		return md;

	}

//	@RequestMapping("/{id}/removerconvidado")
//	@ResponseBody
//	public ModelAndView excluirConvidado(@PathVariable Long id) {
//		ModelAndView md = new ModelAndView("eventos/detalhes");
//		Optional<Convidado> opt = cr.findById(id);
//		if (!opt.isEmpty()) {
//			Convidado convidado = opt.get();
//			cr.delete(convidado);
//			md.setViewName("eventos/detalhes");
//		}
//		
//		return md;
//	}

}
