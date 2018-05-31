-- 查询SCORERULEID 为 1001 时第一个人的对应部门得分
SELECT
	*
FROM
	TB_AVERAGESCORE
WHERE
	TB_AVERAGESCORE.OBJECTID = (
		SELECT
			DEPTID
		FROM
			TB_USER
		WHERE
			USERID = (
				SELECT
					TOP 1 TB_AVERAGESCORE.OBJECTID
				FROM
					TB_AVERAGESCORE
				WHERE
					SCORERULEID = 1001
			)
	) -- 查询SCORERULEID 为 1001 时第一个人的对应得分
	SELECT
		TOP 5 *
	FROM
		TB_AVERAGESCORE
	WHERE
		SCORERULEID = 1001
		
-----------------------------------------------------

		
declare @userid varchar(50)
SET @userid = (SELECT USERID FROM TB_USER WHERE USERNAME = '郭平')

-- 查询部门的分数
declare @deptid INT
SET @deptid = (SELECT TB_USER.DEPTID FROM TB_USER WHERE TB_USER.USERID = @userid)

SELECT * FROM TB_AVERAGESCORE WHERE TB_AVERAGESCORE.OBJECTID = @userid
SELECT * FROM TB_AVERAGESCORE WHERE OBJECTID = @deptid

--评分人类型不是部门其他教职工的求平均分
DECLARE @scorerType varchar(50)
DECLARE @sqlstr varchar(8000)

SET @sqlstr = ''

DECLARE My_Cursor CURSOR --定义游标
  
FOR (SELECT DISTINCT SCORERTYPE FROM TB_SCORERESULT WHERE (OBJECTID = @userid AND STATUS = 2)) 
OPEN My_Cursor
FETCH NEXT FROM My_Cursor INTO @scorerType
WHILE @@FETCH_STATUS = 0  
    BEGIN  
				SET @sqlstr = @sqlstr + 'SELECT SCORERTYPE,AVG(SCORERESULT) FROM TB_SCORERESULT WHERE (OBJECTID = '+@userid+' AND STATUS = 2 AND SCORERTYPE = '+@scorerType+') GROUP BY SCORERTYPE UNION '
        FETCH NEXT FROM My_Cursor INTO @scorerType
    END  
CLOSE My_Cursor --关闭游标  
DEALLOCATE My_Cursor --释放游标

SET @sqlstr = SUBSTRING(@sqlstr, 0, LEN(@sqlstr)-LEN('UNION') )

EXEC(@sqlstr)

GO
