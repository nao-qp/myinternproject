'use strict';
/**
 *  残業申請新規作成
 */

 document.addEventListener("DOMContentLoaded", function () {

		//勤務パターン初期値にユーザー情報の勤務パターンを設定
        // ドロップダウンリストを取得
        const dropdown = document.getElementById("workPatternsId");

        // ドロップダウンの全てのオプションをループ
        for (let option of dropdown.options) {
            // 初期値が一致する場合に選択
            if (option.value == userWorkPatternsId) {
                option.selected = true;
                break;
            }
        }
        
    });