//˵���������ű��ļ�����Ҫ��ִ��ҳ����ת���ܡ�
//Athour:�����
//���ڣ�2007-9-27
//��ں���  postXml  ˵��:��xml��ʽ�ύ�����xml����
//��ں���  postXmlX  ˵��:��xml��ʽ�ύ������ַ�������
//��ں���  postStrX  ˵��:���ַ�����ʽ�ύ�����xml����
//��ں���  postStr  ˵��:���ַ�����ʽ�ύ������ַ�������

//��xml��ʽ�ύ�����xml����
function postXml(xmlDoc,url)
{
	var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	xmlhttp.Open("POST",url,false);
	xmlhttp.Send(xmlDoc);
	return xmlhttp.responseXML;
}
//��xml��ʽ�ύ������ַ�������
function postXmlX(xmlDoc,url)
{
	var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	xmlhttp.Open("POST",url,false);
	xmlhttp.Send(xmlDoc);
   if(xmlhttp.status == "200")
      back=xmlhttp.responseText;
   else
      back="";
   xmlhttp = null;
   delete xmlhttp;
   return back;
}
//���ַ�����ʽ�ύ�����xml����
function postStrX(url)
{
   var back;
   var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
   xmlhttp.open("POST",url,false);
   xmlhttp.send();
   back=xmlhttp.responseXML;
   xmlhttp = null;
   delete xmlhttp;
   return back;
}

//���ַ�����ʽ�ύ������ַ�������
function postStr(url)
{
   var back;
   var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
   xmlhttp.open("POST",url,false);
   xmlhttp.send();
   if(xmlhttp.status == "200")
      back=xmlhttp.responseText;
   else
      back="";
   xmlhttp = null;
   delete xmlhttp;
   return back;
}



function public_Init_keepTrack(str)// �ж�iframe �Ƿ���ȫ�������,���û�еȴ�10����,�ݹ�
{
  try
  {
   if (document.readyState == "complete")
     {
	    eval(str);
     }
   else 
     {
        setTimeout("public_Init_keepTrack('"+str+"')",100);
     }
  }
  catch(e)
  {
     alert("�ݹ����,���ĵ�û��������ȫ!");
  }
}