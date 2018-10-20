package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping("/sendMessage")
    public Greeting getMessage(@RequestBody HelloMessage message) {
        template.convertAndSend("/topic/greetings", new Greeting("Hello " + message.getName()));
        return null;
    }

}
