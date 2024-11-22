package overtime.example.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import overtime.example.domain.user.model.Requests;
import overtime.example.domain.user.model.Users;
import overtime.example.domain.user.service.RequestService;
import overtime.example.domain.user.service.UserService;
import overtime.example.domain.user.service.impl.CustomUserDetails;

@Controller
public class RequestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestService requestService;
	
	//残業申請一覧画面表示
	@GetMapping("request/list")
	public String getRequestList(Model model, Locale locale) {
		
		// 現在のユーザーの認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //認証情報がない場合は、ログインページにリダイレクトする
        if (authentication == null) {
        	 return "redirect:/user/login";
        }
        
        // 認証されたユーザーのIDを取得
        Integer currentUserId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        // 権限を取得
        String role = authentication.getAuthorities().stream()
        				.map(GrantedAuthority::getAuthority)
        				.findFirst()
        				.orElse(null);
        model.addAttribute("role", role);
        // ユーザー情報を取得
        Users user = userService.getUser(currentUserId);
        model.addAttribute("user", user);
        
        // 残業申請データ一覧を取得
        List<Requests> requestList = requestService.getRequestList(currentUserId);
        model.addAttribute("requestList", requestList);
        
        return "list";
	}
	
	//残業申請詳細画面表示
	@GetMapping("request/detail/{id}")
	public String getRequestDetail(Model model, Locale locale, @PathVariable("id") Integer id) {
		
		// 現在のユーザーの認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //認証情報がない場合は、ログインページにリダイレクトする
        if (authentication == null) {
        	 return "redirect:/user/login";
        }
        
        // 認証されたユーザーのIDを取得
        Integer currentUserId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        // 権限を取得
        String role = authentication.getAuthorities().stream()
        				.map(GrantedAuthority::getAuthority)
        				.findFirst()
        				.orElse(null);
        model.addAttribute("role", role);
        // ユーザー情報を取得
        Users user = userService.getUser(currentUserId);
        model.addAttribute("user", user);
        
        // 残業申請データ取得
        Requests request = requestService.getRequest(id);	//requestsテーブルのid
        model.addAttribute("request", request);
        
		return "request/detail";
	}
}
