$root = Split-Path -Parent $PSScriptRoot
$pom = Join-Path $root "week09-async-javafx\pom.xml"
$activities = @{
    "1" = @{ main = "com.example.FreezeDemo"; name = "Freeze Problem" }
    "2" = @{ main = "com.example.TaskDemo"; name = "Background Task" }
    "3" = @{ main = "com.example.ProgressDemo"; name = "Live Progress" }
    "4" = @{ main = "com.example.ServiceDemo"; name = "Reusable Service" }
}

Write-Host "Week 9: Asynchronous Programming in JavaFX"
Write-Host "1. Freeze Problem"
Write-Host "2. Background Task"
Write-Host "3. Live Progress"
Write-Host "4. Reusable Service"
$choice = Read-Host "Choose an activity (1-4)"

if ($activities.ContainsKey($choice)) {
    $activity = $activities[$choice]
    & mvn -f $pom javafx:run "-Djavafx.mainClass=$($activity.main)"
    exit $LASTEXITCODE
}

Write-Error "Invalid choice."
exit 1
