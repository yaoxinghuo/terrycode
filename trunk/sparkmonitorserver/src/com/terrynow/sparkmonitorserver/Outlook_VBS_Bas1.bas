Attribute VB_Name = "模块1"
Sub spark(Item As MailItem)
  ' 工具-引用-打开Microsoft Win Http Service 5.1
    Dim MyRequest As Object
    Set MyRequest = CreateObject("WinHttp.WinHttpRequest.5.1")
    
    MyRequest.Open "POST", "http://localhost:8182/spark", False
    MyRequest.SetRequestHeader "Content-Type", "application/x-www-form-urlencoded"
    Dim sendStr As String
    Dim mesg As String
    mesg = encodeURL("Outlook接收到来自：" + Item.SenderName + "<" + Item.SenderEmailAddress + ">的新邮件：" + Item.Subject)
    sendStr = "username=monitor&password=12345678&host=im.terrynow.com&to=terry@im.terrynow.com&message=" + mesg
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
