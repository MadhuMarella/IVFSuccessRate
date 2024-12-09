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

1. Find the Java installation path using the command:
   ```bash
      /usr/libexec/java_home
   


2. Set JAVA_HOME temporarily (for the current terminal session):

   ```bash
   export JAVA_HOME=$(/usr/libexec/java_home)
   export PATH=$JAVA_HOME/bin:$PATH

3. Verify the setup:

   ```bash
   echo $JAVA_HOME


This should display the Java installation path.


### Step3: Install Apache Tomcat

1.**Download Apache Tomcat:**

Download the apache-tomcat-9.0.97.zip file from the repository and Extract the contents into a folder named apache-tomcat-9.0.97.


---

### Pre-SetUp

1.**Download the IVFCalculator.war file:**

This file contains the prebuilt web application archive.

2.**Deploy the application:**

Copy the IVFCalculator.war file into the apache-tomcat-9.0.97/webapps directory.

---

## Running the Application


1.**Start the Tomcat server:**

Open the terminal, navigate to the Tomcat bin directory:

      cd /path/to/apache-tomcat-9.0.97/bin


Start the server:

      ./catalina.sh start



2.**Access the application:**

Open a web browser and go to:

   http://localhost:8080/IVFCalculator/index.jsp


3.**Use the application:**

Enter the required inputs on the displayed HTML page. 

Click **Submit** to view the success rate and additional options. 

You can click **Try Again** to return to the input page and test new values.
