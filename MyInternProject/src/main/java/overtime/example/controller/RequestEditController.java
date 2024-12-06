package overtime.example.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
public class RequestEditController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private RequestService requestService;

	@Autowired
	private WorkPatternService workPatternService;

	//差し戻し後、申請書修正画面表示
	@GetMapping("request/edit/{id}")
	public String getRequestEdit(Model model, @ModelAttribute RequestForm form, Locale locale,
									@PathVariable("id") Integer id) {

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
        model.addAttribute("user", user);

        //勤務パターンマスター取得
        List<WorkPatterns> workPatternList = workPatternService.getWorkPatternMaster();
        model.addAttribute("workPatternList", workPatternList);

        // 残業申請データ取得
        Requests request = requestService.getRequest(id);	//requestsテーブルのid
        model.addAttribute("request", request);

        //既存データを初期値に設定
        form = modelMapper.map(request, RequestForm.class);
        model.addAttribute("requestForm", form);

		return "request/edit";
	}

	//差し戻し後、申請書修正更新処理
	@PostMapping("request/edit/{id}")
	public String postRequestEdit(Model model, @ModelAttribute RequestForm form, Locale locale,
							@PathVariable("id") Integer id) {

		// 現在のユーザーの認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //認証情報がない場合は、ログインページにリダイレクトする
        if (authentication == null) {
        	 return "redirect:/user/login";
        }

        //修正更新
        Requests request = modelMapper.map(form, Requests.class);
        request.setId(id);
        request.setRestPeriod(LocalTime.of(0, 0));	//TODO:規定休憩時間を設定する
        requestService.updateEdit(request);

		return "redirect:/request/list?fromMenu=true";
	}
}
