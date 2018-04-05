CREATE USER IF NOT EXISTS 'demo_user'@'localhost'
  IDENTIFIED BY 'demo_pass'
  PASSWORD EXPIRE NEVER;

GRANT ALL ON demo_db.* TO 'demo_user'@'localhost';

GRANT ALL ON test_demo_db.* TO 'demo_user'@'localhost';
