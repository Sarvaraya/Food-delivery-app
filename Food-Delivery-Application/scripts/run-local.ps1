$ErrorActionPreference = 'Stop'

$repoRoot = Resolve-Path (Join-Path $PSScriptRoot '..')
$workspaceRoot = Resolve-Path (Join-Path $repoRoot '..')
$mavenHome = Join-Path $workspaceRoot 'tools\apache-maven-3.9.14'
$tomcatHome = Join-Path $workspaceRoot 'tools\apache-tomcat-10.1.54'
$warPath = Join-Path $repoRoot 'target\food-delivery.war'
$webapps = Join-Path $tomcatHome 'webapps'
$deployedWar = Join-Path $webapps 'food-delivery.war'
$deployedDir = Join-Path $webapps 'food-delivery'

if (-not (Test-Path (Join-Path $mavenHome 'bin\mvn.cmd'))) {
    throw "Maven was not found at $mavenHome. Re-run the setup commands or download Maven 3.9.14 into D:\app\tools."
}

if (-not (Test-Path (Join-Path $tomcatHome 'bin\startup.bat'))) {
    throw "Tomcat was not found at $tomcatHome. Re-run the setup commands or download Tomcat 10.1.54 into D:\app\tools."
}

$env:Path = (Join-Path $mavenHome 'bin') + ';' + $env:Path
Push-Location $repoRoot
try {
    mvn clean package
} finally {
    Pop-Location
}

$tomcatRoot = (Resolve-Path -LiteralPath $tomcatHome).Path
foreach ($target in @($deployedWar, $deployedDir)) {
    if (Test-Path -LiteralPath $target) {
        $resolved = (Resolve-Path -LiteralPath $target).Path
        if (-not $resolved.StartsWith($tomcatRoot, [System.StringComparison]::OrdinalIgnoreCase)) {
            throw "Refusing to remove unexpected path $resolved"
        }
        Remove-Item -LiteralPath $resolved -Recurse -Force
    }
}

Copy-Item -LiteralPath $warPath -Destination $deployedWar -Force

$env:CATALINA_HOME = $tomcatHome
$env:CATALINA_BASE = $tomcatHome
if (-not $env:JAVA_HOME -and (Test-Path 'C:\Program Files\Java\jdk-23')) {
    $env:JAVA_HOME = 'C:\Program Files\Java\jdk-23'
}

if (-not (Get-NetTCPConnection -LocalPort 8080 -ErrorAction SilentlyContinue)) {
    Start-Process -FilePath (Join-Path $tomcatHome 'bin\startup.bat') -WorkingDirectory (Join-Path $tomcatHome 'bin') -WindowStyle Hidden
    Start-Sleep -Seconds 8
}

Write-Host 'App is available at http://localhost:8080/food-delivery/home'
Write-Host 'Demo login: demo / demo123'
