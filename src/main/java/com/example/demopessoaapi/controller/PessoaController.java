package com.example.demopessoaapi.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.example.demopessoaapi.exception.DataNascimentoParceiroNaoPreenchidoException;
import com.example.demopessoaapi.exception.NomeParceiroNaoPreenchidoException;
import com.example.demopessoaapi.model.Pessoa;
import com.example.demopessoaapi.model.PessoaForm;
import com.example.demopessoaapi.service.EstadoCivilService;
import com.example.demopessoaapi.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PessoaController {

    private static final int PAGINA_INICIAL = 1;
    private static final int PAGINA_TAMANHO = 5;

    @Autowired
    private PessoaService servicePessoa;
    @Autowired
    private EstadoCivilService serviceEstadoCivil;
    
    @RequestMapping(value = "/pessoa/listar", method = RequestMethod.GET)
    public String listar(Model model, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        int paginaAtual = page.orElse(PAGINA_INICIAL);
        int qtdePorPagina = size.orElse(PAGINA_TAMANHO);
        
        Page<Pessoa> pagePessoa = servicePessoa.findAll(PageRequest.of(paginaAtual - 1, qtdePorPagina));

        model.addAttribute("pessoas", pagePessoa);

        int totalPaginas = pagePessoa.getTotalPages();
        if (totalPaginas > 0) {
            List<Integer> numeroDePaginas = IntStream.rangeClosed(1, totalPaginas).boxed().collect(Collectors.toList());
            model.addAttribute("paginas", numeroDePaginas);
        }

        return "lista-pessoas";
    }
    
    @RequestMapping(value = "/pessoa/filtrar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String filtrar(@RequestBody MultiValueMap<String, String> formData, Model model, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        int paginaAtual = page.orElse(PAGINA_INICIAL);
        int qtdePorPagina = size.orElse(PAGINA_TAMANHO);

        Page<Pessoa> pagePessoa = servicePessoa.findByNome(formData.getFirst("filtro"), PageRequest.of(paginaAtual - 1, qtdePorPagina));

        model.addAttribute("pessoas", pagePessoa);

        int totalPaginas = pagePessoa.getTotalPages();
        if (totalPaginas > 0) {
            List<Integer> numeroDePaginas = IntStream.rangeClosed(1, totalPaginas).boxed().collect(Collectors.toList());
            model.addAttribute("paginas", numeroDePaginas);
        }

        return "lista-pessoas";
    }

    @RequestMapping(value = "/pessoa/cadastro", method = RequestMethod.GET)
    public String cadastro(Model model) {
        model.addAttribute("pessoaForm", new PessoaForm(null));
        model.addAttribute("estadosCivis", serviceEstadoCivil.findAll());
        return "cadastro-pessoa";
    }

    @RequestMapping(value = "/pessoa/cadastrar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String cadastrar(@Valid PessoaForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return respostaComErro(model, "cadastro-pessoa");
        } else {
            try {
                servicePessoa.save(form);
                return "redirect:/pessoa/listar";
            } catch (NomeParceiroNaoPreenchidoException npnpe) {
                result.rejectValue("nomeParceiro", npnpe.getMessage());
                return respostaComErro(model, "cadastro-pessoa");
            } catch (DataNascimentoParceiroNaoPreenchidoException dnpnpe) {
                result.rejectValue("dataNascimentoParceiro", dnpnpe.getMessage());
                return respostaComErro(model, "cadastro-pessoa");
            } catch (RuntimeException re) {
                result.rejectValue(null, re.getMessage());
                return respostaComErro(model, "cadastro-pessoa");
            }
        }
    }

    @RequestMapping(value = "/pessoa/edicao/{id}", method = RequestMethod.GET)
    public String edicao(@PathVariable("id") Long idPessoa, Model model) {
        Pessoa pessoa = servicePessoa.findById(idPessoa);

        if (pessoa == null) throw new IllegalArgumentException("ID de usuário inválido: " + idPessoa);

        PessoaForm pessoaForm = new PessoaForm(pessoa.getId());
        pessoaForm.setNome(pessoa.getNome());
        pessoaForm.setEstadoCivil(pessoa.getEstadoCivil().getId());
        pessoaForm.setDataNascimento(pessoa.getDataNascimento());
        pessoaForm.setNomeParceiro(pessoa.getNomeParceiro());
        pessoaForm.setDataNascimentoParceiro(pessoa.getDataNascimentoParceiro());

        model.addAttribute("estadosCivis", serviceEstadoCivil.findAll());
        model.addAttribute("pessoaForm", pessoaForm);
        return "edicao-pessoa";
    }

    @RequestMapping(value = "/pessoa/editar/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editar(@PathVariable("id") Long idPessoa, @Valid PessoaForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            form.setId(idPessoa);
            return respostaComErro(model, "edicao-pessoa");
        } else {
            try {
                servicePessoa.save(form);
                return "redirect:/pessoa/listar";
            } catch (NomeParceiroNaoPreenchidoException npnpe) {
                result.rejectValue("nomeParceiro", npnpe.getMessage());
                return respostaComErro(model, "edicao-pessoa");
            } catch (DataNascimentoParceiroNaoPreenchidoException dnpnpe) {
                result.rejectValue("dataNascimentoParceiro", dnpnpe.getMessage());
                return respostaComErro(model, "edicao-pessoa");
            } catch (RuntimeException re) {
                result.rejectValue(null, re.getMessage());
                return respostaComErro(model, "edicao-pessoa");
            }
        }
    }

    @RequestMapping(value = "/pessoa/excluir/{id}", method = RequestMethod.GET)
    public String excluir(@PathVariable("id") Long idPessoa, Model model) {
        Pessoa pessoa = servicePessoa.findById(idPessoa);

        if (pessoa == null) throw new IllegalArgumentException("ID de usuário inválido: " + idPessoa);
        
        servicePessoa.deleteById(idPessoa);
        return "redirect:/pessoa/listar";
    }

    private String respostaComErro(Model model, String view) {
        model.addAttribute("estadosCivis", serviceEstadoCivil.findAll());
        return view;
    }

}
