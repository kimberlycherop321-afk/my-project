$root = Split-Path -Parent $PSScriptRoot
$activities = @{
    "1" = @{ folder = "activity1-actionlistener-basics"; main = "FirstButton" }
    "2" = @{ folder = "activity2-listener-techniques"; main = "ListenerDemo" }
    "3" = @{ folder = "activity2-listener-techniques"; main = "EventSourceDemo" }
    "4" = @{ folder = "activity3-item-and-document-listeners"; main = "ItemListenerDemo" }
    "5" = @{ folder = "activity3-item-and-document-listeners"; main = "DocumentListenerDemo" }
    "6" = @{ folder = "activity3-item-and-document-listeners"; main = "LiveSearchDemo" }
    "7" = @{ folder = "activity4-counter-app"; main = "CounterApp" }
    "8" = @{ folder = "activity5-interactive-form"; main = "RegistrationForm" }
    "9" = @{ folder = "activity6-mouse-and-key"; main = "DrawingApp" }
    "10" = @{ folder = "activity6-mouse-and-key"; main = "KeyboardMover" }
}

Write-Host "Week 4: Event Handling in Swing"
Write-Host "1. First Interactive Button"
Write-Host "2. Listener Styles"
Write-Host "3. Event Sources"
Write-Host "4. ItemListener"
Write-Host "5. DocumentListener Character Counter"
Write-Host "6. Live Search"
Write-Host "7. Counter App with Shortcuts"
Write-Host "8. Interactive Registration Form"
Write-Host "9. Mouse Drawing App"
Write-Host "10. Keyboard Mover"
$choice = Read-Host "Choose an activity (1-10)"

if ($activities.ContainsKey($choice)) {
    $activity = $activities[$choice]
    $folder = Join-Path $root $activity.folder
    $sourceFiles = Get-ChildItem -LiteralPath $folder -Filter "*.java" |
        Select-Object -ExpandProperty FullName
    if (-not $sourceFiles) {
        Write-Error "No Java source files found in $folder"
        exit 1
    }

    & javac -Xlint:all -Werror $sourceFiles
    if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
    & java -cp $folder $activity.main
} else {
    Write-Error "Invalid choice."
    exit 1
}
