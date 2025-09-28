# JSON to XML Converter

This project is a **Java-based utility** that converts JSON data into a structured XML format. It is useful when you need to transform JSON documents into XML for integration with systems that primarily support XML.

## Key Features

* Converts JSON **objects** and **arrays** into XML elements.
* Preserves nested structures, including objects inside arrays and arrays inside objects.
* Supports primitive values: **String, Number, Boolean, and Null**.
* Represents JSON keys as XML attributes (`name="key"`).
* Uses **Jackson ObjectMapper** for parsing JSON reliably.

## Example Conversion

**Input (JSON):**

```json
{
  "id": 101,
  "name": "Alice",
  "languages": ["Java", "Python"],
  "active": true
}
```

**Output (XML):**

```xml
<object>
  <number name="id">101</number>
  <string name="name">Alice</string>
  <array name="languages">
    <string>Java</string>
    <string>Python</string>
  </array>
  <boolean name="active">true</boolean>
</object>
```

## How It Works

1. JSON files are read as strings using `Files.readString()`.
2. The data is deserialized into **Map** or **List** using Jackson’s `ObjectMapper`.
3. A recursive method converts objects and arrays into XML.
4. Primitive values are wrapped in their respective XML tags (`<string>`, `<number>`, etc.).
5. The generated XML is saved into the output file.

## Usage

1. Clone the repository.
2. Place your JSON input file in the project directory.
3. Call the `convertJSONtoXML(File json, File xml)` method, passing the input JSON file and desired XML output file.

## Tech Stack

* **Java** (core logic)
* **Jackson Databind** (for JSON parsing)

## Future Scope

* Add **XML-to-JSON** conversion.
* Support customizable root tags.
* Provide **CLI commands** for easier usage.

This lightweight utility is designed for developers and students who want a quick and simple JSON to XML conversion tool without relying on heavy frameworks.
