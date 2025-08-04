# Learning About Auto Mobile - Spring Boot Application

## 📋 Project Overview

This is a **Spring Boot web application** designed to help users learn about automobiles through educational videos. The application features user authentication, video management, visit tracking, and a gamification system with user badges.

## 🏗️ Architecture

### Technology Stack
- **Backend**: Spring Boot 3.5.4 with Java 21
- **Database**: H2 In-Memory Database
- **Frontend**: Thymeleaf templates with CSS
- **Build Tool**: Maven
- **ORM**: Spring Data JPA with Hibernate

### Project Structure
```
learningAboutAutoMobile/
├── src/main/java/com/learningAboutAutoMobile/learningAboutAutoMobile/
│   ├── controller/          # REST controllers
│   │   ├── AdminController.java
│   │   ├── UserController.java
│   │   └── VideoController.java
│   ├── model/              # JPA entities
│   │   ├── User.java
│   │   ├── Video.java
│   │   └── Visit.java
│   ├── repository/         # Data access layer
│   │   ├── UserRepository.java
│   │   ├── VideoRepository.java
│   │   └── VisitRepository.java
│   ├── service/           # Business logic
│   │   └── BadgeService.java
│   └── LearningAboutAutoMobileApplication.java
├── src/main/resources/
│   ├── templates/         # Thymeleaf HTML templates
│   ├── application.properties
│   └── data.sql          # Initial data
└── pom.xml
```

## 🎯 Features

### 1. User Management
- **User Registration**: Users can create accounts with username, email, and password
- **User Authentication**: Login/logout functionality with session management
- **Admin Role**: Special admin users with additional privileges

### 2. Video Management
- **Video Display**: Browse and watch educational automotive videos
- **Video Details**: Individual video pages with embedded YouTube videos
- **Admin Video Management**: Admins can add new videos with title, description, and YouTube ID

### 3. Learning Tracking
- **Visit Tracking**: Automatically records when users watch videos
- **User Activity**: Tracks learning progress and engagement
- **Gamification**: Top users are displayed with badges based on video visits

### 4. User Interface
- **Responsive Design**: Clean, modern UI with CSS styling
- **Navigation**: Easy navigation between different sections
- **Flash Messages**: User feedback for actions (success/error messages)

## 🗄️ Database Design

### Entity Relationships

#### User Entity
```java
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(name = "is_admin")
    private boolean isAdmin = false;
}
```

#### Video Entity
```java
@Entity
@Table(name = "videos")
public class Video {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "youtube_id", nullable = false)
    private String youtubeId;
}
```

#### Visit Entity (Junction Table)
```java
@Entity
@Table(name = "visits")
public class Visit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;
}
```

