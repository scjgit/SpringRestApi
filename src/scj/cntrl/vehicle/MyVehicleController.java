package scj.cntrl.vehicle;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class MyVehicleController {
        
        @RequestMapping(value = "/vehicle/{name}", method = RequestMethod.GET)
        public String getVehicleName(@PathVariable String name, Model model){
                System.err.println("name: "+name);
                model.addAttribute("vehicleName", name);
                return "list";
        }
        
        @RequestMapping(value = "/myVehicle/{name}/{value}", method = RequestMethod.GET)
        public @ResponseBody String getVehicleDetails(@PathVariable String name, @PathVariable String value, Model model){
                System.err.println("name: "+name);
                model.addAttribute("'vehicleName'", name+", 'Value' : "+value);
                return model+"";
        }
        
        @RequestMapping(value = "vehicleDetails", method = RequestMethod.GET)
        public void getName(@RequestParam(value = "name", required = true) String name,
                        @RequestParam(value = "value", required = true) String value,
                        Model model, HttpServletRequest request, HttpServletResponse response){
                model.addAttribute("vehicleName", name);
                model.addAttribute("vehicleValue", value);
                try {
                        response.setContentType("application/json");
                        response.getOutputStream().write("myCallBack(".getBytes());
                        response.getOutputStream().write(new ObjectMapper().writeValueAsBytes(model));
                        response.getOutputStream().write(");".getBytes());
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}