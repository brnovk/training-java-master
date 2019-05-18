cd db

:CheckOS
IF EXIST "%PROGRAMFILES(X86)%" (GOTO 64BIT) ELSE (GOTO 32BIT)

:64BIT
%systemdrive%\Windows\syswow64\odbcad32.exe
GOTO END

:32BIT
odbcad32.exe
GOTO END

:END

::pause
