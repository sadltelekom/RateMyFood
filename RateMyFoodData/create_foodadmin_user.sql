CREATE USER IF NOT EXISTS foodadmin@localhost IDENTIFIED BY 'FeetMakeUsNotHorny';
GRANT ALL PRIVILEGES ON ratemyfood.* TO foodadmin@localhost;
FLUSH PRIVILEGES;