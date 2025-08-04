-- Insert default admin user
INSERT INTO users (username, email, password, is_admin) 
VALUES ('admin', 'admin@automobile.com', 'admin123', true);

-- Insert sample videos
INSERT INTO videos (title, description, youtube_id) 
VALUES 
('How Car Engines Work', 'Learn the basics of how internal combustion engines work and the different types of engines used in modern vehicles.', 'dQw4w9WgXcQ'),
('Electric Car Technology Explained', 'Discover how electric vehicles work, their components, and the future of automotive technology.', 'dQw4w9WgXcQ'),
('Car Maintenance Basics', 'Essential car maintenance tips every driver should know, from oil changes to tire care.', 'dQw4w9WgXcQ'); 