package overtime.example.controller;

import java.time.LocalDate;
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

import overtime.example.domain.user.model.Reports;
import overtime.example.domain.user.model.Users;
import overtime.example.domain.user.model.WorkPatterns;
import overtime.example.domain.user.service.ReportService;
import overtime.example.domain.user.service.UserService;
import overtime.example.domain.user.service.WorkPatternService;
import overtime.example.domain.user.service.impl.CustomUserDetails;
import overtime.example.form.ReportForm;

@Controller
public class ReportAddController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private WorkPatternService workPatternService;
	
	//報告書入力画面表示
	@GetMapping("report/add/{id}")
	public String getReportAdd(Model model, Locale locale, ReportForm form, 
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
        
        //報告データを取得
        Reports report = reportService.getReport(id);
        model.addAttribute("report", report);

        //残業時間、休憩時間の初期値設定
        //残業申請書作成時のデータを初期値として設定する
        //TODO:報告データ修正機能を作成した場合は、1回目の更新かどうか判定して処理を分ける
        form.setStartTime(report.getRequestsStartTime());
        form.setEndTime(report.getRequestsEndTime());
        form.setRestPeriod(report.getRequestsRestPeriod());
        
		return "report/add";
	}
	
	//報告書更新処理
	@PostMapping("report/add/{id}")
	public String postReportAdd(Model model, Locale locale, @ModelAttribute ReportForm form,
									@PathVariable("id") Integer id) {
		
		// 現在のユーザーの認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //認証情報がない場合は、ログインページにリダイレクトする
        if (authentication == null) {
        	 return "redirect:/user/login";
        }

        //申請データ更新
        Reports report = modelMapper.map(form, Reports.class);
        report.setId(id);
        LocalDate reportDate = LocalDate.now();
        report.setReportDate(reportDate);
        reportService.editReport(report);
		
		return "redirect:/report/list";
	}
	
	//新規報告書入力画面表示
	@GetMapping("report/new-add")
	public String getNewReportAdd(Model model, Locale locale, @ModelAttribute ReportForm form) {
		
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
        
        //休憩時間の初期値を00:00に設定
        form.setRestPeriod(LocalTime.of(0, 0));
		
		return "report/new-add";
	}
	
	//報告書新規作成（事後報告）
	@PostMapping("report/new-add")
	public String postNewReportAdd(Model model, Locale locale, @ModelAttribute ReportForm form) {
		
		// 現在のユーザーの認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //認証情報がない場合は、ログインページにリダイレクトする
        if (authentication == null) {
        	 return "redirect:/user/login";
        }
        
        // 認証されたユーザーのIDを取得
        Integer currentUserId = ((CustomUserDetails) authentication.getPrincipal()).getId();
		
        //報告データ作成更新
        Reports report = modelMapper.map(form, Reports.class);
        LocalDate reportDate = LocalDate.now();
        report.setUsersId(currentUserId);
        report.setReportDate(reportDate);
        reportService.addNewReport(report);
        
		return "redirect:/report/list";
	}
}
