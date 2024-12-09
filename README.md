# IVF Success Rate Application

## Overview

The **IVF Success Rate Calculator** is a web application designed to calculate the success rate of In-Vitro Fertilization (IVF) based on user-provided inputs. This application is built with Java, deployed using Apache Tomcat Server, and utilizes JSP and Servlets to deliver a seamless experience.

---

## Prerequisites

Before running the application, ensure the following are installed on your system:

- **Java Development Kit (JDK)** (Java 11 or higher is recommended)
- **Apache Tomcat Server** (Version 9.0.97)

---

## Installation and Setup

### Step 1: Install Java

#### **Option 1: Install Java via Homebrew (Recommended)**

1. **Install Homebrew (if not already installed):**
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

2. **Install OpenJDK:**
   ```bash
   brew install openjdk

3. **Note the installation path** provided after the installation, typically:
   ```bash
   /usr/local/opt/openjdk/libexec/openjdk.jdk/Contents/Home

### Step 2: Configure JAVA_HOME

After installing Java, configure the JAVA_HOME environment variable:

Find the Java installation path using the command:
   ```bash
   /usr/libexec/java_home

This will return a path like:
   ```bash
   /Library/Java/JavaVirtualMachines/jdk-<version>.jdk/Contents/Home
