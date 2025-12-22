sudo /usr/local/mysql/support-files/mysql.server start
/usr/local/mysql/bin/mysql -u root -p

-- ================================================
-- 1️⃣ COUNTRY TABLE
-- ================================================
CREATE TABLE Country (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================
-- 2️⃣ CLASSROOM TABLE
-- ================================================
CREATE TABLE classroom (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country_id INT NOT NULL,
    class_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'
);

-- ================================================
-- 3️⃣ SUBJECT TABLE
-- ================================================
CREATE TABLE subject (
    id INT AUTO_INCREMENT PRIMARY KEY,
    class_id INT NOT NULL,
    subject_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'
);

-- ================================================
-- 4️⃣ TOPIC TABLE
-- ================================================
CREATE TABLE topic (
    id INT AUTO_INCREMENT PRIMARY KEY,
    class_id INT NOT NULL,
    subject_id INT NOT NULL,
    topic_name VARCHAR(150) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    image_url VARCHAR(255),
    video_url VARCHAR(255)
);

-- ================================================
-- 5️⃣ QUESTION TABLE
-- ================================================
CREATE TABLE question (
    id INT AUTO_INCREMENT PRIMARY KEY,
    class_id INT NOT NULL,
    subject_id INT NOT NULL,
    topic_id INT NOT NULL,
    question TEXT NOT NULL,
    difficulty_level ENUM('EASY', 'MEDIUM', 'HARD') DEFAULT 'MEDIUM',
    correct_index VARCHAR(5),
    explanation TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    image_url VARCHAR(255),
    video_url VARCHAR(255)
);

-- ================================================
-- 6️⃣ OPTIONS TABLE
-- ================================================
CREATE TABLE options (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT NOT NULL,
    option_text VARCHAR(255) NOT NULL,
    option_value VARCHAR(10),
    is_correct BOOLEAN DEFAULT FALSE
);

-- ================================================
-- 7️⃣ STUDENT TABLE
-- ================================================
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    class_id INT,
    role ENUM('ADMIN', 'STUDENT', 'TEACHER') DEFAULT 'STUDENT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL,
    status ENUM('ACTIVE', 'SUSPENDED', 'DELETED') DEFAULT 'ACTIVE'
);

-- ================================================
-- 8️⃣ ATTEMPT TABLE
-- ================================================
CREATE TABLE attempt (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    question_id INT NOT NULL,
    chosen_index VARCHAR(5),
    correct BOOLEAN,
    score DECIMAL(5,2) DEFAULT 0.00,
    answered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================
-- 9 Quiz_Management TABLE
-- ================================================

CREATE TABLE quiz_management (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_name VARCHAR(150) NOT NULL,
    description TEXT,
    class_id INT,
    subject_id INT,
    topic_id INT,
    start_time DATETIME,
    end_time DATETIME,
    duration_minutes INT,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'
);


-- ================================================
-- 10 Quiz_Management_Question TABLE
-- ================================================
CREATE TABLE quiz_management_question (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT NOT NULL,
    question_id INT NOT NULL,
    question_order INT DEFAULT 0
);


-- ================================================
-- 11 Quiz_Management_Result TABLE
-- ================================================
CREATE TABLE quiz_management_result (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    quiz_id INT NOT NULL,
    total_questions INT DEFAULT 0,
    correct_answers INT DEFAULT 0,
    score_percentage DECIMAL(5,2) DEFAULT 0.00,
    completed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- ================================================
-- 12 Quiz_Management TABLE
-- ================================================

CREATE TABLE student_progress (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    class_id INT,
    subject_id INT,
    topic_id INT,
    completed BOOLEAN DEFAULT FALSE,
    last_accessed TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================
-- 13 Quiz_Management TABLE
-- ================================================
CREATE TABLE leaderboard (
    student_id INT PRIMARY KEY,
    points INT DEFAULT 0,
    student_rank INT DEFAULT NULL,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- ================================================
-- 14 Quiz_Management TABLE
-- ================================================
CREATE TABLE notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150),
    message TEXT,
    class_id INT,
    student_id INT,
    read_status BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================
-- 9️⃣ INDEXES (PERFORMANCE)
-- ================================================
CREATE INDEX idx_classroom_country ON classroom(country_id);
CREATE INDEX idx_subject_class ON subject(class_id);
CREATE INDEX idx_topic_subject ON topic(subject_id);
CREATE INDEX idx_question_topic ON question(topic_id);
CREATE INDEX idx_options_question ON options(question_id);
CREATE INDEX idx_student_class ON student(class_id);
CREATE INDEX idx_attempt_student ON attempt(student_id);
CREATE INDEX idx_attempt_question ON attempt(question_id);