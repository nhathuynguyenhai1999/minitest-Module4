package cg.codegym.minitest.Controller;

import cg.codegym.minitest.Model.Computer;
import cg.codegym.minitest.Model.Type;
import cg.codegym.minitest.Service.iml.IComputerService;
import cg.codegym.minitest.Service.iml.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/types")
public class TypeController {


    @Autowired
    private ITypeService typeService;


    @Autowired
    private IComputerService customerService;

    @GetMapping
    public ModelAndView listProvince() {
        ModelAndView modelAndView = new ModelAndView("/type/list");
        Iterable<Type> provinces = typeService.findAll();
        modelAndView.addObject("types", provinces);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("type") Type province,
                         RedirectAttributes redirectAttributes) {
        typeService.save(province);
        redirectAttributes.addFlashAttribute("message", "Create new province successfully");
        return "redirect:/types";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Type> province = typeService.findById(id);
        if (province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/update");
            modelAndView.addObject("type", province.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("type") Type province,
                         RedirectAttributes redirect) {
        typeService.save(province);
        redirect.addFlashAttribute("message", "Update type successfully");
        return "redirect:/types";
    }

    @GetMapping("/view-type/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Optional<Type> typeOptional = typeService.findById(id);
        if(!typeOptional.isPresent()){
            return new ModelAndView("/error_404");
        }

        Iterable<Computer> customers = customerService.findAllByType(typeOptional.get());

        ModelAndView modelAndView = new ModelAndView("/computer/list");
        modelAndView.addObject("computers", customers);
        return modelAndView;
    }
}
