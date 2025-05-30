# 残業管理システム
JPT職場体験インターン提出資産です。
## 提出資料
以下のフォルダにまとめています。  
myinternproject/doc/成果物

## コード、デプロイ資産
デプロイ手順で必要なファイルを含みます。  
myinternproject/MyInternProject  
myinternproject/src/sql  

デプロイ手順は成果物フォルダのファイルを参照して下さい。  
[デプロイ手順](doc/成果物/デプロイ手順.pdf)


## システムの仕様
残業管理をするシステムです。
残業申請、報告、確認承認処理、月次資料のデータ出力が行えます。

**ユーザーのロール**
- 社員
　残業申請、残業報告を行う。
- 次長
　残業申請、残業報告の確認処理を行う。
　月次資料の提出、データ出力を行う。
- 課長
　残業申請の承認を行う。
　月次資料のデータ出力を行う。
- 人事部
　月次資料のデータ出力を行う。

  各ユーザーのID、ロールについては、デプロイ手順の◯動作確認を参照  
[デプロイ手順](doc/成果物/デプロイ手順.pdf)

**業務フロー**
1. <社員> 残業申請を行う。
2. <次長> 残業申請を確認する。
3. <課長> 残業申請を承認する。
4. <社員> 残業報告を行う。
5. <次長> 残業報告を確認する。
6. <次長> 月次資料を提出する。データを出力する。
8. <人事部> 月次資料のデータを出力する。
