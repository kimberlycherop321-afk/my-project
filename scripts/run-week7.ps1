$root = Split-Path -Parent $PSScriptRoot
$pom = Join-Path $root "week07-javafx-events\pom.xml"
$activities = @{
    "1" = @{ main = "com.example.EventPropagationDemo"; name = "Event Propagation" }
    "2" = @{ main = "com.example.MouseKeyDemo"; name = "Mouse and Keyboard Events" }
    "3" = @{ main = "com.example.DragDropDemo"; name = "Drag and Drop" }
    "4" = @{ main = "com.example.CustomEventDemo"; name = "Custom Events" }
}

Write-Host "Week 7: Event Handling in JavaFX"
Write-Host "1. Event Propagation"
Write-Host "2. Mouse and Keyboard Events"
Write-Host "3. Drag and Drop"
Write-Host "4. Custom Events"
$choice = Read-Host "Choose an activity (1-4)"

if ($activities.ContainsKey($choice)) {
    $activity = $activities[$choice]
    & mvn -f $pom javafx:run "-Djavafx.mainClass=$($activity.main)"
    exit $LASTEXITCODE
}

Write-Error "Invalid choice."
exit 1
