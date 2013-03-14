Sub spark(Item As MailItem)

  ' 工具-引用-打开Microsoft Win Http Service 5.1
    Dim MyRequest As Object
    Set MyRequest = CreateObject("WinHttp.WinHttpRequest.5.1")
    
    MyRequest.Open "POST", "http://localhost:8182/spark", False
    MyRequest.SetRequestHeader "Content-Type", "application/x-www-form-urlencoded"
    Dim sendStr As String
    Dim mesg As String
    mesg = encodeURL("Outlook接收到来自：" + Item.SenderName + "<" + Item.SenderEmailAddress + ">的新邮件：" + Item.Subject)
    Dim body As String
     If Len(Item.body) > 50 Then
         body = ""
    ElseIf Len(Item.body) > 0 Then
        body = vbCrLf + encodeURL("正文：" + Item.body)
    Else
        body = ""
    End If
    
    sendStr = "username=monitor&password=12345678&host=im.terrynow.com&to=terry@terrynow.com&message=" + mesg + body
    
    MyRequest.Send (sendStr)
End Sub


' 工具-引用-打开Microsoft Script Control
Function encodeURL(str As String)
    Dim ScriptEngine As ScriptControl
    Set ScriptEngine = New ScriptControl
    ScriptEngine.Language = "JScript"
    
    ScriptEngine.AddCode "function encode(str) {return encodeURIComponent(str);}"
    Dim encoded As String
    
    encoded = ScriptEngine.Run("encode", str)
    encodeURL = encoded
End Function
