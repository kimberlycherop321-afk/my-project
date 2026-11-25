$root = Split-Path -Parent $PSScriptRoot
$activities = @{
    "1" = @{ folder = "activity1-weather-station"; main = "WeatherStationMain" }
    "2" = @{ folder = "activity2-legacy-observer"; main = "LegacyObserverMain" }
    "3" = @{ folder = "activity3-custom-observer"; main = "RefactoredMain" }
    "4" = @{ folder = "activity4-comparison"; main = "ComparisonMain" }
}

Write-Host "Week 2: Observer Pattern"
Write-Host "1. Weather Station"
Write-Host "2. Legacy Observer"
Write-Host "3. Custom Observer Refactor"
Write-Host "4. Notification Comparison"
$choice = Read-Host "Choose an activity (1-4)"

if ($activities.ContainsKey($choice)) {
    $activity = $activities[$choice]
    $folder = Join-Path $root $activity.folder
    $sourceFiles = Get-ChildItem -LiteralPath $folder -Filter "*.java" |
        Select-Object -ExpandProperty FullName
    & javac $sourceFiles
    if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
    & java -cp $folder $activity.main
} else {
    Write-Host "Invalid choice."
}