### Database Schema
- **users**: Stores user information and authentication data
- **videos**: Stores video metadata and YouTube IDs
- **visits**: Tracks user video interactions (many-to-many relationship)

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6 or higher

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd learningAboutAutoMobile
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Main application: http://localhost:8080
   - H2 Database Console: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:learningdb`
     - Username: `sa`
     - Password: (empty)

### Default Data
The application comes with pre-loaded data:
- **Admin User**: username: `admin`, password: `admin123`
- **Sample Videos**: 3 automotive educational videos

## 🎮 How to Use

### For Regular Users
1. **Register**: Create a new account at `/register`
2. **Login**: Sign in with your credentials at `/login`
3. **Browse Videos**: View available videos on the home page
4. **Watch Videos**: Click on videos to watch and track your progress
5. **View Progress**: See your learning activity and badges

### For Admin Users
1. **Login**: Use admin credentials (admin/admin123)
2. **Access Admin Panel**: Navigate to `/admin/add-video`
3. **Add Videos**: Create new educational content with YouTube IDs
4. **Manage Content**: Monitor user activity and engagement

## 🔧 Configuration

### Application Properties
```properties
# Database Configuration
spring.datasource.url=jdbc:h2:mem:learningdb
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Data Initialization
spring.sql.init.mode=always
```

## 🏆 Gamification System

### Badge System
The application implements a simple gamification system:
- **Top Users**: Displays the top 3 most active users
- **Visit Tracking**: Records each video visit for user engagement
- **Leaderboard**: Shows user rankings based on video visits

### BadgeService Implementation
```java
@Service
public class BadgeService {
    public List<User> getMostVisitedUsers() {
        // Returns top 3 users by visit count
        List<Object[]> results = visitRepository.findTopUsersByVisitCount();
        // Process and return top users
    }
}
```

## 🔐 Security Features

### Session Management
- Uses `HttpSession` for user authentication
- Session invalidation on logout
- Access control for admin features

### Password Security
**Note**: This is a demo application. In production:
- Implement proper password hashing (BCrypt)
- Add input validation and sanitization
- Implement CSRF protection
- Use HTTPS

## 📱 Frontend Design

### Template Structure
- **Thymeleaf**: Server-side templating engine
- **Responsive CSS**: Mobile-friendly design
- **Flash Messages**: User feedback system
- **Navigation**: Clean, intuitive navigation

### Key Templates
- `home.html`: Main dashboard with featured videos and top users
- `login.html` & `register.html`: Authentication forms
- `video-list.html`: Browse all available videos
- `video-detail.html`: Individual video viewing page
- `admin-add-video.html`: Admin video management

## 🧪 Testing

### Running Tests
```bash
mvn test
```

### Test Structure
- Unit tests for controllers and services
- Integration tests for repositories
- End-to-end testing for user workflows

## 📊 Monitoring & Analytics

### Visit Tracking
- Automatic recording of video visits
- User engagement metrics
- Learning progress tracking

### Database Queries
```sql
-- Top users by visit count
SELECT v.user, COUNT(v) FROM Visit v 
GROUP BY v.user 
ORDER BY COUNT(v) DESC

-- User visit count
SELECT COUNT(v) FROM Visit v WHERE v.user = :user
```

## 🔄 API Endpoints

### User Management
- `GET /register` - Show registration form
- `POST /register` - Create new user
- `GET /login` - Show login form
- `POST /login` - Authenticate user
- `GET /logout` - Logout user

### Video Management
- `GET /videos` - List all videos
- `GET /videos/{id}` - Show specific video
- `GET /admin/add-video` - Admin video form
- `POST /admin/add-video` - Add new video

## 🚀 Deployment

### Production Considerations
1. **Database**: Replace H2 with PostgreSQL/MySQL
2. **Security**: Implement proper authentication (Spring Security)
3. **Password Hashing**: Use BCrypt or similar
4. **HTTPS**: Enable SSL/TLS
5. **Logging**: Add proper logging configuration
6. **Monitoring**: Implement health checks and metrics

### Docker Deployment
```dockerfile
FROM openjdk:21-jdk-slim
COPY target/learningAboutAutoMobile-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🤝 Contributing

### Development Setup
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

### Code Style
- Follow Java naming conventions
- Use meaningful variable names
- Add comments for complex logic
- Maintain consistent formatting

## 📝 License

This project is for educational purposes. Feel free to use and modify as needed.

## 🆘 Troubleshooting

### Common Issues

1. **Port 8080 already in use**
   ```bash
   # Change port in application.properties
   server.port=8081
   ```

2. **Database connection issues**
   - Check H2 console at http://localhost:8080/h2-console
   - Verify JDBC URL and credentials

3. **Build failures**
   ```bash
   mvn clean install -U
   ```

## 📚 Learning Resources

### Spring Boot Concepts Used
- **Spring MVC**: Web application framework
- **Spring Data JPA**: Database access layer
- **Thymeleaf**: Template engine
- **Hibernate**: ORM framework
- **Maven**: Build and dependency management

### Key Learning Points
1. **MVC Pattern**: Separation of concerns
2. **Repository Pattern**: Data access abstraction
3. **Session Management**: User state handling
4. **Template Engine**: Server-side rendering
5. **Database Relationships**: JPA entity mapping

---

**Happy Learning! 🚗💨** 