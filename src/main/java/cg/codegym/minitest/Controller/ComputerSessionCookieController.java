package cg.codegym.minitest.Controller;

import cg.codegym.minitest.Model.Computer;
import cg.codegym.minitest.Model.ComputerSessionCookie;
import cg.codegym.minitest.Service.iml.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/computers")
@SessionAttributes("cart")
public class ComputerSessionCookieController {
    @Autowired
    private IComputerService computerService;

    @ModelAttribute("cart")
    public ComputerSessionCookieController setupCart(){
        return new ComputerSessionCookieController();
    }
    @GetMapping("/shop")
    public ModelAndView showListComputerCart(){
        ModelAndView modelAndView = new ModelAndView("/computer/list1");
        modelAndView.addObject("computers", computerService.findAll());
        return modelAndView;
    }
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute ComputerSessionCookie computerSessionCookie,
                            @RequestParam("action") String action) {
        Optional<Computer> computerSessionCookieOptional = computerService.findById(id);
        if
    }
}
