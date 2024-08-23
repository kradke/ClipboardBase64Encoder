# ClipboardBase64Encoder
A simple, handy 2-Cent Base64 encoder tool

## Building

<pre>javac ClipboardBase64Encoder.java</pre>

## JAR Packaging
<pre>jar cfm ClipboardBase64Encoder.jar manifest.txt ClipboardBase64Encoder.class</pre>

## Executing
<pre>
java ClipboardBase64Encoder
java -jar ClipboardBase64Encoder.jar
</pre>


## USAGE
  <pre>java ClipboardBase64Encoder [options]</pre>
  **Without any option(s), the clipboard is used for both input and output.**

Options:\
-h            Display this help message.\
-q            Enclose the Base64 output in double quotes ("").\
-c            Print the encoded output to the console instead of copying to the clipboard.\
-o <FILENAME> Write the encoded output to the specified file instead of copying to the clipboard.\
-i <FILENAME> Read input data from the specified file instead of the clipboard.\

Example:\
<pre>
  java ClipboardBase64Encoder -q -c\
  java ClipboardBase64Encoder -q -o output.txt\
  java ClipboardBase64Encoder -i input.txt -o output.txt\
</pre>
