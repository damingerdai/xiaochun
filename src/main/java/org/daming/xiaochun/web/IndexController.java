package org.daming.xiaochun.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* 
* @author: aming
* @date: 2018-08-22
* @version: 1.0
*/
@Controller
public class IndexController {
	
//	@RequestMapping("/index")
//	public String index() {
//		return "index";
//	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
