# OpenAI Database Generation

A Java application that leverages the OpenAI API to automatically generate realistic and structured database content — such as seed data, mock records, or SQL inserts — using AI-driven prompts.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Built With](#built-with)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

**OpenAI Database Generation** automates the tedious process of creating test or seed data for databases. By sending structured prompts to the OpenAI API, the application generates contextually relevant records that can be used for development, testing, or demonstration purposes.

---

## Features

- 🤖 AI-powered data generation via the OpenAI API
- 🗄️ Produces structured output suitable for database insertion
- ☕ Written entirely in Java for cross-platform compatibility
- 🔧 Maven-based build for easy dependency management

---

## Prerequisites

Before running this project, make sure you have the following installed:

- **Java 11+** (JDK)
- **Maven 3.6+**
- An **OpenAI API Key** — get one at [platform.openai.com](https://platform.openai.com/)

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/RaymondYnoa/OpenAI_Database_Generation.git
cd OpenAI_Database_Generation
```

### 2. Install dependencies

```bash
mvn install
```

### 3. Set your OpenAI API Key

Set the key as an environment variable:

```bash
# macOS/Linux
export OPENAI_API_KEY=your_api_key_here

# Windows (Command Prompt)
set OPENAI_API_KEY=your_api_key_here
```

### 4. Build and run

```bash
mvn compile exec:java
```

---

## Configuration

| Variable         | Description                        | Required |
|------------------|------------------------------------|----------|
| `OPENAI_API_KEY` | Your OpenAI API key                | ✅ Yes   |

Additional configuration options (e.g., model selection, output format, number of records) may be found within the source files under `src/`.

---

## Usage

Once running, the application sends a prompt to OpenAI and returns generated data. You can customize the prompts and output format to match your specific database schema.

Example output (JSON):

```json
[
  { "id": 1, "name": "Alice Johnson", "email": "alice@example.com", "role": "admin" },
  { "id": 2, "name": "Bob Martinez", "email": "bob@example.com", "role": "user" }
]
```

---

## Project Structure

```
OpenAI_Database_Generation/
├── .idea/              # IntelliJ IDEA project settings
├── src/
│   └── main/
│       └── java/       # Java source files
├── .gitignore
├── pom.xml             # Maven build configuration
└── README.md
```

---

## Built With

- [Java](https://www.java.com/) — Core programming language
- [Maven](https://maven.apache.org/) — Build and dependency management
- [OpenAI API](https://platform.openai.com/docs/) — AI-powered content generation

---

## Contributing

Contributions are welcome! To get started:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add your feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a Pull Request

---

## License

This project is open source. See the repository for license details.
