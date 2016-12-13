; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{84C4719E-C13F-4ABE-ABE1-A623368BC2BF}
AppName=RPG OOP GROUP05
AppVersion=1.0
;AppVerName=RPG OOP GROUP05 1.0
AppPublisher=GROUP05
AppPublisherURL=http://www.example.com/
AppSupportURL=http://www.example.com/
AppUpdatesURL=http://www.example.com/
DefaultDirName={pf}\RPG OOP GROUP 5
DefaultGroupName=RPG OOP GROUP05
LicenseFile=C:\Users\user\Documents\NetBeansProjects\RPG-GAME-OOP-GROUP5\dist\README.TXT
InfoBeforeFile=C:\Users\user\Documents\NetBeansProjects\RPG-GAME-OOP-GROUP5\dist\README.TXT
InfoAfterFile=C:\Users\user\Documents\NetBeansProjects\RPG-GAME-OOP-GROUP5\dist\README.TXT
OutputBaseFilename=setup
Compression=lzma
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "C:\Program Files (x86)\Inno Setup 5\Examples\MyProg.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\user\Documents\NetBeansProjects\RPG-GAME-OOP-GROUP5\dist\RPG_Game.jar"; DestDir: "{app}"; Flags: ignoreversion
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{group}\RPG OOP GROUP05"; Filename: "{app}\MyProg.exe"
Name: "{commondesktop}\RPG OOP GROUP05"; Filename: "{app}\MyProg.exe"; Tasks: desktopicon

[Run]
Filename: "{app}\MyProg.exe"; Description: "{cm:LaunchProgram,RPG OOP GROUP05}"; Flags: nowait postinstall skipifsilent
