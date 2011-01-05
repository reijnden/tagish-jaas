# Microsoft Developer Studio Project File - Name="wsspi" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Static Library" 0x0104

CFG=wsspi - Win32 Debug Unicode
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "wsspi.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "wsspi.mak" CFG="wsspi - Win32 Debug Unicode"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "wsspi - Win32 Debug Unicode" (based on "Win32 (x86) Static Library")
!MESSAGE "wsspi - Win32 Release Unicode" (based on "Win32 (x86) Static Library")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""$/wsspi", LCAAAAAA"
# PROP Scc_LocalPath "."
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "wsspi___Win32_Debug_Unicode"
# PROP BASE Intermediate_Dir "wsspi___Win32_Debug_Unicode"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "lib\"
# PROP Intermediate_Dir "DebugUnicode"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_MBCS" /D "_LIB" /Yu"src\stdafx.h" /FD /GZ /c
# ADD CPP /nologo /MTd /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_UNICODE" /D "_LIB" /Yu"stdafx.h" /FD /GZ /c
# ADD BASE RSC /l 0x409 /d "_DEBUG"
# ADD RSC /l 0x409 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LIB32=link.exe -lib
# ADD BASE LIB32 /nologo
# ADD LIB32 /nologo /out:"lib\wsspidu.lib"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "wsspi___Win32_Release_Unicode"
# PROP BASE Intermediate_Dir "wsspi___Win32_Release_Unicode"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "lib\"
# PROP Intermediate_Dir "ReleaseUnicode"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_MBCS" /D "_LIB" /Yu"src\stdafx.h" /FD /c
# ADD CPP /nologo /MT /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_UNICODE" /D "_LIB" /Yu"stdafx.h" /FD /c
# ADD BASE RSC /l 0x409 /d "NDEBUG"
# ADD RSC /l 0x409 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LIB32=link.exe -lib
# ADD BASE LIB32 /nologo
# ADD LIB32 /nologo /out:"lib\wsspiu.lib"

!ENDIF 

# Begin Target

# Name "wsspi - Win32 Debug Unicode"
# Name "wsspi - Win32 Release Unicode"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=.\src\SspiClient.cpp

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\SspiEx.cpp

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\SspiPackage.cpp

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\SspiServer.cpp

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\StdAfx.cpp

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"
# ADD CPP /Yc"stdafx.h"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"
# ADD CPP /Yc"stdafx.h"

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\wsspi.cpp

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\inc\SspiClient.h

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\inc\SspiDef.h

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\inc\SspiEx.h

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\inc\SspiPackage.h

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\inc\SspiServer.h

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\StdAfx.h

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\inc\wsspi.h

!IF  "$(CFG)" == "wsspi - Win32 Debug Unicode"

# PROP Intermediate_Dir "DebugUnicode"

!ELSEIF  "$(CFG)" == "wsspi - Win32 Release Unicode"

# PROP Intermediate_Dir "ReleaseUnicode"

!ENDIF 

# End Source File
# End Group
# Begin Source File

SOURCE=.\Readme.txt
# End Source File
# End Target
# End Project
