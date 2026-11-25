$root = Split-Path -Parent $PSScriptRoot
$pom = Join-Path $root "week06-javafx\pom.xml"
$activities = @{
    "1" = @{ main = "programmaticdemo.Main"; name = "First JavaFX Window" }
    "2" = @{ main = "programmaticdemo.LayoutDemo"; name = "JavaFX Layouts" }
    "3" = @{ main = "programmaticdemo.FXMLApp"; name = "FXML Login" }
    "4" = @{ main = "programmaticdemo.DashboardApp"; name = "Dashboard" }
}

Write-Host "Week 6: Introduction to Modern JavaFX"
Write-Host "1. First JavaFX Window"
Write-Host "2. JavaFX Layouts"
Write-Host "3. FXML Login"
Write-Host "4. Dashboard"
$choice = Read-Host "Choose an activity (1-4)"

if ($activities.ContainsKey($choice)) {
    $activity = $activities[$choice]
    & mvn -f $pom javafx:run "-Djavafx.mainClass=$($activity.main)"
    exit $LASTEXITCODE
}

Write-Error "Invalid choice."
exit 1
