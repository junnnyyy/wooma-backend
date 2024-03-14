package dotolhee.daramhee.woomabackend.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TestController {

    @GetMapping("")
    fun test():String {
        return "ZZZZ"
    }
    @GetMapping("/hello")
    fun hello(): String {
        return "say hello"
    }
}