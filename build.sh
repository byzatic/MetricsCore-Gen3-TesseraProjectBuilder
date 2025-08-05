#!/bin/sh

print_help() {
  echo "Usage: build -f <project-file.yaml> -o <output-directory>"
  echo ""
  echo "Options:"
  echo "  -f, --file         Input project YAML file"
  echo "  -o, --output       Output zip for compiled project"
  echo "  -h, --help         Show this help message"
}

if [ $# -eq 0 ]; then
  print_help
  exit 0
fi

while [ $# -gt 0 ]; do
  case "$1" in
    -f|--file)
      INPUT_FILE="$2"
      shift 2
      ;;
    -o|--output-dir)
      OUTPUT_FILE="$2"
      shift 2
      ;;
    -h|--help)
      print_help
      exit 0
      ;;
    *)
      echo "Unknown option: $1"
      print_help
      exit 1
      ;;
  esac
done

if [ -z "$INPUT_FILE" ] || [ -z "$OUTPUT_FILE" ]; then
  echo "Error: Both input file and output directory must be specified."
  print_help
  exit 1
fi

exec java -DinputFile="/data/$INPUT_FILE" -DoutputFile="/data/$OUTPUT_FILE" -jar /app/app.jar
