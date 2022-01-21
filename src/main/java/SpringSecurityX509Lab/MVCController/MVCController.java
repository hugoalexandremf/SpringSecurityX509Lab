package SpringSecurityX509Lab.MVCController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RequestMapping("/alwaysController")
public class MVCController {

     private static final Logger LOG = LoggerFactory.getLogger(MVCController.class);

     @GetMapping("/testList")
     public String testList(Principal principal) {
          LOG.info("/alwaysController/testList");
          LOG.info("Principal: " + principal.getName());

          return "index";
     }

}
