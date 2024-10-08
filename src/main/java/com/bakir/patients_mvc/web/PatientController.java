package com.bakir.patients_mvc.web;



import com.bakir.patients_mvc.entities.Patient;
import com.bakir.patients_mvc.repositories.PatientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping(path="/user/index")
    public String patiens(Model model,
                          @RequestParam(name="page",defaultValue = "0") int page,
                          @RequestParam(name="size",defaultValue = "5") int size,
                          @RequestParam(name="keyword",defaultValue = "") String keyword){
 Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
    model.addAttribute("listPatients", pagePatients.getContent());
    model.addAttribute("pages", new int [pagePatients.getTotalPages()]);
    model.addAttribute("currentPage", page);
    model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping(path="/admin/delete")

    public String delete(long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/")
    public String home(){

        return "home";
    }
//    @GetMapping(path="/error")
//    public String error(){
//        return "error";
//    }
    @GetMapping(path="/admin/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

@PostMapping(path="/admin/save")

    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue ="0" ) int page,@RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
    return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

@GetMapping(path="/admin/editPatient")

    public String edit(Model model,long id,String keyword,int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient no found");
        model.addAttribute("patient", patient);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "editPatient";
    }

    @GetMapping(path="/login")
    public String handleLogin(){
        return "custom_login";
    }
 //@GetMapping("/patients")
//    @ResponseBody
 //public List<Patient> getPatients(){
    //return patientRepository.findAll();
  // }
}
