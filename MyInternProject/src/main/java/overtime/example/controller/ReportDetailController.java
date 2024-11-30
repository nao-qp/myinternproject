package overtime.example.controller;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import overtime.example.domain.user.model.Reports;
import overtime.example.domain.user.model.Users;
import overtime.example.domain.user.service.ReportService;
import overtime.example.domain.user.service.UserService;
import overtime.example.domain.user.service.impl.CustomUserDetails;

@Controller
public class ReportDetailController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReportService reportService;
	
	//残業報告詳細画面表示
	@GetMapping("report/detail/{id}")
	public String getReportDetail(Model model, Locale locale, @PathVariable("id") Integer id) {
		
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
        
        // 残業報告データ取得
        Reports report = reportService.getReport(id);	//reportテーブルのid
        model.addAttribute("report", report);
        
        //報告日を編集
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String reportDate = (report.getUpdateDateTime()).format(formatter);
        model.addAttribute("reportDate", reportDate);
        
		return "report/detail";
	}
	
}
