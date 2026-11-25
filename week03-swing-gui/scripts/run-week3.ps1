$root = Split-Path -Parent $PSScriptRoot
$activities = @{
    "1" = @{ folder = "activity1-first-window"; main = "FirstWindow"; name = "First Swing Window" }
    "2" = @{ folder = "activity2-basic-components"; main = "ComponentShowcase"; name = "Basic Components Tour" }
    "3" = @{ folder = "activity3-layout-managers"; main = "FlowLayoutDemo"; name = "FlowLayout Demo" }
    "4" = @{ folder = "activity3-layout-managers"; main = "BorderLayoutDemo"; name = "BorderLayout Demo" }
    "5" = @{ folder = "activity3-layout-managers"; main = "GridLayoutDemo"; name = "GridLayout Demo" }
    "6" = @{ folder = "activity3-layout-managers"; main = "CombinedLayoutDemo"; name = "Combined Layout Demo" }
    "7" = @{ folder = "activity4-edt-rule"; main = "FreezeDemo"; name = "Freeze the EDT (intentional)" }
    "8" = @{ folder = "activity4-edt-rule"; main = "FixedDemo"; name = "Keep the EDT Responsive" }
    "9" = @{ folder = "activity5-complete-mockup"; main = "StudentPortal"; name = "Student Portal Mockup" }
}

Write-Host "Week 3: Swing GUIs & Layouts"
Write-Host "1. First Swing Window"
Write-Host "2. Basic Components Tour"
Write-Host "3. FlowLayout Demo"
Write-Host "4. BorderLayout Demo"
Write-Host "5. GridLayout Demo"
Write-Host "6. Combined Layout Demo"
Write-Host "7. Freeze the EDT (intentional)"
Write-Host "8. Keep the EDT Responsive"
Write-Host "9. Student Portal Mockup"
$choice = Read-Host "Choose an activity (1-9)"

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
