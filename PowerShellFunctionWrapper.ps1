function Encode-Clipboard {
    param (
        [switch]$q,
        [switch]$c,
        [string]$i,
        [string]$o,
        [switch]$h
    )

    # Define the path to your JAR file, ensuring it is properly quoted
    $jarPath = "C:\Users\myaccount\OneDrive - My Organization\myapps\ClipboardBase64Encoder.jar"  # Replace with your actual JAR path
    $jarPath = "`"$jarPath`""  # Surround the JAR path with double quotes

    # Start building the command
    $command = "java -jar $jarPath"

    # Append switches based on the parameters
    if ($q) {
        $command += " -q"
    }

    if ($c) {
        $command += " -c"
    }

    if ($i) {
        $command += " -i `"$i`""  # Surround the input filename with quotes for safety
    }

    if ($o) {
        $command += " -o `"$o`""  # Surround the output filename with quotes for safety
    }

    if ($h) {
        $command += " -h"
    }

    # Execute the command
    Invoke-Expression $command
}