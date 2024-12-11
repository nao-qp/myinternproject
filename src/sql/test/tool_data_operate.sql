-- 申請・報告データリセット
delete from requests;
delete from reports;


-- 承認リセット
update requests set approval_status = null;

