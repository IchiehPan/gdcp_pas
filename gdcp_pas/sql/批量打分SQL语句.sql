-- ������ֳ���

-- ע��SCORERULEIDΪ�ض�ֵ�ķ���ӵ������


DECLARE @id varchar(50)
DECLARE My_Cursor CURSOR --�����α�  
FOR (SELECT id FROM TB_SCORERESULT WHERE SCORERULEID = ?) --�����Ҫ�ļ��Ϸŵ��α���  
OPEN My_Cursor --���α�
FETCH NEXT FROM My_Cursor INTO @id --��ȡ��һ������
WHILE @@FETCH_STATUS = 0  
    BEGIN  
				UPDATE TB_SCORERESULT SET STATUS = 2,	SCORERESULT = FLOOR( RAND()* 20 )+78 WHERE id = @id
        FETCH NEXT FROM My_Cursor INTO @id --��ȡ��һ������
    END  
CLOSE My_Cursor --�ر��α�  
DEALLOCATE My_Cursor --�ͷ��α�  
GO