DROP TABLE IF EXISTS Task;
CREATE TABLE Task (
  id INT AUTO_INCREMENT PRIMARY KEY,
  task_name VARCHAR(255) NOT NULL,
  task_description VARCHAR(255),
  task_number INT UNSIGNED UNIQUE NOT NULL,
  task_status boolean default FALSE NOT NULL
);
