package scj.cntrl.vehicle;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("vehicle")
public class MyVehicleController {
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getVehicle(@PathVariable String name, Model model){
		System.err.println("name: "+name);
		model.addAttribute("vehicleName", name);
		return "list";
	}
	
	@RequestMapping(value = "/{name}/{value}", method = RequestMethod.GET)
	public @ResponseBody String getName(@PathVariable String name, @PathVariable String value, Model model){
		System.err.println("name: "+name);
		model.addAttribute("vehicleName", name);
		model.addAttribute("vehicleValue", value);
		return model.toString();
	}
	
	@RequestMapping(value = "/number/{value}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String getName( @PathVariable String value){
		System.err.println("name: "+value);		
		return "{"+value+"}";
	}
}
