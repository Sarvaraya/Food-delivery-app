Set-Location $PSScriptRoot
$projectRoot = Join-Path $PSScriptRoot 'Food-Delivery-Application'
powershell -NoProfile -ExecutionPolicy Bypass -File (Join-Path $projectRoot 'scripts\run-local.ps1')
