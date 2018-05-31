-- 批量打分程序

-- 注意SCORERULEID为特定值的分数拥有上限


DECLARE @id varchar(50)
DECLARE My_Cursor CURSOR --定义游标  
FOR (SELECT id FROM TB_SCORERESULT WHERE SCORERULEID = ?) --查出需要的集合放到游标中  
OPEN My_Cursor --打开游标
FETCH NEXT FROM My_Cursor INTO @id --读取第一行数据
WHILE @@FETCH_STATUS = 0  
    BEGIN  
				UPDATE TB_SCORERESULT SET STATUS = 2,	SCORERESULT = FLOOR( RAND()* 20 )+78 WHERE id = @id
        FETCH NEXT FROM My_Cursor INTO @id --读取下一行数据
    END  
CLOSE My_Cursor --关闭游标  
DEALLOCATE My_Cursor --释放游标  
GO