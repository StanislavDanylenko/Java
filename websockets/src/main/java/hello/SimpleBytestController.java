package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class SimpleBytestController {

    private static final int KEY = 1215516513;
    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping("/sendMessage")
    public Greeting getMessage(@RequestBody BytesMessage message) {
        String str = processBytes(message.getMessage(), KEY);
        template.convertAndSend("/topic/greetings", new Greeting("Hello " + message.getMessage()));
        return null;
    }

    private String processBytes(byte[] bytes, int key) {
        for (byte b : bytes) {
            b ^= key;
        }
        return new String(bytes);
    }

}
