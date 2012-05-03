set fso=CreateObject("Scripting.FileSystemObject")
set ws=CreateObject("wscript.shell")
set oexcel=CreateObject("excel.application")
set oexcel2=CreateObject("excel.application")
name=fso.getfolder(".")&"\总表.xls":i=1
oexcel.workbooks.add
oexcel.activeworkbook.saveas(name)
oexcel.DisplayAlerts = False
oexcel2.DisplayAlerts = False
oexcel.visible=false
set fs=fso.getfolder(".").files
for each f in fs
ext=LCase(right(f.name,4))
if (ext=".xls" or ext="xlsx") and left(f.name,2)<>"~$" and f.path<>name then
	set w = oexcel2.workbooks.open(fso.getfolder(".")&"\"&f.name)
	For Each s In w.sheets
        s.Rows(1).copy
	oexcel.Worksheets(1).paste(oexcel.Worksheets(1).Rows(i))
	i=i+1
        next
        i=i+1
	oexcel2.activeworkbook.close
end if

next
oexcel.activeworkbook.save
oexcel.activeworkbook.close
msgbox "处理完毕！"
'Created By escortmnm from VBS团队
