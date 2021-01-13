package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Reserve;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.domain.Customer;

import br.ufscar.dc.dsw.service.spec.IReserveService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    private IReserveService reserveService;

//    private boolean isTimeAvailable(Rental rental){
//        List<Reserve> reserve = reserveService.findByRental(rental.getRental());
//       
//        for (int i = 0; i < reserve.size(); i++) {
//            if (reserve.get(i).getDay() == reserve.getDay()){
//                if (reserve.get(i).getTime() == reserve.getTime()){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
            
//    @GetMapping("/listC")
//    public String listReserveByCustomer(ModelMap model) {
//        List<Reserve> reserves = reserveService.findByCustomer(getId());
//        model.addAttribute("reserves", reserves);
//
//        return "customer/list";
//    }
//    
//    @GetMapping("/listR")
//    public String listReserveByRental(ModelMap model) {
//        List<Reserve> reserves = reserveService.findByRental(getId());
//        model.addAttribute("reserves", reserves);
//
//        return "rental/home";
//    }
//
//    @PostMapping("/save")
//    public String save(){
//        
//    }

}
