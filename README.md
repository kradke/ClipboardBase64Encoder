# ClipboardBase64Encoder
A handy 2-cent Base64 encoder tool


## USAGE
  java ClipboardBase64Encoder [options]
  Without any option(s), the clipboard is used for input and output.

Options:
  -h            Display this help message.
  -q            Enclose the Base64 output in double quotes ("").
  -c            Print the encoded output to the console instead of copying to the clipboard.
  -o <FILENAME> Write the encoded output to the specified file instead of copying to the clipboard.
  -i <FILENAME> Read input data from the specified file instead of the clipboard.

Example:
  java ClipboardBase64Encoder -q -c
  java ClipboardBase64Encoder -q -o output.txt
  java ClipboardBase64Encoder -i input.txt -o output.txt
