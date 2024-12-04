package overtime.example.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import overtime.example.domain.user.service.RequestService;

@Controller
public class ApproveRequestExeController {

	@Autowired
	private RequestService requestService;

	//課長/残業申請承認更新処理
	@PostMapping("approve/request/execute")
	public String postApproveRequestExecute(Model model, Locale locale,
			@RequestParam(value = "returnRequests", required = false) List<Integer> returnRequests,
			@RequestParam(value = "approveRequests", required = false) List<Integer> approveRequests) {

		//チェック存在確認
		boolean existReturnChecked = true;
		if (returnRequests == null || returnRequests.isEmpty()) {
			existReturnChecked = false;
		}
		boolean existApproveChecked = true;
		if (approveRequests == null || approveRequests.isEmpty()) {
			existApproveChecked = false;
		}

		if (!existReturnChecked && !existApproveChecked) {
			//承認、差戻もチェックなしの場合、一覧へリダイレクト
			return "redirect:/approve/request/list";
		} else {
			if (existReturnChecked) {
				for (Integer id :returnRequests) {
					//差戻
					requestService.updateReturn(id);
				}
			}
			if (existApproveChecked) {
				for (Integer id :approveRequests) {
					//承認
					requestService.updateApproved(id);
				}
			}
		}

		return "redirect:/approve/request/list";
	}

}
