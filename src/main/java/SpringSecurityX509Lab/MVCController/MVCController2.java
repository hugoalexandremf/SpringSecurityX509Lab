package SpringSecurityX509Lab.MVCController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/statelessController")
public class MVCController2 {

     private static final Logger LOG = LoggerFactory.getLogger(MVCController2.class);

     @GetMapping("/testList")
     public String testList(Principal principal) {
          LOG.info("/statelessController/testList");
          LOG.info("Principal: " + principal.getName());

          return "index";
     }

}
