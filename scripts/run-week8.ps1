$root = Split-Path -Parent $PSScriptRoot
$pom = Join-Path $root "week08-javafx-bindings\pom.xml"
$activities = @{
    "1" = @{ main = "com.example.ListenerDemo"; name = "Observable Model and Listeners" }
    "2" = @{ main = "com.example.UnidirectionalDemo"; name = "Unidirectional Binding" }
    "3" = @{ main = "com.example.BidirectionalDemo"; name = "Bidirectional Binding" }
    "4" = @{ main = "com.example.ReactiveFormApp"; name = "Reactive Form" }
}

Write-Host "Week 8: Properties and Bindings in JavaFX"
Write-Host "1. Observable Model and Listeners"
Write-Host "2. Unidirectional Binding"
Write-Host "3. Bidirectional Binding"
Write-Host "4. Reactive Form"
$choice = Read-Host "Choose an activity (1-4)"

if ($activities.ContainsKey($choice)) {
    $activity = $activities[$choice]
    & mvn -f $pom javafx:run "-Djavafx.mainClass=$($activity.main)"
    exit $LASTEXITCODE
}

Write-Error "Invalid choice."
exit 1
