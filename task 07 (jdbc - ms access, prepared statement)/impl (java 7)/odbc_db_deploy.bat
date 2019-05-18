cd db

:CheckOS
IF EXIST "%PROGRAMFILES(X86)%" (GOTO 64BIT) ELSE (GOTO 32BIT)

:64BIT
%systemdrive%\Windows\syswow64\odbcconf.exe CONFIGDSN ^ "Microsoft Access Driver (*.mdb)" ^ "DSN=segments;DBQ=%cd%\segments.mdb"
GOTO END

:32BIT
odbcconf.exe CONFIGDSN ^ "Microsoft Access Driver (*.mdb)" ^ "DSN=segments;DBQ=%cd%\segments.mdb"
GOTO END

:END

pause
