-- ��ѯSCORERULEID Ϊ 1001 ʱ��һ���˵Ķ�Ӧ���ŵ÷�
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
	) -- ��ѯSCORERULEID Ϊ 1001 ʱ��һ���˵Ķ�Ӧ�÷�
	SELECT
		TOP 5 *
	FROM
		TB_AVERAGESCORE
	WHERE
		SCORERULEID = 1001
		
-----------------------------------------------------

		
declare @userid varchar(50)
SET @userid = (SELECT USERID FROM TB_USER WHERE USERNAME = '��ƽ')

-- ��ѯ���ŵķ���
declare @deptid INT
SET @deptid = (SELECT TB_USER.DEPTID FROM TB_USER WHERE TB_USER.USERID = @userid)

SELECT * FROM TB_AVERAGESCORE WHERE TB_AVERAGESCORE.OBJECTID = @userid
SELECT * FROM TB_AVERAGESCORE WHERE OBJECTID = @deptid

--���������Ͳ��ǲ���������ְ������ƽ����
DECLARE @scorerType varchar(50)
DECLARE @sqlstr varchar(8000)

SET @sqlstr = ''

DECLARE My_Cursor CURSOR --�����α�
  
FOR (SELECT DISTINCT SCORERTYPE FROM TB_SCORERESULT WHERE (OBJECTID = @userid AND STATUS = 2)) 
OPEN My_Cursor
FETCH NEXT FROM My_Cursor INTO @scorerType
WHILE @@FETCH_STATUS = 0  
    BEGIN  
				SET @sqlstr = @sqlstr + 'SELECT SCORERTYPE,AVG(SCORERESULT) FROM TB_SCORERESULT WHERE (OBJECTID = '+@userid+' AND STATUS = 2 AND SCORERTYPE = '+@scorerType+') GROUP BY SCORERTYPE UNION '
        FETCH NEXT FROM My_Cursor INTO @scorerType
    END  
CLOSE My_Cursor --�ر��α�  
DEALLOCATE My_Cursor --�ͷ��α�

SET @sqlstr = SUBSTRING(@sqlstr, 0, LEN(@sqlstr)-LEN('UNION') )

EXEC(@sqlstr)

GO
