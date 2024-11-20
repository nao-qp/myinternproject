package overtime.example.controller;

import java.util.Locale;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestController {

	//残業申請一覧画面表示
	@GetMapping("/request/list")
	public String getRequestList(Model model, Locale locale) {
		
		// 現在のユーザーの認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //認証情報がない場合は、ログインページにリダイレクトする
        if (authentication == null) {
        	 return "redirect:/user/login";
        }
        
        return "list";
	}
}
