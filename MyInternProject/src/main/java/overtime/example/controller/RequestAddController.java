package overtime.example.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import overtime.example.domain.user.model.Requests;
import overtime.example.domain.user.model.Users;
import overtime.example.domain.user.model.WorkPatterns;
import overtime.example.domain.user.service.RequestService;
import overtime.example.domain.user.service.UserService;
import overtime.example.domain.user.service.WorkPatternService;
import overtime.example.domain.user.service.impl.CustomUserDetails;
import overtime.example.form.RequestForm;

@Controller
public class RequestAddController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkPatternService workPatternService;
	
	@Autowired
	private RequestService requestService;
	
	@GetMapping("request/add")
	public String getRequestAdd(Model model, @ModelAttribute RequestForm form, Locale locale) {
		
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
        
        //申請日に今日の日付を設定
        form.setRequestDate(LocalDate.now());
        
        //勤務パターンマスター取得
        List<WorkPatterns> workPatternList = workPatternService.getWorkPatternMaster();
        model.addAttribute("workPatternList", workPatternList);
              
		return "request/add";
	}
	
	
	@PostMapping("request/add")
	public String postRequestAdd(Model model, @ModelAttribute RequestForm form, Locale locale) {
		
		// 現在のユーザーの認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //認証情報がない場合は、ログインページにリダイレクトする
        if (authentication == null) {
        	 return "redirect:/user/login";
        }
        
        // 認証されたユーザーのIDを取得
        Integer currentUserId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        
        // ユーザー情報を取得
        Users user = userService.getUser(currentUserId);
		
   
		//申請書データ更新
        Requests request = modelMapper.map(form, Requests.class);
        request.setUsersId(user.getId());
        request.setDepartmentsId(user.getDepartmentsId());
        request.setRestPeriod(LocalTime.of(0, 0));	//TODO:規定休憩時間を設定する
        requestService.addRequest(request);
		
		return "redirect:/request/list";
	}
}
