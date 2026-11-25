$root = Split-Path -Parent $PSScriptRoot
$activities = @{
    "1" = @{ folder = "activity1-jlist-renderer"; main = "ContactListDemo" }
    "2" = @{ folder = "activity2-jtable"; main = "StudentTableDemo" }
    "3" = @{ folder = "activity3-jtree-tabs"; main = "FileExplorerDemo" }
    "4" = @{ folder = "activity4-menus-dialogs"; main = "MiniEditor" }
    "5" = @{ folder = "activity5-capstone-file-manager"; main = "FileManager" }
}

Write-Host "Week 5: Advanced Swing Components"
Write-Host "1. JList and Custom Renderer"
Write-Host "2. JTable and Table Model"
Write-Host "3. JTree and JTabbedPane"
Write-Host "4. Menus, Toolbar, and Dialogs"
Write-Host "5. Capstone File Manager"
$choice = Read-Host "Choose an activity (1-5)"

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
