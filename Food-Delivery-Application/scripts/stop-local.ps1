$ErrorActionPreference = 'Stop'

$repoRoot = Resolve-Path (Join-Path $PSScriptRoot '..')
$workspaceRoot = Resolve-Path (Join-Path $repoRoot '..')
$tomcatHome = Join-Path $workspaceRoot 'tools\apache-tomcat-10.1.54'

$env:CATALINA_HOME = $tomcatHome
$env:CATALINA_BASE = $tomcatHome
if (-not $env:JAVA_HOME -and (Test-Path 'C:\Program Files\Java\jdk-23')) {
    $env:JAVA_HOME = 'C:\Program Files\Java\jdk-23'
}

& (Join-Path $tomcatHome 'bin\shutdown.bat')
Write-Host 'Stop signal sent to local Tomcat.'
