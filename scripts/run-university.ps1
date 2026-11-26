$root = Split-Path -Parent $PSScriptRoot
$pom = Join-Path $root "UniversitySystem\pom.xml"

Write-Host "Week 10: University Management System"
Write-Host "The app requires DATABASE_URL in the environment or a .env file in the repository root."
& mvn -f $pom javafx:run
exit $LASTEXITCODE
