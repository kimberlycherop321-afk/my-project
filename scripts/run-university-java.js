const fs = require("node:fs");
const path = require("node:path");
const { spawnSync } = require("node:child_process");

const root = path.resolve(__dirname, "..");
const allowedMainClasses = new Set([
  "com.university.UniversitySystemTest",
  "com.university.SeedStudents",
]);
const mainClass = process.argv[2];

if (!allowedMainClasses.has(mainClass)) {
  console.error("Choose a supported UniversitySystem CLI class.");
  process.exitCode = 2;
} else {
  const classesDirectory = path.join(root, "UniversitySystem", "target", "classes");
  const mainClassFile = path.join(
    classesDirectory,
    ...mainClass.split(".").slice(0, -1),
    `${mainClass.split(".").pop()}.class`,
  );
  const driverJar = path.join(
    root,
    "UniversitySystem",
    "lib",
    "postgresql-42.7.3.jar",
  );

  if (!fs.existsSync(mainClassFile) || !fs.existsSync(driverJar)) {
    console.error("Build the UniversitySystem project before running this command.");
    process.exitCode = 1;
  } else {
    const javaExecutable = process.env.JAVA_HOME
      ? path.join(
          process.env.JAVA_HOME,
          "bin",
          process.platform === "win32" ? "java.exe" : "java",
        )
      : "java";
    const classpath = [
      classesDirectory,
      driverJar,
    ].join(path.delimiter);
    const result = spawnSync(
      javaExecutable,
      ["-cp", classpath, mainClass],
      { cwd: root, stdio: "inherit" },
    );

    if (result.error) {
      console.error(`Could not start Java: ${result.error.message}`);
      process.exitCode = 1;
    } else {
      process.exitCode = result.status === null ? 1 : result.status;
    }
  }
}
