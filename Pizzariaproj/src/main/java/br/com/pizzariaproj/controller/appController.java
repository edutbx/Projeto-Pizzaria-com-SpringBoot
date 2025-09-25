package br.com.pizzariaproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class appController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/cardapio")
    public String cardapio() {
        return "cardapio";
    }

    @GetMapping("/pedido")
    public String pedido(@RequestParam String sabor, @RequestParam String borda,
                         @RequestParam String adicionais, @RequestParam String total, Model model ) {

        // Converte o valor de total para Double, substituindo a vírgula por ponto
        Double totalValue = convertToDouble(total);

        List<String> listaAdicionais = Arrays.asList(adicionais.split("\\."));

        // Adiciona os atributos ao modelo
        model.addAttribute("sabor", sabor);
        model.addAttribute("borda", borda);
        model.addAttribute("adicionais", listaAdicionais);
        model.addAttribute("total", totalValue);

        return "pedido";
    }

    // Método para converter o valor de String com vírgula para Double
    private Double convertToDouble(String total) {
        try {
            // Substitui a vírgula por ponto antes de tentar converter para Double
            total = total.replace(",", ".");
            return Double.valueOf(total);
        } catch (NumberFormatException e) {
            // Caso haja erro de conversão, retorna 0.0
            System.err.println("Erro ao converter total para Double: " + e.getMessage());
            return 0.0; // ou você pode lançar uma exceção personalizada, dependendo da lógica de sua aplicação
        }
    }
}
