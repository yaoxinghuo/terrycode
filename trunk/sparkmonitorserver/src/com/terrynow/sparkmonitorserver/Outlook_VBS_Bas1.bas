Attribute VB_Name = "ģ��1"
Sub spark(Item As MailItem)
  ' ����-����-��Microsoft Win Http Service 5.1
    Dim MyRequest As Object
    Set MyRequest = CreateObject("WinHttp.WinHttpRequest.5.1")
    
    MyRequest.Open "POST", "http://localhost:8182/spark", False
    MyRequest.SetRequestHeader "Content-Type", "application/x-www-form-urlencoded"
    Dim sendStr As String
    Dim mesg As String
    mesg = encodeURL("Outlook���յ����ԣ�" + Item.SenderName + "<" + Item.SenderEmailAddress + ">�����ʼ���" + Item.Subject)
    sendStr = "username=monitor&password=12345678&host=im.terrynow.com&to=terry@im.terrynow.com&message=" + mesg
    MyRequest.Send (sendStr)
End Sub
' ����-����-��Microsoft Script Control
Function encodeURL(str As String)
    Dim ScriptEngine As ScriptControl
    Set ScriptEngine = New ScriptControl
    ScriptEngine.Language = "JScript"
    
    ScriptEngine.AddCode "function encode(str) {return encodeURIComponent(str);}"
    Dim encoded As String
    
    encoded = ScriptEngine.Run("encode", str)
    encodeURL = encoded
End Function
