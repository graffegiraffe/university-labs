@echo off
setlocal enabledelayedexpansion


set "home=%CD%"

set /p directory="Set adress " 

if not exist "%directory%" (
    echo Данной папки нет
    exit /b 1
)

cd /d "%directory%"

set "string="

for /r %%f in (*.doc) do (
    set "string=!string!%%~nf"
)

set "result=0"

for /l %%i in (0,1,100) do (
	set "char=!string:~%%i,1!"
	set "count=0"
	
	
	for /l %%j in (0,1,100) do (
		set "char_to_count=!string:~%%j,1!"
		if "!char!"=="!char_to_count!" set /a "count+=1"
	)
	

	if !count! equ 1 (
		set /a "result+=1"
	)
)


cd "%home%"


echo !result!>> "result.txt"

@exit

endlocal
