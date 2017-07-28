package com.bw.handle;


import com.bw.md5.Md5;
import com.bw.model.User;
import com.bw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * Created by zhx on 2017/7/22.
 */
@Controller
public class DcController {
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String dc() {
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return "index";
    }

    @PostMapping("login")
    public String login(String  username,String password, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String oldpw = userRepository.findOldPassword(username);
        String npw = Md5.EncoderByMd5(password);

        if(npw.equals(oldpw)) {
            User user1 = userRepository.findUserByUsernameAndPassword(username,npw);
            session.setAttribute("user",user1);
            return "forward:list";
        }
        return "reg";
    }

    @PostMapping("reg")
    public String reg(User user,String hobby[]) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String pw = user.getPassword();
        String npw = Md5.EncoderByMd5(pw);
        user.setPassword(npw);
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < hobby.length; i++)
        {
            sb. append(hobby[i]);
        }
        String nhobby = sb.toString();
        user.setHobby(nhobby);
        userRepository.save(user);
        return "index";
    }

    @RequestMapping("list")
    public String list(Map<String,Object> map) {
        List<User> users = userRepository.findAll();
        map.put("users",users);
        return "success";
    }

    @RequestMapping("delete")
    public String delete(Integer id) {
        userRepository.delete(id);
        return "forward:list";
    }
}
