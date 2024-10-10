Contact Manager Web App ☁️
Welcome to the Contact Manager Web App, a cloud-based application designed for managing contacts seamlessly across platforms. This app is built with an emphasis on a clean user experience, featuring both dark and light mode options, and secure login functionalities, including OAuth login using Google.

Features ✨
Cloud-Based: Manage contacts anywhere, anytime.
Dark & Light Mode: Switch between themes for a comfortable user experience.
Login & Authentication:
Standard login functionality
OAuth 2.0 integration for Google sign-in
CRUD Operations: Create, Read, Update, and Delete contacts easily.
Responsive Design: Mobile-first design powered by TailwindCSS.
Tech Stack 🛠️
Java: Core backend logic
Spring Boot & Spring MVC: Web framework for handling requests and views
Thymeleaf: Server-side rendering and templating engine
MySQL: Relational database for storing contact data
TailwindCSS: Utility-first CSS framework for responsive and sleek UI
HTML: Structuring the web interface
Getting Started 🚀
Prerequisites
Java 11+
MySQL
Node.js & npm (for managing TailwindCSS)
Google OAuth credentials
Installation
Clone the repository:

bash
Copy code
git clone https://github.com/your-username/contact-manager-web-app.git
Set up the MySQL database:

sql
Copy code
CREATE DATABASE contact_manager;
Configure the application.properties file with your database and Google OAuth credentials.

Install TailwindCSS:

bash
Copy code
npm install
Build and run the Spring Boot application:

bash
Copy code
./mvnw spring-boot:run
Visit http://localhost:8080 to view the app in your browser.

Screenshots 🌙🌞
Light Mode

Dark Mode

Future Enhancements 🚧
Export contacts as CSV
Integrate more OAuth providers (Facebook, GitHub)
Add notifications and reminders for important contacts
